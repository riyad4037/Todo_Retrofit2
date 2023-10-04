package com.example.retrofit2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView postRecyclerView;
    TextView postTitleTextView;
    FloatingActionButton postAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postAddButton = findViewById(R.id.PostAdd);
        postRecyclerView = findViewById(R.id.PostRecyclerView);
        postTitleTextView = findViewById(R.id.PostTitleTextView);

        postAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreatePostActivity2.class));
            }
        });

        getPosts();

    }
    
    public void getPosts(){

        JsonPlaceHolder jsonPlaceHolder = RetrofitOperation.getInstance().create(JsonPlaceHolder.class);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

        Call<List<PostModel>> call = jsonPlaceHolder.getPosts("posts");


        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if(!response.isSuccessful()){
                    postTitleTextView.setText(response.code());
                }

                List<PostModel> posts = response.body();

                postRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                postRecyclerView.setAdapter(new RecyclerViewAdapterForPost(MainActivity.this, posts));

            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                postTitleTextView.setText(t.getLocalizedMessage());
            }
        });
    }
}