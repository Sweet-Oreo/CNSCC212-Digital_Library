package dao;

import domain.Reviewer;

public interface ReviewerDao {
    boolean addReviewer(Reviewer reviewer);
    boolean checkEmail(String email);
    Reviewer findReviewerByEmailAndPassword(String email, String password);
}
