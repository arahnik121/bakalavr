package App.exceptions;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String id) {
        super("Aircraft " + id + " already exist", id);
    }
}
