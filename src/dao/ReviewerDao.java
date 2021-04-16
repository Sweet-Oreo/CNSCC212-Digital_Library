package dao;

import domain.Paper;
import domain.Reviewer;

public interface ReviewerDao {
    boolean addReviewer(Reviewer reviewer);
    boolean checkReviewerEmail(String email);
    boolean checkReviewerMajor(String major);
    Reviewer findReviewerByEmailAndPassword(String email, String password);
    boolean evaluatePaper(String email, String paperId, String comment, int isAccept);
}
