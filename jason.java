package com.example.toterandroid.class_;

import android.os.StrictMode;

import org.json.JSONObject;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class json {
/*


                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("name","hhh");
                String s=json_(Url + "index.php", jsonObject);
                for(String ss:s.split("\n")){
                    if(ss.startsWith("<jason>")&&ss.endsWith("</jason>")) {
                        s=ss.substring(("<jason>").length(),ss.length()-("</jason>").length());
                        break;
                    }
                    }


                    jsonObject = new JSONObject(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
               // jsonObject.getInt("age")

<?php
$json = file_get_contents('php://input');
$obj = json_decode($json);
$name = $obj->{'name'};

$myObj->name = $name;
$myObj->age = 30;
$myJSON = json_encode($myObj);
echo "\n<jason>$myJSON</jason>\n";
?>
//*************************************************************************************
        try {
            String myjsonString = "{\"phonetype\":\"N95\",\"cat\":\"WP\"}";
            JSONObject jsonObject = new JSONObject(myjsonString );
            Toast.makeText(this, ""+jsonObject.getString("phonetype"), Toast.LENGTH_SHORT).show();
        } catch (Exception ignored) {}
*/
//**************************************************************************
    /*

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("name","value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final TextView t=(TextView)findViewById(R.id.textView2);
        t.setText(json_(Url + "index.php",jsonObject));

<?php
$json = file_get_contents('php://input');
$obj = json_decode($json);
$rate = $obj->{'name'};
    echo $rate;
?>
    */

      private static StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    public static String json_(String host,JSONObject jsonObject) {
        StrictMode.setThreadPolicy(policy);
       try {

           OutputStream os = null;
           InputStream is = null;
           HttpURLConnection conn = null;
           try {
               //constants
               URL url = new URL(host);

               String message = jsonObject.toString();
               conn = (HttpURLConnection) url.openConnection();
               conn.setReadTimeout(10000 /*milliseconds*/);
               conn.setConnectTimeout(15000 /* milliseconds */);
               conn.setRequestMethod("POST");
               conn.setDoInput(true);
               conn.setDoOutput(true);
               conn.setFixedLengthStreamingMode(message.getBytes().length);

               //make some HTTP header nicety
               conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
               conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

               //open
               conn.connect();

               //setup send
               os = new BufferedOutputStream(conn.getOutputStream());
               os.write(message.getBytes());
               //clean up
               os.flush();

               //do somehting with response
               is = conn.getInputStream();
               Scanner scan = new Scanner(is).useDelimiter("\\A");
              return scan.hasNext() ? scan.next() : null;

           } catch (IOException e) {
               e.printStackTrace();
               return null;
           } finally {
               //clean up
               try {
                   os.close();
                   is.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }

               conn.disconnect();
           }


       } catch (Exception e) {
           return null;
       }
   }
}
