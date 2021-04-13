package dao.impl;

import dao.UniversityDao;
import domain.Reader;
import domain.University;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.Map;

public class UniversityDaoImpl implements UniversityDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public University findUniversityByEmailAndPassword(String email, String password) {
        try {
            String sql = "select * from university where email= ? and `password` = ?";
            University university = template.queryForObject(sql, new BeanPropertyRowMapper<University>(University.class), email, password);
            // If there is matching university in the database, return it
            return university ;
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
            String querySql = "select email from university where email = ?";
            Map<String, Object> result = template.queryForMap(querySql, university.getEmail());
            // If the registered university already exists in database, return true
            if (result.get("email") != null) return true;
        } catch (DataAccessException e) {
            String sql = "insert into university (email, password, name) values(?, ?, ?)";
            // Add new registered university into database
            template.update(sql, university.getEmail(), university.getPassword(), university.getName());
            return false;
        }
        return false;

    }
}
