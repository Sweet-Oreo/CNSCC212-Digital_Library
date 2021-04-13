package service;

import domain.University;

public interface UniversityService {
    boolean addUniversity(University university);
    boolean checkUniversityEmail(String email);
    University login(University loginUniversity);
}
