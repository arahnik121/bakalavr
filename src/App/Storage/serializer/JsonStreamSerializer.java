package App.Storage.serializer;


import App.Model.Aircraft;
import App.Model.GroundObject;
import App.util.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Aircraft r, GroundObject obj, OutputStream os) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            JsonParser.write(r, writer);
        }
    }

    @Override
    public Aircraft doRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return JsonParser.read(reader, Aircraft.class);
        }
    }

    @Override
    public GroundObject doAnotherRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return JsonParser.read(reader, GroundObject.class);
        }
    }
}
