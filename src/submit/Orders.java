package submit;

import java.util.*;
import main.data.OrderInfo;


public class Orders {
    private Map<Integer,List<OrderInfo>> orders;
    private Map<Integer,List<OrderInfo>> waitingOrders;
    private int orderID;

    public Orders(){
        this.orders = new HashMap<>();
        this.waitingOrders = new HashMap<>();
        this.orderID = 1;
    }

    public int getOrderID() {
        return this.orderID;
    }

    public List<OrderInfo> getOrders(int memberID) {
        return orders.get(memberID);
    }

    public List<OrderInfo> getWaitingOrders(int showID) {
        return waitingOrders.get(showID);
    }

    public int newOrder(OrderInfo order, int waiting) {
        List<OrderInfo> newI;
        if(waiting == 1){
            newI = this.waitingOrders.get(order.showId);
            if (newI == null) {
                newI = new ArrayList<>();
            }
            for(OrderInfo orderInfo: newI){
                if(orderInfo.name == order.name){
                    return this.orderID++;
                }
            }
            newI.add(order);
            this.waitingOrders.put(order.showId,newI);
        }
        else{
            newI = this.orders.get(order.showId);
            if(newI == null){
                newI = new ArrayList<>();
            }
            newI.add(order);
            this.orders.put(order.showId,newI);
        }
        return  orderID++;
    }
}
