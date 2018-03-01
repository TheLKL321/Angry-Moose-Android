package com.example.thelkl321.angrymooseandroid;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get display size
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        // Inflate credits popup layout
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popup = inflater.inflate(R.layout.popup_text, null);

        // Set size of the credits popup
        popupWindow = new PopupWindow(popup);
        popupWindow.setWidth(width - 100);
        popupWindow.setHeight(height - 100);

        // Set a listener for close popup button
        Button btn = popup.findViewById(R.id.closePopupButton);
        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public void playPressed (View view){
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void optionsPressed (View view){
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    public void creditsPressed (View view){
        popupWindow.showAtLocation(findViewById(R.id.mainLayout), Gravity.CENTER,0,0);
        TextView popupText = popupWindow.getContentView().findViewById(R.id.popupText);
        popupText.setText("Lorem ipsum dolor sit amet, consectetur\n" +
                "adipiscing elit. Nullam tempor mi vitae\n" +
                "odio vulputate, nec egestas quam\n" +
                "euismod. Sed sit amet urna justo. In elementum,\n" +
                "nulla eu pharetra lobortis, sem nisi interdum\n" +
                "ante, id luctus ex libero quis justo. Sed\n" +
                "volutpat tincidunt augue ac lobortis. Vestibulum\n" +
                "a lacinia nibh. Mauris porttitor massa ut aliquet\n" +
                "sodales. Integer sit amet turpis scelerisque,\n" +
                "ultrices ipsum et, volutpat mi.");
    }

    public void closePopup (View view){
        popupWindow.dismiss();
    }

    public void exitPressed (View view){
        finish();
        System.exit(0);
    }
}
