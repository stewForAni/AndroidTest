package com.life.jv;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by stew on 2023/5/13.
 * mail: stewforani@gmail.com
 */
class ActivityLifeMethodVisitor extends MethodVisitor {

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
   }
}
