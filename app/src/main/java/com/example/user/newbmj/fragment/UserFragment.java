package com.example.user.newbmj.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.user.newbmj.BackgroundWorker.BackgroundWorker;
import com.example.user.newbmj.Model.Banner;
import com.example.user.newbmj.R;
import com.example.user.newbmj.Retrofit.IDrinkShopAPI;
import com.example.user.newbmj.Utils.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class UserFragment extends Fragment {
    private String profile;
    private TextView tv_walletoid, tv_name, tv_surname, tv_email, tv_tel, tv_birthdate, tv_sex;
    public static String wallet_id, name, surname, email, tel, birthdate, sex, money;
    SliderLayout sliderLayout;
    IDrinkShopAPI mService;
    //Rxjava
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_user,
                container, false);
        initInstances(rootView);
        return rootView;

    }

    public void initInstances(View rootView) {
        mService = Common.getAPI();
        sliderLayout = (SliderLayout) rootView.findViewById(R.id.slider);

        //Get Banner
        getBannerImage();
        //findViewByID here
        tv_walletoid = rootView.findViewById(R.id.tv_wallet_id);
        tv_name = rootView.findViewById(R.id.tv_name);
        tv_surname = rootView.findViewById(R.id.tv_surname);
        tv_email = rootView.findViewById(R.id.tv_email);
        tv_tel = rootView.findViewById(R.id.tv_tel);
        tv_birthdate = rootView.findViewById(R.id.tv_birthdate);
        tv_sex = rootView.findViewById(R.id.tv_sex);
        profile = BackgroundWorker.test;

        try {
            JSONObject jsonObject = new JSONObject(profile);
            JSONArray exArray = jsonObject.getJSONArray("main");
            for (int i = 0; i < exArray.length(); i++) {
                JSONObject jsonObj = exArray.getJSONObject(i);
                wallet_id = jsonObj.getString("wallet_id");
                name = jsonObj.getString("name");
                surname = jsonObj.getString("surname");
                email = jsonObj.getString("email");
                tel = jsonObj.getString("tel");
                birthdate = jsonObj.getString("birthdate");
                sex = jsonObj.getString("sex");
                money = jsonObj.getString("money");
            }
            tv_walletoid.setText(": " + wallet_id);
            tv_name.setText(": " + name);
            tv_surname.setText(": " + surname);
            tv_email.setText(": " + email);
            tv_tel.setText(": " + tel);
            tv_birthdate.setText(": " + birthdate);
            tv_sex.setText(": " + sex);
        } catch (JSONException e) {
            e.printStackTrace();
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

    private void getBannerImage() {
        compositeDisposable.add(mService.getBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Banner>>() {
                    @Override
                    public void accept(List<Banner> banners) throws Exception {
                        displayImage(banners);
                    }
                }));
    }

    private void displayImage(List<Banner> banners) {
        HashMap<String, String> bannerMap = new HashMap<>();
        for (Banner item : banners)
            bannerMap.put(item.getName(), item.getLink());

        for (String name : bannerMap.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView.description(name)
                    .image(bannerMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            sliderLayout.addSlider(textSliderView);
        }
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}


