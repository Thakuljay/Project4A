package com.example.user.newbmj.Activity;

import android.app.Activity;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.newbmj.R;
import com.example.user.newbmj.fragment.LoginFragment;
import com.example.user.newbmj.fragment.RegisterFragment;

public class LoginActivity extends AppCompatActivity {
    Button btn_register, btn_login;
    static LoginActivity loginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= 21) {

        } else {

        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, LoginFragment.newInstance(),
                            "LoginFragment")
                    .commit();
            RegisterFragment registerFragment = RegisterFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, registerFragment,
                            "RegisterFragment")
                    .detach(registerFragment)
                    .commit();
        }

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.contentContainer);
                if (fragment instanceof RegisterFragment == false) {
                    getSupportFragmentManager().beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.contentContainer, RegisterFragment.newInstance())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.contentContainer);
                if (fragment instanceof LoginFragment == false) {
                    getSupportFragmentManager().beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.contentContainer, LoginFragment.newInstance())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

    }

    public static Activity fa;

    LoginActivity() {
        fa = this;
    }


}
