package com.project.mali.countrypedia.data.local;

import com.project.mali.countrypedia.data.CountryDataSource;
import com.project.mali.countrypedia.data.local.entity.CountryEntity;

import java.util.List;

public interface ICountryCacheManager extends CountryDataSource {

    void insertAllCountry(List<CountryEntity> countryEntity);
}
