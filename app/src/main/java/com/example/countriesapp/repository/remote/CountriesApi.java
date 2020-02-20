package com.example.countriesapp.repository.remote;

import com.example.countriesapp.repository.model.CountryModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CountriesApi {

    @GET("DevTides/countries/master/countriesV2.json")
    Single<List<CountryModel>> getCountries();

}
