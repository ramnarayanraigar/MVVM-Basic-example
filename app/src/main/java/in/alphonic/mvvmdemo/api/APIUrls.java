package in.alphonic.mvvmdemo.api;

public class APIUrls {
    private static final String ROOT_URL = "https://www.tozata.com/";

    public interface EndPoint {
        String APP_SETTINGS = ROOT_URL + "api/v1/"+"get-app-setting.php";
    }
}
