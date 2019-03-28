package com.example.user.newbmj.BackgroundWorker;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;

import com.example.user.newbmj.Activity.MainActivity;
import com.example.user.newbmj.Activity.ShopqrActivity;
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

public class ConflimShop extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog.Builder builder;
    public static String test;
    private ArrayList<String> exData;
    private ProgressDialog progressDialog;
    public ConflimShop(Context ctx) {
        context = ctx;
    }

    @Override
    public String doInBackground(String... params) {
        exData = new ArrayList<String>();
        String type = params[0];
        String login_url = "https://telecomt108.000webhostapp.com/vending/ordershop.php";
        if (type.equals("transfer")) {
            String to_wallet = params[1];

            String sort_transfer = params[2];
            String value = params[3];
            try {
                String product_id = value.substring(0,4);
                String amount = value.substring(4,6);


                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("wallet_id", "UTF-8") + "=" + URLEncoder.encode(to_wallet, "UTF-8")+ "&"
                        + URLEncoder.encode("amount", "UTF-8") + "=" + URLEncoder.encode(amount, "UTF-8")+ "&"
                        + URLEncoder.encode("product_id", "UTF-8") + "=" + URLEncoder.encode(product_id, "UTF-8");
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
        builder = new AlertDialog.Builder(context);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(DialogInterface dialog, int id) {
                ShopqrActivity.fa.finish();
                Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(context, R.anim.push_left_in, R.anim.push_left_in);
                context.startActivity(i,options.toBundle());

            }
        });


    }

    @Override
    protected void onPostExecute(String result) {
        progressDialog.dismiss();

        if (result == "") {
            builder.setTitle("Success");
            builder.show();

        } else {
            builder.setTitle("Fail");
            builder.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

