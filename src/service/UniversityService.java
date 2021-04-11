package service;

import domain.University;

public interface UniversityService {
    boolean addUniversity(University university);
    University login(University loginUniversity);
}
