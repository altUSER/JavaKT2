package com.company.view

import com.company.Main
import com.company.modelfx.CGoodFX
import com.company.modelfx.COrderFX
import com.company.viewmodel.CViewModelGoodList
import com.company.viewmodel.CViewModelOrderList
import com.company.viewmodel.CViewModelUserList
import tornadofx.*


class CViewOrderList: View("Заказы"){
    val CviewModelOrderList: CViewModelOrderList by inject()

    val tableView = tableview(CviewModelOrderList.orders)  {
        readonlyColumn("ID заказа", COrderFX::id)
        readonlyColumn("ID товара", COrderFX::good_id)
        readonlyColumn("ID пользователя", COrderFX::owner_id)
        readonlyColumn("Дата", COrderFX::purchase_date)
    }

    override val root = borderpane {
        top{
            menubar{
                menu("Данные") {
                    item("Пользователи").action { replaceWith<CViewUserList>() }
                    item("Товары").action { replaceWith<CViewGoodList>() }
                }
                menu("Правка"){
                    item("Удалить").action{ CviewModelOrderList.delete(tableView.selectedItem) }
                    item("Обновить").action{ CviewModelOrderList.update() }
                    item("Загрузить данные из файла").action {
                        Main.loadAll()
                        CviewModelOrderList.update()
                    }
                    item("Создать отчет") { Main.count() }
                }
            }
        }
        center{
            this += tableView
        }
    }
}