package dao;

import domain.University;

public interface UniversityDao {
    University findUniversityByEmailAndPassword(String email, String password);

    boolean addUniversity(University university);
}
