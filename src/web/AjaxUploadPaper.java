package web;

import domain.Paper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.PaperService;
import service.impl.PaperServiceImpl;
import service.impl.UniversityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/servlet/AjaxUploadPaperServlet")
public class AjaxUploadPaper extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final String date = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        req.setCharacterEncoding("utf-8");
        final PrintWriter writer = resp.getWriter();

        // TODO: To get the type of multipart/form-data, getParameter() is not available. Must find another way

        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
        req.setCharacterEncoding("utf-8");

        Paper paper = new Paper();
        paper.setSubmit_date(date);
        try {
            List<FileItem> list = upload.parseRequest(req);
            for (FileItem fileItem : list) {
                switch (fileItem.getFieldName()) {
                    case "title":
                        paper.setTitle(fileItem.getString());
                        break;
                    case "author":
                        paper.setAuthor(fileItem.getString());
                        break;
                    case "email":
                        paper.setUniversity(new UniversityServiceImpl().findNameByEmail(fileItem.getString()));
                        break;
                    case "outline":
                        paper.setOutline(fileItem.getString());
                        break;
                    case "keyword":
                        paper.setKeyword(fileItem.getString());
                        break;
                    case "major":
                        paper.setMajor(fileItem.getString());
                        break;
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }

/*
        paper.setTitle(req.getParameter("title"));
        paper.setAuthor(req.getParameter("author"));
        paper.setUniversity(new UniversityServiceImpl().findNameByEmail(req.getParameter("email")));
        paper.setOutline(req.getParameter("outline"));
        paper.setKeyword(req.getParameter("keyword"));
        paper.setMajor(req.getParameter("major"));
        paper.setSubmit_date(date);

 */

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

    }

}
