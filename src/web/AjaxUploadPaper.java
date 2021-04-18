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
        final PrintWriter writer = resp.getWriter();

        //Instantiate the disk file list factory
        DiskFileItemFactory factory=new DiskFileItemFactory();
        // Get the file upload object from the factory
        ServletFileUpload upload=new ServletFileUpload(factory);
        // Solving encoding/decoding problem
        req.setCharacterEncoding("utf-8");
        // Paper object that will be added into database
        Paper paper = new Paper();
        paper.setSubmit_date(date);
        try {
            // Obtain all elements in request
            List<FileItem> list = upload.parseRequest(req);
            // Set the attributes of uploaded paper
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

        // Add uploaded paper into database
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
