package com.example.api.ramsha.translator.avtivities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.ramsha.translator.R;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout translatedTextArea;
    ImageButton camera, mike, translate, volume, sound;
    ImageView cancel,swap;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView languageFrom, languageTo, translatedText;
    EditText textToBeTranslated;
    ImageButton inTranslatedMenu;
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    private static final int SPEECH_REQUEST_CODE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSHaredPref();
        toolbar = findViewById(R.id.toolbar);
        languageFrom = findViewById(R.id.leftLanguage);
        languageTo = findViewById(R.id.rightLanguage);
        camera = findViewById(R.id.camera);
        swap=findViewById(R.id.swapLanguage);
        translatedTextArea = findViewById(R.id.translatedTextArea);
        mike = findViewById(R.id.mike);
        sound = findViewById(R.id.sound);
        translate = findViewById(R.id.convert);
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
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String left = languageFrom.getText().toString();
                languageFrom.setText(languageTo.getText());
                languageTo.setText(left);
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
                translatedTextArea.setVisibility(View.VISIBLE);
                translatedText.setText(textToBeTranslated.getText());
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
                }
                if(menuItemid == R.id.conversation)
                {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent i = new Intent(MainActivity.this,Conversation.class);
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
                languageFrom.setText(lang);
                editor.putString("LEFTNAME", lang);
                editor.apply();
            } else if (requestCode == 3) {
                String lang = data.getStringExtra("country");
                languageTo.setText(lang);
                editor.putString("RIGHTNAME", lang);
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
        String lName = sharedPreference.getString("LEFTNAME", "");
        String rName = sharedPreference.getString("RIGHTNAME", "");
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


}