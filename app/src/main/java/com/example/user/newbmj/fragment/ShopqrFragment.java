package com.example.user.newbmj.fragment;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.user.newbmj.Activity.MainActivity;
import com.example.user.newbmj.BackgroundWorker.BackgroundWorker;
import com.example.user.newbmj.BackgroundWorker.BackgroundWorkerTransfer;
import com.example.user.newbmj.BackgroundWorker.CheckqrcodeWorker;
import com.example.user.newbmj.BackgroundWorker.ConflimShop;
import com.example.user.newbmj.R;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ShopqrFragment extends Fragment {
    Context context;
    private TextView tv_surname_transfer, tv_amount, tv_price, tv_number, tv_name_transfer;
    private Button btn_Transfer2, btn_back2;
    private String profile, profile2;
    static String wallet_id2, name2, wallet_id_transfer2, name_transfer2,name3;

    public static ShopqrFragment newInstance() {
        ShopqrFragment fragment = new ShopqrFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_shopqr,
                container, false);
        initInstances(rootView);
        return rootView;


    }

    @SuppressLint("SetTextI18n")
    private void initInstances(View rootView) {
        tv_surname_transfer = rootView.findViewById(R.id.tv_surname_transfer);
        tv_amount = rootView.findViewById(R.id.tv_amount);
        tv_price = rootView.findViewById(R.id.tv_price);
        tv_number = rootView.findViewById(R.id.tv_number);
        tv_name_transfer = rootView.findViewById(R.id.tv_name_transfer);
        btn_Transfer2= rootView.findViewById(R.id.btn_Transfer2);
        btn_back2= rootView.findViewById(R.id.btn_back2);
        profile2 = BackgroundWorker.test;
        try {
            JSONObject jsonObject = new JSONObject(profile2);
            JSONArray exArray = jsonObject.getJSONArray("main");
            for (int i = 0; i < exArray.length(); i++) {
                JSONObject jsonObj = exArray.getJSONObject(i);
                wallet_id2 = jsonObj.getString("wallet_id");
                name2 = jsonObj.getString("name");


            }
            tv_name_transfer.setText(wallet_id2 + " - " + name2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
String Data = CheckqrcodeWorker.jsoncheck3;
        try {
            JSONObject jsonObject = new JSONObject(Data);
            JSONArray exArray = jsonObject.getJSONArray("main");
            for (int i = 0; i < exArray.length(); i++) {
                JSONObject jsonObj = exArray.getJSONObject(i);
                name3 = jsonObj.getString("name");


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String Value = CheckqrcodeWorker.value;
        String product_id = Value.substring(0,4);
        String number = Value.substring(4,6);
        String price = Value.substring(6);
        int number1 = Integer.parseInt(number);
        int price1 = Integer.parseInt(price);
        int Sum_amont = number1*price1;
        tv_amount.setText(Sum_amont+" Bath");
        tv_price.setText(price1+" Bath");
        tv_number.setText(product_id);
        tv_surname_transfer.setText(name3);


        btn_Transfer2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                String qr_wallet = TransferFragment.Result.substring(45,53);
                String sort_transfer = TransferFragment.Result.substring(6,12);
                String type = "transfer";
                String value = TransferFragment.Result.substring(66);
                ConflimShop conflimShop = new ConflimShop(getContext());
                conflimShop.execute(type,wallet_id2,sort_transfer,value);



            }
        });
        btn_back2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MainActivity.class);
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(getContext(), R.anim.push_left_in, R.anim.push_left_in);
                getActivity().startActivity(i, options.toBundle());
                getActivity().finish();
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




