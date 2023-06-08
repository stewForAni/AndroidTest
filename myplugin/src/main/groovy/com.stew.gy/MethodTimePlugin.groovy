package com.stew.gy
import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension

import org.gradle.api.Plugin
import org.gradle.api.Project

class MethodTimePlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        System.out.println("--- MethodTimePlugin start ---")
        def extension
        if(project.getPlugins().hasPlugin("com.android.application")){
            extension = project.extensions.getByType(AppExtension)
        }else {
            extension = project.extensions.getByType(LibraryExtension)
        }
        MethodTimeTransform transform = new MethodTimeTransform()
        extension.registerTransform(transform)
        System.out.println("--- MethodTimePlugin register ---")
    }

}