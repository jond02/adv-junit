package com.jon.order.bo;

import com.jon.order.dao.OrderDAO;
import com.jon.order.dto.Order;

public class OrderBOImpl implements OrderBO {

    private OrderDAO orderDAO;

    public boolean placeOrder(Order order) throws BOException {
        return false;
    }

    public boolean cancelOrder(int id) throws BOException {
        return false;
    }

    public boolean deleteOrder(int id) throws BOException {
        return false;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
}
