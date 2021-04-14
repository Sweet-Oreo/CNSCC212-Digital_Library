package service.impl

import dao.impl.UserLogDaoImpl
import service.UserLogService
import java.sql.Date

class UserLogServiceImpl : UserLogService {

    private val dao = UserLogDaoImpl()

    override fun logSignIn(time: String?, identity: String?, email: String?, ip: String?) {
        dao.logSignIn(time, identity, email, ip)
    }

    override fun logSignOut(time: String?, identity: String?, email: String?, ip: String?) {
        dao.logSignOut(time, identity, email, ip)
    }

    override fun logSignUp(time: String?, identity: String?, email: String?, ip: String?) {
        dao.logSignUp(time, identity, email, ip)
    }

}