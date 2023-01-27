package com.example.api.ramsha.translator.avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.api.ramsha.translator.R;
import com.example.api.ramsha.translator.adapter.LanguageListAdapter;
import com.example.api.ramsha.translator.models.LanguageModel;

import java.util.ArrayList;

public class LanguagesList extends AppCompatActivity {
    Toolbar toolbar;
    ArrayList<LanguageModel> langList = new ArrayList<>();
    public static int resultCode = 0;
    RecyclerView RV;
    LanguageListAdapter rvAdapter = new LanguageListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_languages_list);
        toolbar = findViewById(R.id.toolbarLanguageList);
        RV = findViewById(R.id.languageListRV);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        String title = intent.getStringExtra("whichLanguage");
        if (title.equals("FROM")) {
            getSupportActionBar().setTitle("Translate from");
            resultCode = 2;
        } else {
            getSupportActionBar().setTitle("Translate to");
            resultCode = 3;
        }
        langList.add(new LanguageModel("sq", "Albanian", "shqiptar", "AL"));
        langList.add(new LanguageModel("am", "Amharic", "አማርኛ", "ET"));
        langList.add(new LanguageModel("ar", "Arabic", "العربية", "SA"));
        langList.add(new LanguageModel("hy", "Armenian", "հայերեն", "AM"));
        langList.add(new LanguageModel("az", "Azerbaijani", "Azərbaycan", "AZ"));
        langList.add(new LanguageModel("eu", "Basque", "Euskal", "ES"));
        langList.add(new LanguageModel("be", "Belarusian", "Беларус", "BY"));
        langList.add(new LanguageModel("bn", "Bengali", "বাংলা", "BD"));
        langList.add(new LanguageModel("bs", "Bosnian", "bosanski", "BA"));
        langList.add(new LanguageModel("bg", "Bulgarian", "Български", "BG"));
        langList.add(new LanguageModel("ca", "Catalan", "Català", "ES"));
        langList.add(new LanguageModel("ceb", "Cebuano", "Cebuano", "PH"));
        langList.add(new LanguageModel("zh", "Chinese", "中文", "CN"));
        langList.add(new LanguageModel("co", "Corsican", "Corsu", "FR"));
        langList.add(new LanguageModel("hr", "Croatian", "Hrvatski", "HR"));
        langList.add(new LanguageModel("cs", "Czech", "Čeština", "CZ"));
        langList.add(new LanguageModel("da", "Danish", "Dansk", "DK"));
        langList.add(new LanguageModel("nl", "Dutch", "Nederlands", "NL"));
        langList.add(new LanguageModel("en", "English", "English", "US"));
        langList.add(new LanguageModel("eo", "Esperanto", "Esperanto", "AAE")); // espranto flag
        langList.add(new LanguageModel("et", "Estonian", "Eesti", "EE"));
        langList.add(new LanguageModel("fi", "Finnish", "Suomi", "FI"));
        langList.add(new LanguageModel("fr", "French", "Français", "FR"));
        langList.add(new LanguageModel("fy", "Frisian", "Frysk", "DE"));
        langList.add(new LanguageModel("tl", "Filipino", "Pilipino", "PH"));
        langList.add(new LanguageModel("gl", "Galician", "Galego", "ES"));
        langList.add(new LanguageModel("ka", "Georgian", "ქართული", "GE"));
        langList.add(new LanguageModel("de", "German", "Deutsch", "DE"));
        langList.add(new LanguageModel("el", "Greek", "Ελληνικά", "GR"));
        langList.add(new LanguageModel("gu", "Gujarati", "ગુજરાતી", "IN"));
        langList.add(new LanguageModel("ht", "Haitian Creole", "Haitian Creole Version", "HT"));
        langList.add(new LanguageModel("ha", "Hausa", "Hausa", "NG"));
        langList.add(new LanguageModel("haw", "Hawaiian", "ʻ .lelo Hawaiʻi", "AAH")); // hawai flag
        langList.add(new LanguageModel("he", "Hebrew", "עברית", "IL"));
        langList.add(new LanguageModel("hi", "Hindi", "हिंदी", "IN"));
        langList.add(new LanguageModel("hmn", "Hmong", "Hmong", "CN"));
        langList.add(new LanguageModel("hu", "Hungarian", "Magyar", "HU"));
        langList.add(new LanguageModel("is", "Icelandic", "Íslensku", "IS"));
        langList.add(new LanguageModel("ig", "Igbo", "Ndi Igbo", "NG"));
        langList.add(new LanguageModel("id", "Indonesian", "Indonesia", "ID"));
        langList.add(new LanguageModel("ga", "Irish", "Gaeilge", "IE"));
        langList.add(new LanguageModel("it", "Italian", "Italiano", "IT"));
        langList.add(new LanguageModel("ja", "Japanese", "日本語", "JP"));
        langList.add(new LanguageModel("jw", "Javanese", "Basa jawa", "ID"));
        langList.add(new LanguageModel("kn", "Kannada", "ಕನ್ನಡ", "IN"));
        langList.add(new LanguageModel("kk", "Kazakh", "Қазақ", "KZ"));
        langList.add(new LanguageModel("km", "Khmer", "ខខ្មែរ។", "TH"));
        langList.add(new LanguageModel("ko", "Korean", "한국어", "KR"));
        langList.add(new LanguageModel("ku", "Kurdish", "Kurdî", "IQ"));
        langList.add(new LanguageModel("ky", "Kyrgyz", "Кыргызча", "IQ"));
        langList.add(new LanguageModel("lo", "Lao", "ລາວ", "TH"));
        langList.add(new LanguageModel("la", "Latin", "Latine", "IT"));
        langList.add(new LanguageModel("lv", "Latvian", "Latviešu valoda", "LV"));
        langList.add(new LanguageModel("lt", "Lithuanian", "Lietuvių", "LT"));
        langList.add(new LanguageModel("lb", "Luxembourgish", "Lëtzebuergesch", "LU"));
        langList.add(new LanguageModel("mk", "Macedonian", "Македонски", "MK"));
        langList.add(new LanguageModel("mg", "Malagasy", "Malagasy", "MG"));
        langList.add(new LanguageModel("ms", "Malay", "Bahasa Melayu", "MY"));
        langList.add(new LanguageModel("ml", "Malayalam", "മലയാളം", "IN"));
        langList.add(new LanguageModel("mt", "Maltese", "Il-Malti", "MT"));
        langList.add(new LanguageModel("mi", "Maori", "Maori", "NZ"));
        langList.add(new LanguageModel("mr", "Marathi", "मराठी", "IN"));
        langList.add(new LanguageModel("mn", "Mongolian", "Монгол", "MN"));
        langList.add(new LanguageModel("my", "Myanmar", "မြန်မာ", "MM"));
        langList.add(new LanguageModel("ne", "Nepali", "नेपाली", "NP"));
        langList.add(new LanguageModel("nb", "Norwegian", "Norsk", "NO"));
        langList.add(new LanguageModel("ny", "Nyanja", "Nyanja", "MW"));
        langList.add(new LanguageModel("ps", "Pashto", "پښتو", "PK"));
        langList.add(new LanguageModel("fa", "Persian", "فارسی", "IR"));
        langList.add(new LanguageModel("pl", "Polish", "Polski", "PL"));
        langList.add(new LanguageModel("pt", "Portuguese", "Português", "PT"));
        langList.add(new LanguageModel("pa", "Punjabi", "ਪੰਜਾਬੀ", "IN"));
        langList.add(new LanguageModel("ro", "Romanian", "Română", "RO"));
        langList.add(new LanguageModel("ru", "Russian", "Pусский", "RU"));
        langList.add(new LanguageModel("gd", "Scots Gaelic", "Gàidhlig na h-Alba", "GB"));
        langList.add(new LanguageModel("sr", "Serbian", "Српски", "RS"));
        langList.add(new LanguageModel("sn", "Shona", "Shona", "ZW"));
        langList.add(new LanguageModel("sd", "Sindhi", "سنڌي", "PK"));
        langList.add(new LanguageModel("sk", "Slovak", "Slovenský", "SK"));
        langList.add(new LanguageModel("sl", "Slovenian", "Slovenščina", "SL"));
        langList.add(new LanguageModel("so", "Somali", "Soomaali", "SO"));
        langList.add(new LanguageModel("es", "Spanish", "Español", "ES"));
        langList.add(new LanguageModel("su", "Sundanese", "Urang Sunda", "ID"));
        langList.add(new LanguageModel("sw", "Swahili", "Kiswahili", "CD"));
        langList.add(new LanguageModel("sv", "Swedish", "Svenska", "SE"));
        langList.add(new LanguageModel("tg", "Tajik", "Точик", "TJ"));
        langList.add(new LanguageModel("ta", "Tamil", "தமிழ்", "IN"));
        langList.add(new LanguageModel("te", "Telugu", "తెలుగు", "IN"));
        langList.add(new LanguageModel("th", "Thai", "ไทย", "TH"));
        langList.add(new LanguageModel("tr", "Turkish", "Türk", "TR"));
        langList.add(new LanguageModel("uk", "Ukrainian", "Українська", "UA"));
        langList.add(new LanguageModel("ur", "Urdu", "اردو", "PK"));
        langList.add(new LanguageModel("uz", "Uzbek", "O'zbek", "UZ"));
        langList.add(new LanguageModel("vi", "Vietnamese", "Tiếng Việt", "VN"));
        langList.add(new LanguageModel("cy", "Welsh", "Cymraeg", "GB"));
        langList.add(new LanguageModel("xh", "Xhosa", "isiXhosa", "ZA"));
        langList.add(new LanguageModel("yi", "Yiddish", "יידיש", "DE"));
        langList.add(new LanguageModel("yo", "Yoruba", "Yoruba", "NG"));
        langList.add(new LanguageModel("zu", "Zulu", "IsiZulu", "ZA"));


        RV.setAdapter(rvAdapter);
        RV.setLayoutManager(new LinearLayoutManager(RV.getContext()));
        rvAdapter.setData(langList);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search Language");

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<LanguageModel> filteredlist = new ArrayList<LanguageModel>();

        // running a for loop to compare elements.
        for (LanguageModel item : langList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getLanguageName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            rvAdapter.filterList(filteredlist);
        }
    }

}