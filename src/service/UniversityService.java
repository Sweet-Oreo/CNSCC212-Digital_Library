package service;

import domain.University;

public interface UniversityService {
    University login(University loginUniversity);

    boolean addUniversity(University university);
}
