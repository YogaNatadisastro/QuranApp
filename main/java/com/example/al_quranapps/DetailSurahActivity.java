package com.example.al_quranapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.al_quranapps.model.AyatModel.Response;
import com.example.al_quranapps.model.AyatModel.VersesItem;
import com.example.al_quranapps.model.InfoModel.ChapterInfo;
import com.example.al_quranapps.model.InfoModel.Info;
import com.example.al_quranapps.retroFIt.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class DetailSurahActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterAyats adapterAyats;

    private AdapterInfo adapterInfo;

    private List<VersesItem> results = new ArrayList<>();

    private List<ChapterInfo> result = new ArrayList<>();

    TextView textViewInfoSurah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);

        int id = getIntent().getIntExtra("id", 1);

        setUpView();
        setUpRecyclerView();
        System.out.println(id);
        getDataFromApi(id);
    }



    private void setUpRecyclerView() {
        adapterAyats = new AdapterAyats(results);
        adapterInfo = new AdapterInfo(result);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterInfo);
        recyclerView.setAdapter(adapterAyats);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerViewAyat);
    }

    private void getDataFromApi(int id) {
        ApiService.endpoint().getAyat(id).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()){
                    List<VersesItem> result = response.body().getVerses();
                    Log.d("Ayat", result.toString());
                    adapterAyats.setData(result);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("Ayat", t.toString());
            }
        });



    }


}