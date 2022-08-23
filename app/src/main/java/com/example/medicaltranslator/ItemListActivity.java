package com.example.medicaltranslator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.text.Text;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

public class ItemListActivity extends MainActivity {

    LinearLayout itemList_linearLayout;
    EditText recognize_product_name_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_lists);

//        itemList_linearLayout = findViewById(R.id.item_lists_page);
        recognize_product_name_txt = findViewById(R.id.recognized_product_name_etxt);

        String uri_result_string = getIntent().getStringExtra("uri");
        Uri my_uri = Uri.parse(uri_result_string);
        recognizeImageText(getApplicationContext(),my_uri);

//        ItemListActivity itemListActivity = new ItemListActivity();
        recognize_product_name_txt.setText(this.uri_result_string);

    }


    public void recognizeImageText(Context context, Uri uri) {
        TextRecognizer textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        try {
            InputImage inputImage = InputImage.fromFilePath(context, uri);

            Task<Text> textResult = textRecognizer.process(inputImage)
                    .addOnSuccessListener(new OnSuccessListener<Text>() {
                        @Override
                        public void onSuccess(Text text) {
                            recognize_product_name_txt.setText(text.getText());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ItemListActivity.this,"Failed recognizing text due to "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(this,"Failed preparing image due to "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


}