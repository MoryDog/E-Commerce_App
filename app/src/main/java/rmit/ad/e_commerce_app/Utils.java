package rmit.ad.e_commerce_app;

import java.util.ArrayList;

import rmit.ad.e_commerce_app.ModelClasses.ProductModel;

public class Utils {
    private static Utils instance;

    private static ArrayList<ProductModel> Products;
    private static ArrayList<ProductModel> ShoeProducts;

    private Utils() {
        if (Products == null){
            Products = new ArrayList<>();
            SetInitialData();
        }
        if (ShoeProducts == null){
            ShoeProducts = new ArrayList<>();
            SetShoesData();
        }
    }

    private void SetInitialData() {
        //Manually create initial data for the book and then add it to the all book list
        Products.add(new ProductModel(1, "iPhone 13", "99999 $", "https://www.svstore.vn/uploads/source/iphone-13prm/iphone-13-pro-max-blue-select.png", "Phone", "Apple", 1));
        Products.add(new ProductModel(2, "T-Shirt", "199999 $", "https://chapel.vn/wp-content/uploads/2021/07/hn.jpg", "Phone", "Apple", 1));
        Products.add(new ProductModel(3, "Rolex", "299999 $", "https://transform.octanecdn.com/fitLogo/400x500/https://dynamix-cdn.s3.amazonaws.com/jacobandcocom/jacobandcocom_423193262.png", "Phone", "Apple", 1));
        Products.add(new ProductModel(4, "Shoes", "399999 $", "https://cdn.shopify.com/s/files/1/1626/5391/products/Balenciaga-Triple-S-Nude-Transparent-Sole-Crepslocker-Side_f26facf3-2c43-448b-b5f5-a75381a6b209.jpg?v=1652088899", "Phone", "Apple", 1));
        Products.add(new ProductModel(5, "Cosmetic", "499999 $", "https://product.hstatic.net/1000341646/product/hera-sensual-powder-matte-499-rosy-suede-2_60f6f3f63e0e414c9a3e63d333e19e11.jpg", "Phone", "Apple", 1));
        Products.add(new ProductModel(6, "Household", "599999 $", "https://cdn.nguyenkimmall.com/images/detailed/727/10049167-binh-dun-sieu-toc-sharp-ekj-10dvps-bk-1.jpg", "Phone", "Apple", 1));
        Products.add(new ProductModel(7, "Health", "699999 $", "https://bucket.nhanh.vn/store/4726/ps/20210819/19082021040855_DSCF0825.png", "Phone", "Apple", 1));
        Products.add(new ProductModel(8, "Laptops", "799999 $", "https://m.media-amazon.com/images/I/71NIJloNGoL._SL1500_.jpg", "Phone", "Apple", 1));
    }


    private void SetShoesData() {
        //Manually create initial data for the book and then add it to the all book list
        ShoeProducts.add(new ProductModel(4, "Shoes", "399999 $", "https://cdn.shopify.com/s/files/1/1626/5391/products/Balenciaga-Triple-S-Nude-Transparent-Sole-Crepslocker-Side_f26facf3-2c43-448b-b5f5-a75381a6b209.jpg?v=1652088899", "Phone", "Apple", 1));
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

    public ArrayList<ProductModel> getAllProducts() {
        return Products;
    }

    public ArrayList<ProductModel> getShoeProducts() {
        return ShoeProducts;
    }
}
