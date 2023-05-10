import com.android.build.api.transform.*
import com.stew.plugin.ActivityLifeClassVisitor
import com.android.build.gradle.internal.pipeline.TransformManager

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

import org.apache.commons.io.FileUtils


public class ActivityLifeTransform extends Transform {
    @Override
    String getName() {
        return "ActivityLifeTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<QualifiedContent.Scope> getScopes() {
        return TransformManager.PROJECT_ONLY
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        //拿到所有的class文件
        Collection<TransformInput> transformInputs = transformInvocation.inputs
        TransformOutputProvider outputProvider = transformInvocation.outputProvider
        if (outputProvider != null) {
            outputProvider.deleteAll()
        }

        transformInputs.each {
            it.directoryInputs.each {
                if (it.file.isDirectory()) {
                    it.file.eachFileRecurse {
                        if(it.name.endsWith(".class")){
                            //对class文件进行读取与解析
                            ClassReader classReader = new ClassReader(it.bytes)
                            //对class文件的写入
                            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                            //访问class文件相应的内容，解析到某一个结构就会通知到ClassVisitor的相应方法
                            ClassVisitor classVisitor = new ActivityLifeClassVisitor(classWriter)
                            //依次调用 ClassVisitor接口的各个方法
                            classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES)
                            //toByteArray方法会将最终修改的字节码以 byte 数组形式返回。
                            byte[] bytes = classWriter.toByteArray()

                            //通过文件流写入方式覆盖掉原先的内容，实现class文件的改写。
                            //FileOutputStream outputStream = new FileOutputStream( file.parentFile.absolutePath + File.separator + fileName)
                            FileOutputStream outputStream = new FileOutputStream(it.path)
                            outputStream.write(bytes)
                            outputStream.close()
                        }
                    }
                }

                //处理完输入文件后把输出传给下一个文件
                def dest = outputProvider.getContentLocation(it.name, it.contentTypes, it.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(it.file, dest)
            }
        }
    }
}