package by.tms.onlinerclonec30onl.service;

import by.tms.onlinerclonec30onl.dao.CustomerDAO;
import by.tms.onlinerclonec30onl.dao.OrderItemDAO;
import by.tms.onlinerclonec30onl.dao.OrdersDAO;
import by.tms.onlinerclonec30onl.dao.ShopProductDAO;
import by.tms.onlinerclonec30onl.domain.Customer;
import by.tms.onlinerclonec30onl.domain.Orders;
import by.tms.onlinerclonec30onl.domain.ShopProduct;
import by.tms.onlinerclonec30onl.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrdersDAO ordersDAO;

    @Autowired
    OrderItemDAO orderItemDAO;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    ShopProductDAO shopProductDAO;

    public OrderDto getOrders(Long customerId, Long shopId, Long productId) {
        OrderDto orderDto = new OrderDto();
        Optional<Customer> customer = customerDAO.findByID(customerId);
        if (customer.isPresent()) {
            orderDto.setCustomer(customer.get());
            orderDto.setFirstName(customer.get().getFirstName());
            orderDto.setLastName(customer.get().getLastName());
            orderDto.setPhone(customer.get().getPhone());
            orderDto.setDeliveryAddress(customer.get().getAddress());
        }
        orderDto.setShopProduct(shopProductDAO.findByIdProductAndIdShop(productId, shopId).get());
        return orderDto;
    }
}
