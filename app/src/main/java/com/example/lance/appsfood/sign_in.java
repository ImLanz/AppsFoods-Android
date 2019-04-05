package com.example.lance.appsfood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import Model.User;

public class sign_in extends AppCompatActivity {

    EditText editPhone, editPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_in );

        editPassword = (MaterialEditText)findViewById( R.id.editPassword );
        editPhone = (MaterialEditText)findViewById( R.id.editPhone );
        btnSignIn = (Button)findViewById( R.id.btnSignIn );

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tabla_user = database.getReference("User");

        btnSignIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog( sign_in.this );
                mDialog.setMessage( "Espere un momento..." );
                mDialog.show();

                tabla_user.addValueEventListener( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //chequear si el usuario no existe en la basedato
                        if(dataSnapshot.child( editPhone.getText().toString()).exists()) {

                            //obtener info users
                            mDialog.dismiss();
                            User user = dataSnapshot.child( editPhone.getText().toString() ).getValue( User.class );
                            user.setPhone( editPhone.getText().toString() );
                            if (user.getPassword().equals( editPassword.getText().toString() )) {
                                {
                                    Intent menuIntent = new Intent( sign_in.this, Home.class );
                                    Common.currentUser = user;
                                    startActivity( menuIntent );
                                    finish();
                                }
                            } else {
                                Toast.makeText( sign_in.this, "Sesion Fallida!", Toast.LENGTH_SHORT ).show();
                            }
                        }
                        else {
                            mDialog.dismiss();
                            Toast.makeText( sign_in.this, "Usuario no existe!", Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );
            }
        } );
    }
}
