package web

import service.impl.MajorServiceImpl
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/servlet/AjaxGetMajors")
class AjaxGetMajors : HttpServlet() {

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val majorService = MajorServiceImpl()
        var result = ""
        majorService.findMajors()?.forEach { result += "<option>$it</option>" }
        resp?.writer?.write(result)
    }

}