package App;

import App.Storage.SQLStorage;
import App.Storage.Storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final String PROPS = "/prop.properties";
    private static final Config INSTANCE = new Config();

//    private final File storageDir;
    private final Storage storage;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = Config.class.getResourceAsStream(PROPS)) {
            System.out.println(is);
            Properties props = new Properties();
            props.load(is);
//            storageDir = new File(props.getProperty("storage.dir"));
            storage = new SQLStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS);
        }
    }

//    public File getStorageDir() {
//        return storageDir;
//    }

    public Storage getStorage() {
        return storage;
    }
}

