package bitsplease.makeadifference;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSave;

    private ProgressDialog progressDialog;


    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //initializing views
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonSave = (Button) findViewById(R.id.buttonSave);

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        editTextName.setText(user.getDisplayName());
        editTextEmail.setText(user.getEmail());
        editTextPassword.setText("......");

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        buttonSave.setOnClickListener(this);

    }

    private void updateUserProfile(){

        //getting email and password from edit texts
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please enter name",Toast.LENGTH_LONG).show();
            return;
        }

        //if the name box is not empty
        //displaying a progress dialog

        progressDialog.setMessage("Updating Please Wait...");
        progressDialog.show();

        //updating user profile
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("EditProfileActivity", "User profile updated.");
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }
                    }
                });

        user.updateEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("EditProfileActivity", "User email address updated.");
                        }
                    }
                });

        String newPassword = password;

        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("EditProfileActivity", "User password updated.");
                        }
                    }
                });

//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        //checking if success
//                        if(task.isSuccessful()){
//                            finish();
//                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
//                        }else{
//                            //display some message here
//                            Toast.makeText(CreateProfileActivity.this,"Profile Creation Error",Toast.LENGTH_LONG).show();
//                        }
//                        progressDialog.dismiss();
//                    }
//                });

    }

    @Override
    public void onClick(View view) {

        if(view == buttonSave){
            updateUserProfile();
        }

    }
}