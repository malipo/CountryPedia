package com.project.mali.countrypedia.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.project.mali.countrypedia.data.CountryRepository;
import com.project.mali.countrypedia.data.local.CountryCacheMangaer;
import com.project.mali.countrypedia.data.local.database.AppDatabase;
import com.project.mali.countrypedia.data.local.entity.CountryEntity;
import com.project.mali.countrypedia.data.remote.CountriesApi;
import com.project.mali.countrypedia.data.remote.CountryRemoteDataSource;
import com.project.mali.countrypedia.data.remote.RetrofitHelper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountryViewModel extends AndroidViewModel {

    private CountryRepository repository;
    public MutableLiveData<List<CountryEntity>> countryEntityMutableLiveData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public CountryViewModel(@NonNull Application application) {
        super(application);

        repository = new CountryRepository(
                new CountryRemoteDataSource(new RetrofitHelper().getService(CountriesApi.class)),
                new CountryCacheMangaer(AppDatabase.getAppDatabase(getApplication()).countryDao()));
    }

    public void getCountries() {

        compositeDisposable.add(repository.getCountryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                        subscribeWith(new DisposableSingleObserver<List<CountryEntity>>() {


                            @Override
                            public void onSuccess(List<CountryEntity> countryEntities) {
                                countryEntityMutableLiveData.setValue(countryEntities);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("error", "error");

                            }
                        }));
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
