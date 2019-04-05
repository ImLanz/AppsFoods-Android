package com.example.lance.appsfood;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import Model.User;

public class sign_up extends AppCompatActivity {

    MaterialEditText editPhone, editName, editPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_up );

        editName = (MaterialEditText)findViewById( R.id.editName );
        editPassword = (MaterialEditText)findViewById( R.id.editPassword );
        editPhone = (MaterialEditText)findViewById( R.id.editPhone );

        btnSignUp = (Button)findViewById( R.id.btnSignUp );

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tabla_user = database.getReference("User");

        btnSignUp.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                final ProgressDialog mDialog = new ProgressDialog( sign_up.this );
                mDialog.setMessage( "Espere un momento..." );
                mDialog.show();

                tabla_user.addValueEventListener( new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot dataSnapshot) {

                        //chequea si ya esta registrado el numero
                        if(dataSnapshot.child( editPhone.getText().toString() ).exists()){
                            mDialog.dismiss();
                            Toast.makeText( sign_up.this, "Este numero ya existe!", Toast.LENGTH_SHORT ).show();
                        }
                        else {
                            mDialog.dismiss();
                            User user = new User( editName.getText().toString(),editPassword.getText().toString() );
                            tabla_user.child( editPhone.getText().toString()).setValue( user );
                            Toast.makeText( sign_up.this, "Registrado con exito!", Toast.LENGTH_SHORT ).show();
                            finish();
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
