package info.antor.www.amarnotes;


import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {

    EditText noteText;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

    }
    public boolean insertData(View view){
        noteText=findViewById(R.id.noteText);
        String input=noteText.getText().toString();
        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());

        boolean flag=databaseHelper.insertData(input);
        if(flag){
            Toast.makeText(getApplicationContext(),"Note Added",Toast.LENGTH_SHORT).show();
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
