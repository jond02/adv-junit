package com.jon.order.bo;

import com.jon.order.dao.OrderDAO;
import com.jon.order.dto.Order;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {

    private OrderDAO dao;

    public boolean placeOrder(Order order) throws BOException {

        try {
            int result = dao.create(order);

            if (result == 0) {
                return false;
            }

        } catch (SQLException e) {
            throw new BOException(e);
        }

        return true;
    }

    public boolean cancelOrder(int id) throws BOException {

        try {
            Order order = dao.read(id);
            order.setStatus("cancelled");
            int result = dao.update(order);
            if (result == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new BOException(e);
        }
        return true;
    }

    public boolean deleteOrder(int id) throws BOException {

        try {
            int result = dao.delete(id);
            if (result == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new BOException(e);
        }
        return true;
    }

    public void setDao(OrderDAO dao) {
        this.dao = dao;
    }
}
