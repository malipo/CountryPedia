package com.project.mali.countrypedia.data.remote;

import com.project.mali.countrypedia.data.CountryDataSource;
import com.project.mali.countrypedia.data.local.entity.CountryEntity;
import com.project.mali.countrypedia.data.mapper.CountryMapper;
import com.project.mali.countrypedia.model.Country;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class CountryRemoteDataSource implements CountryDataSource {

    private CountriesApi countriesApi;

    public CountryRemoteDataSource(CountriesApi countriesApi) {
        this.countriesApi = countriesApi;
        RetrofitHelper retrofitHelper = new RetrofitHelper();
        countriesApi = retrofitHelper.getService(CountriesApi.class);


    }

    @Override
    public Single<List<CountryEntity>> getCountryList() {

        return countriesApi.allCountries(RetrofitHelper.COUNTRY_URL).map(new Function<List<Country>, List<CountryEntity>>() {
            @Override
            public List<CountryEntity> apply(List<Country> countries) throws Exception {
                return CountryMapper.transform(countries);
            }
        });

    }
}
