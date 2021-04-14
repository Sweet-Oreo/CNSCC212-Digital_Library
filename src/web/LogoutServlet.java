package web;

import service.UserLogService;
import service.impl.UserLogServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/servlet/logoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Date time = new Date(System.currentTimeMillis());
        Object user_session = req.getSession().getAttribute("USER_SESSION");
        if (user_session != null) {
            String identity = (String) req.getSession().getAttribute("identity");
            String email = (String) req.getSession().getAttribute("email");
            new UserLogServiceImpl().logSignOut(time, identity, email);
            req.getSession().removeAttribute("USER_SESSION");
        }
        resp.sendRedirect(req.getContextPath() + "/library/login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }

}
