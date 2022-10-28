package com.botanicalNameTest.repository.dao;

import com.botanicalNameTest.model.domain.Plant;
import com.botanicalNameTest.model.domain.PlantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlantTableDaoJdbc implements PlantTableDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean createTable(String userId, String tableName) {
        return false;
    }

    @Override
    public boolean deleteTable(String tableName) {
        return false;
    }

    @Override
    public boolean insertPlant(String tableName, Plant plant) {
        return false;
    }

    @Override
    public boolean deletePlant(String tableName, Plant plant) {
        return false;
    }

    @Override
    public List<PlantTable> getTables(String userId) {
        return null;
    }

    @Override
    public List<PlantTable> getAllTables() {
        return null;
    }

    @Override
    public boolean deleteTables(String userId) {
        return false;
    }

    @Override
    public boolean deleteAllTables() {
        return false;
    }
}
