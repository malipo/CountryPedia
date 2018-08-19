package com.project.mali.countrypedia.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.mali.countrypedia.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            CountryListFragment fragment = new CountryListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, CountryListFragment.TAG).commit();
        }
    }
}
