package com.project.mali.countrypedia.data.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.project.mali.countrypedia.model.Country;

@Entity(tableName = "country")

public class CountryEntity {

    @PrimaryKey
    @NonNull
    private String name;
    @ColumnInfo
    private String capital;
    @ColumnInfo
    private int population;
    @ColumnInfo
    private String flag;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public CountryEntity(String name, String capital, int population, String flag) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.flag = flag;
    }

    public CountryEntity() {

    }
}
