package dao;

import domain.Paper;

import java.util.List;

public interface PaperDao {
    List<Paper> findAll();
    int findTotalCount();
    List<Paper> findByPage(int start, int rows);
    boolean checkPaperMajor(String major);
    int addPaper(Paper paper);

    List<Paper> findMyPapers(Object email);

    void delete(int id);
}
