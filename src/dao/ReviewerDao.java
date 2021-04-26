package dao;

import domain.Paper;
import domain.Reviewer;

/**
 * Handle interactions with database associated with reviewer.
 */
public interface ReviewerDao {
    /**
     * Insert new reviewer into the database.
     *
     * @param reviewer Reviewer that will be inserted.
     * @return true if reviewer is inserted successfully.
     */
    boolean addReviewer(Reviewer reviewer);

    /**
     * Check whether the given email is exited in database.
     *
     * @param email Email being checked.
     * @return true if the email does not exist in database.
     */
    boolean checkReviewerEmail(String email);

    boolean checkReviewerMajor(String major);

    /**
     * Query reviewer by given email and password.
     *
     * @param email Email of reviewer being searched.
     * @param password Password of reviewer being searched.
     * @return Return reviewer object if corresponding reviewed is found.
     */
    Reviewer findReviewerByEmailAndPassword(String email, String password);
    boolean evaluatePaper(String email, String paperId, String comment, int isAccept);
}
