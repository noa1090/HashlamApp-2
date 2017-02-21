package com.hashlamapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ofeko on 21/02/2017.
 */
public class Manager {
    private Context context;

    public void login(String username, String password) {
        ConnectionRequest cr = new ConnectionRequest();
        String[] params = {Config.LOGIN, username, password};
        cr.execute(params);
    }


    private class ConnectionRequest extends AsyncTask<String, Void, String[]> {

        private ArrayList<String> answer;
        private HttpURLConnection conn = null;

        private boolean canceled = false;

        public ConnectionRequest() {
            answer = new ArrayList<String>();
        }

        @Override
        protected String[] doInBackground(String... params) {
            String address, attr, Action = params[0];
            OutputStreamWriter request = null;
            if (Action.equals(Config.LOGIN)) {
                String regId = null;
                address = Config.SERVER_URL + "login.php";
                attr = "username=" + params[1] + "&password=" + params[2];
                regId = InstanceID.getInstance(context).getToken(Config.GOOGLE_SENDER_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                Log.d("RegID", regId);
            } else if (Action.equals(Config.REGISTER)) {

                try {


                } catch (IOException e) {
                }
                address = Config.SERVER_URL + "register.php";
                attr = "regId=" + regId + "&username=" + params[1] + "&password=" + params[2] + "&nickname=" + params[3];
            }
            try {
                URL url = new URL(address);
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                request = new OutputStreamWriter(conn.getOutputStream());
                request.write(attr);
                request.flush();
                request.close();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    answer.add(inputLine);
                in.close();
                Log.d("answer", answer.toString());
            } catch (IOException e) {
            } finally {
                if (conn != null)
                    conn.disconnect();
            }
            return params;
        }


        @Override
        protected void onPostExecute(String... params) {
            String Action = params[0];
            if (Action.equals(Config.LOGIN)) {
                if (answer.get(0).equals(Config.success)) {
                    Toast.makeText(context, context.getString(R.string.successLogin), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else
                    Toast.makeText(context, context.getString(R.string.errorLogin), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
