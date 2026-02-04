package jrw.core;

import jrw.module.JrwModule;
import jrw.output.OutputTarget;

/**
 * Engine executes an ExecutionPlan.
 *
 * It orchestrates:
 * - reading input
 * - processing text through modules
 * - writing output
 */
public class Engine {
    public void execute(ExecutionPlan plan){
        // Read the string:
        String text = plan.getInputSource().read();

        // Process through each module
        for(JrwModule module: plan.getModules()){
            text = module.process(text);
        }

        for(OutputTarget output: plan.getOutputTargets()){
            output.write(text);
        }

    }     
}
