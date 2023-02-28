package com.example.api.ramsha.translator.avtivities;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

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
import android.widget.Toast;


import com.example.api.ramsha.translator.R;
import com.example.api.ramsha.translator.adapter.ConversationAdapter;
import com.example.api.ramsha.translator.adapter.LanguageListAdapter;
import com.example.api.ramsha.translator.api.RetrofitClient;
import com.example.api.ramsha.translator.db.LanguageDBClass;
import com.example.api.ramsha.translator.db.LanguageEntity;
import com.example.api.ramsha.translator.models.ConversationModel;
import com.example.api.ramsha.translator.models.LanguageModel;
import com.google.gson.JsonArray;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Conversation extends AppCompatActivity {
    Toolbar toolbar;
    TextView clearButton;
    ImageView left, right,leftMike,rightMike,swapConvo;
    TextView _left, _right;
    RecyclerView RV;
    int type;
    ConversationAdapter rvAdapter = new ConversationAdapter();
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    private static final int SPEECH_REQUEST_CODE = 0;
    private static final int SPEECH_REQUEST_CODE2 = 1;
    String speech = null;
    String input,output;
    String result;
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
                String langCode=data.getStringExtra("languageCode");
                _left.setText(lang);
                editor.putString("LNAME", lang);
                editor.putString("LNAMECODE",langCode);
                editor.apply();
            } else if (requestCode == 3) {
                String lang = data.getStringExtra("country");
                String langCode=data.getStringExtra("languageCode");
                _right.setText(lang);
                editor.putString("RNAME", lang);
                editor.putString("RNAMECODE",langCode);
                editor.apply();
            } else if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
                List<String> results = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                String spokenText = results.get(0);
                String leftCode = sharedPreference.getString("LNAMECODE", "English");
                String rightCode = sharedPreference.getString("RNAMECODE", "Urdu");
                type=1;
                getTranslation(spokenText,leftCode,rightCode);


                // Do something with spokenText.
            } else if (requestCode == SPEECH_REQUEST_CODE2 && resultCode == RESULT_OK) {
                List<String> results = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
              //  input = results.get(0);
                String spokenText = results.get(0);
                String leftCode = sharedPreference.getString("LNAMECODE", "English");
                String rightCode = sharedPreference.getString("RNAMECODE", "Urdu");
                type=2;
                getTranslation(spokenText,rightCode,leftCode);

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
    private void getTranslation(String inputWord, String inputLanguageCode, String targetLanguageCode) {
        RetrofitClient retro = new RetrofitClient();
        Call<JsonArray> translate = retro.getTranslationInterface().getTrans(inputLanguageCode, targetLanguageCode, inputWord);
        StringBuilder sb = new StringBuilder();
        final String[] finalResult = new String[1];
        translate.enqueue(new Callback<JsonArray>() {

             @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                try {
                    JsonArray langArray = response.body();
                    JsonArray array2 = (JsonArray) langArray.get(0);
                    for (int i = 0; i < array2.size(); i++) {
                        JsonArray parseResult = (JsonArray) array2.get(i);
                        result = parseResult.get(0).getAsString();
                        if (!result.contains("null")) {
                            sb.append(result);
                        }
                        else{
                            Toast.makeText(Conversation.this, "hiiiii" , Toast.LENGTH_LONG).show();
                        }
                    }
                    if (speech.equals("leftSpeech")){
                        convoList.add(new ConversationModel(1,inputWord,sb.toString()));
                    clearButton.setVisibility(View.VISIBLE);
                    rvAdapter.setData(convoList);}
                    else if (speech.equals("rightSpeech")) {
                        convoList.add(new ConversationModel(0,inputWord,sb.toString()));
                        clearButton.setVisibility(View.VISIBLE);
                        rvAdapter.setData(convoList);
                    }
                    //addToDb();
                } catch (Exception e) {
                    Toast.makeText(Conversation.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(Conversation.this, "fail", Toast.LENGTH_SHORT).show();
            }

        });
    }
}