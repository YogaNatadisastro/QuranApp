package com.example.al_quranapps.retroFIt;

import com.example.al_quranapps.model.AyatModel.Response;
import com.example.al_quranapps.model.SurahModel.Chapters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiEndpoint {
    @GET("chapters?language=id")
    Call<Chapters> getSurah();

    @GET("quran/verses/uthmani")
    Call<Response> getAyat(@Query("chapter_number")int d);

    @GET("chapters/1/info?language=id")
    Call<Response> getText(@Query("chapter_info")int d);
}
