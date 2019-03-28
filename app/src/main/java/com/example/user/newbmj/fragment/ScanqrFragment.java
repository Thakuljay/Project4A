package com.example.user.newbmj.fragment;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.newbmj.Activity.CheckscanActivity;
import com.example.user.newbmj.BackgroundWorker.BackgroundWorker;
import com.example.user.newbmj.BackgroundWorker.CheckqrcodeWorker;
import com.example.user.newbmj.R;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ScanqrFragment extends Fragment {
    Context context;
    EditText et_money;
    public static TextView tv_towallet;
    private String profile, profile2;
    Button btn_Transfer, btn_scan_qr_code;
    static String wallet_id, name, wallet_id_transfer, name_transfer;
    public static IntentResult result;
    public static String Result;
    private TextView tv_walletuser;
    public static String towallet;
    static String money555;

    public static ScanqrFragment newInstance() {
        ScanqrFragment fragment = new ScanqrFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_scanqr,
                container, false);
        initInstances(rootView);
        return rootView;


    }

    @SuppressLint("SetTextI18n")
    private void initInstances(View rootView) {
        //findViewByID here
        et_money = rootView.findViewById(R.id.et_Money);
        tv_towallet = rootView.findViewById(R.id.tv_towalletuser);
        btn_Transfer = rootView.findViewById(R.id.btn_Transfer);
        tv_walletuser = rootView.findViewById(R.id.tv_walletuser);
        profile = BackgroundWorker.test;
        try {
            JSONObject jsonObject = new JSONObject(profile);
            JSONArray exArray = jsonObject.getJSONArray("main");
            for (int i = 0; i < exArray.length(); i++) {
                JSONObject jsonObj = exArray.getJSONObject(i);
                wallet_id = jsonObj.getString("wallet_id");
                name = jsonObj.getString("name");


            }
            tv_walletuser.setText(wallet_id + " - " + name);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject1 = new JSONObject(CheckqrcodeWorker.jsoncheck2);
            JSONArray exArray1 = jsonObject1.getJSONArray("main");
            for (int ii = 0; ii < exArray1.length(); ii++) {
                JSONObject jsonObj1 = exArray1.getJSONObject(ii);
                name_transfer = jsonObj1.getString("name");
                wallet_id_transfer = jsonObj1.getString("wallet_id");
            }
            tv_towallet.setText(wallet_id_transfer + " - " + name_transfer);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        btn_Transfer.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                money555 = et_money.getText().toString();
                if (money555.isEmpty()) {
                    Toast.makeText(getContext(), "Please Insert Money !!!", Toast.LENGTH_SHORT).show();
                } else {
                    String type = "transfer";
                    String wallet_user = wallet_id;
                    Intent intent = new Intent(getContext(), CheckscanActivity.class);
                    ActivityOptions options =
                            ActivityOptions.makeCustomAnimation(getContext(), R.anim.left_to_right, R.anim.right_to_left);
                    getActivity().startActivity(intent, options.toBundle());
                    getActivity().finish();
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




