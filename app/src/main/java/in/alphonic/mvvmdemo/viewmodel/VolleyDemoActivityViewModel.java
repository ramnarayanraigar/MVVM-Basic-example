package in.alphonic.mvvmdemo.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import in.alphonic.mvvmdemo.model.VolleyDemo;
import in.alphonic.mvvmdemo.repository.UserRepository;

public class VolleyDemoActivityViewModel extends ViewModel {
    private MutableLiveData<VolleyDemo> mutableLiveData;
    private UserRepository repository;

    public LiveData<VolleyDemo> getAppInfo() {
        return mutableLiveData;
    }

    public void updateAppInfo(Context context, JSONObject jsonObject, String TAG, boolean isProgressBar) {
       repository = UserRepository.getInstance();
        mutableLiveData = (MutableLiveData<VolleyDemo>) repository.updateAppInfo(mutableLiveData,context, jsonObject, TAG, isProgressBar);
    }
}
