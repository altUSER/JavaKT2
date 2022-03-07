package com.company.viewmodel

import com.company.modelfx.CUserFX
import javafx.beans.property.Property
import tornadofx.ItemViewModel
import java.time.LocalDate

class CViewModelEditUser(user: CUserFX): ItemViewModel<CUserFX>(user)
{
    val login: Property<String> = bind(CUserFX::propertyLogin)
    val sex: Property<Boolean> = bind(CUserFX::propertySex)
    val dateOfBirth: Property<LocalDate> = bind(CUserFX::propertyDateOfBirth)
}