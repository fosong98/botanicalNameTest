package com.botanicalNameTest.repository.dao;

import com.botanicalNameTest.model.domain.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlantDaoJdbc implements PlantDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public PlantDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(Plant plant) {
        int count = jdbcTemplate.queryForObject("select count(*) from Plants where plantId = ?", Integer.class, plant.getPlantId());
        if (count > 0) {
            return false;
        } else {
            count = jdbcTemplate.update("insert into plants" +
                    "(plantId, plantKorName, familyName, familyKorName, genusName, genusKorName, botanicalName, imgUrl) values(" +
                    "?, ?, ?, ?, ?, ?, ?, ?)",
                    plant.getPlantId(),
                    plant.getKorName(),
                    plant.getFamilyName(),
                    plant.getFamilyKorName(),
                    plant.getGenusName(),
                    plant.getGenusKorName(),
                    plant.getBotanicalName(),
                    plant.getImgUrl());
        }
        return count > 0;
    }

    @Override
    public int update(Plant plant) {
        int count = jdbcTemplate.queryForObject("select count(*) from Plants where plantId = ?", Integer.class, plant.getPlantId());
        if (count == 0) {
            return 0;
        } else {
            count = jdbcTemplate.update("update plants set " +
                    "familyName = ?, " +
                    "familyKorName = ?, " +
                    "genusName = ?, " +
                    "genusKorName = ?, " +
                    "botanicalName = ?, " +
                    "imgUrl = ? where plantKorName = ?",
                    plant.getFamilyName(),
                    plant.getFamilyKorName(),
                    plant.getGenusName(),
                    plant.getGenusKorName(),
                    plant.getBotanicalName(),
                    plant.getImgUrl(),
                    plant.getKorName());
            return count;
        }

    }

    @Override
    public int delete(String plantKorName) {
        int count = jdbcTemplate.queryForObject("select count(*) from Plants where plantKorName = ?", Integer.class, plantKorName);
        if (count == 0) {
            return 0;
        } else {
            count = jdbcTemplate.update("delete from plants where plantKorName = ?", plantKorName);
            return count;
        }
    }

    @Override
    public Optional<Plant> findByKorName(String plantKorName) {
        List<Plant> plant = jdbcTemplate.query(
                "select * from plants where plantKorName = ?",
                (rs, count) -> {
                    return new Plant(
                            rs.getInt("plantId"),
                            rs.getString("plantKorName"),
                            rs.getString("familyName"),
                            rs.getString("familyKorName"),
                            rs.getString("genusName"),
                            rs.getString("genusKorName"),
                            rs.getString("BotanicalName"),
                            rs.getString("imgUrl")
                    );
                }, plantKorName);

        if (plant.size() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(plant.get(0));
        }
    }

    @Override
    public List<Plant> findByFamilyName(String familyName) {
        return jdbcTemplate.query(
                "select * from plants where familyName = ?",
                (rs, count) -> {
                    return new Plant(
                            rs.getInt("plantId"),
                            rs.getString("plantKorName"),
                            rs.getString("familyName"),
                            rs.getString("familyKorName"),
                            rs.getString("genusName"),
                            rs.getString("genusKorName"),
                            rs.getString("BotanicalName"),
                            rs.getString("imgUrl")
                    );
                },
                familyName
        );
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(
                "delete from plants"
        );
    }
}
