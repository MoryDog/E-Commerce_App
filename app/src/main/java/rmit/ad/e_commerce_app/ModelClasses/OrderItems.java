package rmit.ad.e_commerce_app.ModelClasses;

public class OrderItems {
    private String thumbnail;
    private long ID;
    private int quantity;

    public OrderItems(long ID, int quantity) {
        this.ID = ID;
        this.quantity = quantity;
    }

    public OrderItems(String thumbnail, long ID, int quantity) {
        this.thumbnail = thumbnail;
        this.ID = ID;
        this.quantity = quantity;

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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
}
