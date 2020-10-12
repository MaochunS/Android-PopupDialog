package com.maochun.popupinputdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    InputDialog mInputDialog = new InputDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShow = findViewById(R.id.button_showDialog);
        btnShow.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        showInputDialog();
    }

    private void showInputDialog(){
        mInputDialog.show(this, new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                mInputDialog.dismiss();

                String inputMsg = mInputDialog.getInputText();
            }
        }, new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                mInputDialog.dismiss();


            }
        } );
    }
}