package com.botanicalNameTest.model.domain;

import java.util.ArrayList;
import java.util.List;

public class PlantTable {
    private String userId;
    private String tableName;
    private List<Plant> plants;

    public PlantTable(String userId, String tableName) {
        this.tableName = tableName;
        this.userId = userId;
        this.plants = new ArrayList<>();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < plants.size(); ++i) {
            stringBuilder.append(plants.get(i));

            if (i != plants.size() - 1) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }
}
