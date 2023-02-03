package com.example.api.ramsha.translator.avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;


import com.example.api.ramsha.translator.R;
import com.example.api.ramsha.translator.adapter.HistoryAdapter;
import com.example.api.ramsha.translator.adapter.LanguageListAdapter;
import com.example.api.ramsha.translator.db.LanguageDBClass;
import com.example.api.ramsha.translator.db.LanguageEntity;
import com.example.api.ramsha.translator.models.LanguageModel;

import java.util.ArrayList;
import java.util.List;

public class TranslationHistory extends AppCompatActivity {
    Toolbar toolbar;
    ArrayList<LanguageEntity> translationList = new ArrayList<>();
    RecyclerView RV;
    HistoryAdapter rvAdapter = new HistoryAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation_history);
        toolbar=findViewById(R.id.toolbarTranslation);
        RV=findViewById(R.id.TranslationRV);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        LanguageDBClass db = Room.databaseBuilder(getApplicationContext(),LanguageDBClass.class,"Translations_Database").allowMainThreadQueries().build();
        translationList = (ArrayList<LanguageEntity>) db.LanguageDAO().getall();
        RV.setAdapter(rvAdapter);
        RV.setLayoutManager(new LinearLayoutManager(RV.getContext()));
        rvAdapter.setData(translationList);
    }
}