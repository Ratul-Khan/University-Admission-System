package com.example.demo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class DownloadDataActivity extends AppCompatActivity {
    LinearLayout linearlayout;
    Bitmap bitmap;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_data);
        button = (Button) findViewById(R.id.button);
        linearlayout = findViewById(R.id.print);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Log.d("Size", "" + linearlayout.getWidth() + "" + linearlayout.getWidth());
                bitmap = LoadBitmap(linearlayout, linearlayout.getWidth(), linearlayout.getHeight());
                createPdf();
            }
        });

    }

    private Bitmap LoadBitmap(View v, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPdf() {
        WindowManager windowManager=(WindowManager)getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        float width=displayMetrics.widthPixels;
        float height=displayMetrics.heightPixels;
        int convertWidth=(int)width;
        int convertHeight=(int)height;
        PdfDocument document=new PdfDocument();
        PdfDocument.PageInfo pageInfo=new PdfDocument.PageInfo.Builder(convertWidth,convertHeight,1).create();
        PdfDocument.Page page=document.startPage(pageInfo);
        Canvas canvas=page.getCanvas();
        Paint paint=new Paint();
        canvas.drawPaint(paint);
        bitmap=Bitmap.createScaledBitmap(bitmap,convertWidth,convertHeight,true);
        canvas.drawBitmap(bitmap,0,0,null);
        document.finishPage(page);
        String targerpdf="/sdcard/page.pdf";
        File file;
        file=new File(targerpdf);
        try {
            document.writeTo(new FileOutputStream(file));

        }catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Something wrong"+e.toString(),Toast.LENGTH_LONG).show();
            document.close();
            Toast.makeText(getApplicationContext(),"Successfully download",Toast.LENGTH_LONG).show();
        }
    }




}