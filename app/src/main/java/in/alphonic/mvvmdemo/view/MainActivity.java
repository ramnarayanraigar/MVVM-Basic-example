package in.alphonic.mvvmdemo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import in.alphonic.mvvmdemo.R;
import in.alphonic.mvvmdemo.model.User;
import in.alphonic.mvvmdemo.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button btn;
    private MainActivityViewModel mainActivityViewModel;
 // test 123
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_name);
        btn = findViewById(R.id.btn);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.queryRepo();
        mainActivityViewModel.getUserData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Log.v("UserRepository", user.getName());
                textView.setText(user.getName());
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityViewModel.updateUser();
            }
        });
    }
}
