package com.example.android3lesson2.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android3lesson2.R;
import com.example.android3lesson2.data.models.Films;
import com.example.android3lesson2.data.network.GhibliService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionActivity extends AppCompatActivity {

        TextView tv_title, tv_desc, tv_director;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        tv_title = findViewById(R.id.tv_title);
        tv_desc = findViewById(R.id.tv_desc);
        tv_director = findViewById(R.id.tv_director);

        if (getIntent() != null){
            String id = getIntent().getStringExtra("id");
            GhibliService.getInstance().getFilm(id).enqueue(new Callback<Films>() {
                @Override
                public void onResponse(Call<Films> call, Response<Films> response) {
                    Log.d("tag", response.body()+"");
                    setData(response.body());
                }

                @Override
                public void onFailure(Call<Films> call, Throwable t) {
                    Log.d("tag", t.getMessage());                }
            });
        }
    }

    private void setData(Films film) {

        tv_title.setText(film.getTitle());
        tv_desc.setText(film.getDescription());
        tv_director.setText(film.getDirector());
    }
}