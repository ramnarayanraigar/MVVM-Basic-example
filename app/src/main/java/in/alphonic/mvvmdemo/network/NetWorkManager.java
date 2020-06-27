package in.alphonic.mvvmdemo.network;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import in.alphonic.mvvmdemo.utility.LogCustom;
import in.alphonic.mvvmdemo.utility.Utility;

public class NetWorkManager {
    private static RequestQueue requestQueue;
    private static NetWorkManager netWorkManager;
    private ProgressDialog progressDialog;

    private NetWorkManager() {

    }

    public static synchronized void getInstance(Context context) {
        if (netWorkManager == null) {
            netWorkManager = new NetWorkManager();
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }

    public static synchronized NetWorkManager getInstance() {
        if (netWorkManager == null) {
            throw new IllegalStateException(NetWorkManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }

        return netWorkManager;
    }

    public void volleyJsonRequest(final Context context, int methodType /* Get or Post */,
                                  final JSONObject jsonParameter, String apiUrl,
                                  final String TAG, boolean isProgressBar, final NetWorkResponseListener netWorkResponseListener) {

        handleProgressDialog(context, isProgressBar);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(methodType, apiUrl, jsonParameter, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                printLog(TAG, jsonParameter.toString(), apiUrl, response);

                /* remove progressDialog */
                handleProgressDialog(context, false);

                netWorkResponseListener.onSuccess(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // remove progressDialog
                handleProgressDialog(context, false);

                // print log
                printLog(TAG, jsonParameter.toString(), apiUrl, Objects.requireNonNull(error.getMessage()));

                // parse 400 response
                NetworkResponse networkResponse = error.networkResponse;

                if (error instanceof ServerError && networkResponse != null) {
                    try {
                        String response = new String(networkResponse.data,
                                HttpHeaderParser.parseCharset(networkResponse.headers, "utf-8"));

                        printLog(TAG, jsonParameter.toString(), apiUrl, response);

                        if (isValidJson(response)) {
                            netWorkResponseListener.onSuccess(response);
                        } else {
                            netWorkResponseListener.onError(error, "Something went wrong");
                            Utility.toast("Something went wrong");
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    return;
                }


                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Utility.toast("Internet connection is too slow to handle this request");
                    netWorkResponseListener.onError(error, "Internet connection is too slow to handle this request");
                } else if (error instanceof AuthFailureError) {
                    Utility.toast("Authentication error, please try again later");
                    netWorkResponseListener.onError(error, "Authentication error, please try again later");
                } else if (error instanceof ServerError) {
                    Utility.toast("Server error");
                    netWorkResponseListener.onError(error, "Server error");
                } else if (error instanceof NetworkError) {
                    Utility.toast("Network error");
                    netWorkResponseListener.onError(error, "Network error");
                } else if (error instanceof ParseError) {
                    Utility.toast("Parse error");
                    netWorkResponseListener.onError(error, "Parse error");
                } else {
                    Utility.toast("Something went wrong, please try again later");
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();

                headers.put("Content-Type", "application/json");
                headers.put("authorization", "authorization");
                headers.put("token", "token");
                headers.put("os", "android");
                headers.put("Accept-Language", "en");

                return headers;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES
                , DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        jsonObjectRequest.setTag(TAG);

        requestQueue.add(jsonObjectRequest);
        requestQueue.getCache().clear();
    }

    public void volleyStringRequest(final Context context, int methodType /* Get or Post */,
                                    final HashMap<String, String> jsonParameter, String apiUrl,
                                    final String TAG, boolean isProgressBar, final NetWorkResponseListener netWorkResponseListener) {

        handleProgressDialog(context, isProgressBar);

        StringRequest stringRequest = new StringRequest(methodType, apiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                printLog(TAG, jsonParameter.toString(), apiUrl, response);

                /* remove progressDialog */
                handleProgressDialog(context, false);

                netWorkResponseListener.onSuccess(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // remove progressDialog
                handleProgressDialog(context, false);

                // print log
                printLog(TAG, jsonParameter.toString(), apiUrl, Objects.requireNonNull(error.getMessage()));

                // parse 400 response
                NetworkResponse networkResponse = error.networkResponse;

                if (error instanceof ServerError && networkResponse != null) {
                    try {
                        String response = new String(networkResponse.data,
                                HttpHeaderParser.parseCharset(networkResponse.headers, "utf-8"));

                        printLog(TAG, jsonParameter.toString(), apiUrl, response);

                        if (isValidJson(response)) {
                            netWorkResponseListener.onSuccess(response);
                        } else {
                            netWorkResponseListener.onError(error, "Something went wrong");
                            Utility.toast("Something went wrong");
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    return;
                }


                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Utility.toast("Internet connection is too slow to handle this request");
                    netWorkResponseListener.onError(error, "Internet connection is too slow to handle this request");
                } else if (error instanceof AuthFailureError) {
                    Utility.toast("Authentication error, please try again later");
                    netWorkResponseListener.onError(error, "Authentication error, please try again later");
                } else if (error instanceof ServerError) {
                    Utility.toast("Server error");
                    netWorkResponseListener.onError(error, "Server error");
                } else if (error instanceof NetworkError) {
                    Utility.toast("Network error");
                    netWorkResponseListener.onError(error, "Network error");
                } else if (error instanceof ParseError) {
                    Utility.toast("Parse error");
                    netWorkResponseListener.onError(error, "Parse error");
                } else {
                    Utility.toast("Something went wrong, please try again later");
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return updateMap(jsonParameter);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("version", "1.0.0");
                headers.put("os", "android");
                headers.put("Accept-Language", "en");

                return headers;
            }
        };
    }

    public void cancelVolleyRequest(Context context, String tag) {
        requestQueue.cancelAll(tag);

        handleProgressDialog(context, false);
    }

    private Map<String, String> updateMap(Map<String, String> map) {
        map.put("device_type", "android");
        map.put("device_address", "Utils.getDeviceAddress()");

        return map;
    }

    private void printLog(String tag, String parameter, String url, Object response) {
        LogCustom.i(tag + " API Parameter: ", parameter);
        LogCustom.i(tag + " API Url: ", url);
        LogCustom.i(tag + " API Response: ", response.toString());
    }

    private boolean isValidJson(String response) {
        try {
            new JSONObject(response);
        } catch (JSONException e) {
            try {
                new JSONArray(response);
            } catch (JSONException ex) {
                ex.printStackTrace();

                return false;
            }
            e.printStackTrace();
        }

        return true;
    }

    private void handleProgressDialog(final Context context, boolean isShowProgress) {
        try {
            if (isShowProgress) {
                if (progressDialog == null) {
                    progressDialog = ProgressDialog.show(context, "", "Please wait...");
                }
            } else {
                if (progressDialog != null) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
