package in.alphonic.mvvmdemo.utility;

import android.widget.Toast;

import in.alphonic.mvvmdemo.application.Application;

public class Utility {
    public static void toast(String message) {
        Toast.makeText(Application.APP_CONTEXT, message, Toast.LENGTH_SHORT).show();
    }
}
