package rmit.ad.e_commerce_app.HttpClasses;

import android.os.AsyncTask;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReferenceHowtoUseHttpAsynce {
    String jsonString = "";

    /*
    getData.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new GetJson().execute();
        }
    });
     */

    private class GetJson extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            jsonString = HttpHandler.postSignout("http://13.213.54.188:3000/signout", "eyJraWQiOiJWT3JcL1RvY1BKU3hpQWJqUXdDTU5YYU9sdnBtcUkxRkZwb0JMYVlEejBYQT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxMzQzNjljZi00NzZkLTQ5YzYtOTI1Yy0wYTU5MWVjNmNiZWYiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGhlYXN0LTEuYW1hem9uYXdzLmNvbVwvYXAtc291dGhlYXN0LTFfRnM3SWNIMWtyIiwiY2xpZW50X2lkIjoiN3VnaDNxbWhlZTlhNGtqbDdvODNrMmg4ZDEiLCJvcmlnaW5fanRpIjoiYTMwMzI2NDgtNzBjMS00Yjk5LWI3ZWEtNmIxNzNmMzQ4ZWVkIiwiZXZlbnRfaWQiOiIyNmJkMmE4Zi02YzA0LTRmZDAtYmFkOC1mYTUyMGJhNTUzN2QiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjczOTcwNTI1LCJleHAiOjE2NzM5NzQxMjUsImlhdCI6MTY3Mzk3MDUyNSwianRpIjoiZDc4OWZlMWMtYzc2Ni00ZWMzLTlkYjItZWVjN2MzYTlmYjI2IiwidXNlcm5hbWUiOiJtaW5odnU1In0.h7kwBcdf9t3pzObLKuKtZ0Nl80eFlxZMKk4ZkzP4gNDdJyEfI136JesMFtveFqjWEK2UPtVpo5z7lnz6B2peX4pzoyXC82M_NqA__dhEEE95HhuoytG9O4BqVvEyBP0qqkJF_VJ4uRzlV7xzQHYag9tLGEiKS3vJ_oy2LUnBILJuk_IUZn6qtfwAB0CSfUHShDLSF41BT6p-3lBNzonot-Bb8LOzVEGKODCrKKo5V3F_ogPMh3s9uhhW80Gn7wGmfgSpbFmAAU7PrhXqcm7vZBQwfF-WTiq2k0p-1K9Yz5zrLnP3vgIDet50MoOw43INHkR4ourMWAorjNqAyr30wg");
            //jsonString = HttpHandler.getRequest("http://13.213.54.188:3000/search/10/1?input=shoes");
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            //jsonText.setText(jsonString);
            /*
            JSONObject json;
            try {
                json = new JSONObject(jsonString);
                System.out.println(json.get("accessToken"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
             */
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for(int i = 0; i< jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    System.out.println(jsonObject.get("title"));
                    //Product product = new Product(Integer.valueOf((Integer) jsonObject.get("Id")), jsonObject.get("title").toString());

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(jsonString);
        }

    }
}
