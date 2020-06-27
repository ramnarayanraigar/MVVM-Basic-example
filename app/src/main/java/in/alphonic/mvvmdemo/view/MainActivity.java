package in.alphonic.mvvmdemo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
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
    private Button btn, volleyDemo, retroFitDemo;
    private MainActivityViewModel mainActivityViewModel;

    private Context mContext;
 // test 123
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        textView = findViewById(R.id.text_name);
        btn = findViewById(R.id.btn);
        volleyDemo = findViewById(R.id.volley_demo);
        retroFitDemo = findViewById(R.id.retro_demo);

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

        volleyDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, VolleyDemoActivity.class);
                startActivity(intent);
            }
        });

        retroFitDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RetrofitExampleActivity.class);
                startActivity(intent);
            }
        });
    }
}
