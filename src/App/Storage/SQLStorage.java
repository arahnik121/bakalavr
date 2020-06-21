package App.Storage;

import App.Model.Aircraft;
import App.Model.GroundObject;
import App.exceptions.NotExistStorageException;
import App.sql.ConnectionFactory;
import App.sql.SQLExecutor;
import App.sql.SQLHelper;
import App.sql.SQLTransaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLStorage implements Storage {
    public final SQLHelper sqlHelper;

    public SQLStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        this.sqlHelper = new SQLHelper(new ConnectionFactory() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        });
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM groundobject");
    }

    @Override
    public void save(Aircraft a, GroundObject o) {
        sqlHelper.transactionalExecute(new SQLTransaction<Object>() {
            @Override
            public Object wrap(Connection conn) throws SQLException {
                try (PreparedStatement ps = conn.prepareStatement("INSERT INTO groundobject (x, y, classification, aircraft_id) VALUES (?, ?, ?, ?)")) {
                    ps.setInt(1, a.getX());
                    ps.setInt(2, a.getY());
                    ps.setString(3, o.getClassification());
                    ps.setString(4, a.getId());
                    ps.execute();
                    return null;
                }
            }
        });
    }

    @Override
    public void saveAircraft(Aircraft a) {
        sqlHelper.execute("INSERT INTO aircraft (id, x, y, rangeofviewx, rangeofviewy) VALUES (?, ?, ?, ?, ?)", new SQLExecutor<Object>() {
            @Override
            public Object wrap(PreparedStatement ps) throws SQLException {
                ps.setString(1, a.getId());
                ps.setInt(2, a.getX());
                ps.setInt(3, a.getY());
                ps.setInt(4, a.getRangeOfViewX());
                ps.setInt(5, a.getRangeOfViewY());
                ps.execute();
                return null;
            }
        });
    }

    @Override
    public void updateAircraft(Aircraft r) {
        sqlHelper.execute("UPDATE aircraft SET x = ?, y = ?, rangeofviewx = ?, rangeofviewy = ? WHERE id = ?", new SQLExecutor<Object>() {
            @Override
            public Object wrap(PreparedStatement ps) throws SQLException {
                ps.setInt(1, r.getX());
                ps.setInt(2, r.getY());
                ps.setInt(3, r.getRangeOfViewX());
                ps.setInt(4, r.getRangeOfViewY());
                ps.setString(5, r.getId());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getId());
                }
                return null;
            }
        });
    }

    @Override
    public void update(GroundObject o) {
        sqlHelper.execute("UPDATE groundobject SET classification = ? WHERE (x, y) = (?, ?)", new SQLExecutor<Object>() {
            @Override
            public Object wrap(PreparedStatement ps) throws SQLException {
                ps.setString(1, o.getClassification());
                ps.setInt(2, o.getX());
                ps.setInt(3, o.getY());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(o.getClassification());
                }
                return null;
            }
        });
    }

    @Override
    public Aircraft get(String id) {
        return sqlHelper.execute(" SELECT * FROM aircraft a WHERE a.id = ?", new SQLExecutor<Aircraft>() {
            @Override
            public Aircraft wrap(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(id);
                }
                return new Aircraft(id, rs.getInt("x"), rs.getInt("y"), rs.getInt("rangeofviewx"), rs.getInt("rangeofviewy"));
            }
        });
    }

    @Override
    public GroundObject getGroundObjects(int x, int y) {
        return sqlHelper.execute("SELECT * FROM groundobject WHERE (x, y) = (?, ?)", new SQLExecutor<GroundObject>() {
            @Override
            public GroundObject wrap(PreparedStatement ps) throws SQLException {
                ps.setInt(1, x);
                ps.setInt(2, y);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    return new GroundObject(999999, 999999, " ");
                }
                return new GroundObject(x, y, " ");
            }
        });
    }

    @Override
    public void delete(String id) {
        sqlHelper.execute("DELETE FROM aircraft WHERE id = ?", new SQLExecutor<Object>() {
            @Override
            public Object wrap(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(id);
                }
                return null;
            }
        });
    }

    @Override
    public List<Aircraft> getAllAircraftsSorted() {
        return sqlHelper.execute("SELECT * FROM aircraft a ORDER BY id", new SQLExecutor<List<Aircraft>>() {
            @Override
            public List<Aircraft> wrap(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                List<Aircraft> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new Aircraft(rs.getString("id"), rs.getInt("x"), rs.getInt("y"), rs.getInt("rangeofviewx"), rs.getInt("rangeofviewy")));
                }
                return list;
            }
        });
    }

    public List<GroundObject> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM groundobject o ORDER BY x ", new SQLExecutor<List<GroundObject>>() {
            @Override
            public List<GroundObject> wrap(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                List<GroundObject> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new GroundObject(rs.getInt("x"), rs.getInt("y"), rs.getString("classification"), rs.getString("aircraft_id")));
                }
                return list;
            }
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM aircraft", new SQLExecutor<Integer>() {
            @Override
            public Integer wrap(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                return rs.next() ? rs.getInt(1) : 0;
            }
        });
    }

}