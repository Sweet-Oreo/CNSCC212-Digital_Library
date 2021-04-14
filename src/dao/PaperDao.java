package dao;

import domain.Paper;

import java.util.List;

public interface PaperDao {
    List<Paper> findAll();
}
