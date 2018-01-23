package com.wenzon.ppm;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenzon.ppm.PM.Project;

public class DetailActivity extends AppCompatActivity {
    Project p;
    TextView tv1, tv2, tv3, tv4, tv5, tv6;
    ImageView img;
    boolean fastBack = false;
    int id;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        img=(ImageView) findViewById(R.id.imageView);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);
        tv6 = (TextView) findViewById(R.id.textView6);
        id = getIntent().getIntExtra("id", 0);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (fastBack)
        {
            finish();
        }
        p = MainActivity.dao.getProject(id);
        tv1.setText(String.valueOf(p.id));
        tv2.setText(p.name);
        tv3.setText(p.schedule);
        tv4.setText(p.quality);
        tv5.setText(p.budget);
        tv6.setText(p.remark);
    }

    public void clickBack(View v)
    {
        finish();
    }
    public void clickDelete(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        builder.setTitle("刪除確認");
        builder.setMessage("確認要刪除本筆資料嗎?");
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.dao.delete(id);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
    public void clickEdit(View v)
    {
        Intent it = new Intent(DetailActivity.this, EditActivity.class);
        it.putExtra("id", id);
        fastBack = true;
        startActivity(it);
    }
}
