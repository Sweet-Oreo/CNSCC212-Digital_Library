package service.impl

import dao.impl.MajorDaoImpl
import service.MajorService

class MajorServiceImpl : MajorService {

    private val dao = MajorDaoImpl()

    override fun findMajors(): List<String?>? {
        return dao.findMajors()
    }

}