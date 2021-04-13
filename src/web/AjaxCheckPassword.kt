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

@WebServlet("/servlet/AjaxCheckPassword")
class AjaxCheckPassword : HttpServlet() {

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val identity = req?.getParameter("identity").toString()
        val email = req?.getParameter("email").toString()
        val password = req?.getParameter("password").toString()
        val writer = resp?.writer
        when (identity) {
            "university" -> {
                val university = University().apply {
                    this.email = email
                    this.password = password
                }
                val universityService = UniversityServiceImpl()
                writer?.write(if (universityService.login(university) == null) "failed" else "succeed")
            }
            "reviewer" -> {
                val reviewer = Reviewer().apply {
                    this.email = email
                    this.password = password
                }
                val reviewerService = ReviewerServiceImpl()
                writer?.write(if (reviewerService.login(reviewer) == null) "failed" else "succeed")
            }
            "reader" -> {
                val reader = Reader().apply {
                    this.email = email
                    this.password = password
                }
                val readerService = ReaderServiceImpl()
                writer?.write(if (readerService.login(reader) == null) "failed" else "succeed")
            }
            else -> {
                writer?.write("failed")
            }
        }
    }

}