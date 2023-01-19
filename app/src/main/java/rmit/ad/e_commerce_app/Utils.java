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

    String jsonString = "";
    String accessToken;

    public Utils() {
        Products = new ArrayList<>();

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

                int stars  = (int) product.get("stars");
                String brand = product.get("brand").toString();
                String thumbnail = product.get("thumbnail").toString();
                String description = product.get("descriptions").toString();

                Products.add(new Product(title, price, thumbnail, id, category, brand, 1, seller_id, color, sizes, description, stars));
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


    // Find and get the book by respective ID
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

    public boolean AddToFavorite(Product productModel){
        return FavoriteProducts.add(productModel);
    }

    public boolean AddToCart(Product productModelCart) {
        return CartProducts.add(productModelCart);
    }
    public boolean RemoveFavoriteProductList(Product productModel) {
        return FavoriteProducts.remove(productModel);
    }

    public void RemoveAllFavoriteProducts() {
        FavoriteProducts.clear();
    }

}
