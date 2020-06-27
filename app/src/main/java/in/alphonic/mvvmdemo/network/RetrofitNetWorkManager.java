package in.alphonic.mvvmdemo.network;

import java.util.concurrent.TimeUnit;

import in.alphonic.mvvmdemo.api.APIUrls;
import in.alphonic.mvvmdemo.utility.LogCustom;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetWorkManager {
    private static final String TAG = RetrofitNetWorkManager.class.getSimpleName();
    private static Retrofit retrofit;
    private static RetrofitNetWorkManager netWorkManager;

    private RetrofitNetWorkManager() {

    }

    public static void getInstance() {
        if (netWorkManager == null) {
            LogCustom.e(TAG, " retrofit instance created");
            netWorkManager = new RetrofitNetWorkManager();
            retrofit = getRetrofitInstance();
        }
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            LogCustom.e(TAG, " retrofit instance created1");
            retrofit = new Retrofit.Builder()
                    .baseUrl(APIUrls.ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient())
                    .build();
        }

        return retrofit;
    }


    private static OkHttpClient okHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .callTimeout(60, TimeUnit.SECONDS)
                .build();
    }
}
