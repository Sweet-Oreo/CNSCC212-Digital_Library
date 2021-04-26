package service;

import domain.PageBean;
import domain.Paper;

import java.util.List;

/**
 * Handle services associated with paper.
 */
public interface PaperService {
    /**
     * Find papers being displayed on given page.
     *
     * @param currentPage Current page the user is on the website.
     * @param rows Number of papers displayed in each page.
     * @param condition Search conditions that user inputs in the search box.
     * @return Pagination information.
     */
    PageBean<Paper> findPaperByPage(String currentPage, String rows, String condition);

    boolean checkPaperMajor(String major);


    int addPaper(Paper paper);

    /**
     * Find papers for given university.
     *
     * @param email Email of the login university.
     * @return Papers for this university.
     */
    List<Paper> findMyPapers(Object email);

    /**
     * Delete paper administered by given university.
     *
     * @param id id of the login university.
     */
    void deletePaper(String id);

    /**
     * Find id of the last paper in database.
     *
     * @return id of the targeted paper.
     */
    int findLastId();

    /**
     * Find papers required to be reviewed for login reviewer.
     *
     * @param email Email of the login reviewer.
     * @return List of targeted papers.
     */
    List<Paper> findReviewPapers(Object email);

    /**
     * Provide operations for reviewer to reviewer paper.
     *
     * @param comment Comment written by the reviewer.
     * @param isAccept Whether reviewer passed the paper.
     * @param paperId Id of paper being reviewed.
     * @param reviewerEmail Email of the reviewer.
     * @param realPath Path of directory that stores QR code and PDF files.
     */
    void reviewPaper(String comment, String isAccept, String paperId, String reviewerEmail, String realPath);
}
