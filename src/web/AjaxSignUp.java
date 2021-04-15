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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet("/servlet/AjaxSignUp")
public class AjaxSignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis());
        req.setCharacterEncoding("utf-8");
        final String identity = req.getParameter("identity");
        final String email = req.getParameter("email");
        final String name = req.getParameter("name");
        final String password = req.getParameter("password");
        final String major = req.getParameter("major");
        final PrintWriter writer = resp.getWriter();

        switch (identity) {
            case "university": {
                University university = new University();
                university.setEmail(email);
                university.setName(name);
                university.setPassword(password);
                UniversityService universityService = new UniversityServiceImpl();
                if (!universityService.checkUniversityEmail(email)) {
                    writer.write("emailError");
                } else {
                    if (universityService.addUniversity(university)) {
                        writer.write("error");
                    } else {
                        new UserLogServiceImpl().logSignUp(time, identity, email, req.getRemoteAddr());
                        writer.write("succeed");
                    }
                }
                break;
            }
            case "reviewer": {
                Reviewer reviewer = new Reviewer();
                reviewer.setEmail(email);
                reviewer.setName(name);
                reviewer.setPassword(password);
                reviewer.setMajor(major);
                ReviewerService reviewerService = new ReviewerServiceImpl();
                if (!reviewerService.checkReviewerEmail(email)) {
                    writer.write("emailError");
                } else {
                    if (!reviewerService.checkReviewerMajor(major)) {
                        writer.write("majorError");
                    } else {
                        if (reviewerService.addReviewer(reviewer)) {
                            writer.write("error");
                        } else {
                            new UserLogServiceImpl().logSignUp(time, identity, email, req.getRemoteAddr());
                            writer.write("succeed");
                        }
                    }
                }
                break;
            }
            case "reader": {
                Reader reader = new Reader();
                reader.setEmail(email);
                reader.setName(name);
                reader.setPassword(password);
                ReaderService readerService = new ReaderServiceImpl();
                if (!readerService.checkReaderEmail(email)) {
                    writer.write("emailError");
                } else {
                    if (readerService.addReader(reader)) {
                        writer.write("error");
                    } else {
                        new UserLogServiceImpl().logSignUp(time, identity, email, req.getRemoteAddr());
                        writer.write("succeed");
                    }
                }
                break;
            }
            default: {
                writer.write("error");
                break;
            }
        }

    }

}
