package service.impl;

import dao.PaperDao;
import dao.impl.PaperDaoImpl;
import domain.Paper;
import service.PaperService;

import java.util.List;

public class PaperServiceImpl implements PaperService {
    private PaperDao paperDao = new PaperDaoImpl();

    @Override
    public List<Paper> findAll() {

        return paperDao.findAll();
    }
}
