package com.project.mali.countrypedia.data.local;

import com.project.mali.countrypedia.data.local.dao.CountryDao;
import com.project.mali.countrypedia.data.local.entity.CountryEntity;
import com.project.mali.countrypedia.data.remote.CountriesApi;
import com.project.mali.countrypedia.data.remote.RetrofitHelper;

import java.util.List;

import io.reactivex.Single;

public class CountryCacheMangaer implements ICountryCacheManager {

    public CountryCacheMangaer(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    private CountryDao countryDao;

    @Override
    public void insertAllCountry(List<CountryEntity> countryEntity) {
        countryDao.insertAll(countryEntity);

    }

    @Override
    public Single<List<CountryEntity>> getCountryList() {
        return countryDao.loadAllCountries();
    }
}
