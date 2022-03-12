package com.company.modelfx

import tornadofx.getProperty
import tornadofx.property
import java.util.*

class COrderFX (
        val id: UUID? = null,
        val good_id : UUID? = null,
        purchase_date : Date? = Date(),
        val owner_id : UUID? = null,
        val productCount : Int = 0
)
{
    var purchase_date by property(purchase_date)
    fun propertyPurchase_date() = getProperty(COrderFX::purchase_date)


}