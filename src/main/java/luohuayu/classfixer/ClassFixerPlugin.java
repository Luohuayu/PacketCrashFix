package luohuayu.classfixer;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@IFMLLoadingPlugin.SortingIndex(-100)
public class ClassFixerPlugin implements IFMLLoadingPlugin {
    public static Map<String, byte[]> classes = new ConcurrentHashMap<String, byte[]>();

    static {
        File classDir = new File("ClassFixer");
        if (!classDir.exists()) classDir.mkdir();
        for (File file : classDir.listFiles()) {
            String fileName = file.getName();
            if (fileName.endsWith(".class")) {
                String className = fileName.substring(0, fileName.length() - 6);
                System.out.println("Loading fixed class: " + className);
                try {
                    classes.put(className, FileUtils.readFileToByteArray(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Loaded classes count: " + classes.size());
    }

    public String[] getASMTransformerClass() { return new String[] {ClassTransformer.class.getCanonicalName()}; }
    public String getAccessTransformerClass() { return null; }
    public String getModContainerClass() {  return null; }
    public String getSetupClass() { return null; }
    public void injectData(Map<String, Object> arg0) {}
}
