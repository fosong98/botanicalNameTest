package com.botanicalNameTest.repository.dao;

import com.botanicalNameTest.model.domain.Plant;

import java.util.List;
import java.util.Optional;

public interface PlantDao {
    public boolean add(Plant plant);
    public int update(Plant plant);
    public int delete(String plantKorName);

    public Optional<Plant> findByKorName(String plantKorName);
    public List<Plant> findByFamilyName(String familyName);

    public void deleteAll();
}
