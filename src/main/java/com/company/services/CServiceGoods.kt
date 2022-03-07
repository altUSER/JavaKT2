package com.company.services

import com.company.config.CConfigHibernate
import com.company.dao.CDAOGoods
import com.company.model.CGood
import com.company.modelfx.CGoodFX
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.Controller

class CServiceGoods : Controller(){

    private var daoGoods = CDAOGoods(CConfigHibernate.getSessionFactory())
    val Goods = FXCollections.observableArrayList<CGoodFX>()

    //достать все товары из базы данных и преобразовать в CGoodFX для отображения в интерфейсе программы
    fun getAll() : ObservableList<CGoodFX>
    {
        Goods.clear()
        Goods.addAll(daoGoods.all.map {Good-> CGoodFX(Good.id, Good.category, Good.price, Good.name, Good.orders.size)});

        return Goods
    }

    //обновить внесенные в программе изменения в базу данных
    fun save(Goods : List<CGoodFX>)
    {
        val seq = Goods.asSequence().map {Good -> CGood(Good.id, Good.name, Good.price, Good.category, daoGoods.getGoodOrders(Good.id)) }
        val existingGoods = seq.filter {it.id != null}.toList()
        daoGoods.updateList(existingGoods)
        getAll()
    }

    //реализация удаления товара
    fun delete(Goodfx : CGoodFX)
    {
        val cGood = daoGoods.get(Goodfx.id)
        daoGoods.delete(cGood)
        Goods.remove(Goodfx)
    }
}