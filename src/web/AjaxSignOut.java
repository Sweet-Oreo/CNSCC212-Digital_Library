package web;

import service.impl.UserLogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/servlet/AjaxSignOut")
public class AjaxSignOut extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        final String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis());
        HttpSession session = req.getSession();

        if (session.getAttribute("USER_SESSION") != null) {
            final String identity = session.getAttribute("identity").toString();
            final String email = session.getAttribute("email").toString();
            new UserLogServiceImpl().logSignOut(time, identity, email, req.getRemoteAddr());
            session.invalidate();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        this.doPost(req, resp);
    }

}
