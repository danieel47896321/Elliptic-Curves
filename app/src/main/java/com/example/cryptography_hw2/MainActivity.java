package com.example.cryptography_hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView PandG, AliceInfo, BobInfo;
    private Button calcButton, Reset;
    private Integer P,G,AlicePrivateKey,BobPrivateKey;
    private long AlicePubKey, BobPubKey;
    private int Prime[] = {3,5,7,11,13,17,19,23,29,31};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        setID();
        CalcKey();
        CalcInfo();
        Reset();
    }
    private void setID(){
        PandG = findViewById(R.id.PandG);
        AliceInfo = findViewById(R.id.AliceInfo);
        BobInfo = findViewById(R.id.BobInfo);
        calcButton = findViewById(R.id.calcButton);
        Reset = findViewById(R.id.Reset);
    }
    private void CalcInfo(){
        Random random = new Random();
        P = Prime[random.nextInt(Prime.length)];
        G = (random.nextInt(20)+10);
        PandG.setText("Prime: "+P+" - G: "+G);
        AlicePrivateKey=(random.nextInt(9)+2); //0-10   a
        BobPrivateKey=(random.nextInt(9)+2);  //0-10    b
        AlicePubKey = (long)(Math.pow(G,AlicePrivateKey))% P;  // (G^a)%p
        BobPubKey = (long)(Math.pow(G,BobPrivateKey))% P;     //  (G^b)%p
    }
    private void CalcKey(){
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AliceInfo.setText("Private key for Alice: "+AlicePrivateKey+"\n"+"Public key for  Alice: "+AlicePubKey+"\n"+"Secret key: "+(long)(Math.pow(BobPubKey,AlicePrivateKey))% P);  // (BobPubKey^a)%p
                BobInfo.setText("Private key for Bob: "+BobPrivateKey+"\n"+"Public key for  Bob: "+BobPubKey+"\n"+"Secret key: "+(long)(Math.pow(AlicePubKey,BobPrivateKey))% P); // (AlicePubKet^b)%p
            }
        });
    }
    private void Reset(){
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}