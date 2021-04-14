package dao.impl

import dao.UserLogDao
import org.springframework.jdbc.core.JdbcTemplate
import util.JDBCUtils

class UserLogDaoImpl : UserLogDao {

    private val template = JdbcTemplate(JDBCUtils.getDataSource())

    override fun logSignIn(time: String?, identity: String?, email: String?, ip: String?) {
        val operation = "SIGN_IN"
        insertLog(time, operation, identity, email, ip)
    }

    override fun logSignOut(time: String?, identity: String?, email: String?, ip: String?) {
        val operation = "SIGN_OUT"
        insertLog(time, operation, identity, email, ip)
    }

    override fun logSignUp(time: String?, identity: String?, email: String?, ip: String?) {
        val operation = "SIGN_UP"
        insertLog(time, operation, identity, email, ip)
    }

    fun insertLog(time: String?, operation: String?, identity: String?, email: String?, ip: String?) {
        val query = "INSERT INTO `UserLog` (`time`, `operation`, `identity`, `email`, `ip`) VALUES (?, ?, ?, ?, ?);"
        template.update(query, time, operation, identity, email, ip)
    }

}