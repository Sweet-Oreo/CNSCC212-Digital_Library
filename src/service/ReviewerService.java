package service;

import domain.Reviewer;

public interface ReviewerService {
    Reviewer login(Reviewer reviewer);

    boolean addReviewer(Reviewer reviewer);
}
