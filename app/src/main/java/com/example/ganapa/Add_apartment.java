package com.example.ganapa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.os.Bundle;

public class Add_apartment extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText emailEditText, nameEditText, addressEditText, locationEditText,
            districtEditText, roomEditText, rentEditText, accommodationEditText, descriptionEditText;
    private ImageView imageView;
    private Button attachImageButton, submitButton;
    private byte[] imageData;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apartment);

        databaseHelper = new DatabaseHelper(this);

        emailEditText = findViewById(R.id.emailEditText);
        nameEditText = findViewById(R.id.nameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        locationEditText = findViewById(R.id.locationEditText);
        districtEditText = findViewById(R.id.districtEditText);
        roomEditText = findViewById(R.id.roomEditText);
        rentEditText = findViewById(R.id.rentEditText);
        accommodationEditText = findViewById(R.id.accommodationEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);

        imageView = findViewById(R.id.imageView);
        attachImageButton = findViewById(R.id.attachImageButton);
        submitButton = findViewById(R.id.submitButton);

        attachImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addApartment();
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    private void addApartment() {
        // Convert image to byte array
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        imageData = stream.toByteArray();

        // Get other details
        String email = emailEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String location = locationEditText.getText().toString();
        String district = districtEditText.getText().toString();
        String room = roomEditText.getText().toString();
        String rent = rentEditText.getText().toString();
        String accommodation = accommodationEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        // Add apartment to the database
        boolean isInserted = databaseHelper.add_apartment(imageData, email, name, address, location,
                district, room, rent, accommodation, description);

        if (isInserted) {
            Toast.makeText(this, "Apartment added successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

            
        } else {
            Toast.makeText(this, "Failed to add apartment", Toast.LENGTH_SHORT).show();
        }
    }

    private void startActivity(Context applicationContext, Class<MainActivity> mainActivityClass) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}