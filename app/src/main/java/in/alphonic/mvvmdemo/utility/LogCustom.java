package in.alphonic.mvvmdemo.utility;

import android.util.Log;

public class LogCustom {
    public static void v(String tag, String message) {
        Log.v(tag, message);
    }

    public static void d(String tag, String message) {
        Log.d(tag, message);
    }

    public static void i(String tag, String message) {
        Log.i(tag, message);
    }

    public static void w(String tag, String message) {
        Log.w(tag, message);
    }

    public static void e(String tag, String message) {
        Log.e(tag, message);
    }
}
