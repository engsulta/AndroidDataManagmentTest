package com.example.sulta.datamanagment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sulta.datamanagment.com.example.sulta.beans.User;
import com.example.sulta.datamanagment.com.example.sulta.dao.DbHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends Activity implements View.OnClickListener {
    TextView mobileTV, messageTV;
    Button close;
    Intent recivingIntent;
    SharedPreferences data;
    SharedPreferences.Editor editor;
    final String filename = "personaldata";
    DbHandler mydbhandler;
    DataOutputStream dos;
    DataInputStream dis;
    FileOutputStream fos;
    FileInputStream fis;
    boolean mExternalStorageAvailable;
    boolean mExternalStorageWritable;
    String state;

    @Override
    protected void onStart() {
        super.onStart();
        data = getPreferences(MODE_PRIVATE);
        editor = data.edit();
        mydbhandler = new DbHandler(this);
        mExternalStorageAvailable = false;
        mExternalStorageWritable = false;
        state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mExternalStorageAvailable = mExternalStorageWritable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            mExternalStorageWritable = false;
            mExternalStorageAvailable = true;
        } else {
            mExternalStorageAvailable = mExternalStorageWritable = false;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mobileTV = (TextView) findViewById(R.id.second_phone);
        messageTV = (TextView) findViewById(R.id.second_message);
        close = (Button) findViewById(R.id.second_close);
        recivingIntent = getIntent();
        mobileTV.setText(recivingIntent.getStringExtra(FirstActivity.PhoneKey));
        messageTV.setText(recivingIntent.getStringExtra(FirstActivity.MessageKey));


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.second_add2pref).setOnClickListener(this);
        findViewById(R.id.second_removepref).setOnClickListener(this);
        findViewById(R.id.second_add2int).setOnClickListener(this);
        findViewById(R.id.second_removeint).setOnClickListener(this);
        findViewById(R.id.second_add2_ext).setOnClickListener(this);
        findViewById(R.id.second_removext).setOnClickListener(this);
        findViewById(R.id.second_add2sql).setOnClickListener(this);
        findViewById(R.id.second_removesql).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String message;
        String mobileno;

        switch (view.getId()) {

            case R.id.second_add2pref:
                //TODO implement
                mobileno = mobileTV.getText().toString();
                message = messageTV.getText().toString();
                editor.putString("mobileno", mobileno);
                editor.putString("message", message);
                editor.commit();
                mobileTV.setText("");
                messageTV.setText("");
                Toast.makeText(this, "data added to shared prefrence", Toast.LENGTH_SHORT).show();
                break;
            case R.id.second_removepref:
                mobileno = data.getString("mobileno", "");
                message = data.getString("message", "no saved message");
                mobileTV.setText(mobileno);
                messageTV.setText(message);
                break;
            case R.id.second_add2int:
                mobileno = mobileTV.getText().toString();
                message = messageTV.getText().toString();
                try {
                    fos = this.openFileOutput(filename, MODE_PRIVATE);
                    dos = new DataOutputStream(fos);
                    dos.writeUTF(mobileno);
                    dos.writeUTF(message);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                        dos.close();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mobileTV.setText("");
                messageTV.setText("");
                break;
            case R.id.second_removeint:
                //TODO implement
                try {
                    fis = openFileInput(filename);
                    dis = new DataInputStream(fis);
                    mobileTV.setText(dis.readUTF());
                    messageTV.setText(dis.readUTF());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fis.close();
                        dis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.second_add2_ext:
                if (mExternalStorageAvailable && mExternalStorageWritable) {
                    String root = Environment.getExternalStorageDirectory().toString();
                    File mydir = getExternalFilesDir("DIRECTORY_MUSIC");//pass null to get root
                    //File myDir2 = new File(root + "/saved_files");or we can use this
                    Log.i("sulta", mydir.mkdirs() ? "directory" : "not directory");
                    mydir.mkdirs();
                    int n = 0;
                    String fname = "file-" + n + ".txt";
                    File file = new File(mydir, fname);
                    if (file.exists())
                        file.delete();
                    try {

                        FileOutputStream out = new FileOutputStream(file);
                        dos = new DataOutputStream(out);
                        dos.writeUTF(mobileTV.getText().toString());
                        dos.writeUTF(messageTV.getText().toString());
                        mobileTV.setText("");
                        messageTV.setText("");
                        out.flush();
                        out.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
                break;
            case R.id.second_removext:
                //TODO implement
                if (mExternalStorageAvailable) {
                    File mydir = getExternalFilesDir("DIRECTORY_MUSIC");//pass null to get root
                    int n = 0;
                    String fname = "file-" + n + ".txt";
                    //final File file = new File(Environment.getExternalStorageDirectory()
                    //      .getAbsolutePath(), fname);
                   final File file=new File(mydir,fname);
                    try{
                        FileInputStream in=new FileInputStream(file);
                        dis = new DataInputStream(in);
                        mobileTV.setText(dis.readUTF());
                        messageTV.setText(dis.readUTF());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.second_add2sql:
                //TODO implement
                mydbhandler.insertRecords(mobileTV.getText().toString(), messageTV.getText().toString());
                mobileTV.setText("");
                messageTV.setText("");
                break;
            case R.id.second_removesql:
                //TODO implement
                User userdata = mydbhandler.getlastRecord();
                if (userdata != null) {
                    mobileTV.setText(userdata.getPhoneno());
                    messageTV.setText(userdata.getMessage());
                }
                break;
        }
    }
}
