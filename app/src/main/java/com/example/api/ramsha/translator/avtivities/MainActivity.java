package com.example.api.ramsha.translator.avtivities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.ramsha.translator.R;
import com.example.api.ramsha.translator.api.RetrofitClient;
import com.example.api.ramsha.translator.db.LanguageDBClass;
import com.example.api.ramsha.translator.db.LanguageEntity;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout translatedTextArea;
    ImageButton camera, mike, translate, volume, sound;
    ImageView cancel, swap;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView languageFrom, languageTo, translatedText, translatedTextLang;
    EditText textToBeTranslated;
    ImageButton inTranslatedMenu;
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    ProgressBar progressBar;
    private static final int SPEECH_REQUEST_CODE = 0;
    String lName, rName, lNameCode, rNameCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSHaredPref();
        toolbar = findViewById(R.id.toolbar);
        languageFrom = findViewById(R.id.leftLanguage);
        languageTo = findViewById(R.id.rightLanguage);
        camera = findViewById(R.id.camera);
        progressBar = findViewById(R.id.progressBar);
        swap = findViewById(R.id.swapLanguage);
        translatedTextArea = findViewById(R.id.translatedTextArea);
        mike = findViewById(R.id.mike);
        sound = findViewById(R.id.sound);
        translate = findViewById(R.id.convert);
        translatedTextLang = findViewById(R.id.translatedTextLang);
        volume = findViewById(R.id.volume);
        cancel = findViewById(R.id.cancelButton);
        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.Navigation_menu);
        inTranslatedMenu = findViewById(R.id.inTranslatedMenu);
        textToBeTranslated = findViewById(R.id.textToBeTranslated);
        translatedText = findViewById(R.id.translatedText);
        translate.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);
        volume.setVisibility(View.GONE);
        translatedTextArea.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        Bundle i = getIntent().getExtras();
        if(i != null){
        String check = i.getString("CHECK");
        if (check.equals("true")) {
            String leftlang = i.getString("ILN");
            String rightlang = i.getString("TLN");
            String leftlanagcode = i.getString("ILC");
            String rightlangcode = i.getString("TLC");
            String input = i.getString("IW");
            String output = i.getString("OW");
            editor.putString("LEFTNAME", leftlang);
            editor.putString("LEFTNAMECODE", leftlanagcode);
            editor.putString("RIGHTNAME", rightlang);
            editor.putString("RIGHTNAMECODE", rightlangcode);
            languageFrom.setText(leftlang);
            languageTo.setText(rightlang);
            translatedTextArea.setVisibility(View.VISIBLE);
            textToBeTranslated.setText(input);
            translatedText.setText(output);
            translatedTextLang.setText(rightlang);
        }}

// Create a new drawable for the loader
        Drawable newDrawable = progressBar.getIndeterminateDrawable().mutate();
        newDrawable.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

// Set the new drawable as the loader drawable
        progressBar.setIndeterminateDrawable(newDrawable);
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String left = languageFrom.getText().toString();
                languageFrom.setText(languageTo.getText());
                languageTo.setText(left);
                String temp = translatedText.getText().toString();
                translatedText.setText(textToBeTranslated.getText().toString());
                textToBeTranslated.setText(temp);
                translatedTextLang.setText(languageTo.getText().toString());

            }
        });
        textToBeTranslated.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                translate.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);


                camera.setVisibility(View.GONE);
                mike.setVisibility(View.GONE);

                if (textToBeTranslated.getText().toString().length() == 0) {
                    cancel.setVisibility(View.GONE);
                    camera.setVisibility(View.VISIBLE);
                    mike.setVisibility(View.VISIBLE);
                    translate.setVisibility((View.GONE));
                    translatedTextArea.setVisibility(View.GONE);
                } else {
                    camera.setVisibility(View.GONE);
                    mike.setVisibility(View.GONE);
                    cancel.setVisibility(View.VISIBLE);
                    translate.setVisibility(View.VISIBLE);
                }

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translate.setVisibility(View.GONE);
                textToBeTranslated.getText().clear();
                translatedTextArea.setVisibility(View.GONE);
            }
        });

        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                getTranslation(textToBeTranslated.getText().toString(), "en", "ur");

                //translatedText.setText(textToBeTranslated.getText());
            }
        });
        inTranslatedMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, inTranslatedMenu);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupMenu.getMenu());
                popupMenu.show();
            }
        });
        setSupportActionBar(toolbar);
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
                    Intent i = new Intent(MainActivity.this, Conversation.class);
                    startActivity(i);
                } else if (menuItemid == R.id.history) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent i = new Intent(MainActivity.this, TranslationHistory.class);
                    startActivity(i);
                }
                return false;
            }
        });
        languageFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LanguagesList.class);
                intent.putExtra("whichLanguage", "FROM");
                startActivityForResult(intent, 2);
            }
        });
        languageTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LanguagesList.class);
                intent.putExtra("whichLanguage", "TO");
                startActivityForResult(intent, 3);
            }
        });
        mike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySpeechRecognizer();

            }
        });

        getSharedPre();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == 2) {
                String lang = data.getStringExtra("country");
                String langCode = data.getStringExtra("languageCode");
                languageFrom.setText(lang);
                editor.putString("LEFTNAME", lang);
                editor.putString("LEFTNAMECODE", langCode);
                editor.apply();
            } else if (requestCode == 3) {
                String lang = data.getStringExtra("country");
                String langCode = data.getStringExtra("languageCode");
                languageTo.setText(lang);
                editor.putString("RIGHTNAME", lang);
                editor.putString("RIGHTNAMECODE", langCode);
                editor.apply();
            } else if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
                List<String> results = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                String spokenText = results.get(0);
                textToBeTranslated.setText(spokenText);
                translatedTextArea.setVisibility(View.VISIBLE);
                translatedText.setText(spokenText);
                // Do something with spokenText.
            }

        }

    }

    private void getSharedPre() {

        lName = sharedPreference.getString("LEFTNAME", "English");
        rName = sharedPreference.getString("RIGHTNAME", "Urdu");
        lNameCode = sharedPreference.getString("LEFTNAMECODE", "En");
        rNameCode = sharedPreference.getString("RIGHTNAMECODE", "Ur");
        languageFrom.setText(lName);
        languageTo.setText(rName);

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
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    private void getTranslation(String inputWord, String inputLanguageCode, String targetLanguageCode) {
        RetrofitClient retro = new RetrofitClient();
        Call<JsonArray> translate = retro.getTranslationInterface().getTrans(inputLanguageCode, targetLanguageCode, inputWord);
        StringBuilder sb = new StringBuilder();
        translate.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                try {
                    JsonArray langArray = response.body();
                    JsonArray array2 = (JsonArray) langArray.get(0);
                    for (int i = 0; i < array2.size(); i++) {
                        JsonArray parseResult = (JsonArray) array2.get(i);
                        String result = parseResult.get(0).getAsString();
                        if (!result.contains("null")) {
                            sb.append(result);
                        }
                    }
                    progressBar.setVisibility(View.GONE);
                    translatedTextArea.setVisibility(View.VISIBLE);
                    translatedTextLang.setText(languageTo.getText().toString());
                    translatedText.setText(sb);
                    addToDb();
                } catch (Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addToDb() {
        LanguageEntity translationObject = new LanguageEntity();
        translationObject.inputLanguageCode = lNameCode;
        translationObject.translationLanguageCode = rNameCode;
        translationObject.inputLanguageName = lName;
        translationObject.translationLanguageName = rName;
        translationObject.inputWord = textToBeTranslated.getText().toString();
        translationObject.outputWord = translatedText.getText().toString();
        LanguageDBClass db = Room.databaseBuilder(getApplicationContext(), LanguageDBClass.class, "Translations_Database").allowMainThreadQueries().build();
        db.LanguageDAO().InsertTranslation(translationObject);
    }

}