package com.life.gy

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import com.life.jv.ActivityLifeClassVisitor
import org.apache.commons.io.FileUtils
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

public class LifeTransform extends Transform {
    @Override
    String getName() {
        return "LifeTransform"
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

            //这里对jar的处理，是因为gradle 3.6.0以上R类不会转为.class文件而会转成jar，因此在Transform实现中需要单独拷贝
            it.jarInputs.each {
                File file = it.file
                System.out.println("find jar input: " + file.name)
                def dest = outputProvider.getContentLocation(it.name, it.contentTypes, it.scopes, Format.JAR)
                FileUtils.copyFile(file, dest)
            }

            it.directoryInputs.each {

                File dir = it.file
                if (dir.isDirectory()) {
                    dir.eachFileRecurse {
                        if(it.name.endsWith(".class")){
                            System.in.println(it.name)
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

                def dest = outputProvider.getContentLocation(it.name, it.contentTypes, it.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(it.file, dest)

            }
        }
    }
}