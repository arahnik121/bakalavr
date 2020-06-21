package App.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLTransaction<T> {
    T wrap(Connection conn) throws SQLException;
}
