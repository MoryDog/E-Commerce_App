package rmit.ad.e_commerce_app.ModelClasses;

import java.util.ArrayList;

public class Order {
    private int total;
    private String shippingAddress;
    private ArrayList<OrderItems> orderItemsList = new ArrayList<>();


    public Order(int total, String shippingAddress) {
        this.total = total;
        this.shippingAddress = shippingAddress;
    }

    public Order(int total, String shippingAddress, ArrayList<OrderItems> orderItemsList) {
        this.total = total;
        this.shippingAddress = shippingAddress;
        this.orderItemsList = orderItemsList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ArrayList<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(ArrayList<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }




}
