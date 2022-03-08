package com.company.viewmodel

import com.company.modelfx.CUserFX
import com.company.services.CServiceUsers
import tornadofx.ViewModel

class CViewModelUserList: ViewModel()
{
    val serviceUsers: CServiceUsers by inject()
    val users = serviceUsers.getAll()

    fun save() {
        serviceUsers.save(users)
    }
    fun save(user: CUserFX) {
        serviceUsers.save(user)
    }

    fun update(){
        val users = serviceUsers.getAll()
    }
}
