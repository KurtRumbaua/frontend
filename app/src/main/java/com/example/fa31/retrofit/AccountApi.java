package com.example.fa31.retrofit;

import com.example.fa31.model.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AccountApi {

    @GET("/api/accounts")
        //@GET("/")
    Call<List<Account>> getAllAccounts();

    @POST("/api/add-account")
    Call <Account> save (@Body Account account);

    @PUT("/api/update-account/{id}")
    Call<Account> updateAccount(@Path("id") long id, @Body Account account);

    @DELETE("/api/delete-account/{id}")
    Call<Void> deleteAccount(@Path("id") long id);
}
