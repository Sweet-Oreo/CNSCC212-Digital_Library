package web

import service.impl.ReviewerServiceImpl
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/servlet/AjaxCheckMajor")
class AjaxCheckMajor : HttpServlet() {

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val major = req?.getParameter("major")
        val reviewerService = ReviewerServiceImpl()
        resp?.writer?.write(if (reviewerService.checkReviewerMajor(major)) "succeed" else "failed")
    }

}