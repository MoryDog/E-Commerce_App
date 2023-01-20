package rmit.ad.e_commerce_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rmit.ad.e_commerce_app.Activities.GlobalUserAccess;
import rmit.ad.e_commerce_app.Activities.Login;
import rmit.ad.e_commerce_app.Activities.MainActivity;
import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.ModelClasses.Order;
import rmit.ad.e_commerce_app.ModelClasses.Product;
import rmit.ad.e_commerce_app.SellerActivities.SellerActivity;

public class Utils {
    private static Utils instance;

    private static ArrayList<Product> Products = new ArrayList<>();
    private static ArrayList<Product> ShoeProducts;
    private static ArrayList<Product> ShirtProducts;
    private static ArrayList<Product> PhoneProducts;
    private static ArrayList<Product> WatchProducts;
    private static ArrayList<Product> LaptopProducts;
    private static ArrayList<Product> FavoriteProducts = new ArrayList<>();
    private static ArrayList<Product> CartProducts = new ArrayList<>();
    private static ArrayList<Order> Orders = new ArrayList<>();

    String jsonString = "";
    String accessToken;

    public Utils() {
        Products = new ArrayList<>();

    }

    public void setOrdersData(String data){
        try {
            JSONArray jsonArray = new JSONArray(data);
            for(int i = 0; i< jsonArray.length(); i++){
                JSONObject product = jsonArray.getJSONObject(i);

                int id = Integer.parseInt(product.getString("Id"));
                int total = Integer.parseInt(product.getString("total"));
                String shipping_address = product.getString("shipping_adress");
                String order_status = product.getString("order_status");
                Orders.add(new Order(id, total,shipping_address,order_status));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setData(String data){
        try {
            JSONArray jsonArray = new JSONArray(data);
            for(int i = 0; i< jsonArray.length(); i++){
                JSONObject product = jsonArray.getJSONObject(i);
                int id = (int) product.get("Id");
                int seller_id = (int) product.get("seller_id");
                String category = product.get("category").toString();
                String title = product.get("title").toString();
                String price = product.get("price").toString();
                String color = product.get("colors").toString();
                String sizes = product.get("sizes").toString();
                int quantity = (int) product.get("quantity");
                int stars  = (int) product.get("stars");
                String brand = product.get("brand").toString();
                String thumbnail = product.get("thumbnail").toString();
                String description = product.get("descriptions").toString();

                Products.add(new Product(title, price, thumbnail, id, category, brand, quantity, seller_id, color, sizes, description, stars));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public void SetInitialData() {
        /*
        Products.add(new ProductModel(1, "iPhone 13", "99999 $", "https://androidecommercebucket.s3.ap-southeast-1.amazonaws.com/image%253A457075.png", "Phone", "Apple", 1));
        Products.add(new ProductModel(2, "T-Shirt", "199999 $", "https://chapel.vn/wp-content/uploads/2021/07/hn.jpg", "Phone", "Apple", 1));
        Products.add(new ProductModel(3, "Rolex", "299999 $", "https://transform.octanecdn.com/fitLogo/400x500/https://dynamix-cdn.s3.amazonaws.com/jacobandcocom/jacobandcocom_423193262.png", "Phone", "Apple", 1));
        Products.add(new ProductModel(4, "Shoes", "399999 $", "https://cdn.shopify.com/s/files/1/1626/5391/products/Balenciaga-Triple-S-Nude-Transparent-Sole-Crepslocker-Side_f26facf3-2c43-448b-b5f5-a75381a6b209.jpg?v=1652088899", "Phone", "Apple", 1));
        Products.add(new ProductModel(5, "Cosmetic", "499999 $", "https://product.hstatic.net/1000341646/product/hera-sensual-powder-matte-499-rosy-suede-2_60f6f3f63e0e414c9a3e63d333e19e11.jpg", "Phone", "Apple", 1));
        Products.add(new ProductModel(6, "Household", "599999 $", "https://cdn.nguyenkimmall.com/images/detailed/727/10049167-binh-dun-sieu-toc-sharp-ekj-10dvps-bk-1.jpg", "Phone", "Apple", 1));
        Products.add(new ProductModel(7, "Health", "699999 $", "https://bucket.nhanh.vn/store/4726/ps/20210819/19082021040855_DSCF0825.png", "Phone", "Apple", 1));
        Products.add(new ProductModel(8, "Laptops", "799999 $", "https://m.media-amazon.com/images/I/71NIJloNGoL._SL1500_.jpg", "Phone", "Apple", 1));
        */
    }

    private void SetShoesData() {

        ShoeProducts.add(new Product(4, "Shoes", "399999 $", "https://cdn.shopify.com/s/files/1/1626/5391/products/Balenciaga-Triple-S-Nude-Transparent-Sole-Crepslocker-Side_f26facf3-2c43-448b-b5f5-a75381a6b209.jpg?v=1652088899", "Phone", "Apple", 1));
    }

    private void SetShirtData() {

        ShirtProducts.add(new Product(2, "T-Shirt", "199999 $", "https://chapel.vn/wp-content/uploads/2021/07/hn.jpg", "Phone", "Apple", 1));
    }

    public static Utils obtainInstance() {
        if (null == instance) {
            instance = new Utils();
        }

        return instance;
    }


    // Find and get the product by respective ID
    public Product GetProductByID(long id){
        for (Product p:Products){
            if (p.getID() == id){
                return p;
            }
        }
        return null;
    }

    public ArrayList<Product> getLaptopProducts() {
        return LaptopProducts;
    }

    public ArrayList<Product> getAllProducts() {
        return Products;
    }

    public ArrayList<Product> getShoeProducts() {
        return ShoeProducts;
    }

    public ArrayList<Product> getShirtProducts() {
        return ShirtProducts;
    }

    public ArrayList<Product> getPhoneProducts() {
        return PhoneProducts;
    }

    public ArrayList<Product> getWatchProducts() {
        return WatchProducts;
    }

    public ArrayList<Product> getFavoriteProducts() {
        System.out.println("Went to getFavoriteProducts");
        System.out.println(FavoriteProducts);
        return FavoriteProducts;
    }

    public ArrayList<Product> getCartProducts() {
        return CartProducts;
    }

    public int getCartProduct(){
        return CartProducts.size();
    }


    public ArrayList<Order> getOrders() {return  Orders;}



    public boolean AddToFavorite(Product productModel){
        return FavoriteProducts.add(productModel);
    }

    public boolean AddToCart(Product productModelCart) {
        return CartProducts.add(productModelCart);
    }
    public boolean RemoveProductInCart(Product product) {
        return CartProducts.remove(product);
    }

    public boolean RemoveFavoriteProductList(Product productModel) {
        return FavoriteProducts.remove(productModel);
    }

    public void RemoveAllFavoriteProducts() {
        FavoriteProducts.clear();
    }

    private class GetProductByIdAysnc extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            jsonString = HttpHandler.postSignout("http://13.213.54.188:3000/signout", "eyJraWQiOiJWT3JcL1RvY1BKU3hpQWJqUXdDTU5YYU9sdnBtcUkxRkZwb0JMYVlEejBYQT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxMzQzNjljZi00NzZkLTQ5YzYtOTI1Yy0wYTU5MWVjNmNiZWYiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGhlYXN0LTEuYW1hem9uYXdzLmNvbVwvYXAtc291dGhlYXN0LTFfRnM3SWNIMWtyIiwiY2xpZW50X2lkIjoiN3VnaDNxbWhlZTlhNGtqbDdvODNrMmg4ZDEiLCJvcmlnaW5fanRpIjoiYTMwMzI2NDgtNzBjMS00Yjk5LWI3ZWEtNmIxNzNmMzQ4ZWVkIiwiZXZlbnRfaWQiOiIyNmJkMmE4Zi02YzA0LTRmZDAtYmFkOC1mYTUyMGJhNTUzN2QiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjczOTcwNTI1LCJleHAiOjE2NzM5NzQxMjUsImlhdCI6MTY3Mzk3MDUyNSwianRpIjoiZDc4OWZlMWMtYzc2Ni00ZWMzLTlkYjItZWVjN2MzYTlmYjI2IiwidXNlcm5hbWUiOiJtaW5odnU1In0.h7kwBcdf9t3pzObLKuKtZ0Nl80eFlxZMKk4ZkzP4gNDdJyEfI136JesMFtveFqjWEK2UPtVpo5z7lnz6B2peX4pzoyXC82M_NqA__dhEEE95HhuoytG9O4BqVvEyBP0qqkJF_VJ4uRzlV7xzQHYag9tLGEiKS3vJ_oy2LUnBILJuk_IUZn6qtfwAB0CSfUHShDLSF41BT6p-3lBNzonot-Bb8LOzVEGKODCrKKo5V3F_ogPMh3s9uhhW80Gn7wGmfgSpbFmAAU7PrhXqcm7vZBQwfF-WTiq2k0p-1K9Yz5zrLnP3vgIDet50MoOw43INHkR4ourMWAorjNqAyr30wg");
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

        }

    }
}
