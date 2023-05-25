package com.life.gy

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension

import org.gradle.api.Plugin
import org.gradle.api.Project

public class LifePlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        System.out.println("--- ActivityLifePlugin start ---")
        def extension
        if(project.getPlugins().hasPlugin("com.android.application")){
            extension = project.extensions.getByType(AppExtension)
        }else {
            extension = project.extensions.getByType(LibraryExtension)
        }
        LifeTransform transform = new LifeTransform()
        extension.registerTransform(transform)
        System.out.println("--- ActivityLifePlugin register ---")
    }

}