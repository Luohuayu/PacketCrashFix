package luohuayu.classfixer;

import net.minecraft.launchwrapper.IClassTransformer;

public class ClassTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (basicClass == null) return null;
        byte[] fixedClassByte = ClassFixerPlugin.classes.get(transformedName);
        if (fixedClassByte != null) {
            System.out.println("Patched class: " + transformedName);
            return fixedClassByte;
        }
        return basicClass;
    }
}
