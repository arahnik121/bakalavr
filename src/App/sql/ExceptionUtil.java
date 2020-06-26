package App.sql;

import App.exceptions.ExistStorageException;
import App.exceptions.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class ExceptionUtil {
    private ExceptionUtil() {
    }
    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {
            if (e.getSQLState().equals("23505")) {                                      //23505 - Нарушение условия Unique
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}


