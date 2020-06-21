package App.Storage.serializer;

import App.Model.Aircraft;
import App.Model.GroundObject;
import App.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamSerializer implements StreamSerializer {
    private XmlParser xmlParser;

    public XmlStreamSerializer(XmlParser xmlParser) {
        xmlParser = new XmlParser(
                Aircraft.class, GroundObject.class
        );
    }
    @Override
    public void doWrite(Aircraft a, GroundObject obj, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(a, w);
            xmlParser.marshall(obj, w);
        }
    }

    @Override
    public Aircraft doRead(InputStream is) throws IOException {
        try (Reader a = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(a);
        }
    }

    @Override
    public GroundObject doAnotherRead(InputStream is) throws IOException {
        try (Reader a = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(a);
        }
    }
}
