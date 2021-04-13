package web

import service.impl.ReaderServiceImpl
import service.impl.ReviewerServiceImpl
import service.impl.UniversityServiceImpl
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/servlet/AjaxCheckEmail")
<<<<<<< HEAD
class AjaxCheckEmail : HttpServlet() {
=======
class RegisterAjaxServlet : HttpServlet() {
>>>>>>> Resolved conflicts.

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val identity = req?.getParameter("identity").toString()
        val email = req?.getParameter("email").toString()
        val writer = resp?.writer
        when (identity) {
            "university" -> {
                val universityService = UniversityServiceImpl()
<<<<<<< HEAD
                writer?.write(if (universityService.checkEmail(email)) "succeed" else "failed")
            }
            "reviewer" -> {
                val reviewerService = ReviewerServiceImpl()
                writer?.write(if (reviewerService.checkEmail(email)) "succeed" else "failed")
            }
            "reader" -> {
                val readerService = ReaderServiceImpl()
                writer?.write(if (readerService.checkEmail(email)) "succeed" else "failed")
=======
                writer?.write(if (universityService.checkUniversityEmail(email)) "succeed" else "failed")
            }
            "reviewer" -> {
                val reviewerService = ReviewerServiceImpl()
                writer?.write(if (reviewerService.checkReviewerEmail(email)) "succeed" else "failed")
            }
            "reader" -> {
                val readerService = ReaderServiceImpl()
                writer?.write(if (readerService.checkReaderEmail(email)) "succeed" else "failed")
>>>>>>> Resolved conflicts.
            }
            else -> {
                writer?.write("failed")
            }
        }
    }

}