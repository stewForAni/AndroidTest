package com.life.jv;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by stew on 2023/5/13.
 * mail: stewforani@gmail.com
 */
public class ActivityLifeClassVisitor extends ClassVisitor {

    private String cName;
    private String sName;

    public ActivityLifeClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        cName = name;
        sName = superName;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if (sName.equals("androidx/appcompat/app/AppCompatActivity")) {
            if (name.startsWith("onCreate")) {
                //处理onCreate()方法
                return new ActivityLifeMethodVisitor(mv, cName, name);
            }
        }
        return mv;
    }
}