import java.io.IOException;
import java.io.InputStream;

public class EnvConfig {
    static {
        loadConfig();
    }

    public static final String BASE_URI = System.getProperty("base.uri");
    public static final String BASE_PATH = System.getProperty("base.path");

    private static void loadConfig() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        String fileName = System.getProperty("environment", "dev") + ".properties";

        try (InputStream resource = contextClassLoader.getResourceAsStream(fileName)) {
            System.getProperties().load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}