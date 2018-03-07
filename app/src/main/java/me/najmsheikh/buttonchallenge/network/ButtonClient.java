package me.najmsheikh.buttonchallenge.network;

import java.util.List;

import me.najmsheikh.buttonchallenge.models.Transfer;
import me.najmsheikh.buttonchallenge.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Najm Sheikh <hello@najmsheikh.me> on 3/5/18.
 */

public interface ButtonClient {
    String CANDIDATE = "npcompete";

    @POST("/user")
    Call<User> addUser(@Body User user);

    @GET("/user?candidate=" + CANDIDATE)
    Call<List<User>> getUsers();

    @GET("/user/{id}?candidate=" + CANDIDATE)
    Call<User> getUser(@Path("id") String id);

    @GET("/user/{id}/transfers?candidate=" + CANDIDATE)
    Call<List<Transfer>> getTransfers(@Path("id") String id);

    @DELETE("/user/{id}?candidate=" + CANDIDATE)
    Call<Void> deleteUser(@Path("id") String id);

    @POST("/transfer")
    Call<Transfer> addTransfer(@Body Transfer transfer);

    @GET("/transfer/{id}?candidate=" + CANDIDATE)
    Call<Transfer> getTransferById(@Path("id") String id);
}
