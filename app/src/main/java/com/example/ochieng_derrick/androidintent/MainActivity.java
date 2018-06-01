package com.example.ochieng_derrick.androidintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    Button btnWebsite, btnLocation, btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWebsite = findViewById(R.id.btnWebsite);
        btnLocation = findViewById(R.id.btnLocation);
        btnShare = findViewById(R.id.btnLocation);

        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String UrlString = "https://github.com/Ochieng424";
              openWebPage(UrlString);
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addressString = "1600 Amphitheatre, CA";
                Uri.Builder builder= new Uri.Builder();
                builder.scheme("geo")
                        .path("0,0")
                        .query(addressString);

                Uri addressUri = builder.build();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(addressUri);

                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "App to open maps not available", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToShare = "Happy Madaraka Day";
              shareText(textToShare);
            }
        });
    }

    //Function called when button is pressed
    private void openWebPage(String url){
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        //Checks if there is an app that can handle the request
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    public void shareText(String textToShare){
        String mimeType = "text/plain";
        String title = "Learning how to Share";

        ShareCompat.IntentBuilder.from(this)
                .setChooserTitle(title)
                .setType(mimeType)
                .setText(textToShare)
                .startChooser();
    }
}
