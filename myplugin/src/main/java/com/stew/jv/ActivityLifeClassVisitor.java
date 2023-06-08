package com.stew.jv;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by stew on 2023/5/13.
 * mail: stewforani@gmail.com
 */
public class ActivityLifeClassVisitor extends ClassVisitor {

    private String cName;

    public ActivityLifeClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM7, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        cName = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        return new ActivityLifeMethodVisitor(mv, access, descriptor, cName, name);
    }

}