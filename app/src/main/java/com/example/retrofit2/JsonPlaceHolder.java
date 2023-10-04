package com.example.retrofit2;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolder {

    @GET("posts")
    Call<List<PostModel>> getPosts(@Query("userId") Integer UserId, @Query("_sort") String sort, @Query("_order") String order);

    @GET("posts")
    Call<PostModel> getPost(@Query("id") int id);

    @GET("posts")
    Call<List<PostModel>> getPosts(
            @QueryMap Map<String, String> parameters
            );

    @GET("posts/{id}")
    Call<PostModel> getPostById(@Path("id") int identity);

//    @GET("posts/{id}/comments")
//    Call<List<CommentsModel>> getComments(@Path("id") int PostId);

    @GET
    Call<List<PostModel>> getPosts(@Url String url);

    @POST("posts")
    Call<PostModel> createPost(@Body PostModel post);

    @FormUrlEncoded
    @POST("posts")
    Call<PostModel> createPost(
            @Field("userId") int UserId,
            @Field("title") String title,
            @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<PostModel> createPost(@FieldMap Map<String, String> fields);

    @PUT("posts/{id}")
    Call<PostModel> putPost(@Path("id") int id, @Body PostModel post);

    @PATCH("posts/{id}")
    Call<PostModel> patchPost(@Path("id") int id, @Body PostModel post);


    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

}
