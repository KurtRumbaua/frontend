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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {

    EditText user;
    EditText pass;
    Button btn;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_login);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.password);
        btn = (Button) findViewById(R.id.signinButton);
        create = (Button)  findViewById(R.id.registerButton);

        Button ret = (Button) findViewById(R.id.returnButton);

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(Login.this,MainActivity.class);

                //startActivity(i);
                //finish();

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();

                // Create an instance of Retrofit service
                RetrofitService retrofitService = new RetrofitService();
                AccountApi accountApi = retrofitService.getRetrofit().create(AccountApi.class);

                // Perform the request to get all accounts
                accountApi.getAllAccounts().enqueue(new Callback<List<Account>>() {
                    @Override
                    public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Account> accounts = response.body();
                            boolean found = false;
                            for (Account account : accounts) {
                                if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                                    found = true; // Mark as found
                                    // Determine the privilege and navigate accordingly
                                    if ("admin".equals(account.getPrivilege())) {
                                        Intent adminIntent = new Intent(loginActivity.this, adminActivity.class);
                                        startActivity(adminIntent);
                                    } else if ("user".equals(account.getPrivilege())) {
                                        Intent userIntent = new Intent(loginActivity.this, userActivity.class);
                                        startActivity(userIntent);
                                    }
                                    Toast.makeText(loginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                    break; // Exit the loop as we've found a match
                                }
                            }

                            if (!found) {
                                // No account matched
                                Toast.makeText(loginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(loginActivity.this, "Login failed: Unable to retrieve accounts", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Account>> call, Throwable t) {
                        Toast.makeText(loginActivity.this, "Login failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(loginActivity.this, registerActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

}