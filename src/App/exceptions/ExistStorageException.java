package App.exceptions;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String id) {
        super("Resume " + id + " already exist", id);
    }
}
