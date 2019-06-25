package com.example.databaseencriptionexample.view;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.databaseencriptionexample.R;
import com.example.databaseencriptionexample.database.DatabaseHelper;
import com.example.databaseencriptionexample.database.SecretsDatabaseKeyGenerator;
import com.example.databaseencriptionexample.model.User;
import com.example.databaseencriptionexample.model.UserDetails;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private ArrayList<UserDetails> userDetailsList = new ArrayList<>();
    private TextView txtDisplayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        SQLiteDatabase.loadLibs(this);
    }

    private void initView() {
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        txtDisplayData = findViewById(R.id.display_data);
    }

    public void addData(View view) {
        if (firstName.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.fill_first_name), Toast.LENGTH_SHORT).show();
            return;
        }
        if (lastName.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.fill_last_name), Toast.LENGTH_SHORT).show();
            return;
        }


        if (!firstName.getText().toString().isEmpty() && !lastName.getText().toString().isEmpty()) {
            SQLiteDatabase database = DatabaseHelper.getInstance(this).getWritableDatabase(SecretsDatabaseKeyGenerator.getInstance(this).getKey());

            ContentValues values = new ContentValues();
            values.put(User.UserDetails.COLUMN_FIRST_NAME, firstName.getText().toString());
            values.put(User.UserDetails.COLUMN_LAST_NAME, lastName.getText().toString());

            long insertId = database.insert(User.UserDetails.TABLE_NAME, null, values);
            if (insertId >= 0) {
                Toast.makeText(this, getString(R.string.value_inserted_in_db), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, getString(R.string.error_value_inserting), Toast.LENGTH_LONG).show();
            }
            firstName.setText("");
            lastName.setText("");
        }
    }

    public void fetchData(View view) {
        userDetailsList.clear();
        SQLiteDatabase database = DatabaseHelper.getInstance(this).getWritableDatabase(SecretsDatabaseKeyGenerator.getInstance(this).getKey());

        Cursor cursor = database.query(User.UserDetails.TABLE_NAME, new String[]{User.UserDetails.COLUMN_FIRST_NAME, User.UserDetails.COLUMN_LAST_NAME}, null, null, null, null, null);
        Log.d(MainActivity.class.getSimpleName(), "Rows count: " + cursor.getCount());

        if (cursor.getCount() == 0) {
            Toast.makeText(this, getString(R.string.db_is_empty), Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                UserDetails userDetails = new UserDetails(cursor.getString(cursor.getColumnIndex(User.UserDetails.COLUMN_FIRST_NAME)), cursor.getString(cursor.getColumnIndex(User.UserDetails.COLUMN_LAST_NAME)));
                userDetailsList.add(userDetails);
            }
        }

        cursor.close();
        database.close();

        showDatabaseValues(userDetailsList);
    }

    private void showDatabaseValues(ArrayList<UserDetails> userDetailsList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < userDetailsList.size(); i++) {
            UserDetails userDetails = userDetailsList.get(i);
            stringBuilder.append(i + " : " + userDetails.getmUserFirstName() + " " + userDetails.getmUserLastName()).append("\n");
        }

        txtDisplayData.setText(stringBuilder.toString());

    }

}
