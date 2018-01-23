package com.wenzon.ppm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenzon.ppm.PM.Project;

public class EditActivity extends AppCompatActivity {
    int id;
    Project p;
    TextView tv7;
    EditText ed2, ed8, ed9, ed10, ed11;
    ImageView img;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        id = getIntent().getIntExtra("id", 0);
        p = MainActivity.dao.getProject(id);
        tv7 = findViewById(R.id.textView7);
        img=findViewById(R.id.imageView);
        ed2 = findViewById(R.id.editText2);
        ed8 = findViewById(R.id.editText8);
        ed9 = findViewById(R.id.editText9);
        ed10 = findViewById(R.id.editText10);
        ed11 = findViewById(R.id.editText11);
        tv7.setText(String.valueOf(p.id));
        ed2.setText(p.name);
        ed8.setText(p.schedule);
        ed9.setText(p.quality);
        ed10.setText(p.budget);
        ed11.setText(p.remark);
    }
}
