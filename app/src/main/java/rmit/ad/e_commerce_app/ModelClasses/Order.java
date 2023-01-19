package rmit.ad.e_commerce_app.ModelClasses;

import java.util.ArrayList;

public class Order {
    private int total;
    private String shippingAddress, orderTitle, orderStatus;
    private ArrayList<OrderItems> orderItemsList = new ArrayList<>();


    public Order(int total, String shippingAddress, String orderTitle, String orderStatus) {
        this.total = total;
        this.shippingAddress = shippingAddress;
        this.orderTitle = orderTitle;
        this.orderStatus = orderStatus;
    }

    public Order(int total, String shippingAddress, String orderTitle, String orderStatus, ArrayList<OrderItems> orderItemsList) {
        this.total = total;
        this.shippingAddress = shippingAddress;
        this.orderTitle = orderTitle;
        this.orderStatus = orderStatus;
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

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ArrayList<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(ArrayList<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }




}
