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
import com.parse.ParseObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseAnalytics;
import com.parse.ParseQuery;

import com.parse.ParseException;

import java.util.List;
//import java.text.ParseException;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());

/*
      ParseObject parseObject = new ParseObject("NewScore");
      parseObject.put("username", "poo2");
      parseObject.put("score", 100);
      parseObject.put("username", "poo3");
      parseObject.put("score", 100);
      parseObject.put("username", "poo4");
      parseObject.put("score", 100);
      parseObject.put("username", "poo5");
      parseObject.put("score", 100);

      try {
          Log.i("whatThe", "trying");
          parseObject.saveInBackground();
          Log.i("whatThe", "sucess");
      } catch (Exception e) {
          Log.i("whatThe", "Exception");
          e.printStackTrace();
      }

*/
/*
      ParseQuery<ParseObject> query = ParseQuery.getQuery("NewScore");
      query.findInBackground(new FindCallback<ParseObject>() {
          @Override
          public void done(List<ParseObject> objects, ParseException e) {
              if(e == null){
                  Log.i("findList","size : "+ objects.size());
                  for(ParseObject object : objects){
                      Log.i("findList", (String) object.get("username"));
                      //object.put("score",200);
                      //object.saveInBackground();
                  }
              }
              else{
                  Log.i("findList","exception");
                  e.printStackTrace();
              }
          }
      });
*/

     ParseQuery<ParseObject> query = ParseQuery.getQuery("NewScore");
     //query.whereEqualTo("username","poo");

      query.findInBackground(new FindCallback<ParseObject>() {
          @Override
          public void done(List<ParseObject> objects, ParseException e) {
            if(e== null) {
                for (ParseObject object : objects) {
                    //object.put("score", 300);
                    //object.saveInBackground();
                    Log.i("whatThe", (String) object.get("username"));
                    Log.i("whatThe", String.valueOf(object.get("score")));
                }
            }
          }
      });
/*
      ParseQuery<ParseObject> query =  ParseQuery.getQuery("NewScore");
      query.getInBackground("3Lggm86NZS", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject object, com.parse.ParseException e) {
              if (e == null) {
                  object.put("username", "yashasnew");

                  try {
                      Log.i("whatThe", "trying");
                      object.saveInBackground();
                      Log.i("whatThe", "sucess");
                  } catch (Exception t) {
                      Log.i("whatThe", "Exception");
                      t.printStackTrace();
                  }
              }
              else{
                  Log.i("whatThe", "Exception2");
                  e.printStackTrace();

              }

          }
      });


*/

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
}
