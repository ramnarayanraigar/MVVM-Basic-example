package in.alphonic.mvvmdemo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import in.alphonic.mvvmdemo.model.Success;
import in.alphonic.mvvmdemo.repository.UserRepository;

public class RetrofitExampleViewModel extends ViewModel {
    private MutableLiveData<Success> volleyDemoMutableLiveData;

    public void appSettings() {
        UserRepository repository = UserRepository.getInstance();

        volleyDemoMutableLiveData = (MutableLiveData<Success>) repository.getAppSettings(volleyDemoMutableLiveData);
    }

    public LiveData<Success> getAppSettings() {
        return volleyDemoMutableLiveData;
    }
}
