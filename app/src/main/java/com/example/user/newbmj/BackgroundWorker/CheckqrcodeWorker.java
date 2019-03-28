package com.example.user.newbmj.BackgroundWorker;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.user.newbmj.Activity.MainActivity;
import com.example.user.newbmj.Activity.ScanqrActivity;
import com.example.user.newbmj.Activity.ShopqrActivity;
import com.example.user.newbmj.R;
import com.example.user.newbmj.fragment.TransferFragment;

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

public class CheckqrcodeWorker extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    public static String jsoncheck2,jsoncheck3;
    ScanqrActivity scanqrActivity;
    private ArrayList<String> exData;
    private ProgressDialog progressDialog;
    public static String value;
    public CheckqrcodeWorker(Context ctx) {
        context = ctx;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public String doInBackground(String... params) {
        exData = new ArrayList<String>();
       String sort_transfer1= TransferFragment.sort_transfer;
        int sort_transfer2 = Integer.parseInt(sort_transfer1);
        int sort_transfer3 =sort_transfer2;
        String type = params[0];
        String login_url = "https://telecomt108.000webhostapp.com/check_transfer.php";

            try {
                String to_wallet = params[1];
                String sort_transfer = params[2];
                value = params[3];

                if(sort_transfer2 == 10212) {


                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("to_wallet", "UTF-8") + "=" + URLEncoder.encode(to_wallet, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result_check2 = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result_check2 += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    if (result_check2 != "") {
                        jsoncheck2 = result_check2;
                        MainActivity.fa.finish();

                        Intent i = new Intent(context.getApplicationContext(), ScanqrActivity.class);
                        ActivityOptions options =
                                ActivityOptions.makeCustomAnimation(context, R.anim.left_to_right, R.anim.right_to_left);
                        context.startActivity(i, options.toBundle());

                    } else {

                    }
                    return result_check2;
                }
                if(sort_transfer2 == 10211){
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("to_wallet", "UTF-8") + "=" + URLEncoder.encode(to_wallet, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result_check2 = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result_check2 += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    if (result_check2 != "") {
                        jsoncheck3 = result_check2;
                        MainActivity.fa.finish();

                        Intent i = new Intent(context.getApplicationContext(), ShopqrActivity.class);
                        ActivityOptions options =
                                ActivityOptions.makeCustomAnimation(context, R.anim.left_to_right, R.anim.right_to_left);
                        context.startActivity(i, options.toBundle());

                    } else {

                    }
                    return result_check2;
                }
                else  {
//                    String product_id = value.substring(0,4);
//                            String amount = value.substring(4,6);
//
//
//                    URL url = new URL(login_url2);
//                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                    httpURLConnection.setRequestMethod("POST");
//                    httpURLConnection.setDoOutput(true);
//                    httpURLConnection.setDoInput(true);
//                    OutputStream outputStream = httpURLConnection.getOutputStream();
//                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//                    String post_data = URLEncoder.encode("wallet_id", "UTF-8") + "=" + URLEncoder.encode(to_wallet, "UTF-8")+ "&"
//                            + URLEncoder.encode("amount", "UTF-8") + "=" + URLEncoder.encode(amount, "UTF-8")+ "&"
//                            + URLEncoder.encode("product_id", "UTF-8") + "=" + URLEncoder.encode(product_id, "UTF-8");
//                    bufferedWriter.write(post_data);
//                    bufferedWriter.flush();
//                    bufferedWriter.close();
//                    outputStream.close();
//                    InputStream inputStream = httpURLConnection.getInputStream();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
//                    String result_check2 = "";
//                    String line = "";
//                    while ((line = bufferedReader.readLine()) != null) {
//                        result_check2 += line;
//                    }
//                    bufferedReader.close();
//                    inputStream.close();
//                    httpURLConnection.disconnect();
//                    if (result_check2 != "") {
//                        jsoncheck3 = result_check2;
                    String result_check2 = "";


//                    } else {
//
//                    }
                    return result_check2;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
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
    protected void onPostExecute(String result_check2) {
        progressDialog.dismiss();
        alertDialog.setMessage("QR Code Incorrect!!!");
        if (result_check2 == "") {
            alertDialog.show();
        } else {

        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}


