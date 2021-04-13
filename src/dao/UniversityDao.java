package dao;

import domain.University;

public interface UniversityDao {
    boolean addUniversity(University university);
    boolean checkEmail(String email);
    University findUniversityByEmailAndPassword(String email, String password);
}
