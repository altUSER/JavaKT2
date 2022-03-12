package com.company.dao;

import com.company.config.CConfigHibernate;
import com.company.model.CGood;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CTestDAOGoods {
    @Test
    void TEST_getProductByID() {
        CDAOGoods  daoGoods = new CDAOGoods(CConfigHibernate.getSessionFactory());
        CGood good = daoGoods.get(UUID.fromString("c5ae6430-e706-4128-91d4-84dd164f9d57"));
        assertNotNull(good, "Тест daoGoods.get(). Не найден товар по идентификатору c5ae6430-e706-4128-91d4-84dd164f9d57");
        assertEquals("Велосипед", good.name, "Тест daoGoods.get(). Наименования товара с идентификатором c5ae6430-e706-4128-91d4-84dd164f9d57 не соответствует ожидаемому результату");
    }
}
