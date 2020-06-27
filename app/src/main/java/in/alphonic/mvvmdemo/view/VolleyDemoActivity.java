package in.alphonic.mvvmdemo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import in.alphonic.mvvmdemo.R;
import in.alphonic.mvvmdemo.model.VolleyDemo;
import in.alphonic.mvvmdemo.network.NetWorkManager;
import in.alphonic.mvvmdemo.utility.LogCustom;
import in.alphonic.mvvmdemo.viewmodel.VolleyDemoActivityViewModel;

public class VolleyDemoActivity extends AppCompatActivity {
    private static final String TAG = VolleyDemoActivity.class.getSimpleName();
    private TextView textView;
    private Button btn;
    private Context context;

    private boolean isButtonClick = false;

    VolleyDemoActivityViewModel volleyDemoActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_demo);

        context = this;

        textView = findViewById(R.id.text);
        btn = findViewById(R.id.btn);

        volleyDemoActivityViewModel = ViewModelProviders.of(this).get(VolleyDemoActivityViewModel.class);

        volleyDemoActivityViewModel.updateAppInfo(context, new JSONObject(), TAG, true);

        volleyDemoActivityViewModel.getAppInfo().observe(this, new Observer<VolleyDemo>() {
            @Override
            public void onChanged(VolleyDemo volleyDemo) {
                if (volleyDemo != null) {
                    if (!isButtonClick) {
                        textView.setText(volleyDemo.getStatus());
                    } else {
                        textView.setText(volleyDemo.getData()[0].getCustomer_app_version());
                        isButtonClick = false;
                    }
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isButtonClick = true;
                volleyDemoActivityViewModel.updateAppInfo(context, new JSONObject(), TAG, true);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        NetWorkManager.getInstance().cancelVolleyRequest(context, TAG);
    }
}
