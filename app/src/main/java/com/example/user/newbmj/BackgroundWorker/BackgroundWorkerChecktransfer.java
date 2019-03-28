package com.example.user.newbmj.BackgroundWorker;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.user.newbmj.Activity.ChecktransferActivity;
import com.example.user.newbmj.Activity.MainActivity;
import com.example.user.newbmj.R;

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

public class BackgroundWorkerChecktransfer extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    public static String jsoncheck;
    private ArrayList<String> exData;
    private ProgressDialog progressDialog;

    public BackgroundWorkerChecktransfer(Context ctx) {
        context = ctx;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public String doInBackground(String... params) {
        exData = new ArrayList<String>();
        String type = params[0];
        String login_url = "https://telecomt108.000webhostapp.com/check_transfer.php";
        if (type.equals("transfer")) {
            try {
                String wallet_id = params[1];
                String transfer = params[2];
                String to_wallet = params[3];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("wallet_id", "UTF-8") + "=" + URLEncoder.encode(wallet_id, "UTF-8") + "&"
                        + URLEncoder.encode("transfer", "UTF-8") + "=" + URLEncoder.encode(transfer, "UTF-8") + "&"
                        + URLEncoder.encode("to_wallet", "UTF-8") + "=" + URLEncoder.encode(to_wallet, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result_check = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result_check += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                if (result_check != "") {
                    jsoncheck = result_check;
                    MainActivity.fa.finish();
                    Intent i = new Intent(context.getApplicationContext(), ChecktransferActivity.class);
                    ActivityOptions options =
                            ActivityOptions.makeCustomAnimation(context, R.anim.left_to_right, R.anim.right_to_left);
                    context.startActivity(i, options.toBundle());

                } else {

                }
                return result_check;
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
        alertDialog.setTitle("Check Status");

    }

    @Override
    protected void onPostExecute(String result1) {
        progressDialog.dismiss();
        alertDialog.setMessage("Wallet ID Incorrect!!!");
        if (result1 == "") {
            alertDialog.show();
        } else {

        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}


