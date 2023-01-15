package rmit.ad.e_commerce_app.ModelClasses;

public class ProductModel {
    private String name;
    private String price;
    private String img_url;
    private long ID;
    private String category;
    private String supplier;
    private int quantity;


    public ProductModel(long ID, String title, String price, String img_name, String category, String supplier, int quantity) {
        this.name = title;
        this.price = price;
        this.img_url = img_name;
        this.ID = ID;
        this.category = category;
        this.supplier = supplier;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg_name() {
        return img_url;
    }

    public void setImg_name(String img_name) {
        this.img_url = img_name;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
