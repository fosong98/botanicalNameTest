package com.botanicalNameTest.model.domain;


import java.util.Objects;

public class Plant {
    private static int count = 0;
    private int plantId;
    private String korName;
    private String familyName;
    private String familyKorName;
    private String genusName;
    private String genusKorName;
    private String botanicalName;
    private String imgUrl;

    public Plant(int plantId, String korName, String familyName, String familyKorName, String genusName,
                 String genusKorName, String botanicalName, String imgUrl) {
        this.plantId = plantId;
        this.korName = korName;
        this.familyName = familyName;
        this.familyKorName = familyKorName;
        this.genusName = genusName;
        this.genusKorName = genusKorName;
        this.botanicalName = botanicalName;
        this.imgUrl = imgUrl;
    }

    public Plant(String korName, String familyName, String familyKorName, String genusName,
                 String genusKorName, String botanicalName, String imgUrl) {
        this.plantId = count++;
        this.korName = korName;
        this.familyName = familyName;
        this.familyKorName = familyKorName;
        this.genusName = genusName;
        this.genusKorName = genusKorName;
        this.botanicalName = botanicalName;
        this.imgUrl = imgUrl;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Plant.count = count;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }


    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyKorName() {
        return familyKorName;
    }

    public void setFamilyKorName(String familyKorName) {
        this.familyKorName = familyKorName;
    }


    public String getGenusName() {
        return genusName;
    }

    public void setGenusName(String genusName) {
        this.genusName = genusName;
    }

    public String getGenusKorName() {
        return genusKorName;
    }

    public void setGenusKorName(String genusKorName) {
        this.genusKorName = genusKorName;
    }


    public String getBotanicalName() {
        return botanicalName;
    }

    public void setBotanicalName(String botanicalName) {
        this.botanicalName = botanicalName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "{" +
                "plantId: " + plantId +
                ", korName: '" + korName + '\'' +
                ", familyName: '" + familyName + '\'' +
                ", familyKorName: '" + familyKorName + '\'' +
                ", genusName: '" + genusName + '\'' +
                ", genusKorName: '" + genusKorName + '\'' +
                ", botanicalName: '" + botanicalName + '\'' +
                ", imgUrl: '" + imgUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return plantId == plant.plantId && korName.equals(plant.korName) && familyName.equals(plant.familyName) && familyKorName.equals(plant.familyKorName) && genusName.equals(plant.genusName) && genusKorName.equals(plant.genusKorName) && botanicalName.equals(plant.botanicalName) && Objects.equals(imgUrl, plant.imgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plantId, korName, familyName, familyKorName, genusName, genusKorName, botanicalName, imgUrl);
    }
}
