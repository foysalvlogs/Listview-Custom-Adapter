package com.foysaldev.mycustomadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter adapter;
    String [] title;
    String [] description;
    int [] icon;
    ArrayList<Model> arrayList = new ArrayList<Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        icon = new int[]{R.drawable.bhaluka, R.drawable.trishal, R.drawable.haluaghat,
              R.drawable.muktagacha, R.drawable.dhobaura, R.drawable.fulbaria, R.drawable.gaffargaon, R.drawable.gauripur,
              R.drawable.ishwarganj, R.drawable.mymensingh_sadar, R.drawable.nandail, R.drawable.
              phulpur, R.drawable.tarakhanda};

        title = getResources().getStringArray(R.array.mymensingh_district);
        description = new String[] {"bhaluka...","trishal...","haluaghat...", "muktagacha...","dhobaura...",
        "fulbaria...","gaffargaon...","gauripur","ishwarganj...","mymensingh_sadar...","nandail",
                "phulpur...","tarakhanda",};
        listView = findViewById(R.id.ListViewid);

        for (int i = 0; i < title.length; i++) {
            Model model = new Model(title[i], description[i], icon[i]);
            arrayList.add(model);
        }

        adapter = new CustomAdapter(this,arrayList);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout,menu);

        MenuItem menuItem = menu.findItem(R.id.SearchViewid);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    adapter.filter("");
                    listView.clearTextFilter();
                } else {
                    adapter.filter(newText);
                }
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}