package com.example.api.ramsha.translator.avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.api.ramsha.translator.R;

public class Conversation extends AppCompatActivity {
Toolbar toolbar;
Button clearButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        toolbar=findViewById(R.id.toolbarConversation);
   clearButton=findViewById(R.id.chatClearButton);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}