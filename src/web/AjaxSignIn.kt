package web

import domain.Reader
import domain.Reviewer
import domain.University
import service.PaperService
import service.impl.*
import java.text.SimpleDateFormat
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/servlet/AjaxSignIn")
class AjaxSignIn : HttpServlet() {

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {

        val time = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis())
        req?.characterEncoding = "utf-8"
        val identity = req?.getParameter("identity").toString()
        val email = req?.getParameter("email").toString()
        val password = req?.getParameter("password").toString()

        when (identity) {
            "university" -> {
                val university = University().apply {
                    this.email = email
                    this.password = password
                }
                if (UniversityServiceImpl().login(university) == null) {
                    resp?.writer?.write("passwordError")
                    return
                }
            }
            "reviewer" -> {
                val reviewer = Reviewer().apply {
                    this.email = email
                    this.password = password
                }
                if (ReviewerServiceImpl().login(reviewer) == null) {
                    resp?.writer?.write("passwordError")
                    return
                }
            }
            "reader" -> {
                val reader = Reader().apply {
                    this.email = email
                    this.password = password
                }
                if (ReaderServiceImpl().login(reader) == null) {
                    resp?.writer?.write("passwordError")
                    return
                }
            }
            else -> {
                resp?.writer?.write("error")
                return
            }
        }

        UserLogServiceImpl().logSignUp(time, identity, email, req?.remoteAddr)
        req?.session?.apply {
            this.setAttribute("USER_SESSION", this.id)
            this.setAttribute("identity", identity)
            this.setAttribute("email", email)
            this.setAttribute("papers", PaperServiceImpl().findAll())
        }
        resp?.writer?.write("succeed")

    }

}