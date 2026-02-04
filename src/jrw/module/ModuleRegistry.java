package jrw.module;

import java.util.HashMap;
import java.util.Map;

import jrw.module.builtin.PassThroughModule;

public class ModuleRegistry {
    private final Map<String, JrwModule> modules = new HashMap<>();
    
    public ModuleRegistry(){
        register("pass", new PassThroughModule());
    }

    public void register(String name, JrwModule module){
        modules.put(name, module);
    }

    public JrwModule get(String name){
        return modules.get(name);
    }
}
