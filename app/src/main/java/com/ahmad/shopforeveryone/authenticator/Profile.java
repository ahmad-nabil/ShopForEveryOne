package com.ahmad.shopforeveryone.authenticator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.ahmad.shopforeveryone.Home;
import com.ahmad.shopforeveryone.R;
import com.ahmad.shopforeveryone.authenticator.DataBase.firebaseauthmanager;
import com.ahmad.shopforeveryone.databinding.ActivityProfileBinding;
import com.ahmad.shopforeveryone.databinding.DialogPickImgBinding;
import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.varunest.sparkbutton.SparkEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    ActivityProfileBinding profileBinding;
    firebaseauthmanager authManager = new firebaseauthmanager();

    Profilemodel model;
    Uri imguri;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inflate Data Binding
        profileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        //initialize live model
        model = new ViewModelProvider(this).get(Profilemodel.class);
        profileBinding.setProfilemodel(model);
        profileBinding.setLifecycleOwner(this);
//request permission
        requestPermission();
//get img URI
        model.getUserData().observe(this, dataUser -> {
            if (dataUser.profileImg != null) {
                imguri = Uri.parse(dataUser.profileImg);
                Glide.with(Profile.this).load(Uri.parse(dataUser.profileImg)).into(profileBinding.profileIMG);
            }
        });

        //set listener for buttons
        profileBinding.updateimg.setOnClickListener(this);
        //listener nav btn back
        profileBinding.barProfile.setNavigationOnClickListener(v -> {
            startActivity(new Intent(Profile.this, Home.class));
            finish();
        });
        //get location using GPS Fused if clicked in btnAddress
        profileBinding.getlocationbtn.setOnClickListener(v -> {
            fetchLocation();
        });
//UpdateData in Database
        profileBinding.EditData.setOnClickListener(v -> {
            UpdateData();
        });
//logout btn
        logoutOperation();
    }

    @SuppressLint("MissingPermission")
    private void fetchLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);

    }

    private void UpdateData() {
        if (!TextUtils.isEmpty(profileBinding.EmailProfileEt.getText()) &&
                !TextUtils.isEmpty(profileBinding.NameProfileET.getText()) &&
                !TextUtils.isEmpty(profileBinding.passwordprofileEt.getText())) {
            HashMap<String, Object> update = new HashMap<>();
            update.put("Email", profileBinding.EmailProfileEt.getText().toString());
            update.put("FullName", profileBinding.NameProfileET.getText().toString());
            if (!TextUtils.isEmpty(profileBinding.phonenumber.getText().toString())) {
                update.put("numberPhone", profileBinding.phonenumber.getText().toString());
            }
            if (!TextUtils.isEmpty(profileBinding.addressET.getText().toString())) {
                update.put("Location", profileBinding.addressET.getText().toString());
            }
            update.put("password", profileBinding.passwordprofileEt.getText().toString());
            if (imguri != null) {
                update.put("profileImg", imguri.toString());

            }


            authManager.updateEmail(profileBinding.EmailProfileEt.getText().toString()).addOnSuccessListener(unused ->
                    authManager.updatepassword(profileBinding.passwordprofileEt.getText().toString())
                            .addOnSuccessListener(unused1 ->
                                    authManager.UpdateUserData(authManager.getCurrentUser().getUid(), update)));
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == profileBinding.updateimg.getId()) {
            show_dialog();
        }
    }

    //logout spark button
    private void logoutOperation() {
        profileBinding.logoutbtn.setAnimationSpeed(1.5f);
        profileBinding.logoutbtn.setEventListener(new SparkEventListener() {
            @Override
            public void onEvent(ImageView button, boolean buttonState) {

            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {
                authManager.SignOut();
                startActivity(new Intent(Profile.this, AuthManager.class));
                finish();
            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }
        });
    }
    //check permission

    private void requestPermission() {
        String[] Permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        ActivityCompat.requestPermissions(this, Permission, 0);
    }


    //show dialog for cam and gallery to update img
    private void show_dialog() {
        DialogPickImgBinding pickImg = DialogPickImgBinding.inflate(getLayoutInflater());
        AlertDialog alertDialogBuilder = new MaterialAlertDialogBuilder(this).setView(pickImg.getRoot()).show();
        pickImg.GalleryBtn.setOnClickListener(v -> {
            OpenGallery();
            alertDialogBuilder.dismiss();
        });
        pickImg.cameraBtn.setOnClickListener(v -> {
            alertDialogBuilder.dismiss();
            OpenCamera();
        });
    }

    private void OpenCamera() {
        Intent OpenCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imgPicker.launch(OpenCam);
    }

    private void OpenGallery() {
        Intent OpenGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imgPicker.launch(OpenGallery);
    }


    //request permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            boolean allPermissionGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allPermissionGranted = false;
                    break;
                }
            }
            if (!allPermissionGranted) {

                if (shouldShowRationale()) {
                    // Show rationale dialog
                    showRationaleDialog();
                } else {
                    // The user denied the permission and selected "Don't ask again"
                    informUserAboutPermission();
                }
            }
        }
    }

    private void informUserAboutPermission() {
        new MaterialAlertDialogBuilder(this).setTitle("Permission Required")
                .setMessage("It seems like you have denied the required permissions. Please grant the permissions.").setPositiveButton("ok", (dialog, which) -> {
                    OPENSETTING();
                }).show();
    }

    private void OPENSETTING() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private void showRationaleDialog() {
        new MaterialAlertDialogBuilder(this).setTitle("Permission Required")
                .setMessage("We need permissions to update the image and location. Please grant the permissions.").setPositiveButton("ok", (dialog, which) -> {
                    requestPermission();
                }).show();
    }

    private boolean shouldShowRationale() {
        return ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) ||
                ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) ||
                ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            if (locationResult == null) {
                return;
            }
            for (Location location : locationResult.getLocations()) {
                GecoderLocation(location.getLongitude(), location.getLatitude());

            }
            super.onLocationResult(locationResult);
        }

        @Override
        public void onLocationAvailability(@NonNull LocationAvailability locationAvailability) {
            super.onLocationAvailability(locationAvailability);
        }
    };

    //gecoder address
    private void GecoderLocation(double longitude, double latitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                String fulladdress = address.getAddressLine(0);
                profileBinding.addressET.setText(fulladdress);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    ActivityResultLauncher<Intent> imgPicker = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        imguri = result.getData().getData();
                        if (imguri != null) {
                            Glide.with(Profile.this).load(imguri).into(profileBinding.profileIMG);
                        } else {
                            Bundle bundle = result.getData().getExtras();
                            if (bundle != null) {
                                Bitmap bitmap = (Bitmap) bundle.get("data");
                                getImageUri(Profile.this, bitmap);
                                Glide.with(Profile.this).load(imguri).into(profileBinding.profileIMG);

                            }
                        }
                    }
                }
            });

    //convert img camera to uri
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        imguri = Uri.parse(path);
        return Uri.parse(path);
    }
}