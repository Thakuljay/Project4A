package com.example.user.newbmj.fragment;

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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.newbmj.Activity.MainActivity;
import com.example.user.newbmj.BackgroundWorker.BackgroundWorker;
import com.example.user.newbmj.BackgroundWorker.BackgroundWorkerTransferscan;
import com.example.user.newbmj.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CheckscanFragment extends Fragment {
    private String profile, profile_transfer;
    String wallet_id, name_transfer, surname_transfer, wallet_id_from, name_from, wallet_id_to;
    TextView tv_name_transfer, tv_surname_transfer, tv_money_transfer;
    Button btn_Transfer, btn_back;
    Context context;

    public static CheckscanFragment newInstance() {
        CheckscanFragment fragment = new CheckscanFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_checkscan,
                container, false);
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {

        wallet_id_from = ScanqrFragment.wallet_id;
        name_from = ScanqrFragment.name;
        wallet_id_to = ScanqrFragment.wallet_id_transfer;
        name_transfer = ScanqrFragment.name_transfer;
        //findViewByID here
        final CheckscanFragment activity = this;

        profile = BackgroundWorker.test;
        try {
            JSONObject jsonObject = new JSONObject(profile);
            JSONArray exArray = jsonObject.getJSONArray("main");
            for (int i = 0; i < exArray.length(); i++) {
                JSONObject jsonObj = exArray.getJSONObject(i);
                wallet_id = jsonObj.getString("wallet_id");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String money555 = ScanqrFragment.money555;
        tv_name_transfer = rootView.findViewById(R.id.tv_name_transfer);
        tv_surname_transfer = rootView.findViewById(R.id.tv_surname_transfer);
        tv_money_transfer = rootView.findViewById(R.id.tv_money_transfer);
        btn_Transfer = rootView.findViewById(R.id.btn_Transfer);
        btn_back = rootView.findViewById(R.id.btn_back);
        tv_name_transfer.setText(": " + wallet_id_from + " - " + name_from);
        tv_surname_transfer.setText(": " + wallet_id_to + " - " + name_transfer);
        tv_money_transfer.setText(": " + money555 + " Bath");
        btn_Transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String money555 = ScanqrFragment.money555;
                if (money555.isEmpty()) {
                    Toast.makeText(getContext(), "Please Money !!!", Toast.LENGTH_SHORT).show();
                } else {
                    String type = "transfer";
                    String wallet_user = wallet_id;
                    BackgroundWorkerTransferscan backgroundWorkerTransferscan = new BackgroundWorkerTransferscan(getContext());
                    backgroundWorkerTransferscan.execute(type, wallet_id, money555, wallet_id_to);
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(getContext(), "Back button clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


}


