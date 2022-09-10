package com.example.medicaltranslator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicaltranslator.database.MedicalBaseHelper;
import com.example.medicaltranslator.database.MedicalDbSchema;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.text.Text;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

//This class used to display the searched medicine results
public class ItemListActivity extends MainActivity {
    LinearLayout itemList_linearLayout;
    // TODO: Why EditText? Why not TextView?
    TextView recognize_product_name_txt;  //show the recognize medicine name

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_lists);

        // get database here
        db = new MedicalBaseHelper(getApplicationContext()).getReadableDatabase();

//        itemList_linearLayout = findViewById(R.id.item_lists_page);
        recognize_product_name_txt = findViewById(R.id.recognized_product_name_etxt);

        //recognize image text, get cropped image's uri -> parse uri -> recognize
        String uri_result_string_in_main = getIntent().getStringExtra("uri");
        Uri my_uri = Uri.parse(uri_result_string_in_main);
        recognizeImageText(getApplicationContext(),my_uri);

    }

    //recognize image text function
    public void recognizeImageText(Context context, Uri uri) {
        TextRecognizer textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        try {
            //import the image
            InputImage inputImage = InputImage.fromFilePath(context, uri);

            Task<Text> textResult = textRecognizer.process(inputImage)
                    .addOnSuccessListener(new OnSuccessListener<Text>() {
                        @Override
                        public void onSuccess(Text text) {
//                            recognize_product_name_txt.setText(text.getText());
                            /*
                            String processing
                            Just trying to get the first word out of the whole recognized text
                            assuming the word(s) you want to recognized is at the top of the crop picture
                            */
                            String word = text.getText().split("\n")[0];

                            recognize_product_name_txt.setText(word);

                            // Run query get result
                            String table = MedicalDbSchema.MedicalTable.NAME;
                            Cursor cursor = db.rawQuery("SELECT * FROM " + MedicalDbSchema.MedicalTable.NAME, null);

                            // TODO: do something with the table rows result (English, Chinese)
                            if(cursor.moveToFirst()) {
                                do {
                                    String medicineENG = cursor.getString(cursor.getColumnIndex(MedicalDbSchema.MedicalTable.Cols.ENG_NAME));
                                    String medicineCN = cursor.getString(cursor.getColumnIndex(MedicalDbSchema.MedicalTable.Cols.CN_NAME));
                                } while (cursor.moveToNext());
                            }

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