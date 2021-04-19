package dao.impl;

import dao.PaperDao;
import domain.Paper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;
import java.util.Map;

public class PaperDaoImpl implements PaperDao {

    private final JdbcTemplate template = new JdbcTemplate((JDBCUtils.getDataSource()));

    @Override
    public int findTotalCount(String condition) {

        if (condition == null || "".equals(condition)) {
            String sql = "SELECT COUNT(*) FROM `paper`";
            return template.queryForObject(sql, Integer.class);
        } else {
            String sqlCondition = "SELECT COUNT(*) FROM `paper` WHERE `title` like ? or `author` like ? or `keyword` like ?;";
            String likeCondition = "%" + condition + "%";
            return template.queryForObject(sqlCondition, Integer.class, likeCondition, likeCondition, likeCondition);
        }
        /*
        String sql = "SELECT COUNT(*) FROM `paper` WHERE 1 = 1;";
        StringBuilder stringBuilder = new StringBuilder(sql);
        // If the search box is non-empty, consider the condition in the box
        if (condition != null && !"".equals(condition)) {
            stringBuilder.append(" and (title like ? or author like ? or keyword like ?);");
        }
        System.out.println(stringBuilder.toString());
        return template.queryForObject(stringBuilder.toString(), Integer.class, condition);

         */
    }

    @Override
    public List<Paper> findByPage(int start, int rows, String condition) {
        if (condition == null || "".equals(condition)) {
            String sql = "SELECT * FROM `paper` LIMIT ?, ?;";
            return template.query(sql, new BeanPropertyRowMapper<>(Paper.class), start, rows);
        } else {
            String sqlCondition = "SELECT COUNT(*) FROM `paper` WHERE `title` like ? or `author` like ? or `keyword` like ? limit ?, ?;";
            String likeCondition = "%" + condition + "%";
            return template.query(sqlCondition, new BeanPropertyRowMapper<Paper>(Paper.class), likeCondition, likeCondition, likeCondition, start, rows);
        }
    }

    @Override
    public boolean checkPaperMajor(String major) {
        return new ReviewerDaoImpl().checkReviewerMajor(major);
    }

    @Override
    public int addPaper(Paper paper) {
        // Search in database to find the reviewers in the same major
        final String findReviewers = "SELECT `id` FROM `reviewer` WHERE `major` = ? ORDER BY `count_task`;";
        List<Map<String, Object>> reviewersMaps = template.queryForList(findReviewers, paper.getMajor());
        // If the number of majors is less than 3, return 0
        if (reviewersMaps.size() < 3) {
            return 0;
        }
        // Set the reviewers' IDs
        paper.setRev_id_1(Integer.parseInt(reviewersMaps.get(0).get("id").toString()));
        paper.setRev_id_2(Integer.parseInt(reviewersMaps.get(1).get("id").toString()));
        paper.setRev_id_3(Integer.parseInt(reviewersMaps.get(2).get("id").toString()));
        // Insert new paper information into the table
        final String insertPaper = "INSERT INTO `paper` " +
                "(title, author, university, outline, keyword, major, rev_id_1, rev_id_2, rev_id_3, submit_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        template.update(insertPaper, paper.getTitle(), paper.getAuthor(), paper.getUniversity(),
                paper.getOutline(), paper.getKeyword(), paper.getMajor(), paper.getRev_id_1(),
                paper.getRev_id_2(), paper.getRev_id_3(), paper.getSubmit_date());
        // Update the reviewer information of the amount tasks
        final String addCountTask = "UPDATE `reviewer` SET `count_task` = `count_task` + 1 WHERE `id` IN (?, ?, ?);";
        template.update(addCountTask, paper.getRev_id_1(), paper.getRev_id_2(), paper.getRev_id_3());
        return 1;
    }

    @Override
    public List<Paper> findMyPapers(Object email) {
        // Query university name for given email
        String queryName = "select name from university where email = ?";
        Map<String, Object> universityName = template.queryForMap(queryName, email);
        // Query papers for given university
        String sql = "select * from paper where university = ? and is_published = ?";
        List<Paper> myPapers = template.query(sql, new BeanPropertyRowMapper<Paper>(Paper.class), universityName.get("name"), 1);
        return myPapers;

    }

    @Override
    public void delete(int id) {
        // Delete paper with given id
        String sql = "delete from paper where id = ?";
        template.update(sql, id);
    }

    @Override
    public int findLastId() {
        // Find the id of the last record
        String sql = "select id from paper order by id desc limit 1";
        Map<String, Object> map = template.queryForMap(sql);
        Number id = (Number) map.get("id");
        return id.intValue();
    }

}
