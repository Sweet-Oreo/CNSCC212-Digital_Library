package web;

import domain.Reader;
import domain.Reviewer;
import domain.University;
import service.ReaderService;
import service.ReviewerService;
import service.UniversityService;
import service.impl.ReaderServiceImpl;
import service.impl.ReviewerServiceImpl;
import service.impl.UniversityServiceImpl;
import service.impl.UserLogServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

@WebServlet("/servlet/registerServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Get the parameters from request object
        String username = (String) req.getAttribute("name");
        String email = (String) req.getAttribute("email");
        String password = (String) req.getAttribute("password");
        String identity = (String) req.getAttribute("identity");
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis());

        // Recognize the identity of the login user
        switch (identity) {
            case "university": { // If login user is a university
                University university = new University();
                university.setEmail(email);
                university.setPassword(password);
                university.setName(username);
                UniversityService universityService = new UniversityServiceImpl();
                // Add university to database
                boolean isRepeat = universityService.addUniversity(university);
                // If the email user inputs already exists in the database, send the alert
                if (isRepeat) {
                    PrintWriter writer = resp.getWriter();
                    writer.write("<script>alert('This email already exists');history.go(-1)</script>");
                    return;
                }
                new UserLogServiceImpl().logSignUp(time, identity, email, req.getRemoteAddr());
                break;
            }
            case "reader": { // If login user is a reader
                Reader reader = new Reader();
                reader.setEmail(email);
                reader.setPassword(password);
                reader.setName(username);
                ReaderService readerService = new ReaderServiceImpl();
                // Add reader to database
                boolean isRepeat = readerService.addReader(reader);
                if (isRepeat) {
                    PrintWriter writer = resp.getWriter();
                    writer.write("<script>alert('This email already exists');history.go(-1)</script>");
                    return;
                }
                new UserLogServiceImpl().logSignUp(time, identity, email, req.getRemoteAddr());
                break;
            }
            case "reviewer": { // If login user is a reviewer
                Reviewer reviewer = new Reviewer();
                reviewer.setEmail(email);
                reviewer.setPassword(password);
                reviewer.setName(username);
                reviewer.setMajor((String) req.getAttribute("major"));
                ReviewerService reviewerService = new ReviewerServiceImpl();
                // Add reviewer to database
                boolean isRepeat = reviewerService.addReviewer(reviewer);
                if (isRepeat) {
                    PrintWriter writer = resp.getWriter();
                    writer.write("<script>alert('This email already exists');history.go(-1)</script>");
                    return;
                }
                new UserLogServiceImpl().logSignUp(time, identity, email, req.getRemoteAddr());
                break;
            }
        }

        // Redirect to login page after registration
        resp.sendRedirect(req.getContextPath() + "/library/login.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }
    
}
