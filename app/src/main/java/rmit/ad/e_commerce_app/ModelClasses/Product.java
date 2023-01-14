package rmit.ad.e_commerce_app.ModelClasses;

public class Product {
    private long ID;
    private String title, description, category, image, supplier;
    private int quantity;
    private double price;

    // Constructors
    public Product() {};

    public Product(long ID, String title, String description, String category, String image, String supplier, int quantity, double price) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.category = category;
        this.image = image;
        this.supplier = supplier;
        this.quantity = quantity;
        this.price = price;
    }

    // Setters and Getters
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
