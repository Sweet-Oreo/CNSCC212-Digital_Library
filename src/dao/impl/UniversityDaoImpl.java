package dao.impl;

import dao.UniversityDao;
import domain.University;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.Map;

public class UniversityDaoImpl implements UniversityDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public University findUniversityByEmailAndPassword(String email, String password) {
        try {
            String sql = "SELECT * FROM `university` WHERE `email` = ? AND `password` = ?;";
            // If there is matching university in the database, return it
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(University.class), email, password);
        } catch (Exception e) {
            e.printStackTrace();
            // If there's no matching university in the database, return null
            return null;
        }
    }

    @Override
    public boolean addUniversity(University university) {
        try {
            // Query if registered university already exists in database
            String querySql = "SELECT `email` FROM `university` WHERE `email` = ?;";
            Map<String, Object> result = template.queryForMap(querySql, university.getEmail());
            // If the registered university already exists in database, return true
            if (result.get("email") != null) return true;
        } catch (DataAccessException e) {
            String sql = "INSERT INTO `university` VALUES (NULL, ?, ?, ?)";
            // Add new registered university into database
            template.update(sql, university.getEmail(), university.getPassword(), university.getName());
            return false;
        }
        return false;
    }

}
