package io.advantageous.config;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * Javascript configuration loader.
 *
 * @author Geoff Chandler <geoffc@gmail.com>
 */
public class JsLoader {

    /**
     * Do not allow instantiation of this class.
     */
    private JsLoader() {
        throw new IllegalStateException("this class is not to be instantiated.");
    }

    //TODO: make this return a config object
    static Object load(final String path) {
        final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval(new FileReader(Paths.get(ClassLoader.getSystemResource(path).toURI()).toString()));
        } catch (ScriptException | FileNotFoundException | URISyntaxException e) {
            throw new IllegalArgumentException("unable to load javascript config at path: " + path);
        }
        return engine.get("config");
    }

}
