package com.project.mali.countrypedia.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.project.mali.countrypedia.data.local.entity.CountryEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CountryEntity> countries);

    @Query("SELECT * FROM country")
    Single<List<CountryEntity>> loadAllCountries();

}
