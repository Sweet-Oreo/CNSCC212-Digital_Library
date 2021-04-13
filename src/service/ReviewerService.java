package service;

import domain.Reviewer;

public interface ReviewerService {
    boolean addReviewer(Reviewer reviewer);
    boolean checkReviewerEmail(String email);
    boolean checkReviewerMajor(String major);
    Reviewer login(Reviewer reviewer);
}
