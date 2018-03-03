package com.example.sulta.datamanagment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.Locale;

public class SelectLanguages extends Activity {
    CheckBox arab,engl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_languages);
        arab=(CheckBox) findViewById(R.id.selected_arabic);
        engl=(CheckBox) findViewById(R.id.selected_english);
        arab.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Resources res = SelectLanguages.this.getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                android.content.res.Configuration conf = res.getConfiguration();
                Locale loc = new Locale("ar");
                conf.setLocale(loc); // API 17+ only.
// Use conf.locale = new Locale(...) if targeting lower versions
                res.updateConfiguration(conf, dm);
                Intent mainIntent = new Intent(SelectLanguages.this,FirstActivity.class);
                startActivity(mainIntent);

            }
        });
        engl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent mainIntent = new Intent(SelectLanguages.this,FirstActivity.class);

            }
        });
    }
}
