package com.botanicalNameTest.repository.dao;

import com.botanicalNameTest.model.domain.Plant;
import com.botanicalNameTest.model.domain.PlantTable;
import com.botanicalNameTest.model.domain.User;

import java.util.List;

public interface PlantTableDAO {
    public boolean createTable(String userId, String tableName);

    public boolean deleteTable(String tableName);

    public boolean insertPlant(String tableName, Plant plant);

    public boolean deletePlant(String tableName, Plant plant);

    public List<PlantTable> getTables(String userId);

    public List<PlantTable> getAllTables();

    public boolean deleteTables(String userId);

    public boolean deleteAllTables();
}
