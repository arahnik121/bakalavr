package App.exceptions;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String id) {
        super("Resume " + id + " not exist", id);
    }
}
