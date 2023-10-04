package com.example.retrofit2;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewAdapterForPost extends RecyclerView.Adapter<RecyclerViewHolderClass> {

    Context context;
    List<PostModel> postModelsList;

//    PostOperationActivity postActivity = new PostOperationActivity();

    public RecyclerViewAdapterForPost(Context context, List<PostModel> postModelsList) {
        this.context = context;
        this.postModelsList = postModelsList;
    }

    @NonNull
    @Override
    public RecyclerViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolderClass(LayoutInflater.from(context).inflate(R.layout.recycler_post_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderClass holder, int position) {

        PostModel postModel = postModelsList.get(position);


        holder.contents.setText("UserId: "+postModel.getUserId()+"\n"+"ID: "+postModel.getId()+"\n"+"Title: "+postModel.getTitle()+"\n"+"Body: "+postModel.getBody());

        holder.ButtonDeleteItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    JsonPlaceHolder jsonPlaceHolder = RetrofitOperation.getInstance().create(JsonPlaceHolder.class);

                    Call<Void> call = jsonPlaceHolder.deletePost(postModel.getId());

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(context, "Error!! Code: "+response.code(), Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(context, "Deleted Successfully!! Code: "+response.code(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                            Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }catch (Exception e){
                    Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

        holder.ButtonEditItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("userIdExtra", postModel.getUserId());
                intent.putExtra("idExtra", postModel.getId());
                intent.putExtra("titleExtra", postModel.getTitle());
                intent.putExtra("bodyExtra", postModel.getBody());
                System.out.println(postModel.getId()+"   "+postModel.getUserId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postModelsList.size();
    }
}
