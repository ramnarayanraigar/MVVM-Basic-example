package in.alphonic.mvvmdemo.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Random;

import in.alphonic.mvvmdemo.model.User;

/* Repository is used for get data from
       web service and database and send this data to viewmodel */
public class UserRepository {
    private static final String TAG = UserRepository.class.getSimpleName();
    private static UserRepository userRepository;

    /* Singleton Pattern */
    public static UserRepository getInstance() {
        if (userRepository == null)
            userRepository = new UserRepository() ;

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
}
