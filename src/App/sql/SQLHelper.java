package App.sql;

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
}
