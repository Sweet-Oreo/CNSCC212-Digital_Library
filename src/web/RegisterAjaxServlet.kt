package web

import domain.Reader
import domain.Reviewer
import domain.University
import service.impl.ReaderServiceImpl
import service.impl.ReviewerServiceImpl
import service.impl.UniversityServiceImpl
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/register_ajax")
class RegisterAjaxServlet : HttpServlet() {
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val identity = req!!.getAttribute("identity").toString()
        val email = req.getAttribute("email").toString()
        val writer = resp!!.writer
        when (identity) {
            "university" -> {
                val university = University()
                university.email = email
                val universityService = UniversityServiceImpl()
                println("req")
                writer.write( if (universityService.checkEmail(email)) "succeed" else "failed" )
            }
            "reviewer" -> {
                val reviewer = Reviewer()
                reviewer.email = email
                val reviewerService = ReviewerServiceImpl()
                writer.write( if (reviewerService.checkEmail(email)) "succeed" else "failed" )
            }
            "reader" -> {
                val reader = Reader()
                reader.email = email
                val readerService = ReaderServiceImpl()
                writer.write( if (readerService.checkEmail(email)) "succeed" else "failed" )
            }
            else -> {
                writer.write(0)
            }
        }
    }
}