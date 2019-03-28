package com.example.user.newbmj.Activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.newbmj.BackgroundWorker.BackgroundWorker;
import com.example.user.newbmj.R;
import com.example.user.newbmj.fragment.TransectionFragment;

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

public class SeetranscantionActivity extends AppCompatActivity {
    private ListView lv_transection;
    private ArrayList<String> exData_towallet, exData_total, exData_name_t, exData_surname_t, exData_name_r, exData_surname_r, exData_timestamp, exData_wallet_id;
    private ProgressDialog progressDialog;
    String profile, wallet_id_transection, money;
    Context context;
    Button btn_see_more;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seetranscantion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Transactions");
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        exData_towallet = new ArrayList<String>();
        exData_wallet_id = new ArrayList<String>();
        exData_name_t = new ArrayList<String>();
        exData_surname_t = new ArrayList<String>();
        exData_name_r = new ArrayList<String>();
        exData_surname_r = new ArrayList<String>();
        exData_total = new ArrayList<String>();
        exData_timestamp = new ArrayList<String>();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(SeetranscantionActivity.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Downloading ...");
                progressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {
                profile = BackgroundWorker.test;
                try {
                    JSONObject jsonObject = new JSONObject(profile);
                    JSONArray exArray = jsonObject.getJSONArray("main");
                    for (int i = 0; i < exArray.length(); i++) {
                        JSONObject jsonObj = exArray.getJSONObject(i);
                        wallet_id_transection = jsonObj.getString("wallet_id");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    String wallet_id = wallet_id_transection;
                    String login_url = "https://telecomt108.000webhostapp.com/transection.php";
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("wallet_id", "UTF-8") + "=" + URLEncoder.encode(wallet_id, "UTF-8");
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
                    Log.d("JSON Result", result.toString());

                    JSONObject jsonObject = new JSONObject(result.toString());
                    JSONArray exArray = jsonObject.getJSONArray("main");

                    for (int i = 0; i < exArray.length(); i++) {
                        JSONObject jsonObj = exArray.getJSONObject(i);
                        exData_name_t.add(jsonObj.getString("name_t"));
                        exData_surname_t.add(jsonObj.getString("surname_t"));
                        exData_name_r.add(jsonObj.getString("name_r"));
                        exData_surname_r.add(jsonObj.getString("surname_r"));
                        exData_towallet.add(jsonObj.getString("to_wallet"));
                        exData_total.add(jsonObj.getString("total"));
                        exData_timestamp.add(jsonObj.getString("timestamp"));
                        exData_wallet_id.add(jsonObj.getString("from_wallet"));
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    String wallet_id = wallet_id_transection;
                    String login_url = "https://telecomt108.000webhostapp.com/transection2.php";
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("wallet_id", "UTF-8") + "=" + URLEncoder.encode(wallet_id, "UTF-8");
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
                    Log.d("JSON Result", result.toString());

                    JSONObject jsonObject = new JSONObject(result.toString());
                    JSONArray exArray = jsonObject.getJSONArray("main");

                    for (int i = 0; i < exArray.length(); i++) {
                        JSONObject jsonObj = exArray.getJSONObject(i);
                        money = jsonObj.getString("money");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                ListView listView = (ListView) findViewById(R.id.listView);
                CustomAdapter customAdapter = new CustomAdapter();
                listView.setAdapter(customAdapter);
                progressDialog.dismiss();

            }
        }.execute();
    }

    public class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return exData_total.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint({"ResourceAsColor", "ViewHolder"})
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout, null);
            TextView tv_total = (TextView) view.findViewById(R.id.tv_total);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_timestamp = (TextView) view.findViewById(R.id.tv_timestamp);
            if (exData_towallet.get(i).isEmpty()) {
                tv_total.setTextColor(Color.GREEN);

                tv_name.setText("From :" + exData_wallet_id.get(i) + " - " + exData_name_r.get(i));
                tv_total.setText(exData_total.get(i));
                tv_timestamp.setText("Timestamp | " + exData_timestamp.get(i));
            } else {
                tv_total.setTextColor(Color.RED);
                tv_name.setText("To :" + exData_towallet.get(i) + " - " + exData_name_t.get(i));
                tv_total.setText(exData_total.get(i));
                tv_timestamp.setText("Timestamp | " + exData_timestamp.get(i));
            }

            return view;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long

        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

        }
        if (id == android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
