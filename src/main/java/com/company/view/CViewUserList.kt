package com.company.view


import com.company.modelfx.CUserFX
import com.company.viewmodel.CViewModelEditUser
import com.company.viewmodel.CViewModelUserList
import javafx.scene.layout.BorderPane
import tornadofx.*

class CViewUserList : View("Пользователи") {
    val CViewModelUserList: CViewModelUserList by inject()
    val viewModelEditUser = CViewModelEditUser(CUserFX())
    override val root = BorderPane()

    val tableView = tableview(CViewModelUserList.users) {
        readonlyColumn("ID", CUserFX::id)
        column("Логин", CUserFX::propertyLogin).makeEditable()
        column("Пол", CUserFX::propertySex).makeEditable()
        column("Дата рождения", CUserFX::propertyDateOfBirth).makeEditable()
        readonlyColumn("Возраст", CUserFX::age)
        readonlyColumn("Количество заказов", CUserFX::orderCount)

//        CViewModelUserList.rebindOnChange(this) { selectedPerson ->
//            item = selectedPerson ?: CUserFX()
//        }
    }

    init { with(root) { top { menubar {
        menu("Данные") { item("Товары").action { replaceWith<CViewGoodList>()}}
        menu("Правка") {
            item("Сохранить").action {CViewModelUserList.save()}
            item("Добавить").action {tableView.selectionModel.clearSelection()}
                    }
                }
            }
            center { this += tableView }
            right {
                form {
                    fieldset("Редактирование пользователя") {
                        field("Логин") {
                            textfield(viewModelEditUser.login)
                        }
                        field("Дата рождения") {
                            datepicker(viewModelEditUser.dateOfBirth)
                        }
                        field("Пол") {
                            choicebox(viewModelEditUser.sex)

                        }
                        button("Сохранить") {
                            enableWhen(viewModelEditUser.dirty)
                            action {
                                save()
                            }
                        }
                        button("Отмена").action {
                            viewModelEditUser.rollback()
                        }
                    }
                }
            }
        }
    }
    private fun save() {
        // Flush changes from the text fields into the model
        viewModelEditUser.commit()

        CViewModelUserList.save(viewModelEditUser.item)
    }

}
