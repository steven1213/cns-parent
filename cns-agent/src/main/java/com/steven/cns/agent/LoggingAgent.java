package com.steven.cns.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author dr.panda
 */
@Slf4j
public class LoggingAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        ClassFileTransformer transformer = new LoggingTransformer();
        inst.addTransformer(transformer);
    }

    static class LoggingTransformer implements ClassFileTransformer {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain, byte[] classfileBuffer) {
            if (className.startsWith("com/steven/cns/log")) {
                try {
                    ClassPool pool = ClassPool.getDefault();
                    CtClass ctClass = pool.makeClass(new ByteArrayInputStream(classfileBuffer));
                    for (CtMethod declaredMethod : ctClass.getDeclaredMethods()) {
                        if (!declaredMethod.isEmpty()) {
                            declaredMethod.insertBefore("System.out.println(\"before\");");
                            declaredMethod.insertAfter("System.out.println(\"after\");");
                        }
                    }
                    byte[] byteCode = ctClass.toBytecode();
                    ctClass.detach();
                    return byteCode;
                } catch (Exception e) {
//                    log.warn("LoggingAgent error", e);
                }
            }
            return null;
        }
    }
}
