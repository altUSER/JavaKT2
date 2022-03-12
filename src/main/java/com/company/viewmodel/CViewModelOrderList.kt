package com.company.viewmodel

import com.company.modelfx.COrderFX
import com.company.services.CServiceOrders
import tornadofx.ViewModel

class CViewModelOrderList : ViewModel(){
    val serviceOrders : CServiceOrders by inject()
    val orders = serviceOrders.getAll()


    fun delete(orderfx : COrderFX?)
    {
        orderfx?:return
        serviceOrders.delete(orderfx)
    }

    fun update(){
        val users = serviceOrders.getAll()
    }
}