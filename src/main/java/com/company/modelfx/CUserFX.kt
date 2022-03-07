package com.company.modelfx

import tornadofx.getProperty
import tornadofx.property
import java.time.LocalDate
import java.time.Period
import java.util.*

class CUserFX(
        val id: UUID?
        = null,
        login: String
        = "",
        name: String
        = "",
        sex: Boolean
        = false,
        dayOfBirth: LocalDate?
        = null,
        val orderCount: Int
        = 0
)
{
    var login by property(login)
    fun propertyLogin() = getProperty(CUserFX::login)

    var name by property(name)
    fun propertyName() = getProperty(CUserFX::name)

    var sex by property(sex)
    fun propertySex() = getProperty(CUserFX::sex)

    var dateOfBirth by property(dayOfBirth)
    fun propertyDateOfBirth() = getProperty(CUserFX::dateOfBirth)

    val age: Int
        get() {
            return if (dateOfBirth==null)
                0
            else
                Period.between(dateOfBirth, LocalDate.now()).years
        }
}