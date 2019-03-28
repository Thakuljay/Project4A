package com.example.user.newbmj.Activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.newbmj.R;
import com.example.user.newbmj.fragment.ChecktransferFragment;

public class ChecktransferActivity extends AppCompatActivity {
    Toolbar toolBar;
    Context context;
    static ChecktransferActivity checktransferActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checktransfer);
        if (Build.VERSION.SDK_INT >= 21) {

        } else {

        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ChecktransferFragment.newInstance(),
                            "ChecktransferFragment")
                    .commit();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            ActivityOptions options =
                    ActivityOptions.makeCustomAnimation(context, R.anim.right_to_left, R.anim.left_to_right);
            context.startActivity(myIntent, options.toBundle());
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static Activity fa;

    ChecktransferActivity() {
        fa = this;
    }
}
