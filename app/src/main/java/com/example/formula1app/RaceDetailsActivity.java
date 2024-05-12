package com.example.formula1app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RaceDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_details);

        ImageView imageView = findViewById(R.id.image_race);
        TextView textView = findViewById(R.id.text_view_race_details);

        String raceInfo = getIntent().getStringExtra("RACE_INFO");
        textView.setText(getRaceDescription(raceInfo));
        imageView.setImageResource(getImageResourceId(raceInfo));

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private String getRaceDescription(String raceInfo) {
        switch (raceInfo) {
            case "Magyar Nagydíj":
                return "A vártnál nagyobb az éreklődés a Magyar Nagydíj iránt, 1 hét alatt elkeltek a jegyek a július végi megmérettetésre. ";
            case "Olasz Nagydíj":
                return "Nagy esőzések miatt 1 órával tolódik az időmérő, a Ferrari nem örül.";
            case "Lando Norris hosszabbítot":
                return "A Red Bull mellett a McLaren is bebiztosította magát 2026-ig: Lando Norris hosszabbított.";
            case "Miami Nagydíj":
                return "BREAKING: Egyre nő az amerikai futamok nézőzáma, jövőre bekerülhet még egy.";
            case "Monaco szűkös utcái":
                return "Izgalmas futam várható a Mediterrán tenger menti luxusvárosban, idén megviccelheti a versenyzőket az időjárás.";
            default:
                return "Nincs leírás ehhez a versenyhez.";
        }
    }

    private int getImageResourceId(String raceInfo) {
        switch (raceInfo) {
            case "Magyar Nagydíj":
                return R.drawable.hungarian_gp;
            case "Olasz Nagydíj":
                return R.drawable.imola;
            case "Lando Norris hosszabbítot":
                return R.drawable.lando_norris;
            case "Miami Nagydíj":
                return R.drawable.miami;
            case "Monaco szűkös utcái":
                return R.drawable.monaco;
            default:
                return R.drawable.f1_pic;
        }
    }
}


