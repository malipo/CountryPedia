package com.project.mali.countrypedia.data;

import android.util.Log;

import com.project.mali.countrypedia.CountryApplication;
import com.project.mali.countrypedia.data.local.ICountryCacheManager;
import com.project.mali.countrypedia.data.local.database.AppDatabase;
import com.project.mali.countrypedia.data.local.entity.CountryEntity;


import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

public class CountryRepository implements CountryDataSource {

    private CountryDataSource countryRemoteDataSource;
    private ICountryCacheManager countryCacheMangaer;

    public CountryRepository(CountryDataSource remoteDataSource, ICountryCacheManager casheManager) {
        countryRemoteDataSource = remoteDataSource;
        countryCacheMangaer = casheManager;
    }


    @Override
    public Single<List<CountryEntity>> getCountryList() {
        if (CountryApplication.getInstance().isNetworkConnected()) {
            return countryRemoteDataSource.getCountryList().doAfterSuccess(new Consumer<List<CountryEntity>>() {
                @Override
                public void accept(List<CountryEntity> countryEntityList) throws Exception {
                    countryCacheMangaer.insertAllCountry(countryEntityList);
                }
            });

        } else {

            return countryCacheMangaer.getCountryList();
        }

    }
}