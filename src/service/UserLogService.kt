package service

import java.sql.Date

interface UserLogService {
    fun logSignIn(time: String?, identity: String?, email: String?, ip: String?)
    fun logSignOut(time: String?, identity: String?, email: String?, ip: String?)
    fun logSignUp(time: String?, identity: String?, email: String?, ip: String?)
}