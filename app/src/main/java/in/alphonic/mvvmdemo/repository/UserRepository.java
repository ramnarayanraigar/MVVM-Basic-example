package in.alphonic.mvvmdemo.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Random;

import in.alphonic.mvvmdemo.api.APIUrls;
import in.alphonic.mvvmdemo.model.User;
import in.alphonic.mvvmdemo.model.VolleyDemo;
import in.alphonic.mvvmdemo.network.NetWorkManager;
import in.alphonic.mvvmdemo.network.NetWorkResponseListener;
import in.alphonic.mvvmdemo.utility.Constants;

/* Repository is used for get data from
       web service and database and send this data to viewmodel */
public class UserRepository {
    private static final String TAG = UserRepository.class.getSimpleName();
    private static UserRepository userRepository;

    /* Singleton Pattern */
    public static UserRepository getInstance() {
        if (userRepository == null)
            userRepository = new UserRepository();

        return userRepository;
    }

    /* Remote and Local database operation or business logic */
    public LiveData<User> getUser() {
        MutableLiveData<User> mutableLiveData = new MutableLiveData<>();

        User user = new User();
        Random random = new Random();
        int num = random.nextInt(10000);
        user.setName("Ram Narayan Raigar " + num);
        Log.v(TAG, "Ram Narayan Raigar " + num);

        mutableLiveData.setValue(user);

        return mutableLiveData;
    }

    public void updateUser(MutableLiveData<User> userMutableLiveData) {
        User user = new User();

        Random random = new Random();
        int num = random.nextInt(10000);
        user.setName("Ram Narayan Raigar " + num);

        userMutableLiveData.postValue(user);
    }

    public LiveData<VolleyDemo> updateAppInfo(MutableLiveData<VolleyDemo> mutableLiveData1, Context context, JSONObject parameter,
                                              String TAG, boolean isProgressBar) {

        MutableLiveData<VolleyDemo> mutableLiveData;
        if (mutableLiveData1 == null) {
            mutableLiveData = new MutableLiveData<>();
        } else {
            mutableLiveData = mutableLiveData1;
        }
        NetWorkManager.getInstance().volleyJsonRequest(context, Constants.GET, parameter, APIUrls.EndPoint.APP_SETTINGS, TAG, isProgressBar
                , new NetWorkResponseListener() {
                    @Override
                    public void onSuccess(String response) {
                        VolleyDemo volleyDemo = new Gson().fromJson(response, VolleyDemo.class);

                        if (mutableLiveData1 == null) {
                            mutableLiveData.setValue(volleyDemo);
                        } else {
                            mutableLiveData.postValue(volleyDemo);
                        }
                    }

                    @Override
                    public void onError(VolleyError volleyError, String message) {
                        if (mutableLiveData1 == null) {
                            mutableLiveData.setValue(null);
                        } else {
                            mutableLiveData.postValue(null);
                        }

                    }
                });

        return mutableLiveData;
    }
}
