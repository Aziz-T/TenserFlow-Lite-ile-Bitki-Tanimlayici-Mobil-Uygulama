package org.tensorflow.lite.examples.classification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BilgiActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    FlowerClass flowerClass;
    List<FlowerClass> flowerClasses;

    private ImageView imageView;
    private TextView baslik,bilgi;
    private String isim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgi);
        imageView = findViewById(R.id.imageView);
        baslik = findViewById(R.id.baslik);
        bilgi = findViewById(R.id.bilgi);
        isim=getIntent().getStringExtra("name");
        isim = isim.substring(0, isim.indexOf("/"));
        flowerClasses=new ArrayList<>();


        myRef.child("Flowers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    flowerClasses.add(dataSnapshot.getValue(FlowerClass.class));
                    flowerClass = dataSnapshot.getValue(FlowerClass.class);
                    if(flowerClass.getBaslik().contains(isim)){
                        Toast.makeText(BilgiActivity.this, "calisti", Toast.LENGTH_SHORT).show();
                        baslik.setText(flowerClass.getBaslik());
                        Glide.with(BilgiActivity.this).load(flowerClass.getUrl()).into(imageView);
                        bilgi.setText(flowerClass.getBilgi());
                    }
                }

              /*  for(FlowerClass flowerClass1 : flowerClasses){

                }*/


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BilgiActivity.this, "Hata!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}