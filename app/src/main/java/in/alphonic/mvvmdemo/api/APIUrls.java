package in.alphonic.mvvmdemo.api;

import in.alphonic.mvvmdemo.model.RetrofitDemo;
import in.alphonic.mvvmdemo.model.Success;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class APIUrls {
    public static final String ROOT_URL = "https://www.tozata.com/";

    public interface EndPoint {
        String APP_SETTINGS = "api/v1/"+"get-app-setting.php";
        String CHECK_MOBILE_EXISTS = "api/v1/" + "check-mobile.php";

        @GET(APP_SETTINGS)
        Call<RetrofitDemo> getAppSettings();

        @FormUrlEncoded
        @POST(CHECK_MOBILE_EXISTS)
        Call<Success> checkMobileExists(@Header ("token") String token, @Field("contact_no") String mobileNumber);
    }
}
