package android.shineweather.com.shineweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LSG on 2017/12/17.
 */

public class Now {
    @SerializedName("tmp")
    public String temperautre;
    @SerializedName("cond")
    public More more;
    public class More{
        @SerializedName("txt")
        public String info;
    }
}
