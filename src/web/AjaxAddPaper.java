package web;

import domain.Paper;
import service.PaperService;
import service.impl.PaperServiceImpl;
import service.impl.UniversityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet("/servlet/AjaxAddPaper")
public class AjaxAddPaper extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        final String date = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        req.setCharacterEncoding("utf-8");
        final PrintWriter writer = resp.getWriter();

        Paper paper = new Paper();
        paper.setTitle(req.getParameter("title"));
        paper.setAuthor(req.getParameter("author"));
        paper.setUniversity(new UniversityServiceImpl().findNameByEmail(req.getParameter("email")));
        paper.setOutline(req.getParameter("outline"));
        paper.setKeyword(req.getParameter("keyword"));
        paper.setMajor(req.getParameter("major"));
        paper.setSubmit_date(date);

        PaperService paperService = new PaperServiceImpl();
        if (!paperService.checkPaperMajor(paper.getMajor())) {
            writer.write("majorError");
        } else {
            int errCode = paperService.addPaper(paper);
            if (errCode == 0) {
                writer.write("reviewerError");
            } else if (errCode == 1) {
                writer.write("succeed");
            } else {
                writer.write("error");
            }
        }

       // req.getRequestDispatcher("/servlet/uploadServlet").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        this.doPost(req, resp);
    }

}
