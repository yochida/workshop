package com.tracklocation.workshop;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper mHelper;
    List<String> allUser;
    DB_Structure user;
    DB_Structure userByID;
    DB_Structure userUpdate;
    private EditText mUsername;
    private EditText mPassword;
    private RelativeLayout mButtonlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DBHelper(this);
        user = new DB_Structure();
        userByID = new DB_Structure();
        userUpdate = new DB_Structure();
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mButtonlogin = (RelativeLayout) findViewById(R.id.buttonLogin);

		// github
        // setup preference
        new Prefs.Builder()
                .setContext(getApplicationContext())
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        setEvent();

        // restore last preference
        mUsername.setText(Prefs.getString("username", ""));
        mPassword.setText(Prefs.getString("password", ""));
        if(!Prefs.getString("username", "").equals("") && !Prefs.getString("password", "").equals("")){
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
        }

        //region "sqlite CRUD"

        // add user
        /*user.setUsername("yochida");
        user.setPassword("2015");
        mHelper.addUser(user);*/

        // update
        /*userUpdate.setId(2);
        userUpdate.setUsername("taweesak");
        userUpdate.setPassword("5710997114");
        mHelper.updateUser(userUpdate);*/

        // get * from
        allUser = mHelper.getUser();
        Log.d("getData", "* FROM: " + allUser);

        /*userByID = mHelper.getUserID(mUsername.getText().toString() , mPassword.getText().toString());
        Log.d("getData", "get by id: " + userByID.getId() + userByID.getUsername() + userByID.getPassword());*/

        // delete
        //mHelper.deleteUser("3");

        //endregion

    }

    private void setEvent() {
        mButtonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userByID = mHelper.getUserID(mUsername.getText().toString() , mPassword.getText().toString());
                Log.d("getData", "get by id: " + userByID.getId() + userByID.getUsername() + userByID.getPassword());

                if(userByID.getId() != 0){
                    // save username into preference
                    Prefs.putString("username", userByID.getUsername());
                    Prefs.putString("password", userByID.getPassword());

                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
