package service;

import domain.PageBean;
import domain.Paper;

import javax.servlet.ServletContext;
import java.util.List;

public interface PaperService {
    PageBean<Paper> findPaperByPage(String currentPage, String rows, String condition);
    boolean checkPaperMajor(String major);
    int addPaper(Paper paper);

    List<Paper> findMyPapers(Object email);

    void deletePaper(String id);

    int findLastId();

    List<Paper> findReviewPapers(Object email);

    void reviewPaper(String comment, String isAccept, String paperId, String reviewerEmail, ServletContext servletContext);
}
