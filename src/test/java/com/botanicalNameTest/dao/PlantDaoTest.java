package com.botanicalNameTest.dao;

import com.botanicalNameTest.model.domain.Plant;
import com.botanicalNameTest.repository.dao.PlantDaoJdbc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlantDaoTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    PlantDaoJdbc plantDao;

    static final List<Plant> coll = List.of(
            new Plant("가", "a", "ㄱ",
                        "aa", "ㄱㄱ", "aaa", null),
            new Plant("나", "b", "ㄴ",
                    "bb", "ㄴㄴ", "bbb", null),
            new Plant("다", "c", "ㄷ",
                    "cc", "ㄷㄷ", "ccc", null),
            new Plant("라", "a", "ㄱ",
                    "dd", "ㄹㄹ", "ddd", null)
    );

    @BeforeEach
    public void setUp() {
        plantDao = new PlantDaoJdbc(jdbcTemplate);
    }

    @Test
    public void addPlantTest() {
        plantDao.add(coll.get(0));
        plantDao.add(coll.get(1));
        plantDao.add(coll.get(2));

        assertEquals(coll.get(0), plantDao.findByKorName(coll.get(0).getKorName()).get());
        assertEquals(coll.get(1), plantDao.findByKorName(coll.get(1).getKorName()).get());
        assertEquals(coll.get(2), plantDao.findByKorName(coll.get(2).getKorName()).get());
    }

    @Test
    public void updatePlantTest() {
        plantDao.add(coll.get(0));
        plantDao.add(coll.get(1));
        plantDao.add(coll.get(2));

        coll.get(1).setFamilyKorName("ㄹ");

        plantDao.update(coll.get(1));

        assertEquals(coll.get(1), plantDao.findByKorName(coll.get(1).getKorName()).get());
    }

    @Test
    public void deletePlantTest() {
        plantDao.add(coll.get(0));
        plantDao.add(coll.get(1));
        plantDao.add(coll.get(2));

        plantDao.delete(coll.get(1).getKorName());

        assertTrue(plantDao.findByKorName(coll.get(1).getKorName()).isEmpty());
    }

    @Test
    public void findByFamilyNameTest() {
        coll.stream().forEach(plantDao::add);

        List<Plant> plants = plantDao.findByFamilyName(coll.get(0).getFamilyName());

        assertEquals(2, plants.size());
        assertTrue(plants.stream().anyMatch(coll.get(0)::equals));
        assertTrue(plants.stream().anyMatch(coll.get(3)::equals));
    }

    @AfterEach
    public void close() {
        plantDao.deleteAll();
    }

    
}


