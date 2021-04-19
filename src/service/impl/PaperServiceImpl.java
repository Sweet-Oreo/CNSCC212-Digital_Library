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
    public PageBean<Paper> findPaperByPage(String _currentPage, String _rows, String condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        // Handle situation where user clicks "previous arrow" while in first page
        if (currentPage <= 0) {
            currentPage = 1;
        }
        PageBean<Paper> pb = new PageBean<>();
        // Set total numbers of papers each page
        pb.setRows(rows);
        // Query total amount of papers
        int totalCount = paperDao.findTotalCount();
        pb.setTotalCount(totalCount);
        // Calculate numbers of pages
        int totalPage = totalCount % rows == 0 ? totalCount / rows : (totalCount / rows) + 1;
        pb.setTotalPage(totalPage);
        // Handle situations where user clicks "next arrow" while in last page
        if (currentPage >= totalPage) {
            currentPage = totalPage;
        }
        // Calculate the index of the head record for each page
        int start = (currentPage - 1) * rows;
        // Query list of papers for given page
        List<Paper> paperList = paperDao.findByPage(start, rows);
        pb.setList(paperList);
        // Set current page
        pb.setCurrentPage(currentPage);
        return pb;
    }

    @Override
    public boolean checkPaperMajor(String major) {
        return paperDao.checkPaperMajor(major);
    }

    @Override
    public int addPaper(Paper paper) {
        return paperDao.addPaper(paper);
    }

    @Override
    public List<Paper> findMyPapers(Object email) {
        return paperDao.findMyPapers(email);
    }

    @Override
    public void deletePaper(String id) {
        paperDao.delete(Integer.parseInt(id));
    }

    @Override
    public int findLastId() {
        return paperDao.findLastId();
    }

}
