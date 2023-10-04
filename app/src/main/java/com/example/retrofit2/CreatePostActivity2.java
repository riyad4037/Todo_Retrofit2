package com.example.retrofit2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostActivity2 extends AppCompatActivity {

    EditText createUserIdEditText, createTitleEditText, createBodyEditText;

    Button createPostButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post2);

        createPostButton = findViewById(R.id.CreatePostButton);
        createUserIdEditText = findViewById(R.id.UserIdPost);
        createTitleEditText = findViewById(R.id.TitlePost);
        createBodyEditText = findViewById(R.id.BodyPost);

        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    PostModel postModel = new PostModel(Integer.parseInt(createUserIdEditText.getText().toString()),createTitleEditText.getText().toString(), createBodyEditText.getText().toString());
                    createPost(postModel);
                } catch (Exception e){
                    Toast.makeText(CreatePostActivity2.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void createPost(PostModel post) {
        JsonPlaceHolder jsonPlaceHolder = RetrofitOperation.getInstance().create(JsonPlaceHolder.class);

        Call<PostModel> call = jsonPlaceHolder.createPost(post);

        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(CreatePostActivity2.this, "Error! Code: "+response.code(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(CreatePostActivity2.this, "Successfully Created!! Code: "+response.code(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CreatePostActivity2.this, MainActivity.class));
            }
            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {

                Toast.makeText(CreatePostActivity2.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}