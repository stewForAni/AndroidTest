package com.life.gy

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

public class LifePlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        System.out.println("--- ActivityLifePlugin start ---")
        AppExtension android = project.extensions.getByType(AppExtension)
        LifeTransform transform = new LifeTransform()
        android.registerTransform(transform)
        System.out.println("--- ActivityLifePlugin register ---")
    }

}