package com.company.viewmodel

import com.company.modelfx.CGoodFX
import com.company.services.CServiceGoods
import tornadofx.ViewModel

class CViewModelGoodList: ViewModel() {

    val serviceGoods: CServiceGoods by inject()
    val goods = serviceGoods.getAll()

    fun save() {
        serviceGoods.save(goods)
    }
    fun addNew() {
        goods.add(CGoodFX())
    }
    fun delete( good: CGoodFX? ){
        good?:return
        serviceGoods.delete(good)
    }

    fun update(){
        val goods = serviceGoods.getAll()
    }

}