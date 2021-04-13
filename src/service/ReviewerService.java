package service;

import domain.Reviewer;

public interface ReviewerService {
    boolean addReviewer(Reviewer reviewer);
    boolean checkEmail(String email);
    Reviewer login(Reviewer reviewer);
}
