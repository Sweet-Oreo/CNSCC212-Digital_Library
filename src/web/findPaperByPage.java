package web;

import domain.PageBean;
import domain.Paper;
import service.PaperService;
import service.impl.PaperServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/findPaperByPageServlet")
public class findPaperByPage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPage = req.getParameter("currentPage"); // Current page
        String rows = req.getParameter("rows"); // Total number of papers each page
        // Handle situations where parameters from browsers are null
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "5";
        }
        PaperService paperService = new PaperServiceImpl();
        PageBean<Paper> pb = paperService.findPaperByPage(currentPage, rows);
        req.setAttribute("pb", pb);
        req.getRequestDispatcher("/library/index.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
