package dao.impl

import dao.UserLogDao
import java.sql.Date

class UserLogDaoImpl : UserLogDao {

    override fun logSignIn(time: Date?, identity: String?, email: String?) {
        // TODO: Insert log information into database
        println("$time - SIGN_IN - $email ($identity)")
    }

    override fun logSignOut(time: Date?, identity: String?, email: String?) {
        // TODO: Insert log information into database
        println("$time - SIGN_OUT - $email ($identity)")
    }

    override fun logSignUp(time: Date?, identity: String?, email: String?) {
        // TODO: Insert log information into database
        println("$time - SIGN_UP - $email ($identity)")
    }

}