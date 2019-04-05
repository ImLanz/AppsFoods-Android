package com.example.lance.appsfood;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSingUp, btnSingIn;
    TextView txtSlogan, txtLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main1 );


        btnSingIn = (Button)findViewById( R.id.btnSignIn );
        btnSingUp = (Button)findViewById( R.id.btnSignUp );

        txtSlogan = (TextView)findViewById( R.id.txtSlogan );
        txtLogo = (TextView)findViewById( R.id.txtLogo );

        Typeface face = Typeface.createFromAsset( getAssets(), "Fonts/Animals.ttf" );
        txtSlogan.setTypeface( face );
        txtLogo.setTypeface( face );

        btnSingIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );

        btnSingUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent( MainActivity.this, sign_up.class);
                startActivity( signUp );

            }
        } );

        btnSingIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent( MainActivity.this, sign_in.class);
                startActivity( signIn );
            }
        } );

    }
}
