package com.example.user.newbmj.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.newbmj.BackgroundWorker.BackgroundWorker;
import com.example.user.newbmj.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFragment extends Fragment {
    static LoginFragment loginFragment;
    EditText UsernameEt, PasswordEt;
    Button btnlogin;
    Context context;


    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login,
                container, false);
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {
        //findViewByID here
        UsernameEt = (EditText) rootView.findViewById(R.id.etUserName);
        PasswordEt = (EditText) rootView.findViewById(R.id.etPassword);
        btnlogin = (Button) rootView.findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = UsernameEt.getText().toString();
                String password = PasswordEt.getText().toString();
                if (username.isEmpty() | password.isEmpty()) {
                    Toast.makeText(getContext(), "Username or Password Incorrect", Toast.LENGTH_SHORT).show();

                }
                if (!isValidEmail(username)) {
                    //Set error message for email field
                    UsernameEt.setError("Invalid Username");
                }


                if (!isValidPassword(password)) {
                    //Set error message for password field
                    PasswordEt.setError("Password cannot be empty");
                } else {

                    String type = "login";
                    BackgroundWorker backgroundWorker = new BackgroundWorker(getContext());
                    backgroundWorker.execute(type, username, password);
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            //Restore state here
        }
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }
}


