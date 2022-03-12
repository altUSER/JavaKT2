package com.company.view


import com.company.Main
import com.company.modelfx.CUserFX
import com.company.viewmodel.CViewModelUserList
import javafx.scene.layout.BorderPane
import tornadofx.*

class CViewUserList: View("Пользователи") {
    val CViewModelUserList: CViewModelUserList by inject()
    override val root = BorderPane()

    val tableView = tableview(CViewModelUserList.users) {
        readonlyColumn("ID", CUserFX::id)
        column("Логин", CUserFX::propertyLogin).makeEditable()
        column("Имя", CUserFX::propertyName).makeEditable()
        column("Пол", CUserFX::propertySex).makeEditable()
        column("Дата рождения", CUserFX::propertyDateOfBirth).makeEditable()
        readonlyColumn("Возраст", CUserFX::age)
        readonlyColumn("Количество заказов", CUserFX::orderCount)

//        CViewModelUserList.rebindOnChange(this) { selectedPerson ->
//            item = selectedPerson ?: CUserFX()
//        }
    }

    init { with(root) { top { menubar {
        menu("Данные") {
            item("Товары").action { replaceWith<CViewGoodList>()}
            item("Заказы").action { replaceWith<CViewOrderList>() }
        }
        menu("Правка") {
            item("Сохранить").action {CViewModelUserList.save()}
            item("Добавить").action {tableView.selectionModel.clearSelection()}
            item("Обновить").action {CViewModelUserList.update()}
            item("Загрузить данные из файла").action {
                Main.loadAll()
                CViewModelUserList.update()
            }
            item("Создать отчет") { Main.count() }
                    }
                }
            }
            center { this += tableView }
        }
    }

}
