package rmit.ad.e_commerce_app.ModelClasses;

public class OrderItems {
    private long ID;
    private int quantity;

    public OrderItems(long ID, int quantity) {
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
}
