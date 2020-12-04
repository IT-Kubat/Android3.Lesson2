package com.example.android3lesson2.data.network;

import com.example.android3lesson2.data.models.Films;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GhibliApi {

    @GET("films/{filmId}")
    Call<Films> getFilm(
            @Path("filmId") String filmId
    );

    @GET("films")
    Call<List<Films>> getFilmList();

}
