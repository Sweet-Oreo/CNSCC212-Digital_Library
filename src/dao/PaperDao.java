package dao;

import domain.Paper;

import java.util.List;

/**
 * Handle interactions with database associated with paper.
 */
public interface PaperDao {
    /**
     * Query total number of papers displayed on the website.
     *
     * @param condition Search condition input by user in search box.
     * @return Total number of papers queried.
     */
    int findTotalCount(String condition);

    /**
     * Query papers in particular page on the website.
     *
     * @param start Starting point of query in the database.
     * @param rows Number of papers in each page.
     * @param condition Search condition in the search box.
     * @return List of papers being found.
     */
    List<Paper> findByPage(int start, int rows, String condition);

    boolean checkPaperMajor(String major);

    int addPaper(Paper paper);

    /**
     * Query papers for given university.
     *
     * @param email Email of university.
     * @return List of papers being found for given university.
     */
    List<Paper> findMyPapers(Object email);

    /**
     * Delete paper with given id.
     *
     * @param id Id of paper being deleted.
     */
    void delete(int id);

    /**
     * Query the id of last paper in database.
     *
     * @return Id of the last paper in database.
     */

    int findLastId();

    /**
     * Query papers waiting to be reviewed by given reviewer.
     *
     * @param email Email of reviewer.
     * @return List of papers waiting to be reviewed for given reviewer.
     */
    List<Paper> findReviewPapers(Object email);

    /**
     * Provide operations for reviewing paper.
     *
     * @param comment Comment written by reviewer for the paper.
     * @param isAccept Whether paper is accepted by reviewer.
     * @param paperId Id of paper being reviewed.
     * @param reviewerEmail Email of reviewer who reviews the paper.
     * @param realPath Path of directory that stores QR code and PDF files.
     */
    void reviewPaper(String comment, int isAccept, int paperId, String reviewerEmail, String realPath);
}
