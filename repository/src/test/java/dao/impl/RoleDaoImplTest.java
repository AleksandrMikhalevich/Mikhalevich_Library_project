package dao.impl;

import dao.exceptions.DaoException;
import dao.impl.mocks.MockUtils;
import dao.interfaces.Dao;
import entities.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dao.impl.mocks.MockConstants.*;

/**
 * @author Alex Mikhalevich
 * @created 2022-09-24 14:52
 */
class RoleDaoImplTest {

    @Test
    void shouldCreateRoleInDatabase() throws DaoException {
        Role role = MockUtils.createRole();
        Dao<Role> roleDao = new RoleDaoImpl();
        roleDao.save(role);
        Role roleFromDB = roleDao.findById(role.getId());
        Assertions.assertNotNull(roleFromDB);
        Assertions.assertEquals(role, roleFromDB);
    }

    @Test
    void shouldFindRoleInDatabaseById() throws DaoException {
        Role role = MockUtils.createRole();
        Dao<Role> roleDao = new RoleDaoImpl();
        roleDao.save(role);
        Role roleFromDB = roleDao.findById(role.getId());
        Assertions.assertNotNull(roleFromDB);
        Assertions.assertNotNull(roleFromDB.getId());
        Assertions.assertEquals(ROLE_NAME, roleFromDB.getName(),"Role name is not equals");
    }

    @Test
    void shouldUpdateRoleInDatabase() throws DaoException {
        Role role = MockUtils.createRole();
        Dao<Role> roleDao = new RoleDaoImpl();
        roleDao.save(role);
        Role roleToUpdate = Role.builder()
                .id(role.getId())
                .name(UPDATE_ROLE_NAME)
                .build();
        roleDao.update(roleToUpdate);
        Role roleFromDB = roleDao.findById(roleToUpdate.getId());
        Assertions.assertEquals(roleToUpdate, roleFromDB);
    }

    @Test
    void shouldDeleteRoleFromDatabase() throws DaoException {
        Role role = MockUtils.createRole();
        Dao<Role> roleDao = new RoleDaoImpl();
        roleDao.save(role);
        roleDao.delete(role.getId());
        Role roleFromDB = roleDao.findById(role.getId());
        Assertions.assertNull(roleFromDB);
    }

    @Test
    void shouldFindRoleInDatabaseByName() throws DaoException {
        Role role = MockUtils.createRole();
        Dao<Role> roleDao = new RoleDaoImpl();
        roleDao.save(role);
        List<Role> listFromDB = roleDao.findByName(ROLE_NAME);
        Assertions.assertTrue(listFromDB.contains(role));
    }

    @Test
    void shouldFindAllRolesInDatabase() throws DaoException {
        Role role = MockUtils.createRole();
        Role role2 = Role.builder()
                .name(ROLE_ADMIN)
                .build();
        Dao<Role> roleDao = new RoleDaoImpl();
        roleDao.save(role);
        roleDao.save(role2);
        List<Role> listFromDB = roleDao.findAll();
        Assertions.assertTrue(listFromDB.contains(role));
        Assertions.assertTrue(listFromDB.contains(role2));
    }
}