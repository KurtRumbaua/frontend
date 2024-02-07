package com.example.fa31;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fa31.model.Account;
import com.example.fa31.retrofit.AccountApi;
import com.example.fa31.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registerActivity extends AppCompatActivity {
    EditText user;
    EditText pass;
    Button btn;
    Button create;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_registration);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.password);
        create = (Button) findViewById(R.id.signinButton);
        Button ret = (Button) findViewById(R.id.returnButton);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitService retrofitService = new RetrofitService();
                AccountApi accountApi = retrofitService.getRetrofit().create(AccountApi.class);

                create.setOnClickListener(view ->{
                    String AUsername, APassword, Privilege;

                    AUsername = user.getText().toString();
                    APassword = pass.getText().toString();

                    Account account = new Account();

                    account.setUsername(AUsername);
                    account.setPassword(APassword);
                    account.setPrivilege("user");


                    accountApi.save(account)
                            .enqueue(new Callback<Account>() {
                                @Override
                                public void onResponse(Call<Account> call, Response<Account> response) {
                                    Toast.makeText(registerActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(registerActivity.this, loginActivity.class);

                                    startActivity(i);
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<Account> call, Throwable t) {
                                    Toast.makeText(registerActivity.this,"Save Failed!", Toast.LENGTH_SHORT).show();
                                    Logger.getLogger(loginActivity.class.getName()).log(Level.SEVERE,"Error Occurred",t);
                                }
                            });
                });
            }
        });

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(registerActivity.this, loginActivity.class);

                startActivity(i);
                finish();

            }
        });
    }
}
