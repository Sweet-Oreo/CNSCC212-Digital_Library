package web;

import service.MajorService;
import service.impl.MajorServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet/AjaxGetMajors")
public class AjaxGetMajors extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        MajorService majorService = new MajorServiceImpl();
        StringBuilder result = new StringBuilder();
        for (String major : majorService.findMajors()) {
            result.append("<option>").append(major).append("</option>");
        }
        resp.getWriter().write(result.toString());
    }

}
