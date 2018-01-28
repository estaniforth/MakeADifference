package bitsplease.makeadifference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Main Activity", "Hello World!!");

        button = (Button) findViewById(R.id.FEED);
        button.setOnClickListener( new View.OnClickListener(){
            public void onClick (View v){
                next_page(v);
            }
        });
    }

    public void next_page(View v) {
        Intent intent = new Intent(this, FeedActivity.class);
        startActivity(intent);
    }
}
