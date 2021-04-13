package dao;

import domain.Reviewer;

public interface ReviewerDao {
    Reviewer findReviewerByEmailAndPassword(String email, String password);

    boolean addReviewer(Reviewer reviewer);
}
