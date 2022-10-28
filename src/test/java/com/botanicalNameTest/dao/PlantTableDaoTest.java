package com.botanicalNameTest.dao;

import com.botanicalNameTest.model.domain.Plant;
import com.botanicalNameTest.model.domain.PlantTable;
import com.botanicalNameTest.model.domain.User;
import com.botanicalNameTest.repository.dao.PlantDao;
import com.botanicalNameTest.repository.dao.PlantTableDAO;
import com.botanicalNameTest.repository.dao.UserDao;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlantTableDaoTest {
    @Autowired
    PlantTableDAO plantTableDAO;

    List<PlantTable> plantTableList;
    List<User> userList;
    List<Plant> plantList;

    @MockBean
    PlantDao plantDao;

    @MockBean
    UserDao userDao;

    @BeforeEach
    public void setUp() {
        userList = List.of(
                new User("fosong", "1356"),
                new User("JaeDoo", "1999"),
                new User("test", "ttest")
        );

        plantList = List.of(
                new Plant("가", "a", "ㄱ",
                        "aa", "ㄱㄱ", "aaa", null),
                new Plant("나", "b", "ㄴ",
                        "bb", "ㄴㄴ", "bbb", null),
                new Plant("다", "c", "ㄷ",
                        "cc", "ㄷㄷ", "ccc", null),
                new Plant("라", "a", "ㄱ",
                        "dd", "ㄹㄹ", "ddd", null)
        );

        plantTableList = List.of(
                new PlantTable(userList.get(0).getUserId(), "0a"),
                new PlantTable(userList.get(0).getUserId(), "0b"),
                new PlantTable(userList.get(0).getUserId(), "0c"),
                new PlantTable(userList.get(1).getUserId(), "1a"),
                new PlantTable(userList.get(1).getUserId(), "1b")
        );

        given(userDao.add(any(User.class))).willReturn(true);
        given(plantDao.add(any(Plant.class))).willReturn(true);

        userList.stream().forEach((user)->given(userDao.get(user.getUserId())).willReturn(Optional.of(user)));
        plantList.stream().forEach((plant)->given(plantDao.findByKorName(plant.getKorName())).willReturn(Optional.of(plant)));
    }

    @Test
    public void createTableTest() {
        plantTableList.stream().forEach((table)->plantTableDAO.createTable(table.getUserId(), table.getTableName()));

        plantTableDAO.getAllTables().stream().forEach((table)->{
            assertTrue(plantTableList.stream().anyMatch((originTable)->table.getTableName().equals(originTable.getTableName())));
        });
    }

    @Test
    public void deleteTableTest() {
        plantTableList.stream().forEach((table)->plantTableDAO.createTable(table.getUserId(), table.getTableName()));
        plantTableDAO.deleteTable(plantTableList.get(1).getTableName());

        assertTrue(
                plantTableDAO.getAllTables().stream().noneMatch(
                        (table)->plantTableList.get(1).getTableName().equals(table.getTableName())
                    )
                );
    }

    @Test
    public void getTablesTest() {
        plantTableList.stream().forEach((table)->plantTableDAO.createTable(table.getUserId(), table.getTableName()));

        plantTableList.stream().distinct().forEach((table1)->{
            List<PlantTable> userTableList = plantTableDAO.getTables(table1.getUserId());

            assertEquals(plantTableList.stream().filter((table2)->table2.getUserId().equals(table2.getUserId())).count(),
                    userTableList.size());

            assertTrue(userTableList.stream().allMatch((table2)->table2.getUserId().equals(table1.getUserId())));
        });
    }

    @Test
    public void deleteTablesTest() {
        plantTableList.stream().forEach((table)->plantTableDAO.createTable(table.getUserId(), table.getTableName()));

        plantTableDAO.deleteTables(plantTableList.get(0).getUserId());

        plantTableDAO.getAllTables().stream().noneMatch((table)->table.getUserId().equals(plantTableList.get(0).getUserId()));
    }

    @Test
    public void plantInsertTest() {
        plantTableList.stream().forEach((table)->plantTableDAO.createTable(table.getUserId(), table.getTableName()));

        plantTableDAO.insertPlant(plantTableList.get(0).getTableName(), plantList.get(1));
        plantTableDAO.insertPlant(plantTableList.get(0).getTableName(), plantList.get(2));
        plantTableDAO.insertPlant(plantTableList.get(0).getTableName(), plantList.get(3));

        plantTableDAO.insertPlant(plantTableList.get(2).getTableName(), plantList.get(3));
        plantTableDAO.insertPlant(plantTableList.get(2).getTableName(), plantList.get(0));


        for (PlantTable table : plantTableDAO.getAllTables()) {
            if (table.getUserId().equals(plantTableList.get(0).getTableName())) {
                assertTrue(table.getPlants().contains(plantList.get(1)));
                assertTrue(table.getPlants().contains(plantList.get(2)));
                assertTrue(table.getPlants().contains(plantList.get(3)));
            } else if (table.getUserId().equals(plantTableList.get(2).getTableName())) {
                assertTrue(table.getPlants().contains(plantList.get(0)));
                assertTrue(table.getPlants().contains(plantList.get(3)));
            } else {
                assertEquals(0, table.getPlants().size());
            }
        }
    }

    @Test
    public void plantDeleteTest() {
        plantTableList.stream().forEach((table)->plantTableDAO.createTable(table.getUserId(), table.getTableName()));

        plantTableDAO.insertPlant(plantTableList.get(0).getTableName(), plantList.get(1));
        plantTableDAO.insertPlant(plantTableList.get(0).getTableName(), plantList.get(2));
        plantTableDAO.insertPlant(plantTableList.get(0).getTableName(), plantList.get(3));

        plantTableDAO.deletePlant(plantTableList.get(0).getTableName(), plantList.get(2));

        assertTrue(
                plantTableDAO.getAllTables().stream()
                .filter((table)->table.getTableName().equals(plantTableList.get(0).getTableName()))
                .findFirst().get()
                .getPlants().stream()
                .noneMatch(plantList.get(2)::equals)
        );
    }

    @AfterEach
    public void cleaning() {
        plantTableDAO.deleteAllTables();
    }

}
