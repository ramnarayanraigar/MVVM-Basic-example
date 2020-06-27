package in.alphonic.mvvmdemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import in.alphonic.mvvmdemo.R;
import in.alphonic.mvvmdemo.api.APIUrls;
import in.alphonic.mvvmdemo.model.RetrofitDemo;
import in.alphonic.mvvmdemo.model.Success;
import in.alphonic.mvvmdemo.network.RetrofitNetWorkManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitExampleActivity extends AppCompatActivity {
    private TextView text;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofir_example);

        text = findViewById(R.id.text);
        btn = findViewById(R.id.btn);

        apiCall();
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              // apiCall();
               apiCallPost();
           }
       });
    }

    private void apiCall() {
        APIUrls.EndPoint endPoint = RetrofitNetWorkManager.getRetrofitInstance().create(APIUrls.EndPoint.class);

        Call<RetrofitDemo> retrofitDemoCall = endPoint.getAppSettings();

        retrofitDemoCall.enqueue(new Callback<RetrofitDemo>() {
            @Override
            public void onResponse(@NotNull Call<RetrofitDemo> call, @NotNull Response<RetrofitDemo> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        RetrofitDemo retrofitDemo = response.body();

                        text.setText(retrofitDemo.getStatus());
                    }
                }
            }

            @Override
            public void onFailure(Call<RetrofitDemo> call, Throwable t) {

            }
        });
    }

    private void apiCallPost() {
        APIUrls.EndPoint endPoint = RetrofitNetWorkManager.getRetrofitInstance().create(APIUrls.EndPoint.class);

        Call<Success> successCall = endPoint.checkMobileExists("token","8619713127");

        successCall.enqueue(new Callback<Success>() {
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {

            }

            @Override
            public void onFailure(Call<Success> call, Throwable t) {

            }
        });
    }
}
