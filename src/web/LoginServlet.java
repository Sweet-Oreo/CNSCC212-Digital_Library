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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet("/servlet/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Set encoding
        req.setCharacterEncoding("utf-8");
        // Get the parameters from user's browser
        String action = req.getParameter("action");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String identity = req.getParameter("identity");
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis());

        // If user is signing up, forward to registerServlet
        if (action.equals("sign_up")) {
            if (identity.equals("reviewer")) {
                req.setAttribute("major", req.getParameter("major"));
            }
            // Store the parameters from browser into request object
            req.setAttribute("identity", identity);
            req.setAttribute("email", email);
            req.setAttribute("password", password);
            req.setAttribute("name", req.getParameter("name"));
            req.getRequestDispatcher("/servlet/registerServlet").forward(req, resp);
            return;
        }

        switch (identity) {
            case "reader": // If user is a reader, login as reader
                Reader loginReader = new Reader(); // Reader from login browser
                loginReader.setEmail(email);
                loginReader.setPassword(password);
                ReaderService readerService = new ReaderServiceImpl();
                // Reader from database that may match the login reader
                Reader reader = readerService.login(loginReader);
                if (reader != null) { // User inputs correct email and password
                    startSession(req, resp, time, identity, email);
                } else {
                    // If user inputs incorrect email or password, send the alert
                    PrintWriter writer = resp.getWriter();
                    writer.write("<script>alert('Invalid email or incorrect password');history.go(-1)</script>");
                }
                break;
            case "university": // If user is a university, login as university
                University loginUniversity = new University(); // University from login browser
                loginUniversity.setEmail(email);
                loginUniversity.setPassword(password);
                UniversityService universityService = new UniversityServiceImpl();
                // University from database that may match the login university
                University university = universityService.login(loginUniversity);
                if (university != null) {
                    startSession(req, resp, time, identity, email);
                } else {
                    PrintWriter writer = resp.getWriter();
                    writer.write("<script>alert('Invalid email or incorrect password');history.go(-1)</script>");
                }
                break;
            case "reviewer": // If user is a reviewer, login as reviewer
                Reviewer loginReviewer = new Reviewer(); // Reviewer from login browser
                loginReviewer.setEmail(email);
                loginReviewer.setPassword(password);
                ReviewerService reviewerService = new ReviewerServiceImpl();
                // Reviewer from database that may match the login reviewer
                Reviewer reviewer = reviewerService.login(loginReviewer);
                if (reviewer != null) {
                    startSession(req, resp, time, identity, email);
                } else {
                    PrintWriter writer = resp.getWriter();
                    writer.write("<script>alert('Invalid email or incorrect password');history.go(-1)</script>");
                }
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    private void startSession(HttpServletRequest req, HttpServletResponse resp, String time, String identity, String email) throws ServletException, IOException {
        new UserLogServiceImpl().logSignIn(time, identity, email, req.getRemoteAddr());
        // Set session for login reviewer
        HttpSession session = req.getSession();
        session.setAttribute("USER_SESSION", session.getId());
        session.setAttribute("identity", identity);
        session.setAttribute("email", email);
        // Forward to servlet displaying papers
        req.getRequestDispatcher("/servlet/paperListServlet").forward(req, resp);
    }

}
