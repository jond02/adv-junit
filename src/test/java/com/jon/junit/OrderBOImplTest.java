package com.jon.junit;

import com.jon.order.bo.BOException;
import com.jon.order.bo.OrderBOImpl;
import com.jon.order.dao.OrderDAO;
import com.jon.order.dto.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class OrderBOImplTest {

    @Mock
    private OrderDAO dao;

    private OrderBOImpl bo;
    private final int ORDER_ID = 123;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        bo = new OrderBOImpl();
        bo.setDao(dao);
    }

    @Test
    public void placeOrder_ShouldCreateOrder() throws SQLException, BOException {

        Order order = new Order();

        //return 1 for the order id when create is called
        when(dao.create(order)).thenReturn(1);

        //test that the proper result is returned with a value of 1
        boolean result = bo.placeOrder(order);

        assertTrue(result);

        //use mockito to verify that the method was called
        verify(dao).create(order);

    }

    @Test
    public void placeOrder_ShouldNotCreateOrder() throws SQLException, BOException {

        Order order = new Order();

        //return 1 for the order id when create is called
        when(dao.create(order)).thenReturn(0);

        //test that the proper result is returned with a value of 0
        boolean result = bo.placeOrder(order);

        assertFalse(result);

        //use mockito to verify that the method was called
        verify(dao, atLeast(1)).create(order);
        //times(1) number of times that method was run
        //atLeast(1) at least run number of times

    }

    @Test(expected = BOException.class)
    public void placeOrder_ShouldThrowBOException() throws SQLException, BOException {

        Order order = new Order();
        //matcher, can use any instead of creating an order
        when(dao.create(any(Order.class))).thenThrow(SQLException.class);
        bo.placeOrder(order);
    }

    @Test
    public void cancelOrder_ShouldCancelOrder() throws SQLException, BOException {

        Order order = new Order();

        //matchers, any methods, don't need to input exact values
        when(dao.read(anyInt())).thenReturn(order);
        when(dao.update(order)).thenReturn(1);

        boolean result = bo.cancelOrder(ORDER_ID);

        assertTrue(result);

        verify(dao).read(ORDER_ID);
        verify(dao).update(order);
    }

    @Test
    public void cancelOrder_ShouldNotCancelOrder() throws SQLException, BOException {

        Order order = new Order();

        when(dao.read(ORDER_ID)).thenReturn(order);
        when(dao.update(order)).thenReturn(0);

        boolean result = bo.cancelOrder(ORDER_ID);

        assertFalse(result);
        
        verify(dao).read(ORDER_ID);
        verify(dao).update(order);
    }

    @Test(expected = BOException.class)
    public void cancelOrder_ShouldThrowABOExceptionOnRead() throws SQLException, BOException {

        when(dao.read(ORDER_ID)).thenThrow(SQLException.class);
        bo.cancelOrder(ORDER_ID);
    }

    @Test(expected = BOException.class)
    public void cancelOrder_ShouldThrowABOExceptionOnUpdate() throws SQLException, BOException {

        Order order = new Order();

        when(dao.read(ORDER_ID)).thenReturn(order);
        when(dao.update(order)).thenThrow(SQLException.class);
        bo.cancelOrder(ORDER_ID);
    }

    @Test
    public void deleteOrder_DeletesOrder() throws SQLException, BOException {

        when(dao.delete(ORDER_ID)).thenReturn(1);
        boolean result = bo.deleteOrder(ORDER_ID);
        assertTrue(result);
        verify(dao).delete(ORDER_ID);
    }

    @Test
    public void deleteOrder_ShouldNotDeleteOrder() throws SQLException, BOException {

        when(dao.delete(ORDER_ID)).thenReturn(0);
        boolean result = bo.deleteOrder(ORDER_ID);
        assertFalse(result);
        verify(dao).delete(ORDER_ID);
    }

    @Test(expected = BOException.class)
    public void deleteOrder_ShouldThrowABOException() throws SQLException, BOException {

        when(dao.delete(ORDER_ID)).thenThrow(SQLException.class);
        bo.deleteOrder(ORDER_ID);
    }
}
