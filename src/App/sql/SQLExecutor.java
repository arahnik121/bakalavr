package App.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SQLExecutor<T> {
    T wrap(PreparedStatement st) throws SQLException;
}
