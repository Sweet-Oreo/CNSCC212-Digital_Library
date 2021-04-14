package service

import java.sql.Date

interface UserLogService {
    fun logSignIn(time: Date?, identity: String?, email: String?)
    fun logSignOut(time: Date?, identity: String?, email: String?)
    fun logSignUp(time: Date?, identity: String?, email: String?)
}