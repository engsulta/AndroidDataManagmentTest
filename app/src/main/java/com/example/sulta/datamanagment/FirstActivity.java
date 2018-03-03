package com.example.sulta.datamanagment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends Activity {
    EditText messageET,mobileET;
    Button next,close;
    final static String PhoneKey="phone";
    final static String MessageKey="message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        messageET=(EditText) findViewById(R.id.first_message);
        mobileET=(EditText) findViewById(R.id.first_phone);
        next=(Button) findViewById(R.id.first_next);
        close=(Button) findViewById(R.id.first_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra(PhoneKey,mobileET.getText().toString());
                intent.putExtra(MessageKey,messageET.getText().toString());
                startActivity(intent);
            }
        });



    }

}
