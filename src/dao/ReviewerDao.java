package dao;

import domain.Reviewer;

public interface ReviewerDao {
    boolean addReviewer(Reviewer reviewer);
    Reviewer findReviewerByEmailAndPassword(String email, String password);
}
