
//package bitsplease.makeadifference;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//
//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//
//    //defining view objects
//    private EditText editTextEmail;
//    private EditText editTextPassword;
//    private Button buttonSignup;
//
//    private TextView textViewSignin;
//
//    private ProgressDialog progressDialog;
//
//
//    //defining firebaseauth object
//    private FirebaseAuth firebaseAuth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //initializing firebase auth object
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        //if getCurrentUser does not returns null
//        if(firebaseAuth.getCurrentUser() != null){
//            //that means user is already logged in
//            //so close this activity
//            finish();
//
//            //and open profile activity
//            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
//        }
//
//        //initializing views
//        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
//        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
//        textViewSignin = (TextView) findViewById(R.id.textViewSignin);
//
//        buttonSignup = (Button) findViewById(R.id.buttonSignup);
//
//        progressDialog = new ProgressDialog(this);
//
//        //attaching listener to button
//        buttonSignup.setOnClickListener(this);
//        textViewSignin.setOnClickListener(this);
//    }
//
//    private void registerUser(){
//
//        //getting email and password from edit texts
//        String email = editTextEmail.getText().toString().trim();
//        String password  = editTextPassword.getText().toString().trim();
//
//        //checking if email and passwords are empty
//        if(TextUtils.isEmpty(email)){
//            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        if(TextUtils.isEmpty(password)){
//            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        //if the email and password are not empty
//        //displaying a progress dialog
//
//        progressDialog.setMessage("Registering Please Wait...");
//        progressDialog.show();
//
//        //creating a new user
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
//                            Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
//                        }
//                        progressDialog.dismiss();
//                    }
//                });
//
//    }
//
//    @Override
//    public void onClick(View view) {
//
//        if(view == buttonSignup){
//            registerUser();
//        }
//
//        if(view == textViewSignin){
//            //open login activity when user taps on the already registered textview
//            startActivity(new Intent(this, LoginActivity.class));
//        }
//
//    }
//}
//
////import android.content.Intent;
////import android.os.Bundle;
////import android.support.annotation.NonNull;
////import android.support.v7.app.AppCompatActivity;
////import android.support.v7.widget.Toolbar;
////import android.view.View;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.ProgressBar;
////import android.widget.Toast;
////
////import com.google.android.gms.tasks.OnCompleteListener;
////import com.google.android.gms.tasks.Task;
////import com.google.firebase.auth.FirebaseAuth;
////import com.google.firebase.auth.FirebaseUser;
////
////public class MainActivity extends AppCompatActivity {
////
////    private Button btnChangeEmail, btnChangePassword, btnSendResetEmail, btnRemoveUser,
////            changeEmail, changePassword, sendEmail, remove, signOut;
////
////    private EditText oldEmail, newEmail, password, newPassword;
////    private ProgressBar progressBar;
////    private FirebaseAuth.AuthStateListener authListener;
////    private FirebaseAuth auth;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////
////        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////        toolbar.setTitle(getString(R.string.app_name));
////        setSupportActionBar(toolbar);
////
////        //get firebase auth instance
////        auth = FirebaseAuth.getInstance();
////
////        //get current user
////        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////
////        authListener = new FirebaseAuth.AuthStateListener() {
////            @Override
////            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
////                FirebaseUser user = firebaseAuth.getCurrentUser();
////                if (user == null) {
////                    // user auth state is changed - user is null
////                    // launch login activity
////                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
////                    finish();
////                }
////            }
////        };
////
////        btnChangeEmail = (Button) findViewById(R.id.change_email_button);
////        btnChangePassword = (Button) findViewById(R.id.change_password_button);
////        btnSendResetEmail = (Button) findViewById(R.id.sending_pass_reset_button);
////        btnRemoveUser = (Button) findViewById(R.id.remove_user_button);
////        changeEmail = (Button) findViewById(R.id.changeEmail);
////        changePassword = (Button) findViewById(R.id.changePass);
////        sendEmail = (Button) findViewById(R.id.send);
////        remove = (Button) findViewById(R.id.remove);
////        signOut = (Button) findViewById(R.id.sign_out);
////
////        oldEmail = (EditText) findViewById(R.id.old_email);
////        newEmail = (EditText) findViewById(R.id.new_email);
////        password = (EditText) findViewById(R.id.password);
////        newPassword = (EditText) findViewById(R.id.newPassword);
////
////        oldEmail.setVisibility(View.GONE);
////        newEmail.setVisibility(View.GONE);
////        password.setVisibility(View.GONE);
////        newPassword.setVisibility(View.GONE);
////        changeEmail.setVisibility(View.GONE);
////        changePassword.setVisibility(View.GONE);
////        sendEmail.setVisibility(View.GONE);
////        remove.setVisibility(View.GONE);
////
////        progressBar = (ProgressBar) findViewById(R.id.progressBar);
////
////        if (progressBar != null) {
////            progressBar.setVisibility(View.GONE);
////        }
////
////        btnChangeEmail.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                oldEmail.setVisibility(View.GONE);
////                newEmail.setVisibility(View.VISIBLE);
////                password.setVisibility(View.GONE);
////                newPassword.setVisibility(View.GONE);
////                changeEmail.setVisibility(View.VISIBLE);
////                changePassword.setVisibility(View.GONE);
////                sendEmail.setVisibility(View.GONE);
////                remove.setVisibility(View.GONE);
////            }
////        });
////
////        changeEmail.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                progressBar.setVisibility(View.VISIBLE);
////                if (user != null && !newEmail.getText().toString().trim().equals("")) {
////                    user.updateEmail(newEmail.getText().toString().trim())
////                            .addOnCompleteListener(new OnCompleteListener<Void>() {
////                                @Override
////                                public void onComplete(@NonNull Task<Void> task) {
////                                    if (task.isSuccessful()) {
////                                        Toast.makeText(MainActivity.this, "Email address is updated. Please sign in with new email id!", Toast.LENGTH_LONG).show();
////                                        signOut();
////                                        progressBar.setVisibility(View.GONE);
////                                    } else {
////                                        Toast.makeText(MainActivity.this, "Failed to update email!", Toast.LENGTH_LONG).show();
////                                        progressBar.setVisibility(View.GONE);
////                                    }
////                                }
////                            });
////                } else if (newEmail.getText().toString().trim().equals("")) {
////                    newEmail.setError("Enter email");
////                    progressBar.setVisibility(View.GONE);
////                }
////            }
////        });
////
////        btnChangePassword.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                oldEmail.setVisibility(View.GONE);
////                newEmail.setVisibility(View.GONE);
////                password.setVisibility(View.GONE);
////                newPassword.setVisibility(View.VISIBLE);
////                changeEmail.setVisibility(View.GONE);
////                changePassword.setVisibility(View.VISIBLE);
////                sendEmail.setVisibility(View.GONE);
////                remove.setVisibility(View.GONE);
////            }
////        });
////
////        changePassword.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                progressBar.setVisibility(View.VISIBLE);
////                if (user != null && !newPassword.getText().toString().trim().equals("")) {
////                    if (newPassword.getText().toString().trim().length() < 6) {
////                        newPassword.setError("Password too short, enter minimum 6 characters");
////                        progressBar.setVisibility(View.GONE);
////                    } else {
////                        user.updatePassword(newPassword.getText().toString().trim())
////                                .addOnCompleteListener(new OnCompleteListener<Void>() {
////                                    @Override
////                                    public void onComplete(@NonNull Task<Void> task) {
////                                        if (task.isSuccessful()) {
////                                            Toast.makeText(MainActivity.this, "Password is updated, sign in with new password!", Toast.LENGTH_SHORT).show();
////                                            signOut();
////                                            progressBar.setVisibility(View.GONE);
////                                        } else {
////                                            Toast.makeText(MainActivity.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
////                                            progressBar.setVisibility(View.GONE);
////                                        }
////                                    }
////                                });
////                    }
////                } else if (newPassword.getText().toString().trim().equals("")) {
////                    newPassword.setError("Enter password");
////                    progressBar.setVisibility(View.GONE);
////                }
////            }
////        });
////
////        btnSendResetEmail.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                oldEmail.setVisibility(View.VISIBLE);
////                newEmail.setVisibility(View.GONE);
////                password.setVisibility(View.GONE);
////                newPassword.setVisibility(View.GONE);
////                changeEmail.setVisibility(View.GONE);
////                changePassword.setVisibility(View.GONE);
////                sendEmail.setVisibility(View.VISIBLE);
////                remove.setVisibility(View.GONE);
////            }
////        });
////
////        sendEmail.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                progressBar.setVisibility(View.VISIBLE);
////                if (!oldEmail.getText().toString().trim().equals("")) {
////                    auth.sendPasswordResetEmail(oldEmail.getText().toString().trim())
////                            .addOnCompleteListener(new OnCompleteListener<Void>() {
////                                @Override
////                                public void onComplete(@NonNull Task<Void> task) {
////                                    if (task.isSuccessful()) {
////                                        Toast.makeText(MainActivity.this, "Reset password email is sent!", Toast.LENGTH_SHORT).show();
////                                        progressBar.setVisibility(View.GONE);
////                                    } else {
////                                        Toast.makeText(MainActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
////                                        progressBar.setVisibility(View.GONE);
////                                    }
////                                }
////                            });
////                } else {
////                    oldEmail.setError("Enter email");
////                    progressBar.setVisibility(View.GONE);
////                }
////            }
////        });
////
////        btnRemoveUser.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                progressBar.setVisibility(View.VISIBLE);
////                if (user != null) {
////                    user.delete()
////                            .addOnCompleteListener(new OnCompleteListener<Void>() {
////                                @Override
////                                public void onComplete(@NonNull Task<Void> task) {
////                                    if (task.isSuccessful()) {
////                                        Toast.makeText(MainActivity.this, "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
////                                        startActivity(new Intent(MainActivity.this, SignupActivity.class));
////                                        finish();
////                                        progressBar.setVisibility(View.GONE);
////                                    } else {
////                                        Toast.makeText(MainActivity.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
////                                        progressBar.setVisibility(View.GONE);
////                                    }
////                                }
////                            });
////                }
////            }
////        });
////
////        signOut.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                signOut();
////            }
////        });
////
////    }
////
////    //sign out method
////    public void signOut() {
////        auth.signOut();
////    }
////
////    @Override
////    protected void onResume() {
////        super.onResume();
////        progressBar.setVisibility(View.GONE);
////    }
////
////    @Override
////    public void onStart() {
////        super.onStart();
////        auth.addAuthStateListener(authListener);
////    }
////
////    @Override
////    public void onStop() {
////        super.onStop();
////        if (authListener != null) {
////            auth.removeAuthStateListener(authListener);
////        }
////    }
////}
//
////
////import android.content.Intent;
////import android.net.Uri;
////import android.os.Build;
////import android.os.Bundle;
////import android.provider.Settings;
////import android.support.v7.app.AppCompatActivity;
////import android.view.View;
////import android.widget.Toast;
////import android.util.Log;
////
////public class MainActivity extends AppCompatActivity {
////    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////
////        //Check if the application has draw over other apps permission or not?
////        //This permission is by default available for API<23. But for API > 23
////        //you have to ask for the permission in runtime.
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
////
////            //If the draw over permission is not available open the settings screen
////            //to grant the permission.
////            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
////                    Uri.parse("package:" + getPackageName()));
////            startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
////        } else {
////            initializeView();
////        }
////    }
////
////    /**
////     * Set and initialize the view elements.
////     */
////    private void initializeView() {
////        findViewById(R.id.notify_me).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                startService(new Intent(MainActivity.this, ChatHeadService.class));
////                finish();
////            }
////        });
////    }
////
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
////
////            //Check if the permission is granted or not.
////            if (resultCode == RESULT_OK) {
////                initializeView();
////            } else { //Permission is not available
////                Toast.makeText(this,
////                        "Draw over other app permission not available. Closing the application",
////                        Toast.LENGTH_SHORT).show();
////
////                finish();
////            }
////        } else {
////            super.onActivityResult(requestCode, resultCode, data);
////        }
////    }
////}

// package bitsplease.makeadifference;

// import android.content.Intent;
// import android.net.Uri;
// import android.os.Build;
// import android.os.Bundle;
// import android.provider.Settings;
// import android.support.v7.app.AppCompatActivity;
// import android.view.View;
// import android.widget.Toast;
// import android.util.Log;
// import android.view.View;
// import android.widget.Button;

// import java.util.NavigableMap;

// public class MainActivity extends AppCompatActivity {
//     private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;

//     Button button;
//     Button button2;

//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_main);

//         //Check if the application has draw over other apps permission or not?
//         //This permission is by default available for API<23. But for API > 23
//         //you have to ask for the permission in runtime.
//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {

//             //If the draw over permission is not available open the settings screen
//             //to grant the permission.
//             Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                     Uri.parse("package:" + getPackageName()));
//             startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
//         } else {
//             initializeView();
//         }

//         Log.d("Main Activity", "Hello World!!");

//         button = (Button) findViewById(R.id.FEED);
//         button.setOnClickListener( new View.OnClickListener(){
//             public void onClick (View v){
//                 next_page(v);
//             }
//         });

//         button2 = (Button) findViewById(R.id.DASHBOARD);
//         button2.setOnClickListener( new View.OnClickListener(){
//             public void onClick (View v){
//                 navigateToDashboard(v);
//             }
//         });


//     }

//     /**
//      * Set and initialize the view elements.
//      */
//     private void initializeView() {
//         findViewById(R.id.notify_me).setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                 startService(new Intent(MainActivity.this, ChatHeadService.class));
//                 finish();
//             }
//         });
//     }

//     @Override
//     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//         if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {

//             //Check if the permission is granted or not.
//             if (resultCode == RESULT_OK) {
//                 initializeView();
//             } else { //Permission is not available
//                 Toast.makeText(this,
//                         "Draw over other app permission not available. Closing the application",
//                         Toast.LENGTH_SHORT).show();

//                 finish();
//             }
//         } else {
//             super.onActivityResult(requestCode, resultCode, data);
//         }
//     }

//     public void next_page(View v) {
//         Intent intent = new Intent(this, FeedActivity.class);
//         startActivity(intent);
//     }

//     public void navigateToDashboard(View v) {
//         Intent intent = new Intent(this, DashboardActivity.class);
//         startActivity(intent);
//     }
// }