package com.company.services

import com.company.config.CConfigHibernate
import com.company.dao.CDAOUsers
import com.company.model.CUser
import com.company.modelfx.CUserFX
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.Controller

class CServiceUsers : Controller() {
    private var daoUsers = CDAOUsers(CConfigHibernate.getSessionFactory())

    val users = FXCollections.observableArrayList<CUserFX>()

    fun getAll(): ObservableList<CUserFX> {
        users.clear()
        users.addAll(daoUsers.all.map {user->CUserFX(user.id, user.login, user.name, user.sex, user.dateOfBirth, user.orders.size)})
        return users
    }
    fun save(user: CUserFX){
        val user1 = CUser(user.id, user.login, user.name, user.sex, user.dateOfBirth)
        if (user1.id==null)
            daoUsers.save(user1)
        else
            daoUsers.update(user1)
        getAll()
    }

    fun save(users: List<CUserFX>) {
        val seq = users.asSequence().map { user -> CUser(user.id, user.login, user.name, user.sex, user.dateOfBirth)}
        val newUsers = seq.filter { it.id==null }.toList()

        daoUsers.saveList(newUsers)

        val existingUsers = seq.filterNot { it.id==null }.toList()

        daoUsers.updateList(existingUsers)
        getAll()
    }
}