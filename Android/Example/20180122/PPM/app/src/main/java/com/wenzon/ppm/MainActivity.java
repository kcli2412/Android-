package com.wenzon.ppm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wenzon.ppm.PM.PType;
import com.wenzon.ppm.PM.Project;
import com.wenzon.ppm.PM.ProjectDAO;
import com.wenzon.ppm.PM.ProjectDAOFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ProjectDAO dao;
    PType pType;
    ListView lv;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pType = PType.FILE;
        dao = ProjectDAOFactory.getDAOInstance(this, pType);
    }
    @Override
    protected void onResume() {
        super.onResume();
        lv = (ListView) findViewById(R.id.listView);
        ArrayList<String> projectNames = new ArrayList<>();
        for (Project p : dao.getList())
        {
            projectNames.add(p.name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, projectNames);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> adapterView, View view, int position, long l) {
                Intent it = new Intent(MainActivity.this, DetailActivity.class);
                it.putExtra("id", dao.getList().get(position).id);
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add)
        {
            Intent it = new Intent(MainActivity.this, AddActivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
}
