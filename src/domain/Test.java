package domain;

import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class Test {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    public static void main(String[] args) {
        Test test = new Test();
        test.tr();




    }
    public void tr() {
        String sql = "update test set name = ? where name = ?";
        template.update(sql, "fuck", "name_test");

    }




}
