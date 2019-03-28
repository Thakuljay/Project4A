package com.example.user.newbmj.Utils;

import com.example.user.newbmj.Retrofit.IDrinkShopAPI;
import com.example.user.newbmj.Retrofit.RetrofitClient;

public class Common {
    private static final String BASE_URL = "http://telecomt108.000webhostapp.com/Shop/";


    public static IDrinkShopAPI getAPI() {
        return RetrofitClient.getRetrofit(BASE_URL).create(IDrinkShopAPI.class);
    }
}
