package com.example.user.newbmj.BackgroundWorker;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.user.newbmj.Activity.LoginActivity;
import com.example.user.newbmj.Activity.MainActivity;
import com.example.user.newbmj.R;
import com.example.user.newbmj.fragment.LoginFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class BackgroundWorker extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    public static String test, wallet_id, name;
    private ArrayList<String> exData;
    private ProgressDialog progressDialog;

    public BackgroundWorker(Context ctx) {
        context = ctx;
    }

    LoginFragment loginFragment;
    LoginActivity loginActivity;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public String doInBackground(String... params) {
        exData = new ArrayList<String>();
        String type = params[0];
        String login_url = "https://telecomt108.000webhostapp.com/login_main.php";
        if (type.equals("login")) {
            try {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                if (result != "") {
                    test = result;

                    loginActivity.fa.finish();
                    Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
                    ActivityOptions options =
                            ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
                    context.startActivity(i, options.toBundle());
                } else {


                }
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Processing ...");
        progressDialog.show();
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");

    }

    @Override
    protected void onPostExecute(String result) {
        progressDialog.dismiss();
        alertDialog.setMessage(result);
        if (result == "") {
            alertDialog.setMessage("Failed");
            alertDialog.show();
        } else {
            try {
                JSONObject jsonObject = new JSONObject(test);
                JSONArray exArray = jsonObject.getJSONArray("main");
                for (int i = 0; i < exArray.length(); i++) {
                    JSONObject jsonObj = exArray.getJSONObject(i);
                    wallet_id = jsonObj.getString("wallet_id");
                    name = jsonObj.getString("name");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
