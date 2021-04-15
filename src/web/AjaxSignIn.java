package web;

import domain.Reader;
import domain.Reviewer;
import domain.University;
import service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet("/servlet/AjaxSignIn")
public class AjaxSignIn extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis());
        req.setCharacterEncoding("utf-8");
        final String identity = req.getParameter("identity");
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");
        final PrintWriter writer = resp.getWriter();

        switch (identity) {
            case "university": {
                University university = new University();
                university.setEmail(email);
                university.setPassword(password);
                if (new UniversityServiceImpl().login(university) == null) {
                    writer.write("passwordError");
                    return;
                }
                break;
            }
            case "reviewer": {
                Reviewer reviewer = new Reviewer();
                reviewer.setEmail(email);
                reviewer.setPassword(password);
                if (new ReviewerServiceImpl().login(reviewer) == null) {
                    writer.write("passwordError");
                    return;
                }
                break;
            }
            case "reader": {
                Reader reader = new Reader();
                reader.setEmail(email);
                reader.setPassword(password);
                if (new ReaderServiceImpl().login(reader) == null) {
                    writer.write("passwordError");
                    return;
                }
                break;
            }
            default: {
                writer.write("error");
                return;
            }
        }

        new UserLogServiceImpl().logSignIn(time, identity, email, req.getRemoteAddr());
        HttpSession session = req.getSession();
        session.setAttribute("USER_SESSION", session.getId());
        session.setAttribute("identity", identity);
        session.setAttribute("email", email);
        session.setAttribute("papers", new PaperServiceImpl().findAll());
        writer.write("succeed");

    }

}
