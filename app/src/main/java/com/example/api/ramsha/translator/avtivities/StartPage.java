package com.example.api.ramsha.translator.avtivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.api.ramsha.translator.R;
import com.google.android.material.navigation.NavigationView;

public class StartPage extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
NavigationView navigationView;
View translate,conversation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        toolbar=findViewById(R.id.toolbar2);
        drawerLayout = findViewById(R.id.DrawerLayout2);
        navigationView = findViewById(R.id.Navigation_menu1);
translate=findViewById(R.id.TranslateModule);
conversation=findViewById(R.id.ConversationModule);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuItemid = item.getItemId();
                if (menuItemid == R.id.clipboard) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItemid == R.id.conversation) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent i = new Intent(StartPage.this, Conversation.class);
                    startActivity(i);
                } else if (menuItemid == R.id.translationHistory) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent i = new Intent(StartPage.this, TranslationHistory.class);
                    startActivity(i);
                }
                return false;
            }
        });
        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StartPage.this, MainActivity.class);
                startActivity(i);
            }
        });
        conversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StartPage.this, Conversation.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.crown, menu);
        return true;
    }
}