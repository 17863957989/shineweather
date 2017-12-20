package android.shineweather.com.shineweather.util;

import android.shineweather.com.shineweather.db.City;
import android.shineweather.com.shineweather.db.County;
import android.shineweather.com.shineweather.db.Province;
import android.shineweather.com.shineweather.gson.Weather;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LSG on 2017/12/16.
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvince = new JSONArray(response);
                for (int i = 0;i<allProvince.length();i++){
                    JSONObject provinceObject = allProvince.getJSONObject(i);
                    Province province =new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

/**
 * 解析和处理服务器返回的市级数据
 */
public static boolean handleCityResponse(String response,int provinceId) {
    if (!TextUtils.isEmpty(response)) {
        try {
            JSONArray allCities = new JSONArray(response);
            for (int i = 0; i < allCities.length(); i++) {
                JSONObject provinceObject = allCities.getJSONObject(i);
                City city = new City();
                city.setCityName(provinceObject.getString("name"));
                city.setCityCode(provinceObject.getInt("id"));
                city.setProvinceId(provinceId);
                city.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    return false;
}
    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response,int cityId) {
        if (!TextUtils.isEmpty(response)) {
          try {
              JSONArray allCounties = new JSONArray(response);
              for (int i = 0; i<allCounties.length();i++){
                  JSONObject countyObject = allCounties.getJSONObject(i);
                  County county = new County();
                  county.setCountyName(countyObject.getString("name"));
                  county.setWeatherId(countyObject.getString("weather_id"));
                  county.setCityId(cityId);
                  county.save();

              }
              return true;
          }catch (JSONException e){
              e.printStackTrace();
          }
        }
        return false;
    }
    public static Weather handleWeatherResponse(String respone){
        try {
            JSONObject jsonObject = new JSONObject(respone);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
