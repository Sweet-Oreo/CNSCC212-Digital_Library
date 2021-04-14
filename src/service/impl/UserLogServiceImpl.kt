package service.impl

import dao.impl.UserLogDaoImpl
import service.UserLogService
import java.sql.Date

class UserLogServiceImpl : UserLogService {

    private val dao = UserLogDaoImpl()

    override fun logSignIn(time: Date?, identity: String?, email: String?) {
        dao.logSignIn(time, identity, email)
    }

    override fun logSignOut(time: Date?, identity: String?, email: String?) {
        dao.logSignOut(time, identity, email)
    }

    override fun logSignUp(time: Date?, identity: String?, email: String?) {
        dao.logSignUp(time, identity, email)
    }

}