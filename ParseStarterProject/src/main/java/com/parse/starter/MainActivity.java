/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseQuery;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;
//import java.text.ParseException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText usernameField;
    EditText passwordField;
    TextView changeSignUpModeTextView;
    //Boolean signModeActive;
    Button signUpButton;
    Button logInButton;
    RelativeLayout relativeLayout;
    ImageView logo;

    public void showList(){
        Intent i = new Intent(getApplicationContext(),NewUser.class);
        startActivity(i);
    }

    public void logInMode(View v) {
        Log.i("AppInfo", "Inside click");
        ParseUser.logInInBackground(String.valueOf(usernameField.getText()), String.valueOf(passwordField.getText()), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Toast.makeText(getApplicationContext(), "Successfully logged in", Toast.LENGTH_LONG).show();
                    showList();
                } else  {
                    Toast.makeText(getApplicationContext(), "Failed to log in", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

  public void signUpOrLogin(View view) {
      Log.i("AppInfo", String.valueOf(usernameField.getText()));
      Log.i("AppInfo", String.valueOf(passwordField.getText()));


          ParseUser user = new ParseUser();
      user.setUsername(String.valueOf(usernameField.getText()));
      user.setPassword(String.valueOf(passwordField.getText()));

      user.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {

              if (e == null) {

                  Toast.makeText(getApplicationContext(), "Signed up successfully", Toast.LENGTH_LONG).show();
                  showList();
              } else {
                  Toast.makeText(getApplicationContext(), e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_LONG).show();
              }

          }
      });

  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
    //setTitle("Instagram");
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
      usernameField = (EditText) findViewById(R.id.username);
      passwordField = (EditText) findViewById(R.id.password);
      //changeSignUpModeTextView = (TextView) findViewById(R.id.changeSignUpMode);
      signUpButton = (Button) findViewById(R.id.signUpButton);
      logInButton = (Button) findViewById(R.id.logIn);
      relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
      logo = (ImageView) findViewById(R.id.insta);

      if(ParseUser.getCurrentUser() != null){
          showList();
      }
      //passwordField.setOnKeyListener(this);
      //signModeActive = true;
      //changeSignUpModeTextView.setOnClickListener(this);
      relativeLayout.setOnClickListener(this);
      logo.setOnClickListener(this);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }


    @Override
    public void onClick(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
}