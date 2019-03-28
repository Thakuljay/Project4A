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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.user.newbmj.BackgroundWorker.BackgroundWorkerRegister;
import com.example.user.newbmj.R;
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;


public class RegisterFragment extends Fragment {
    Button btnRegister;
    EditText name, surname, birthdate, username, password, cfpassword, email, tel;
    String sex;
    RadioGroup rgOpertor;
    Context context;

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_register,
                container, false);
        initInstances(rootView);
        return rootView;


    }

    private void initInstances(View rootView) {
        //findViewByID here
        rgOpertor = rootView.findViewById(R.id.radio);
        name = (EditText) rootView.findViewById(R.id.et_name);
        surname = (EditText) rootView.findViewById(R.id.et_surname);
        birthdate = (EditText) rootView.findViewById(R.id.et_birth);
        email = (EditText) rootView.findViewById(R.id.et_email);
        tel = (EditText) rootView.findViewById(R.id.et_tel);
        username = (EditText) rootView.findViewById(R.id.et_username);
        password = (EditText) rootView.findViewById(R.id.et_password);
        cfpassword = (EditText) rootView.findViewById(R.id.et_cfpassword);
        birthdate.addTextChangedListener(new PatternedTextWatcher("####-##-##"));
        tel.addTextChangedListener(new PatternedTextWatcher("###-###-####"));
        btnRegister = rootView.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (rgOpertor.getCheckedRadioButtonId()) {
                    case R.id.rbmale:
                        sex = "Male";
                        break;
                    case R.id.rbfemale:
                        sex = "Female";
                        break;
                }
                String str_name = name.getText().toString();
                String str_surname = surname.getText().toString();
                String str_birthdate = birthdate.getText().toString();
                String str_email = email.getText().toString();
                String str_tel = tel.getText().toString();
                String str_sex = sex;
                String str_username = username.getText().toString();
                String str_password = password.getText().toString();
                String str_cfpassword = cfpassword.getText().toString();
                if (str_name.isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                } else if (str_surname.isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter Surname", Toast.LENGTH_SHORT).show();
                } else if (str_birthdate.isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter Birthday", Toast.LENGTH_SHORT).show();
                } else if (str_email.isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                } else if (str_tel.isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter Tel", Toast.LENGTH_SHORT).show();
                } else if (str_username.isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter Username", Toast.LENGTH_SHORT).show();
                } else if (str_password.isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else if (!str_password.equals(str_cfpassword)) {
                    Toast.makeText(getContext(), "Password Not matching", Toast.LENGTH_SHORT).show();
                } else {
                    String type = "register";
                    BackgroundWorkerRegister backgroundWorkerRegister = new BackgroundWorkerRegister(getContext());
                    backgroundWorkerRegister.execute(type, str_username, str_password, str_name, str_surname, str_birthdate, str_email, str_tel, str_sex);
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

}

