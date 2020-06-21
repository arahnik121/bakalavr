package App.Storage.serializer;

import App.Model.Aircraft;
import App.Model.GroundObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializer {
    void doWrite(Aircraft r, GroundObject obj, OutputStream os) throws IOException;

    Aircraft doRead(InputStream is) throws IOException;

    GroundObject doAnotherRead(InputStream is) throws IOException;
}
