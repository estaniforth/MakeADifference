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

import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private TextView textView;
    private EditText editTextName;
    private Button buttonNext;

    private ProgressDialog progressDialog;


    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    //defining database
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //initializing database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //initializing views
        editTextName = (EditText) findViewById(R.id.editTextName);

        buttonNext = (Button) findViewById(R.id.buttonNext);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        buttonNext.setOnClickListener(this);

    }

    private void createUserProfile(){

        //getting email and password from edit texts
        String name = editTextName.getText().toString().trim();

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
                            Log.d("CreateProfileActivity", "User profile updated.");

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            writeNewUser(user.getEmail(), user.getUid(), user.getDisplayName());

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
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

    private void writeNewUser(String email, String userId, String name) {
        Map<String, Goal> map = new HashMap<String, Goal>();
        map.put("goal1", new Goal(1));
        User user = new User(email, name, map);

        mDatabase.child("users").child(userId).setValue(user);
    }

    @Override
    public void onClick(View view) {

        if(view == buttonNext){
            createUserProfile();
        }

    }
}