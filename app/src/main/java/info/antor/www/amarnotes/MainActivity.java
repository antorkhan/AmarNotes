package info.antor.www.amarnotes;


import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {

    EditText noteText;
    DatabaseHelper databaseHelper;
    private AdView adView;
    String addUnitKey="ca-app-pub-7485001410940669/2514328687";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this,addUnitKey);
        databaseHelper = new DatabaseHelper(this);
        adView=findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);
//        adView.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                // Code to be executed when an ad finishes loading.
//                Toast.makeText(getApplicationContext(),"onAdload",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                // Code to be executed when an ad request fails.
//                Toast.makeText(getApplicationContext(),"Failed to load",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAdOpened() {
//                // Code to be executed when an ad opens an overlay that
//                // covers the screen.
//                Toast.makeText(getApplicationContext(),"add opened",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//                // Code to be executed when the user has left the app.
//            }
//
//            @Override
//            public void onAdClosed() {
//                // Code to be executed when when the user is about to return
//                // to the app after tapping on an ad.
//            }
//        });


    }
    public boolean insertData(View view){
        noteText=findViewById(R.id.noteText);
        String input=noteText.getText().toString();
        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());

        boolean flag=databaseHelper.insertData(input);
        if(flag){
            noteText.clearComposingText();
            Toast.makeText(getApplicationContext(),"Note Added",Toast.LENGTH_SHORT).show();
            noteText.getText().clear();
        }
        else{

        }


        return flag;
    }


    public void getAll(View view) {
        Cursor results= databaseHelper.getAll();
       // showMessage("Error!","No data found!");
        //Toast.makeText(getApplicationContext(),"getAllClicked",Toast.LENGTH_SHORT).show();
        if(results.getCount()==0){
            showMessage("Sorry!","No notes found!");
            return;
        }
        else{
            StringBuffer buffer=new StringBuffer();
            int counter=1;
            while(results.moveToNext()){
                buffer.append("Note # "+counter+"\n"+results.getString(1)+"\n\n");
                counter++;


            }
            showMessage("Notes",buffer.toString());
        }
        return;
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearAll(View view) {

        boolean flag=databaseHelper.clearAll();
        if(flag)
            Toast.makeText(this,"Cleared", Toast.LENGTH_SHORT).show();
    }
}
