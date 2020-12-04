package com.example.android3lesson2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.android3lesson2.adapter.FilmAdapter;
import com.example.android3lesson2.data.Car;
import com.example.android3lesson2.data.OnItemClickListener;
import com.example.android3lesson2.data.models.Films;
import com.example.android3lesson2.data.network.GhibliService;
import com.example.android3lesson2.ui.detail.DescriptionActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    FilmAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_films);
        adapter = new FilmAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        String s1 = "Geektech";
//        String s2 = "Geektech";
//        boolean res = s1 == s2;
        Car audi1 = new Car("audi", 23);
        Car audi2 = new Car("audi", 23);
        boolean res = audi1.equals(audi2);
        Log.d("tag", String.valueOf(res));


        GhibliService.getInstance().getFilmList().enqueue(new Callback<List<Films>>() {
            @Override
            public void onResponse(@NonNull Call<List<Films>> call, @NonNull Response<List<Films>> response) {
                Log.d("TAG", "onResponse: " + response.body());
                adapter.addFilm(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Films>> call, @NonNull Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });

//        GhibliService.getInstance().getFilm("2baf70d1-42bb-4437-b551-e5fed5a87abe").enqueue(
//                new Callback<Films>() {
//                    @Override
//                    public void onResponse(@NonNull Call<Films> call, @NonNull Response<Films> response) {
//                        if (response.isSuccessful() && response.body() != null) {
//                            Films film = response.body();
//                            Log.d("tag", film.getTitle());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(@NonNull Call<Films> call, @NonNull Throwable t) {
//
//                        Log.d("tag", "Failure" + "" + t.getLocalizedMessage());
//
//                    }
//                }
//        );

    }


    @Override
    public void onClick(String id) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);

    }
}
