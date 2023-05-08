import org.gradle.api.Plugin;
import org.gradle.api.Project;
import com.android.build.gradle.AppExtension


public class AsmLifePlugin implements Plugin<Project>{
    void apply(Project project) {
        System.out.println("###------AsmLifePlugin start-------###")
        def android = project.extensions.getByType(AppExtension)
        AsmLifeTrans trans = new AsmLifeTrans()
        android.registerTransform(trans)
        System.out.println("###------AsmLifeTrans register-------###")
    }
}