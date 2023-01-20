package rmit.ad.e_commerce_app.ModelClasses;

public class Product {
    private String title;
    private String price;
    private String thumbnail;
    private long ID;
    private String category;
    private String brand;
    private int quantity;

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    private int quantityOrdered;
    private int seller_id;
    private String colors;
    private String sizes;
    private String description;
    private int stars;

    public Product(long ID , String title, String price, String thumbnail, String category, String brand, int quantity) {
        this.title = title;
        this.price = price;
        this.thumbnail = thumbnail;
        this.ID = ID;
        this.category = category;
        this.brand = brand;
        this.quantity = quantity;
    }

    public Product(String title, String price, String thumbnail, long ID, String category, String brand, int quantity, int seller_id, String colors, String sizes, String description, int stars) {
        this.title = title;
        this.price = price;
        this.thumbnail = thumbnail;
        this.ID = ID;
        this.category = category;
        this.brand = brand;
        this.quantity = quantity;
        this.seller_id = seller_id;
        this.colors = colors;
        this.sizes = sizes;
        this.description = description;
        this.stars = stars;
    }

    public Product(String title, String price, String thumbnail, long ID, String category, String brand, int quantity, int seller_id, String colors, String sizes, String description, int stars, int quantityOrdered) {
        this.title = title;
        this.price = price;
        this.thumbnail = thumbnail;
        this.ID = ID;
        this.category = category;
        this.brand = brand;
        this.quantity = quantity;
        this.seller_id = seller_id;
        this.colors = colors;
        this.sizes = sizes;
        this.description = description;
        this.stars = stars;
        this.quantityOrdered = quantityOrdered;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg_name() {
        return thumbnail;
    }

    public void setImg_name(String img_name) {
        this.thumbnail = img_name;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}