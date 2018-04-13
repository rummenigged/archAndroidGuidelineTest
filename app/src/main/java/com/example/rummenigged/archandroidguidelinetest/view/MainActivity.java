package com.example.rummenigged.archandroidguidelinetest.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rummenigged.archandroidguidelinetest.R;
import com.example.rummenigged.archandroidguidelinetest.data.AppDatabase;
import com.example.rummenigged.archandroidguidelinetest.data.model.CryptocurrencyRaw;
import com.example.rummenigged.archandroidguidelinetest.data.network.Webservice;
import com.example.rummenigged.archandroidguidelinetest.data.repositoryImpl.NetworkRepository;
import com.example.rummenigged.archandroidguidelinetest.data.repositoryImpl.RetrofitApiCryptocurrencyRepository;
import com.example.rummenigged.archandroidguidelinetest.data.repositoryImpl.RoomCryptocurrencyRepository;
import com.example.rummenigged.archandroidguidelinetest.data.util.CryptocurrencyMapper;
import com.example.rummenigged.archandroidguidelinetest.data.util.Endpoint;
import com.example.rummenigged.archandroidguidelinetest.domain.model.Resource;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvData;
    private FloatingActionButton fabRefresh;
    private MainViewModel viewModel;
    private Context context = this;
    private Observer<Resource<List<CryptocurrencyRaw>>> onCryptocurrencyChangedObserver;
    private Observer<Resource<Boolean>> onNetworkConnectedChangedObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        setUpListeners();
        initViewModel();
        onCryptocurrencyChangedObserver = response -> {
          switch (response.getStatus()){
              case Resource.SUCCESS:
                  String resource = "";
                  for (CryptocurrencyRaw c: response.getData()) {
                      resource += c.getName() + " - " + c.getPriceUsd() + " / ";
                  }
                  tvData.setText(resource);
              break;

              case Resource.ERROR:
                  tvData.setText(response.getMessage());
              break;

              case Resource.LOADING:
                  tvData.setText("Loading...");
              break;
          }
        };

        onNetworkConnectedChangedObserver = isConnected -> {
            if (!isConnected.getData()){
                Toast.makeText(this, isConnected.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        subscribeToCryptocurrenciesChange();
    }

    private void setUpUI(){
        tvData = findViewById(R.id.tv_data);
        fabRefresh = findViewById(R.id.fab_refresh);
    }

    private void setUpListeners(){
        fabRefresh.setOnClickListener(this);
    }

    private void initViewModel(){
        viewModel = ViewModelProviders.of((FragmentActivity) context,
                new MainViewModel.MainViewModelFactory(
                        new RetrofitApiCryptocurrencyRepository(new RoomCryptocurrencyRepository(
                                                                    Room.databaseBuilder(
                                                                                context
                                                                                , AppDatabase.class
                                                                                , "cryptocurrency-db")
                                                                            .fallbackToDestructiveMigration()
                                                                            .build())

                                                                , new Retrofit.Builder()
                                                                    .baseUrl(Endpoint.BASE_URL)
                                                                    .addConverterFactory(GsonConverterFactory.create())
                                                                    .build()
                                                                    .create(Webservice.class)

                                                                , new CryptocurrencyMapper())
                        , new NetworkRepository(context)))
                .get(MainViewModel.class);
    }

    private void subscribeToCryptocurrenciesChange(){
        viewModel.getCryptocurrency().observe(this, onCryptocurrencyChangedObserver);
        viewModel.isConnected().observe(this, onNetworkConnectedChangedObserver);
        viewModel.isConnected();
        viewModel.getCryptocurrency();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_refresh:
                viewModel.refreshCryptocurrency();
                viewModel.isConnected();
            break;
        }
    }
}
