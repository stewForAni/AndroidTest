package com.stew.asmlife;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AsmLifeMethodVisitor extends MethodVisitor {
    private String className;
    private String methodName;

    public AsmLifeMethodVisitor(MethodVisitor methodVisitor, String className, String methodName) {
        super(Opcodes.ASM9, methodVisitor);
        this.className = className;
        this.methodName = methodName;
    }
    @Override
    public void visitCode() {
        super.visitCode();
    }
}