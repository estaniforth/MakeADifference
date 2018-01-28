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
import android.widget.Spinner;
import java.util.HashMap;
import bitsplease.makeadifference.User;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class NewGoalActivity extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private EditText editTextName;
    private Button buttonSave;
    private Spinner spinnerStart;
    private Spinner spinnerEnd;
    private User mUser;

    private ProgressDialog progressDialog;


    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    //defining database
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goal);

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
        spinnerStart = (Spinner) findViewById(R.id.spinnerStart);
        spinnerEnd = (Spinner) findViewById(R.id.spinnerEnd);

        buttonSave = (Button) findViewById(R.id.buttonSave);

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        progressDialog = new ProgressDialog(this);

        mDatabase.child("users").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    mUser = dataSnapshot.getValue(User.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

        //attaching listener to button
        buttonSave.setOnClickListener(this);

    }

    private void updateGoal() {

        String name = editTextName.getText().toString().trim();
        String start = String.valueOf(spinnerStart.getSelectedItem());
        String end = String.valueOf(spinnerEnd.getSelectedItem());

        int startT = Integer.parseInt(start.substring(0, 2).trim());
        int endT = Integer.parseInt(end.substring(0, 2).trim());
        if (start.substring(start.length() - 2, start.length()) == "rs") {
            startT *= 60;
        }
        if (end.substring(end.length() - 2, end.length()) == "rs") {
            endT *= 60;
        }

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter a goal", Toast.LENGTH_LONG).show();
            return;
        }

        //if the name box is not empty
        //displaying a progress dialog


        Log.d("NewGoalActivity", name);
        if (mUser == null) {
            Log.d("NewGoalActivity", "scREAM");
        } else {
            mUser.addGoal(new Goal(name, startT, endT));
        }
        startActivity(new Intent(getApplicationContext(), GoalManagerActivity.class));


    }

    public void onClick(View view) {

        if(view == buttonSave){
            updateGoal();
        }

    }


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

//    }

//    @Override
//    public void onClick(View view) {
//
//        if(view == buttonSave){
//            updateGoal();
//        }
//
//    }
}