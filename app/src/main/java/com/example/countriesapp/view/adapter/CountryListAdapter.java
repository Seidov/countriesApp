package com.example.countriesapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countriesapp.R;
import com.example.countriesapp.repository.model.CountryModel;
import com.example.countriesapp.utils.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private List<CountryModel> countryModelList;

    public CountryListAdapter(List<CountryModel> countryModelList) {
        this.countryModelList = countryModelList;
    }

    public void updateCountries(List<CountryModel> newCountries){
        countryModelList.clear();
        countryModelList.addAll(newCountries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country,parent,false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(countryModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return countryModelList.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imageView)
        ImageView countryImage;

        @BindView(R.id.name)
        TextView countryName;

        @BindView(R.id.capital)
        TextView countryCapital;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(CountryModel country) {
            countryName.setText(country.getCountryName());
            countryCapital.setText(country.getCapital());
            Util.loadImage(countryImage, country.getFlag(), Util.getProgressDrawable(countryImage.getContext()));

        }

    }
}


