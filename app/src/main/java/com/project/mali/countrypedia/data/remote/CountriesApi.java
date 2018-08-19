package com.project.mali.countrypedia.data.remote;

import com.project.mali.countrypedia.model.Country;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface CountriesApi {

    @GET
    Single<List<Country>> allCountries(@Url String url);

}
