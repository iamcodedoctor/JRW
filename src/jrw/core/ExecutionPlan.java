package jrw.core;

import java.util.List;

import jrw.input.InputSource;
import jrw.module.JrwModule;
import jrw.output.OutputTarget;

/**
 * ExecutionPlan describes a single execution of jrw.
 *
 * It specifies:
 * - where input comes from
 * - which modules process the text
 * - where output is written
 *
 * This class contains no logic.
 */
public class ExecutionPlan {
    private final InputSource inputsource;
    private final List<JrwModule> modules;
    private final List<OutputTarget> outputTargets;

    public ExecutionPlan(
        InputSource inputSource,
        List<JrwModule> modules,
        List<OutputTarget> outputTargets
    ){
        this.inputsource = inputSource;
        this.modules = modules;
        this.outputTargets = outputTargets;
    }

    public InputSource getInputSource(){
        return inputsource;
    }

    public List<JrwModule> getModules(){
        return modules;
    }

    public List<OutputTarget> getOutputTargets(){
        return outputTargets;
    }

}
