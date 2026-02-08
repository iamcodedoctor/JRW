package jrw.cli;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import jrw.core.Engine;
import jrw.core.ExecutionPlan;
import jrw.input.FileInputSource;
import jrw.input.InputSource;
import jrw.input.StdInInputSource;
import jrw.input.StringInputSource;
import jrw.module.JrwModule;
import jrw.module.builtin.PassThroughModule;
import jrw.output.ConsoleOutput;
import jrw.output.FileAppendOutput;
import jrw.output.FileOverwriteOutput;
import jrw.output.OutputTarget;

/**
 * CliRunner wires CLI parsing to execution.
 */
public class CliRunner {

    public void run(String[] args) {

        CliParser parser = new CliParser();
        CliConfig config = parser.parse(args);

        // 1. Handle help
        if (config.isHelpRequested()) {
            new HelpPrinter().print();
            return;
        }

        // 2. Build InputSource
        InputSource inputSource;
        switch (config.getInputType()) {
            case STRING:
                inputSource = new StringInputSource(config.getInputValue());
                break;
            case FILE:
                inputSource = new FileInputSource(Path.of(config.getInputValue()));
                break;
            case STDIN:
            default:
                inputSource = new StdInInputSource();
                break;
        }

        // 3. Modules (for now, just pass-through)
        List<JrwModule> modules = new ArrayList<>();
        modules.add(new PassThroughModule());

        // 4. Build OutputTargets
        List<OutputTarget> outputs = new ArrayList<>();

        if (config.isPrintToConsole()) {
            outputs.add(new ConsoleOutput());
        }

        if (config.getOutputFile() != null) {
            outputs.add(new FileOverwriteOutput(Path.of(config.getOutputFile())));
        }

        if (config.getAppendFile() != null) {
            outputs.add(new FileAppendOutput(Path.of(config.getAppendFile())));
        }

        // 5. Execute
        ExecutionPlan plan =
                new ExecutionPlan(inputSource, modules, outputs);

        Engine engine = new Engine();
        engine.execute(plan);
    }
}