package com.project.mali.countrypedia.view;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.arch.lifecycle.ViewModelProviders;

import com.project.mali.countrypedia.R;
import com.project.mali.countrypedia.data.local.entity.CountryEntity;
import com.project.mali.countrypedia.viewmodel.CountryViewModel;

import java.util.List;

public class CountryListFragment extends Fragment {

    public static final String TAG = "CountryListFragment";
    private CountryAdapter countryAdapter;
    private RecyclerView recyclerView;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_countrylist, container, false);

        }
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final CountryViewModel viewModel =
                ViewModelProviders.of(this).get(CountryViewModel.class);
        viewModel.getCountries();
        setupViews(viewModel);

    }

    private void setupViews(CountryViewModel countryViewModel) {
        recyclerView = rootView.findViewById(R.id.rv_countryList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


        countryViewModel.countryEntityMutableLiveData.observe(this, new Observer<List<CountryEntity>>() {
            @Override
            public void onChanged(@Nullable List<CountryEntity> countryEntities) {
                Log.e("country", countryEntities.size() + "");

                countryAdapter = new CountryAdapter(getContext(), countryEntities, new CountryAdapter.OnCountryClick() {
                    @Override
                    public void onClick(int position) {
                        Toast.makeText(getActivity(), "The population is" + countryEntities.get(position).getPopulation(),
                                Toast.LENGTH_LONG).show();
                    }
                });

                recyclerView.setAdapter(countryAdapter);
                countryAdapter.notifyDataSetChanged();
            }
        });
    }

    public static CountryListFragment newInstance() {
        Bundle args = new Bundle();
        CountryListFragment fragment = new CountryListFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
