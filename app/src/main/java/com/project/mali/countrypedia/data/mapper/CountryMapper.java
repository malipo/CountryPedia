package com.project.mali.countrypedia.data.mapper;

import com.project.mali.countrypedia.data.local.entity.CountryEntity;
import com.project.mali.countrypedia.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryMapper {

    public static List<CountryEntity> transform(List<Country> countries) {

        ArrayList<CountryEntity> countryEntityArrayList = new ArrayList<>();

        for (Country country : countries) {
            CountryEntity countryEntity = new CountryEntity();

            countryEntity.setName(country.getName());
            countryEntity.setCapital(country.getCapital());
            countryEntity.setFlag(country.getFlag());
            countryEntity.setPopulation(country.getPopulation());

            countryEntityArrayList.add(countryEntity);
        }

        return countryEntityArrayList;
    }
}
