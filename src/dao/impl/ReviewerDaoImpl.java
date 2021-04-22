package dao.impl;

import dao.ReviewerDao;
import domain.Reviewer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReviewerDaoImpl implements ReviewerDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Reviewer findReviewerByEmailAndPassword(String email, String password) {
        try {
            String sql = "SELECT * FROM `reviewer` WHERE `email` = ? AND `password` = ?;";
            // If there is matching reviewer in the database, return it
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(Reviewer.class), email, password);
        } catch (Exception e) {
            // It will print the error if no matched result found, it should not be printed (by Li, Qi)
            // e.printStackTrace();
            // If there's no matching reviewer in the database, return null
            return null;
        }
    }

    @Override
    public boolean checkReviewerEmail(String email) {
        try {
            String sql = "SELECT `id` FROM `reviewer` WHERE `email` = ?;";
            return template.queryForList(sql, email).isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkReviewerMajor(String major) {
        try {
            String sql = "SELECT `id` FROM `major` WHERE `name` = ?;";
            return !template.queryForList(sql, major).isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addReviewer(Reviewer reviewer) {
        try {
            // Query if registered reviewer already exists in database
            String querySql = "SELECT `email` FROM `reviewer` WHERE `email` = ?;";
            Map<String, Object> result = template.queryForMap(querySql, reviewer.getEmail());
            // If the registered reviewer already exists in database, return true
            if (result.get("email") != null) return true;
        } catch (DataAccessException e) {
            String sql = "INSERT INTO `reviewer` (`email`, `password`, `name`, `major`) VALUES (?, ?, ?, ?)";
            // Add new registered reviewer into database
            template.update(sql, reviewer.getEmail(), reviewer.getPassword(), reviewer.getName(), reviewer.getMajor());
            return false;
        }
        return false;
    }

    @Override
    public boolean evaluatePaper(String email, String paperId, String comment, int isAccept) {
        // Find the reviewers and acceptances of the paper
        final String queryFindPaperInfo = "SELECT `rev_id_1`, `rev_id_2`, `rev_id_3`, `acceptance_1`, `acceptance_2`, " +
                "`acceptance_3` FROM `paper` WHERE `id` = ?;";
        List<Map<String, Object>> infoMaps = template.queryForList(queryFindPaperInfo, paperId);
        // Find the ID of the reviewer
        final String queryFindMyId = "SELECT `id` FROM `reviewer` WHERE `email` = ?;";
        List<Map<String, Object>> myIdMaps = template.queryForList(queryFindMyId, email);
        // Take the values out
        String reviewerId = myIdMaps.get(0).get("id").toString();
        final List<String> reviewerIds = new ArrayList<>();
        reviewerIds.add(infoMaps.get(0).get("rev_id_1").toString());
        reviewerIds.add(infoMaps.get(0).get("rev_id_2").toString());
        reviewerIds.add(infoMaps.get(0).get("rev_id_3").toString());
        int acceptance1 = Integer.parseInt(infoMaps.get(0).get("acceptance_1").toString());
        int acceptance2 = Integer.parseInt(infoMaps.get(0).get("acceptance_2").toString());
        int acceptance3 = Integer.parseInt(infoMaps.get(0).get("acceptance_3").toString());
        // Locate the reviewer number to build the update query
        String queryEvaluate = null;
        for (int i = 1; i <= 3; i++) {
            if (reviewerId.equals(reviewerIds.get(i - 1))) {
                queryEvaluate = "UPDATE `paper` SET `rev_id_" + i + "` = ?, `acceptance_" + i + "` = ? WHERE `id` = ?;";
                break;
            }
        }
        try {
            // Execute the update query
            template.update(queryEvaluate, comment, isAccept, paperId);
            // Decrease the amount of task
            final String queryUpdateCount = "UPDATE `reviewer` SET `count_task` = `count_task` - 1 WHERE `email` = ?;";
            template.update(queryUpdateCount, email);
            // Check the final status of acceptance of the paper
            // When the acceptances are all nonzero
            if (acceptance1 != 0 && acceptance2 != 0 && acceptance3 != 0) {
                // When any two or more of the reviewers accepted
                if (acceptance1 + acceptance2 + acceptance3 > 0) {
                    // Update the is_published and publish_date arguments in table paper
                    final String queryPublish = "UPDATE `paper` SET `is_published` = TRUE, `publish_date` = ? WHERE `id` = ?;";
                    final String today = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
                    template.update(queryPublish, today, paperId);
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
