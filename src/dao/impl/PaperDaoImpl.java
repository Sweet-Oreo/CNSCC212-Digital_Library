package dao.impl;

import dao.PaperDao;
import domain.Paper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class PaperDaoImpl implements PaperDao {

    private final JdbcTemplate template = new JdbcTemplate((JDBCUtils.getDataSource()));

    @Override
    public List<Paper> findAll() {
        String sql = "SELECT * FROM `paper`;";
        return template.query(sql, new BeanPropertyRowMapper<>(Paper.class));
    }

}
