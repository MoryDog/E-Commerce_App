package rmit.ad.e_commerce_app.Activities;

import android.app.Application;

public class GlobalUserAccess extends Application {

    private String accessToken;
    private String idToken;
    private String refreshToken;
    private String userRole;

    public GlobalUserAccess() {
        accessToken = "";
        idToken = "";
        refreshToken = "";
        userRole = "";
    }

    public GlobalUserAccess(String accessToken, String idToken, String refreshToken, String userRole) {
        this.accessToken = accessToken;
        this.idToken = idToken;
        this.refreshToken = refreshToken;
        this.userRole = userRole;
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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
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
