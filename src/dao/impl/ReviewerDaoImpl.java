package dao.impl;

import dao.ReviewerDao;
import domain.Reviewer;
import domain.University;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.Map;

public class ReviewerDaoImpl implements ReviewerDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Reviewer findReviewerByEmailAndPassword(String email, String password) {
        try {
            String sql = "select * from reviewer where email= ? and `password` = ?";
            Reviewer reviewer = template.queryForObject(sql, new BeanPropertyRowMapper<Reviewer>(Reviewer.class), email, password);
            // If there is matching reviewer in the database, return it
            return reviewer;
        } catch (Exception e) {
            e.printStackTrace();
            // If there's no matching reviewer in the database, return null
            return null;
        }
    }

    @Override
    public boolean addReviewer(Reviewer reviewer) {
        try {
            // Query if registered reviewer already exists in database
            String querySql = "select email from reviewer where email = ?";
            Map<String, Object> result = template.queryForMap(querySql, reviewer.getEmail());
            // If the registered reviewer already exists in database, return true
            if (result.get("email") != null) return true;
        } catch (DataAccessException e) {
            String sql = "insert into reviewer values(null, ?, ?, ?, ?)";
            // Add new registered reviewer into database
            template.update(sql, reviewer.getEmail(), reviewer.getPassword(), reviewer.getName(), reviewer.getMajor());
            return false;
        }
        return false;

    }
}
