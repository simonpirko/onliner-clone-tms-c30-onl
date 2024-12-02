package by.tms.onlinerclonec30onl.service;

import by.tms.onlinerclonec30onl.dao.CustomerDAO;
import by.tms.onlinerclonec30onl.dao.OrderItemDAO;
import by.tms.onlinerclonec30onl.dao.OrdersDAO;
import by.tms.onlinerclonec30onl.dao.ShopProductDAO;
import by.tms.onlinerclonec30onl.domain.Customer;
import by.tms.onlinerclonec30onl.domain.OrderItem;
import by.tms.onlinerclonec30onl.domain.Orders;
import by.tms.onlinerclonec30onl.domain.ShopProduct;
import by.tms.onlinerclonec30onl.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
            orderDto.setCustomerId(customer.get().getId());
            orderDto.setFirstName(customer.get().getFirstName());
            orderDto.setLastName(customer.get().getLastName());
            orderDto.setPhone(customer.get().getPhone());
            orderDto.setDeliveryAddress(customer.get().getAddress());
        }
        Optional<ShopProduct> shopProduct = shopProductDAO.findByIdProductAndIdShop(productId, shopId);
        if (shopProduct.isPresent()) {
            orderDto.setShopProductId(shopProduct.get().getId());
            orderDto.setProductName(shopProduct.get().getProduct().getName());
            orderDto.setShopName(shopProduct.get().getShop().getName());
            orderDto.setShopDelivery(shopProduct.get().getDelivery());
            orderDto.setTotalPrice(shopProduct.get().getPrice());
            if (!shopProduct.get().getProduct().getPhotos().isEmpty()) {
                orderDto.setPhoto(shopProduct.get().getProduct().getPhotos().get(0));
            } else {
                orderDto.setPhoto("https://www.sales-soluciones.es/server/Portal_0010494/img/products/no_image_xxl.jpg");
            }
        }
        return orderDto;
    }

    public void save(OrderDto orderDto) {
        Orders orders = new Orders();
        orders.setCustomer(customerDAO.findByID(orderDto.getCustomerId()).get());
        orders.setTotalPrice(orderDto.getTotalPrice());
        orders.setFirstName(orderDto.getFirstName());
        orders.setLastName(orderDto.getLastName());
        orders.setPhone(orderDto.getPhone());
        orders.setDeliveryAddress(orderDto.getDeliveryAddress());
        orders.setStatus(Orders.Status.OPEN);
        Long i = ordersDAO.saveReturnId(orders);
        orders.setId(i);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrders(orders);
        orderItem.setShopProduct(shopProductDAO.findByID(orderDto.getShopProductId()).get());
        orderItem.setQuantity(1);
        orderItem.setPrice(orderDto.getTotalPrice());
        orderItemDAO.save(orderItem);
    }
}
