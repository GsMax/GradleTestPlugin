package io.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Dependency;
import org.gradle.api.tasks.testing.Test;
import org.gradle.api.tasks.testing.testng.TestNGOptions;

import java.util.stream.Collectors;

public class MyTestPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        ((Test)project.getTasksByName("test", true).toArray()[0]).useTestNG();
        ((TestNGOptions)((Test)project.getTasksByName("test", true)
                .toArray()[0]).getOptions()).getListeners().add("io.test_listener.MyTestListener");

        project.getDependencies().add("testRuntime", "org.example:Listener:1.0-SNAPSHOT");
//        project.getConfigurations().getByName("testRuntime").getDependencies().stream()
//                .filter(i -> ((Dependency)i).getName().equals("Listener")).collect(Collectors.toList());
    }
}
