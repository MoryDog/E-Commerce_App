package rmit.ad.e_commerce_app.Activities;

import android.app.Application;

public class GlobalUserAccess extends Application {

    private String accessToken;
    private String idToken;
    private String refreshToken;
    public GlobalUserAccess() {
        accessToken = "";
        idToken = "";
        refreshToken = "";
    }

    public GlobalUserAccess(String accessToken, String idToken, String refreshToken) {
        this.accessToken = accessToken;
        this.idToken = idToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "GlobalUserAccess{" +
                "accessToken='" + accessToken + '\'' +
                ", idToken='" + idToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
