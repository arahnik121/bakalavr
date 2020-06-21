package App.sql;

import App.exceptions.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLHelper {
    private final ConnectionFactory connectionFactory;

    public SQLHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        execute(sql, new SQLExecutor<Boolean>() {
            @Override
            public Boolean wrap(PreparedStatement ps) throws SQLException {
                return ps.execute();
            }
        });
    }

    public <T> T execute(String sql, SQLExecutor<T> executor) {
        try {
            Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            return executor.wrap(ps);
        } catch (SQLException e) {
            throw ExceptionUtil.convertException(e);
        }
    }

    public <T> T transactionalExecute(SQLTransaction<T> executor) {
        try (Connection conn = connectionFactory.getConnection()) {
            try {
                conn.setAutoCommit(false);
                T res = executor.wrap(conn);
                conn.commit();
                return res;
            } catch (SQLException e) {
                conn.rollback();
                throw ExceptionUtil.convertException(e);
            }
        }
        catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
