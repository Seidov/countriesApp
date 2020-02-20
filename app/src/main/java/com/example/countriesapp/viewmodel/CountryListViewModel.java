package com.example.countriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countriesapp.repository.remote.CountriesService;
import com.example.countriesapp.repository.model.CountryModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountryListViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<List<CountryModel>>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> countryLoading = new MutableLiveData<Boolean>();

    private CountriesService countriesService = CountriesService.getInstance();
    private CompositeDisposable disposable = new CompositeDisposable();

    public void refresh() {
        fetchCountries();
    }

    private void fetchCountries() {
        countryLoading.setValue(true);
        disposable.add(
                countriesService.getCountries()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<CountryModel>>(){

                            @Override
                            public void onSuccess(List<CountryModel> countryModels) {

                                countries.setValue(countryModels);
                                countryLoadError.setValue(false);
                                countryLoading.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                                countryLoadError.setValue(true);
                                countryLoading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
