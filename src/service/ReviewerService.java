package service;

import domain.Reviewer;

public interface ReviewerService {
    boolean addReviewer(Reviewer reviewer);
    Reviewer login(Reviewer reviewer);
}
