package com.example.retrofit2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity {
    private static final String TAG="UpdateActivity";

    EditText updateUserIdEditText, updateTitleEditText, UpdateBodyEditText;
    TextView updateIdEditText;

    Button UpdatePostButton;

    int id;


    PostModel posting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        int userId;
        String UTitle, UBody;
        Intent intent = getIntent();
        userId = intent.getIntExtra("userIdExtra", 0);
        id = intent.getIntExtra("idExtra", 0);
        UTitle = intent.getStringExtra("titleExtra");
        UBody = intent.getStringExtra("bodyExtra");

        System.out.println(userId +" $ "+ id);

        updateIdEditText = findViewById(R.id.UpdateIdPost);
        updateUserIdEditText = findViewById(R.id.UpdateUserIdPost);
        updateTitleEditText = findViewById(R.id.UpdateTitlePost);
        UpdateBodyEditText = findViewById(R.id.UpdateBodyPost);

        UpdatePostButton = findViewById(R.id.UpdatePostButton);

        try{
            updateUserIdEditText.setText(String.valueOf(userId));
            updateIdEditText.setText(String.valueOf(id));
            updateTitleEditText.setText(UTitle);
            UpdateBodyEditText.setText(UBody);
        }catch (Exception e)
        {
            Toast.makeText(UpdateActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }




        UpdatePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int Uid = Integer.valueOf(updateUserIdEditText.getText().toString());
                posting = new PostModel(Uid, updateTitleEditText.getText().toString(), UpdateBodyEditText.getText().toString());
                try{

                    putOperation(id, posting);

                }catch (Exception e){
                    System.out.println(e.getLocalizedMessage());
                    Log.d(TAG, e.getLocalizedMessage());
                    Toast.makeText(UpdateActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }

                Toast.makeText(UpdateActivity.this, posting.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void putOperation(int identity, PostModel postModel) {
        Log.d(TAG, "putOperation: " + identity + " " + postModel);


        JsonPlaceHolder jsonPlaceHolder = RetrofitOperation.getInstance().create(JsonPlaceHolder.class);

        Call<PostModel> call = jsonPlaceHolder.putPost(identity, postModel);

        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(UpdateActivity.this, "Error!! "+ response.code(), Toast.LENGTH_LONG).show();
                }
                Toast.makeText(UpdateActivity.this, "Updated Successfully!! Code: "+response.code(), Toast.LENGTH_SHORT).show();

                getPost(id);
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Error!! Code: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getPost(int id) {
        JsonPlaceHolder jsonPlaceHolder = RetrofitOperation.getInstance().create(JsonPlaceHolder.class);

        Call<PostModel> call = jsonPlaceHolder.getPost(id);
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(UpdateActivity.this, "Error! Code: "+response.code(), Toast.LENGTH_SHORT).show();
                }
                PostModel model = response.body();

                updateUserIdEditText.setText(String.valueOf(model.getUserId()));
                updateTitleEditText.setText(model.getTitle());
                UpdateBodyEditText.setText(model.getBody());
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {

            }
        });
    }
}