import com.android.build.api.transform.Context
import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import groovy.io.FileType
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.apache.commons.io.FileUtils
import com.stew.asmlife.AsmLifeClassVisitor

public class AsmLifeTrans extends Transform {

    @Override
    String getName() {
        return "AsmLifeTrans"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.PROJECT_ONLY
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {


        Collection<TransformInput> inputs = transformInvocation.inputs

        TransformOutputProvider outputProvider = transformInvocation.outputProvider
        //删除之前的输出
        if (outputProvider != null){
            outputProvider.deleteAll()
        }

        // 遍历directoryInputs(文件夹中的class文件) directoryInputs代表着以源码方式参与项目编译的所有目录结构及其目录下的源码文件
        // 比如我们手写的类以及R.class、BuildConfig.class以及MainActivity.class等
        inputs.each {
            it.directoryInputs.each {

                if(it.file.isDirectory()){
                    it.file.eachFileRecurse {
                        if(it.name.endsWith(".class")){
                            System.out.println("find class: " + it.name)
                            ClassReader classReader = new ClassReader(it.bytes)
                            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)

                            //访问class文件相应的内容，解析到某一个结构就会通知到ClassVisitor的相应方法
                            ClassVisitor classVisitor = new AsmLifeClassVisitor(classWriter)
                            //依次调用 ClassVisitor接口的各个方法
                            classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES)

                            byte[] bytes = classWriter.toByteArray()
                            FileOutputStream outputStream = new FileOutputStream(it.getPath())
                            outputStream.write(bytes)
                            outputStream.close()
                        }
                    }
                }

                def dest = outputProvider.getContentLocation(it.name,
                        it.contentTypes, it.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(it.file, dest)

            }
        }

    }
}