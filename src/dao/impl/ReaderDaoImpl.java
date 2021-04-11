package dao.impl;

import dao.ReaderDao;
import domain.Reader;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.Map;

public class ReaderDaoImpl implements ReaderDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Reader findReaderByEmailAndPassword(String email, String password) {
        try {
            String sql = "SELECT * FROM `reader` WHERE `email` = ? AND `password` = ?;";
            // If there is matching reviewer in the database, return it
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(Reader.class), email, password);
        } catch (Exception e) {
            e.printStackTrace();
            // If there's no matching reader in the database, return null
            return null;
        }
    }

    @Override
    public boolean addReader(Reader reader) {
        try {
            // Query if registered reader already exists in database
            String querySql = "SELECT `email` FROM `reader` WHERE `email` = ?;";
            Map<String, Object> result = template.queryForMap(querySql, reader.getEmail());
            // If the registered reader already exists in database, return true
            if (result.get("email") != null) return true;
        } catch (DataAccessException e) {
            String sql = "INSERT INTO `reader` VALUES (NULL, ?, ?, ?);";
            // Add new registered reader into database
            template.update(sql, reader.getEmail(), reader.getPassword(), reader.getName());
            return false;
        }
        return false;
    }

}
