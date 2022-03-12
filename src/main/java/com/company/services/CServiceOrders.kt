package com.company.services

import com.company.config.CConfigHibernate
import com.company.dao.CDAOOrders
import com.company.dao.CDAOUsers
import com.company.model.CUser
import com.company.modelfx.COrderFX
import com.company.modelfx.CUserFX
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.Controller

class CServiceOrders : Controller() {
    private var daoOrders = CDAOOrders(CConfigHibernate.getSessionFactory())
    val orders = FXCollections.observableArrayList<COrderFX>()

    //достать все заказы из базы данных и преобразовать в COrderFX для отображения в интерфейсе программы
    fun getAll() : ObservableList<COrderFX>
    {
        orders.clear()
        orders.addAll(daoOrders.all.map {order-> COrderFX(order.id, order.good.id, order.date, order.owner.id)})

        return orders
    }


    fun delete(orderfx : COrderFX)
    {
        val order = daoOrders.get(orderfx.id)
        daoOrders.delete(order)
        orders.remove(orderfx)
    }
}