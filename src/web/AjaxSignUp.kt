package web

import domain.Reader
import domain.Reviewer
import domain.University
import service.impl.ReaderServiceImpl
import service.impl.ReviewerServiceImpl
import service.impl.UniversityServiceImpl
import service.impl.UserLogServiceImpl
import java.text.SimpleDateFormat
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/servlet/AjaxSignUp")
class AjaxSignUp : HttpServlet() {

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {

        val time = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis())
        req?.characterEncoding = "utf-8"
        val identity = req?.getParameter("identity")
        val email = req?.getParameter("email")
        val name = req?.getParameter("name")
        val password = req?.getParameter("password")
        val major = req?.getParameter("major").toString()

        when (identity) {
            "university" -> {
                val university = University().apply {
                    this.email = email
                    this.name = name
                    this.password = password
                }
                val universityService = UniversityServiceImpl()
                if (!universityService.checkUniversityEmail(email)) {
                    resp?.writer?.write("emailError")
                } else {
                    if (universityService.addUniversity(university)) {
                        resp?.writer?.write("error")
                    } else {
                        UserLogServiceImpl().logSignUp(time, identity, email, req.remoteAddr)
                        resp?.writer?.write("succeed")
                    }
                }
            }
            "reviewer" -> {
                val reviewer = Reviewer().apply {
                    this.email = email
                    this.name = name
                    this.password = password
                    this.major = major
                }
                val reviewerService = ReviewerServiceImpl()
                if (!reviewerService.checkReviewerEmail(email)) {
                    resp?.writer?.write("emailError")
                } else {
                    if (!reviewerService.checkReviewerMajor(major)) {
                        resp?.writer?.write("majorError")
                    } else {
                        if (reviewerService.addReviewer(reviewer)) {
                            resp?.writer?.write("error")
                        } else {
                            UserLogServiceImpl().logSignUp(time, identity, email, req.remoteAddr)
                            resp?.writer?.write("succeed")
                        }
                    }
                }
            }
            "reader" -> {
                val reader = Reader().apply {
                    this.email = email
                    this.name = name
                    this.password = password
                }
                val readerService = ReaderServiceImpl()
                if (!readerService.checkReaderEmail(email)) {
                    resp?.writer?.write("emailError")
                } else {
                    if (readerService.addReader(reader)) {
                        resp?.writer?.write("error")
                    } else {
                        UserLogServiceImpl().logSignUp(time, identity, email, req.remoteAddr)
                        resp?.writer?.write("succeed")
                    }
                }
            }
            else -> {
                resp?.writer?.write("error")
            }
        }

    }

}