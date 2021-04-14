package web;

import service.impl.UserLogServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/servlet/logoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis());
        HttpSession session = req.getSession();
        if (session.getAttribute("USER_SESSION") != null) {
            String identity = session.getAttribute("identity").toString();
            String email = session.getAttribute("email").toString();
            new UserLogServiceImpl().logSignOut(time, identity, email, req.getRemoteAddr());
            // The invalidate() method destroys the current session and removes all the attributes
            // Then start a clear new session, the session ID also changes
            req.getSession().invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/library/login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }

}
