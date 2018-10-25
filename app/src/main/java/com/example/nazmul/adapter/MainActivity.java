package com.example.nazmul.adapter;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private String[] countryNames;
    private Spinner languageList;
    private SearchableSpinner listView;
    private TextView textView;
    String currentLanguage = "bn";
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Locale myLocale = getResources().getConfiguration().locale;
        if(myLocale.getLanguage().equals("en")){

            setDefaultLanguage("bn");
            currentLanguage = "bn";
        } else {
            setDefaultLanguage("en");
            currentLanguage = "en";
        }

        getSupportActionBar().setTitle("Country LIST");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        countryNames = getResources().getStringArray(R.array.country_names);

        listView = findViewById(R.id.listView);
       // languageList = findViewById(R.id.languageList);
        textView = findViewById(R.id.countryDetails);

        // Language Dropdown
        List<String> langList = new ArrayList<String>();
        langList.add("Select Language");
        langList.add("English");
        langList.add("Bangla");
        /*ArrayAdapter<String> langAdapter = new ArrayAdapter<String>(this,R.layout.sample_view, R.id.textViewId,langList);
        languageList.setAdapter(langAdapter);*/



        // Country Dropdown
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.sample_view, R.id.textViewId,countryNames);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


       //Toast.makeText(MainActivity.this, lang.getLanguage() + lang.getDisplayCountry(), Toast.LENGTH_SHORT).show();


    }

    public void setDefaultLanguage(String lang){
        // Create a new Locale object
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        // Create a new configuration object
        Configuration config = new Configuration();
        // Set the locale of the new configuration
        config.locale = locale;
        // Update the configuration of the Accplication context
        getResources().updateConfiguration(
                config,
                getResources().getDisplayMetrics()
        );
    }

    /*public void setLocale(String localeName) {

        myLocale = new Locale(localeName);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        refresh.putExtra(currentLang, localeName);
        startActivity(refresh);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.btnLangSwitcher){
            //setLocale("bn","BD");

            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);

            finish();
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.mymenu, menu);

        if(currentLanguage.equalsIgnoreCase("en")){

            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.bangladesh));
            menu.getItem(0).setTitle("Switch to Bangla");

        } else {

            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.united_kingdom));
            menu.getItem(0).setTitle("Switch to English");

        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
