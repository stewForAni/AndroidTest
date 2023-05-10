package com.stew.plugin;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by stew on 2023/5/10.
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
        this.cName = name;
        this.sName = superName;
        System.out.println(cName + " --- " + sName);
    }

    //androidx/appcompat/app/AppCompatActivity
    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

}
