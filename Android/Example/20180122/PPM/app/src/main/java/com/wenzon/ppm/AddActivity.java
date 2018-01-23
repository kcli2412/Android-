package com.wenzon.ppm;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.wenzon.ppm.PM.PType;
import com.wenzon.ppm.PM.Project;
import com.wenzon.ppm.PM.ProjectDAOFactory;

import java.io.FileNotFoundException;

public class AddActivity extends AppCompatActivity {
    private ImageView img;
    private DisplayMetrics mPhone;

    private final static int PHOTO = 99 ;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final int PHOTO = 99 ;
            //讀取手機解析度
            mPhone = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(mPhone);

            img= (ImageView) findViewById(R.id.imageView);

            Button mPhoto = (Button) findViewById(R.id.photo);



            mPhoto.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //開啟相簿相片集，須由startActivityForResult且帶入requestCode進行呼叫，原因
                    // 為點選相片後返回程式呼叫onActivityResult
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, PHOTO);
                }
            });
        }

        //拍照完畢或選取圖片後呼叫此函式
        @Override
        protected void onActivityResult(int requestCode, int resultCode,Intent data)
        {
            //藉由requestCode判斷是否為開啟相機或開啟相簿而呼叫的，且data不為null
            if (( requestCode == PHOTO ) && data != null)
            {
                //取得照片路徑uri
                Uri uri = data.getData();
                ContentResolver cr = this.getContentResolver();

                try
                {
                    //讀取照片，型態為Bitmap
                    Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

                    //判斷照片為橫向或者為直向，並進入ScalePic判斷圖片是否要進行縮放
                    if(bitmap.getWidth()>bitmap.getHeight())ScalePic(bitmap,
                            mPhone.heightPixels);
                    else ScalePic(bitmap,mPhone.widthPixels);
                }
                catch (FileNotFoundException e)
                {
                }
            }

            super.onActivityResult(requestCode, resultCode, data);
        }

    private void ScalePic(Bitmap bitmap,int phone)
    {
        //縮放比例預設為1
        float mScale = 1 ;

        //如果圖片寬度大於手機寬度則進行縮放，否則直接將圖片放入ImageView內
        if(bitmap.getWidth() > phone )
        {
            //判斷縮放比例
            mScale = (float)phone/(float)bitmap.getWidth();

            Matrix mMat = new Matrix() ;
            mMat.setScale(mScale, mScale);

            Bitmap mScaleBitmap = Bitmap.createBitmap(bitmap,
                    0,
                    0,
                    bitmap.getWidth(),
                    bitmap.getHeight(),
                    mMat,
                    false);
           img.setImageBitmap(mScaleBitmap);
        }
        else img.setImageBitmap(bitmap);


    }
    public void clickAdd(View v)
    {
        EditText ed1 = (EditText) findViewById(R.id.editText);
        EditText ed7= (EditText) findViewById(R.id.editText7);
        ImageView img= (ImageView) findViewById(R.id.imageView);
        EditText ed3 = (EditText) findViewById(R.id.editText3);
        EditText ed4= (EditText) findViewById(R.id.editText4);
        EditText ed5 = (EditText) findViewById(R.id.editText5);
        EditText ed6 = (EditText) findViewById(R.id.editText6);
        int id = Integer.valueOf(ed1.getText().toString());
        String name = ed7.getText().toString();

        String schedule = ed3.getText().toString();
        String quality= ed4.getText().toString();
        String budget = ed5.getText().toString();
        String remark = ed6.getText().toString();

        MainActivity.dao.add(new Project(id, name, schedule, quality, budget, remark));
        finish();
    }
}
