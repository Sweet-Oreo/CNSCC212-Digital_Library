package dao.impl;

import dao.PaperDao;
import domain.Paper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class PaperDaoImpl implements PaperDao {
    private JdbcTemplate template = new JdbcTemplate((JDBCUtils.getDataSource()));

    @Override
    public List<Paper> findAll() {
        String sql = "select * from paper";
        List<Paper> papers = template.query(sql, new BeanPropertyRowMapper<Paper>(Paper.class));
        return papers;
    }
}
