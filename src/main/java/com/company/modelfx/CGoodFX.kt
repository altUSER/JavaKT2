package com.company.modelfx

import tornadofx.getProperty
import tornadofx.property
import java.util.*

class CGoodFX(
        val id : UUID? = null,
        name : String = "",
        price : Double = 0.0,
        category : String = "",
        orderCount : Int = 0
)

{
    var name by property(name)
    fun propertyName() = getProperty(CGoodFX::name)

    var category by property(category)
    fun propertyCategory() = getProperty(CGoodFX::category)

    var price by property(price)
    fun propertyPrice() = getProperty(CGoodFX::price)

    var orderCount by property(orderCount)
    fun propertyPrderCount() = getProperty(CGoodFX::orderCount)
}