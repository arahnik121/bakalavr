package App.exceptions;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String id) {
        super("Aircraft " + id + " not exist", id);
    }
}
