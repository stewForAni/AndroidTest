package com.stew.jv;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.LocalVariablesSorter;

import proguard.classfile.attribute.preverification.ObjectType;

/**
 * Created by stew on 2023/5/13.
 * mail: stewforani@gmail.com
 */
public class ActivityLifeMethodVisitor extends LocalVariablesSorter {

    private final String cName;
    private final String mName;

    private int startTime;
    private int endTime;
    private int costTime;

    public ActivityLifeMethodVisitor(MethodVisitor mv, int access, String descriptor, String className, String name) {
        super(ASM7, access, descriptor, mv);
        cName = className;
        mName = name;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        //long startTime = System.currentTimeMillis();
        startTime = newLocal(Type.LONG_TYPE);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        mv.visitVarInsn(LSTORE, startTime);

//        mv.visitLdcInsn("TAG");
//        mv.visitVarInsn(LLOAD, startTime);
//        mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(J)Ljava/lang/String;", false);
//        mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
//        mv.visitInsn(POP);
    }

    @Override
    public void visitInsn(int opcode) {
        //方法执行后插入
        if (opcode == Opcodes.RETURN) {

            //long endTime = System.currentTimeMillis();
            endTime = newLocal(Type.LONG_TYPE);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitVarInsn(LSTORE, endTime);

            //long costTime = endTime - startTime;
            mv.visitVarInsn(LLOAD, endTime);
            mv.visitVarInsn(LLOAD, startTime);
            mv.visitInsn(LSUB);
            costTime = newLocal(Type.LONG_TYPE);
            mv.visitVarInsn(LSTORE, costTime);

            mv.visitLdcInsn("MT");
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitLdcInsn(cName + "/" + mName + "/");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitVarInsn(LLOAD, costTime);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
            mv.visitLdcInsn("ms");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);


            mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
            mv.visitInsn(POP);

            mv.visitEnd();
        }
        super.visitInsn(opcode);

    }
}
