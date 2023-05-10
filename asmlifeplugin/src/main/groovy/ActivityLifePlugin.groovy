import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

public class ActivityLifePlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
        System.in.println("------ActivityLifePlugin start-----")
        AppExtension android = project.extensions.getByType(AppExtension)
        ActivityLifeTransform transform = new ActivityLifeTransform()
        android.registerTransform(transform)
        System.in.println("------ActivityLifePlugin register-----")
    }
}