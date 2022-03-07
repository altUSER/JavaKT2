package com.company.dao;

import com.company.config.CConfigHibernate;
import com.company.model.CUser;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CTestDAOUsers {

    @Test
    void checkIfGetWorksProperly() {
        CDAOUsers daoUsers = new CDAOUsers(CConfigHibernate.getSessionFactory());
        CUser user = daoUsers.get(UUID.fromString("6720a44c-af02-41e9-9d19-c4bb1c38c9a9"));
        assertNotNull(user, "Метод daoUsers.get не нашел пользователя по идентификатору.");
        assertEquals("Alexander", user.login, "Логин пользователя не соответствует ожидаемому.");
    }
}