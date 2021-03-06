package in.alphonic.mvvmdemo.application;

import android.content.Context;

import in.alphonic.mvvmdemo.network.NetWorkManager;
import in.alphonic.mvvmdemo.network.RetrofitNetWorkManager;

public class Application extends android.app.Application {
    public static Application application;
    public static Context APP_CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
        APP_CONTEXT = getApplicationContext();

        NetWorkManager.getInstance(APP_CONTEXT);
        RetrofitNetWorkManager.getInstance();
    }

    public static Application getInstance() {
        return application;
    }
}
