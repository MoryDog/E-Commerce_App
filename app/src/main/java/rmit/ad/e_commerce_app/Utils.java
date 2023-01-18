package rmit.ad.e_commerce_app;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

import rmit.ad.e_commerce_app.HttpClasses.HttpHandler;
import rmit.ad.e_commerce_app.ModelClasses.ProductModel;

public class Utils {
    private static Utils instance;

    private static ArrayList<ProductModel> Products = new ArrayList<>();
    private static ArrayList<ProductModel> ShoeProducts;
    private static ArrayList<ProductModel> ShirtProducts;
    private static ArrayList<ProductModel> PhoneProducts;
    private static ArrayList<ProductModel> WatchProducts;
    private static ArrayList<ProductModel> LaptopProducts;
    private static ArrayList<ProductModel> FavoriteProducts;

    public Utils() {

        if (Products == null){
            Products = new ArrayList<>();
        }

        if (FavoriteProducts == null){
            FavoriteProducts = new ArrayList<>();
        }

        if (ShoeProducts == null){
            ShoeProducts = new ArrayList<>();
            SetShoesData();
        }
        if (ShirtProducts == null){
            ShirtProducts = new ArrayList<>();
            SetShirtData();
        }

        if (PhoneProducts == null){
            PhoneProducts = new ArrayList<>();
            SetPhoneData();
        }

        if (WatchProducts == null){
            WatchProducts = new ArrayList<>();
            SetWatchData();
        }

        if (LaptopProducts == null){
            LaptopProducts = new ArrayList<>();
            SetLaptopData();
        }
    }

    private void SetLaptopData() {
        LaptopProducts.add(new ProductModel(8, "Laptops", "799999 $", "https://m.media-amazon.com/images/I/71NIJloNGoL._SL1500_.jpg", "Phone", "Apple", 1));
    }

    private void SetWatchData() {
        WatchProducts.add(new ProductModel(3, "Rolex", "299999 $", "https://transform.octanecdn.com/fitLogo/400x500/https://dynamix-cdn.s3.amazonaws.com/jacobandcocom/jacobandcocom_423193262.png", "Phone", "Apple", 1));
    }

    private void SetPhoneData() {
        PhoneProducts.add(new ProductModel(1, "iPhone 13", "99999 $", "https://androidecommercebucket.s3.ap-southeast-1.amazonaws.com/image%253A457075.png", "Phone", "Apple", 1));
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

                Products.add(new ProductModel(title, price, thumbnail, id, category, brand, 1, seller_id, color, sizes, description, stars));
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

        ShoeProducts.add(new ProductModel(4, "Shoes", "399999 $", "https://cdn.shopify.com/s/files/1/1626/5391/products/Balenciaga-Triple-S-Nude-Transparent-Sole-Crepslocker-Side_f26facf3-2c43-448b-b5f5-a75381a6b209.jpg?v=1652088899", "Phone", "Apple", 1));
    }

    private void SetShirtData() {

        ShirtProducts.add(new ProductModel(2, "T-Shirt", "199999 $", "https://chapel.vn/wp-content/uploads/2021/07/hn.jpg", "Phone", "Apple", 1));
    }

    public static Utils obtainInstance() {
        if (null == instance) {
            instance = new Utils();
        }
        return instance;
    }


    // Find and get the book by respective ID
    public ProductModel GetProductByID(long id){
        for (ProductModel p:Products){
            if (p.getID() == id){
                return p;
            }
        }
        return null;
    }
    public ArrayList<ProductModel> getLaptopProducts() {
        return LaptopProducts;
    }

    public ArrayList<ProductModel> getAllProducts() {
        return Products;
    }

    public ArrayList<ProductModel> getShoeProducts() {
        return ShoeProducts;
    }

    public ArrayList<ProductModel> getShirtProducts() {
        return ShirtProducts;
    }

    public ArrayList<ProductModel> getPhoneProducts() {
        return PhoneProducts;
    }

    public ArrayList<ProductModel> getWatchProducts() {
        return WatchProducts;
    }

    public ArrayList<ProductModel> getFavoriteProducts() {
        return FavoriteProducts;
    }

    public boolean AddToFavorite(ProductModel productModel){
        return FavoriteProducts.add(productModel);
    }
}
