package com.company.view

import com.company.modelfx.CGoodFX
import com.company.viewmodel.CViewModelGoodList
import tornadofx.*

class CViewGoodList: View("Товары") {

    val CviewModelGoodList: CViewModelGoodList by inject()

    val tableView = tableview(CviewModelGoodList.goods)  {
        readonlyColumn("ID", CGoodFX::id)
        column("Категория", CGoodFX::propertyCategory).makeEditable()
        column("Название", CGoodFX::propertyName).makeEditable()
        column("Цена", CGoodFX::propertyPrice).makeEditable()
        readonlyColumn("Количество заказов у товара", CGoodFX::orderCount)
    }

    override val root = borderpane {
        top{
            menubar{
                menu("Данные") {
                    item("Пользователи").action {
                        replaceWith<CViewUserList>()
                    }
                }
                menu("Правка"){
                    item("Сохранить").action{
                        CviewModelGoodList.save()
                    }
                    item("Добавить").action{
                        CviewModelGoodList.addNew()
                    }
                    item("Удалить").action{
                        CviewModelGoodList.delete(tableView.selectedItem)
                    }
                }
            }
        }
        center{
            this += tableView
        }
    }
}