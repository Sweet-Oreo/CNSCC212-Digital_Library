package dao;

import domain.University;

public interface UniversityDao {
    boolean addUniversity(University university);
    University findUniversityByEmailAndPassword(String email, String password);
}
