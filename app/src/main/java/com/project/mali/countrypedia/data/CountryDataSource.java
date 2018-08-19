package com.project.mali.countrypedia.data;

import com.project.mali.countrypedia.data.local.entity.CountryEntity;
import com.project.mali.countrypedia.model.Country;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface CountryDataSource {

    Single<List<CountryEntity>> getCountryList();
}
