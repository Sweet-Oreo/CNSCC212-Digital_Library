package web;

import service.PaperService;
import service.impl.PaperServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/peerReviewServlet")
public class PeerReview extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get parameters from user browser
        String comment = req.getParameter("comment");
        String isAccept = req.getParameter("accept");
        String paperId = req.getParameter("id");
        // Update the paper being reviewed in the database
        PaperService paperService = new PaperServiceImpl();
        paperService.reviewPaper(comment, isAccept, paperId, (String) req.getSession().getAttribute("email"));
        // Redirect
        resp.sendRedirect(req.getContextPath() + "/library/review.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
