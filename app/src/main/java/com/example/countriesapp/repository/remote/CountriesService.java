package com.example.countriesapp.repository.remote;

import com.example.countriesapp.repository.model.CountryModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesService {

    private static final String BASE_URL="https://raw.githubusercontent.com";

    private static CountriesService instance;

    public CountriesApi api=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi.class);

    private CountriesService() {

    }

    public static CountriesService getInstance() {
        if (instance == null) {
            instance = new CountriesService();
        }
        return instance;
    }

    public Single<List<CountryModel>> getCountries() {
        return api.getCountries();
    }

}
