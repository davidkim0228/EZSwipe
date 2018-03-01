package com.gazua.ezswipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DiningSelect extends AppCompatActivity {

//    final FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference ref = database.getReference("https://ezswipe-1db4e.firebaseio.com/");

    private TextView MessageText;
    private static DatabaseReference reference;
    private Button mFirebaseBtn;
    private Button deNeve;
    private Button bplate;
    private Button covel;
    private Button feast;
    private DatabaseReference mDatabase;
    private String diningHall;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_select);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signOutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                mAuth.signOut();
                startActivity(new Intent(DiningSelect.this, MainActivity.class));
                Toast.makeText(DiningSelect.this, "Sign Out.", Toast.LENGTH_LONG).show();
            }
        });

        mFirebaseBtn = (Button) findViewById(R.id.buyer_btn);
        bplate = (Button) findViewById(R.id.bplate_btn);
        covel = (Button) findViewById(R.id.covel_btn);
        deNeve = (Button) findViewById(R.id.deNeve_btn);
        feast = (Button) findViewById(R.id.feast_btn);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mFirebaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String name = currentFirebaseUser.getDisplayName();
                String uid = currentFirebaseUser.getUid();
                Date currentTime = Calendar.getInstance().getTime();

                SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

                String strDt = simpleDate.format(currentTime);

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Location", diningHall);
                map.put("Price", "");
                map.put("Number", "");
                map.put("Status", "0");
                map.put("Buyer_ID", uid);
                map.put("Buyer_name", name);
                map.put("Seller_name", "");
                map.put("Created_at", strDt);

//                mDatabase.push().setValue(map);
                reference = mDatabase.push();
                reference.setValue(map);
//                reference.child("Seller_name").setValue("David");
//
//                String st = reference.toString();
//                Toast.makeText(BuyerPrice.this, st, Toast.LENGTH_LONG).show();

//                mDatabase.child("Location").setValue("Covel");
//                mDatabase.child("Price").setValue("8");
//                mDatabase.child("Number").setValue("1");
//                mDatabase.child("Status").setValue("0");
//                mDatabase.child("Buyer_ID").setValue("1");
//                mDatabase.child("Buyer_name").setValue("Bruin");
//                mDatabase.child("Seller_name").setValue("Bear");
//                mDatabase.child("Created_at").setValue("10");
                startActivity(new Intent(DiningSelect.this, BuyerPrice.class));
                Toast.makeText(DiningSelect.this, "Buyer Price.", Toast.LENGTH_LONG).show();
            }
        });

        bplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diningHall = "Bruin Plate";
            }
        });

        covel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diningHall = "Covel";
            }
        });

        deNeve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diningHall = "De Neve";
            }
        });

        feast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diningHall = "Feast";
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

//        ref.child("Test").setValue("Test!!");
    }

    public static DatabaseReference push_reference() {
        return reference;
    }
}
