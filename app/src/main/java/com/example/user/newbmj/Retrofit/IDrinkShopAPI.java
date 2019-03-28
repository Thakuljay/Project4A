package com.example.user.newbmj.Retrofit;

import com.example.user.newbmj.Model.Banner;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IDrinkShopAPI {

    @GET("getbanner.php")
    Observable<List<Banner>> getBanners();

}