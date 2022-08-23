package com.example.medicaltranslator;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.medicaltranslator.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
//import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;

public class MainActivity extends SideMenuBaseActivity {

    ActivityMainBinding activityMainBinding;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    ImageButton searchImage_btn;


//    EditText recognize_product_name_txt;
    protected String readText = "\"   \"";

    private static final int CAMERA_REQUEST_CODE = 200;
    private static final int STORAGE_REQUEST_CODE = 400;
    private static final int IMAGE_PICK_GALLERY_CODE = 1000;
    private static final int IMAGE_PICK_CAMERA_CODE = 1001;
//    private final String SAMPLE_CROPPED_IMG = "CropImage";
//    private ImageView imgView;
    private TextRecognizer textRecognizer;
    Uri uri_image;
    Uri uri_result;
    String uri_result_string = "";
    String camera_permission[];
    String storage_permission[];
//    Bitmap bitmap = null;
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(drawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera_permission = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storage_permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        allocateActivityTitle("Medical Translator App");
        setContentView(activityMainBinding.getRoot());

//        recognize_product_name_txt = findViewById(R.id.recognized_product_name_etxt);
//        recognize_product_name_txt.setText(readText);

//        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
//        textRecognizer = TextRecognition.getClient(new TextRecognizerOptions.Builder().build());
//        drawerLayout = findViewById(R.id.drawer_layout_main_page);
//        drawerLayout = findViewById(R.id.main_drawer_layout);
//        navigationView = findViewById(R.id.nav_menu_view);
//        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.draw_open,R.string.draw_close);
//        drawerLayout.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int item_id = item.getItemId();
//                if (item_id == R.id.nav_account) {
//                    startActivity(new Intent(MainActivity.this,SideMenuAccountActivity.class));
//                }
//                drawerLayout = findViewById(R.id.drawer_layout_main_page);
//                drawerLayout.closeDrawer(GravityCompat.START);
//                return true;
//            }
//        });

        searchImage_btn = findViewById(R.id.search_main_btn);
        searchImage_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkCameraPermission()) {
                    pickCamera();
//                    recognizeImageText();
                }
                else {
                    requestCameraPermission();
                }
            }
        });


    }
//    public void openMenuAccount() {
//        Intent intent = new Intent(this, SideMenuAccountActivity.class);
//        startActivity(intent);
//    }
//    public void openMenuHelp() {
//        Intent intent = new Intent(this, Menu_Help_Activity.class);
//        startActivity(intent);
//    }
//    public void openMenuLogin() {
//        Intent intent = new Intent(this, Menu_Login_Activity.class);
//        startActivity(intent);
//    }
//    public void openMenuSaved() {
//        Intent intent = new Intent(this, Menu_Saved_Activity.class);
//        startActivity(intent);
//    }
//    public void openMenuHistory() {
//        Intent intent = new Intent(this, Menu_History_Activity.class);
//        startActivity(intent);
//    }
//    public void openMenuSetting() {
//        Intent intent = new Intent(this, Menu_Setting_Activity.class);
//        startActivity(intent);
//    }

//    @Override
//    public  void onBackPressed() {
//        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }
//        else{
//            super.onBackPressed();
//        }
//    }
//    public void clickMenu(View view) {
//        OpenDrawer(drawerLayout);
//    }
//    public void closeMenu(View view) {
//        CloseDrawer(drawerLayout);
//    }
//
//    public static void OpenDrawer(DrawerLayout drawerLayout) {
//        drawerLayout.openDrawer(GravityCompat.START);
//    }
//    public static void CloseDrawer(DrawerLayout drawerLayout) {
//        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }
//    }
//
//    public void clickAccount(View view) {
//        redirectActivity(this,SideMenuAccountActivity.class);
//    }
//    public void clickSaved(View view) {
//        redirectActivity(this,SideMenuSavedActivity.class);
//    }
//    public void clickHistory(View view) {
//        redirectActivity(this,SideMenuHistoryActivity.class);
//    }
//    public void clickSetting(View view) {
//        redirectActivity(this,SideMenuSettingActivity.class);
//    }
//    public void clickHelp(View view) {
//        redirectActivity(this,SideMenuHelpActivity.class);
//    }
//    public void clickLogout(View view) {
//        logout(this);
//    }
//
//    public static void logout(Activity activity) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        builder.setTitle("Log Out");
//        builder.setMessage("Are you sure you want to logout ?");
//        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                activity.finishAffinity();
//                System.exit(0);
//            }
//        });
//        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//        builder.show();
//    }
//
//    public static void redirectActivity(Activity new_activity,Class new_class) {
//        Intent intent = new Intent(new_activity,new_class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        new_activity.startActivity(intent);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // close Drawer
//        CloseDrawer(drawerLayout);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if(drawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }

        int id = item.getItemId();
        if (id == R.id.upload_image) {
            showImageImportDialog();
        }
//        if (id == R.id.settings) {
//            Toast.makeText(this,"Settings", Toast.LENGTH_SHORT).show();
//        }
        return super.onOptionsItemSelected(item);
    }
//    public void clickUploadImage() {
//        showImageImportDialog();
//    }

    private void showImageImportDialog() {
        String[] items = {" Camera", " Gallery"};
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Select Image");
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    //camera option clicked
                    if (!checkCameraPermission()){
                        requestCameraPermission();
                    }
                    else {
                        pickCamera();
//                        recognizeImageText();
                    }
                }
                if (i == 1) {
                    //gallery option clicked
                    if (!checkStoragePermission()){
                        requestStoragePermission();
                    }
                    else {
                        pickGallery();
//                        recognizeImageText();
                    }
                }
            }
        });
        dialog.create().show();
    }




    private void pickGallery() {
        Intent gallery_intent = new Intent(Intent.ACTION_PICK);
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        gallery_intent.setType("image/*");
        startActivityForResult(gallery_intent,IMAGE_PICK_GALLERY_CODE);
//        galleryActivityResultLauncher.launch(intent);
    }
//    private ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        Intent data = result.getData();
//                        uri_image = data.getData();
////                        imgView.setImageURI(uri_image); // set to ImageView
//                    }
//                    else{
//                        Toast.makeText(MainActivity.this,"Cancelled...",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//    );

    private void pickCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"NewPicture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"Image To Text");
        uri_image = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT,uri_image);
        startActivityForResult(camera_intent,IMAGE_PICK_CAMERA_CODE);
//        cameraActivityResultLauncher.launch(camera_intent);
    }
//    private ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
////                        imgView.setImageURI(uri_image); // set to ImageView
//                    }
//                    else{
//                        Toast.makeText(MainActivity.this,"Cancelled...",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//    );

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,storage_permission,STORAGE_REQUEST_CODE);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_DENIED);
        return result;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this,camera_permission,CAMERA_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {
        boolean result1 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_DENIED);
        boolean result2 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_DENIED);
        return result1 && result2;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writestorageAccpted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && writestorageAccpted) {
                        pickCamera();
                    } else {
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case STORAGE_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean writestorageAccpted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (writestorageAccpted) {
                        pickGallery();
                    } else {
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setCropMenuCropButtonIcon(R.drawable.ic_check_32)
                        .start(this);
//                Uri imageUri1 = data.getData();
//                if (imageUri1 != null) {
//                    startCrop(imageUri1);
                }
            }
            if (requestCode == IMAGE_PICK_CAMERA_CODE) {
                CropImage.activity(uri_image)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setCropMenuCropButtonIcon(R.drawable.ic_check_32)
//                        .setActivityMenuIconColor(Color.parseColor("#ADD8E6"))
                        .start(this);
//                Uri imageUri2 = uri_image;
//                if (imageUri2 != null) {
//                    startCrop(imageUri2);
//                }
            }
//            if (requestCode == UCrop.REQUEST_CROP) {
////                Uri imageUriResultCrop = UCrop.getOutput(data);
//                uri_result = UCrop.getOutput(data);
//                if (uri_result != null) {
//                    imgView.setImageURI(uri_result);
////                    openItemList();
////                    if (imgView == null) {
////                        Toast.makeText(MainActivity.this,"Please pick image first",Toast.LENGTH_SHORT).show();
////                    }
////                    else {
////                        recognizeImageText();
////                    }
//                }
//            }
//            if (resultCode == UCrop.RESULT_ERROR) {
//                final Throwable cropError = UCrop.getError(data);
//            }
//        }
        //get cropped image
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult crop_result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                uri_result = crop_result.getUri();
                uri_result_string = uri_result.toString();
                Intent showItemIntent = new Intent(MainActivity.this,ItemListActivity.class);
                showItemIntent.putExtra("uri",uri_result_string);
                startActivity(showItemIntent);
//                recognizeImageText();

//                imgView.setImageURI(uri_result);  //show cropped image
//                recognizeImageText();
//                Intent showItemIntent = new Intent(MainActivity.this,ItemListActivity.class);
//                startActivity(showItemIntent);
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri_result);
//                } catch (IOException e) {
//                    Toast.makeText(this,"Failed preparing image due to "+e.getMessage(),Toast.LENGTH_SHORT).show();
//                }
            }
        }
    }

//    private void startCrop(@NonNull Uri uri) {
//        String destinationFileName = SAMPLE_CROPPED_IMG;
//        destinationFileName += ".jpg";
//        UCrop uCrop = UCrop.of(uri,Uri.fromFile(new File(getCacheDir(),destinationFileName)));
//
//        uCrop.withAspectRatio(1,1);
////        uCrop.withAspectRatio(3,4);
////        uCrop.withAspectRatio(2,3);
////        uCrop.withAspectRatio(16,9);
////        uCrop.useSourceImageAspectRatio();
//        uCrop.withMaxResultSize(450,450);
//        uCrop.withOptions(getCropOption());
//        uCrop.start(MainActivity.this);
//    }
//    private UCrop.Options getCropOption() {
//        UCrop.Options options = new UCrop.Options();
//        options.setCompressionQuality(70);
////        options.setCompressionFormat(Bitmap.CompressFormat.PNG);
////        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
//        options.setHideBottomControls(false);
//        options.setFreeStyleCropEnabled(true);
//        options.setStatusBarColor(getResources().getColor(R.color.light_blue));
//        options.setToolbarColor(getResources().getColor(R.color.white));
//        options.setToolbarTitle("Crop Image");
//        return options;
//    }

//    private void recognizeImageText() {
////        Intent showItemIntent = new Intent(MainActivity.this,ItemListActivity.class);
////        startActivity(showItemIntent);
//        try {
//            InputImage inputImage = InputImage.fromFilePath(this, uri_result);
//
//            Task<Text> textResult = textRecognizer.process(inputImage)
//                    .addOnSuccessListener(new OnSuccessListener<Text>() {
//                        @Override
//                        public void onSuccess(Text text) {
//                            readText = "\"" + text.getText() +"\"";
////                            recognize_product_name_txt.setText(readText);
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(MainActivity.this,"Failed recognizing text due to "+e.getMessage(),Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        } catch (Exception e) {
//            Toast.makeText(this,"Failed preparing image due to "+e.getMessage(),Toast.LENGTH_SHORT).show();
//        }
//    }



}
