package com.example.ganapa;

import static com.example.ganapa.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_page extends AppCompatActivity {
    EditText username, password, repassword,email_1,Phone,Address;
    Button signup;
    TextView tv;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_login_page);
        username = (EditText) findViewById(id.username);
        password = (EditText) findViewById(id.password);
        repassword = (EditText) findViewById(id.repassword);
        email_1=findViewById(id.email_1);
        Phone=findViewById(id.Phone);
        Address=findViewById(id.Address);
        TextView t2=findViewById(id.text_view);

        signup = (Button) findViewById(id.btnsignup);
        DB = new DBHelper(this);




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String em=email_1.getText().toString();
                String ph=Phone.getText().toString();
                String ad=Address.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("")||em.equals("")||ph.equals("")||ad.equals(""))
                    Toast.makeText(Login_page.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user, em);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user,pass,em,ph,ad);
                            if(insert==true){
                                Toast.makeText(Login_page.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(Login_page.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Login_page.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Login_page.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });
       t2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent  = new Intent(getApplicationContext(), LoginActivity.class);
               startActivity(intent);
           }
       });

    }
}