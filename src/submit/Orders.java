package submit;

import java.util.*;
import main.data.OrderInfo;


public class Orders {
    private List<OrderInfo> orders;
    private List<OrderInfo> waitingOrders;
    private int orderID;

    public Orders(){
        this.orders = new ArrayList<>();
        this.waitingOrders = new ArrayList<>();
        this.orderID = 1;
    }

    public int getOrderID() {
        return this.orderID;
    }

    public List<OrderInfo> getOrders() {
        return orders;
    }

    public List<OrderInfo> getWaitingOrders() {
        return waitingOrders;
    }

    public int newOrder(OrderInfo order, int waiting) {
        if(waiting == 1){
            waitingOrders.add(order);
        }
        else{
            orders.add(order);
        }
        int orderId = this.orderID;
        orderID++;
        return  orderId;
    }
}
