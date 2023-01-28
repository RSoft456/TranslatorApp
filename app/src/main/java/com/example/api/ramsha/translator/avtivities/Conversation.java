package com.example.api.ramsha.translator.avtivities;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.api.ramsha.translator.R;
import com.example.api.ramsha.translator.adapter.ConversationAdapter;
import com.example.api.ramsha.translator.adapter.LanguageListAdapter;
import com.example.api.ramsha.translator.models.ConversationModel;
import com.example.api.ramsha.translator.models.LanguageModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Conversation extends AppCompatActivity {
    Toolbar toolbar;
    TextView clearButton;
    ImageView left, right,leftMike,rightMike,swapConvo;
    TextView _left, _right;
    RecyclerView RV;
    ConversationAdapter rvAdapter = new ConversationAdapter();
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    private static final int SPEECH_REQUEST_CODE = 0;
    private static final int SPEECH_REQUEST_CODE2 = 1;
    String speech = null;
    ArrayList<ConversationModel> convoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSHaredPref();
        Intent i = new Intent(Conversation.this, LanguagesList.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        leftMike=findViewById(R.id.leftConvoMike);
        rightMike=findViewById(R.id.rightConvoMike);
        toolbar = findViewById(R.id.toolbarConversation);
        clearButton = findViewById(R.id.chatClearButton);
        left = findViewById(R.id.leftLanguageDropdown);
        RV=findViewById(R.id.conversationRecyclerView);
        right = findViewById(R.id.rightLanguageDropdown);
        _left = findViewById(R.id.leftLanguage);
        swapConvo=findViewById(R.id.swapConvo);
        _right = findViewById(R.id.rightLanguage);
clearButton.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        swapConvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String left = _left.getText().toString();
                _left.setText(_right.getText());
                _right.setText(left);
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        leftMike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speech = "leftSpeech";
                displaySpeechRecognizer();
            }
        });
        rightMike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speech = "rightSpeech";
                displaySpeechRecognizer();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("whichLanguage", "FROM");
                startActivityForResult(i, 2);
            }
        });
        _left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("whichLanguage", "FROM");
                startActivityForResult(i, 2);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("whichLanguage", "TO");
                startActivityForResult(i, 3);
            }
        });
        _right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("whichLanguage", "TO");
                startActivityForResult(i, 3);
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Conversation.this);
                builder1.setMessage("Delete conversation?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                clearButton.setVisibility(View.GONE);
                                convoList.clear();
                                rvAdapter.setData(convoList);
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
        getSharedPre();
        RV.setAdapter(rvAdapter);
        RV.setLayoutManager(new LinearLayoutManager(RV.getContext()));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.conversation_menu, menu);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == 2) {
                String lang = data.getStringExtra("country");
                _left.setText(lang);
                editor.putString("LNAME", lang);
                editor.apply();
            } else if (requestCode == 3) {
                String lang = data.getStringExtra("country");
                _right.setText(lang);
                editor.putString("RNAME", lang);
                editor.apply();
            } else if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
                List<String> results = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                String spokenText = results.get(0);
                convoList.add(new ConversationModel(1,spokenText,spokenText));
                clearButton.setVisibility(View.VISIBLE);
                rvAdapter.setData(convoList);
                // Do something with spokenText.
            } else if (requestCode == SPEECH_REQUEST_CODE2 && resultCode == RESULT_OK) {
                List<String> results = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                String spokenText = results.get(0);
                convoList.add(new ConversationModel(0,spokenText,spokenText));
                clearButton.setVisibility(View.VISIBLE);
                rvAdapter.setData(convoList);
                // Do something with spokenText.
            }


        }

    }

    private void getSharedPre() {
        String leftName = sharedPreference.getString("LNAME", "English");
        String rightName = sharedPreference.getString("RNAME", "Urdu");
        _left.setText(leftName);
        _right.setText(rightName);

    }

    private void initSHaredPref() {
        sharedPreference = getSharedPreferences("my_Pref", MODE_PRIVATE);
        editor = sharedPreference.edit();

    }


    private void displaySpeechRecognizer() {
        Log.d(TAG, "displaySpeechRecognizer: ");
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
// This starts the activity and populates the intent with the speech text.
        if (speech.equals("leftSpeech"))
            startActivityForResult(intent, SPEECH_REQUEST_CODE);
        else if (speech.equals("rightSpeech"))
            startActivityForResult(intent, SPEECH_REQUEST_CODE2);

    }

}