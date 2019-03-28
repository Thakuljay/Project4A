package com.example.user.newbmj.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.newbmj.BackgroundWorker.BackgroundWorker;
import com.example.user.newbmj.BackgroundWorker.BackgroundWorkerChecktransfer;
import com.example.user.newbmj.BackgroundWorker.CheckqrcodeWorker;
import com.example.user.newbmj.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class TransferFragment extends Fragment {
    Context context;
    EditText et_money;
    public static EditText et_towallet;
    private String profile;
    Button btn_Transfer, btn_scan_qr_code;
    static String wallet_id;
    static String name;
    public static IntentResult result;
    public static String Result;
    private TextView tv_walletuser;
    public static String towallet;
    static String money555;
    public static String sort_transfer;

    public static TransferFragment newInstance(String s) {
        TransferFragment fragment = new TransferFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_transfer,
                container, false);
        initInstances(rootView);
        return rootView;
    }

    @SuppressLint("SetTextI18n")
    private void initInstances(View rootView) {
        //findViewByID here
        et_money = rootView.findViewById(R.id.et_Money);
        et_towallet = rootView.findViewById(R.id.et_Towallet);
        btn_Transfer = rootView.findViewById(R.id.btn_Transfer);
        btn_scan_qr_code = rootView.findViewById(R.id.btn_scan_qr_code);
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
        btn_Transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                towallet = et_towallet.getText().toString();
                money555 = et_money.getText().toString();
                if (towallet.isEmpty() | money555.isEmpty()) {
                    Toast.makeText(getContext(), "Pleasa Insert Wallet ID or Money !!!", Toast.LENGTH_SHORT).show();
                } else {
                    String type = "transfer";
                    String wallet_user = wallet_id;

                    BackgroundWorkerChecktransfer backgroundWorkerChecktransfer = new BackgroundWorkerChecktransfer(getContext());
                    backgroundWorkerChecktransfer.execute(type, wallet_user, money555, towallet);
                }
            }
        });
        btn_scan_qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.forSupportFragment(TransferFragment.this).initiateScan();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getContext(), "You Cancel the Scanning", Toast.LENGTH_LONG).show();
            }
            if(result.getContents().length ( )<=75||result.getContents().length ( )>=77){
                Toast.makeText(getContext(), "QR Code Incorrect", Toast.LENGTH_LONG).show();
            }
            else {
                String type = "transfer";
                Result = result.getContents();
                String qr_wallet = Result.substring(45,53);
                sort_transfer = Result.substring(6,12);
                String value = Result.substring(66);
                CheckqrcodeWorker checkqrcodeWorker = new CheckqrcodeWorker(getContext());
                checkqrcodeWorker.execute(type, qr_wallet,sort_transfer,value);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
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




