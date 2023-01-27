package com.example.api.ramsha.translator.api;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.api.ramsha.translator.R;
import com.example.api.ramsha.translator.api.apicalls.RetrofitClient;
import com.example.api.ramsha.translator.api.apicalls.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiActivity extends AppCompatActivity {

    Button btn;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_integration);
        btn = findViewById(R.id.Button);
        text = findViewById(R.id.text);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllUsers();
            }
        });
    }

    public void getAllUsers() {
        RetrofitClient retro = new RetrofitClient();
        Call<Users> userDetail = retro.getUserService().getUserDetail(1);
        userDetail.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Users user = response.body();
                text.setText(user.getName());
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(ApiActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
//        Call<List<Users>> userCall = retro.getUserService().getAllUsers();
//        userCall.enqueue(new Callback<List<Users>>() {
//            @Override
//            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
//                if(response!=null){
//                    List<Users> dataList = response.body();
////                for(int i =0 ; i < dataList.size();i++){
////
////                }
//                    if(!dataList.isEmpty()){
//                        String data = dataList.get(0).getName();
//                        text.setText(data);
//                    }
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Users>> call, Throwable t) {
//                //if response is not successful
//
//            }
//        });
    }
}