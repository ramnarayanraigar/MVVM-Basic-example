package in.alphonic.mvvmdemo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import in.alphonic.mvvmdemo.model.User;
import in.alphonic.mvvmdemo.repository.UserRepository;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<User> userMutableLiveData;
    private UserRepository userRepository;

    /* Set data by repositry */
    public void queryRepo() {
        userRepository = UserRepository.getInstance();
        userMutableLiveData = (MutableLiveData<User>) userRepository.getUser();
    }

    /* get data updated by repositry and return in view */
    public LiveData<User> getUserData() {
        return userMutableLiveData;
    }

    public void updateUser() {
        userRepository.updateUser(userMutableLiveData);
    }
}
