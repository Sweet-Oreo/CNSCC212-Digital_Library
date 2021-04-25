package dao;

import domain.Paper;

import javax.servlet.ServletContext;
import java.util.List;

public interface PaperDao {
    int findTotalCount(String condition);

    List<Paper> findByPage(int start, int rows, String condition);

    boolean checkPaperMajor(String major);

    int addPaper(Paper paper);

    List<Paper> findMyPapers(Object email);

    void delete(int id);

    int findLastId();

    List<Paper> findReviewPapers(Object email);

    void reviewPaper(String comment, int isAccept, int paperId, String reviewerEmail, ServletContext servletContext);
}
