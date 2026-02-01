package jrw.module.builtin;

import jrw.module.JrwModule;

public class PassThroughModule implements JrwModule {

    @Override
    public String process(String input){
        return input;
    }
    
}
