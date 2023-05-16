package com.life.jv;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by stew on 2023/5/13.
 * mail: stewforani@gmail.com
 */
public class ActivityLifeMethodVisitor extends MethodVisitor {

   private String cName;
   private String mName;

   public ActivityLifeMethodVisitor(MethodVisitor mv, String className, String name) {
      super(Opcodes.ASM5,mv);
      cName = className;
      mName = name;
   }

   @Override
   public void visitCode() {
      super.visitCode();
      mv.visitVarInsn(Opcodes.ALOAD,0);
      mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
      mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
      mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/stew/androidtest/util/AppLogUtil", "addLifeLog", "(Ljava/lang/String;)V", false);
   }
}
