package bitsplease.makeadifference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class DashboardActivity extends AppCompatActivity {

    Button project1Button;
    Button project2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        project1Button = findViewById(R.id.projectbutton1);
        project1Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                navigateToProjectOverview(v);
            }
        });
    }

    public void navigateToProjectOverview(View v) {
        Intent intent = new Intent(this, GoalManagerActivity.class);
        startActivity(intent);
    }
}
