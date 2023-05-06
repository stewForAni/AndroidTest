package asm.life.plugin
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class AsmLifePlugin implements Plugin<Project>{
    void apply(Project project) {
        System.out.println("@------AsmLifePlugin-------@")
    }
}