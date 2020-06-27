package in.alphonic.mvvmdemo.network;

import com.android.volley.VolleyError;

public interface NetWorkResponseListener {
    void onSuccess(String response);
    void onError(VolleyError volleyError, String message);
}
