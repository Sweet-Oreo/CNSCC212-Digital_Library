package service.impl;

import dao.PaperDao;
import dao.impl.PaperDaoImpl;
import domain.PageBean;
import domain.Paper;
import service.PaperService;

import java.util.List;

public class PaperServiceImpl implements PaperService {

    private final PaperDao paperDao = new PaperDaoImpl();

    @Override
    public List<Paper> findAll() {
        return paperDao.findAll();
    }

    @Override
    public PageBean<Paper> findPaperByPage(String _currentPage, String _rows) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<Paper> pb = new PageBean<Paper>();
        // Set current page and total numbers of papers each page
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        // Query total amount of papers
        int totalCount = paperDao.findTotalCount();
        pb.setTotalCount(totalCount);
        // Calculate the index of the head record for each page
        int start = (currentPage - 1) * rows;
        // Query list of papers for given page
        List<Paper> paperList = paperDao.findByPage(start, rows);
        pb.setList(paperList);
        // Calculate numbers of pages
        int totalPage = totalCount % rows == 0 ? totalCount / rows : (totalCount / rows) + 1;
        pb.setTotalPage(totalPage);
        return pb;




    }

}
