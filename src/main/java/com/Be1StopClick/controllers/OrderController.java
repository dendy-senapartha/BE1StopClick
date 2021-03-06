package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.*;
import com.Be1StopClick.dto.InvoiceDTO;
import com.Be1StopClick.dto.OrderDTO;
import com.Be1StopClick.dto.PaymentMethodDTO;
import com.Be1StopClick.dto.request.*;
import com.Be1StopClick.dto.response.AddOrderItemToOrderResponse;
import com.Be1StopClick.dto.response.GetOrderDetailsResponse;
import com.Be1StopClick.dto.response.PayingOrderResponse;
import com.Be1StopClick.dto.response.RemoveOrderItemFromOrderResponse;
import com.Be1StopClick.model.*;
import org.hibernate.Hibernate;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Created by dendy-prtha on 04/06/2019.
 * Controller for order
 */

@RestController
public class OrderController {

    @Autowired
    private OrderDao orderRepository;

    @Autowired
    private UserDao userRepository;

    @Autowired
    private ProductDao productRepository;

    @Autowired
    private PaymentMethodDao paymentMethodRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BalanceDao balanceRepository;

    private Converter<Orders, OrderDTO> orderConverter = new Converter<Orders, OrderDTO>() {
        @Override
        public OrderDTO convert(MappingContext<Orders, OrderDTO> mappingContext) {
            Orders source = mappingContext.getSource();
            OrderDTO destination = new OrderDTO();
            destination.setId(source.getId());
            destination.setOrderDate(source.getOrderDate());
            destination.setTotalAmount(source.getTotalAmount());

            InvoiceDTO invoiceDTO = new InvoiceDTO();
            invoiceDTO.setId(source.getInvoice().getId());
            invoiceDTO.setCreated(source.getInvoice().getCreated());
            invoiceDTO.setDescription(source.getInvoice().getDescription());
            invoiceDTO.setStatus(source.getInvoice().getStatus());

            PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
            paymentMethodDTO.setId(source.getInvoice().getPaymentMethod().getId());
            paymentMethodDTO.setCode(source.getInvoice().getPaymentMethod().getCode());
            paymentMethodDTO.setName(source.getInvoice().getPaymentMethod().getName());
            invoiceDTO.setPaymentMethod(paymentMethodDTO);

            String orderTitle = "";
            for (OrderItem orderItem : source.getOrderItemList()) {
                orderTitle += orderItem.getProduct().getProductName() + ", ";
            }

            destination.setOrderTitle(orderTitle);
            destination.setInvoice(invoiceDTO);
            return destination;
        }
    };

    //@PostMapping(value = "/order/create-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean createOrder(@RequestBody CreateOrderRequest createOrderRequest) throws ParseException {

        Date orderDate = new SimpleDateFormat("dd-MM-yyyy").parse(createOrderRequest.getOrderDate());
        int paymentMethodId = Integer.parseInt(createOrderRequest.getPaymentMethodId());
        String invoiceStatus = createOrderRequest.getInvoiceStatus();
        String description = createOrderRequest.getDescription();
        long userId = Long.parseLong(createOrderRequest.getUserId());
        List<CreateOrderRequest.OrderItem> orderItemList = new ArrayList<>(createOrderRequest.getOrderItemList());

        Map<String, String> result = new HashMap<>();
        Orders order = new Orders();
        BigDecimal totalAmount = new BigDecimal(0);

        Optional<User> userOptional = userRepository.findById(userId);
        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }

        Optional<PaymentMethod> paymentMethodOptional = paymentMethodRepository.findById(paymentMethodId);
        PaymentMethod paymentMethod = null;
        if (paymentMethodOptional.isPresent()) {
            paymentMethod = paymentMethodOptional.get();
        }

        for (CreateOrderRequest.OrderItem orderItemDTO : orderItemList) {
            int quantity = Integer.parseInt(orderItemDTO.getQuantity());
            BigDecimal subtotal = new BigDecimal(orderItemDTO.getSubtotal());
            Optional<Product> productOptional = productRepository.findById(Integer.valueOf(orderItemDTO.getProductId()));
            Product product = null;
            if (productOptional.isPresent()) {
                product = productOptional.get();
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(quantity);
            orderItem.setSubtotal(subtotal);
            orderItem.setProduct(product);

            totalAmount = totalAmount.add(subtotal);
            order.addOrderItem(orderItem);
        }

        Invoice invoice = new Invoice();
        invoice.setPaymentMethod(paymentMethod);
        invoice.setUser(user);
        invoice.setStatus(invoiceStatus);
        invoice.setDescription(description);
        invoice.setCreated(orderDate);

        order.addInvoice(invoice);
        order.setTotalAmount(totalAmount);
        order.setOrderDate(orderDate);

        //result.put("result", "" + orderRepository.save(order));
        return orderRepository.save(order);
    }

    @PostMapping(value = "/order/add-item-to-order",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, AddOrderItemToOrderResponse> addItemToOrder(@RequestBody AddOrderItemToOrderRequest orderItem) throws ParseException {
        Map<String, AddOrderItemToOrderResponse> result = new HashMap<>();
        AddOrderItemToOrderResponse response = new AddOrderItemToOrderResponse();

        AddOrderItemToOrderRequest.OrderItem theItem = orderItem.getOrderItem();
        int quantity = Integer.parseInt(theItem.getQuantity());
        int productId = Integer.parseInt(theItem.getProductId());
        long userId = Long.parseLong(orderItem.getUserId());

        //find the order item product
        Optional<Product> productOptional = productRepository.findById(productId);
        Product product = null;
        if (productOptional.isPresent()) {
            product = productOptional.get();
        }
        //find if there any draft order
        List<Orders> draftOrder = orderRepository.findUserDraftOrder(userId);
        if (!draftOrder.isEmpty()) {

            BigDecimal subtotal = product.getPrice().multiply(new BigDecimal(quantity));
            OrderItem orderItemToAdd = new OrderItem();
            orderItemToAdd.setQuantity(quantity);
            orderItemToAdd.setSubtotal(subtotal);
            orderItemToAdd.setProduct(product);

            //make sure there is only one order that has DRAFT status
            if (draftOrder.size() == 1) {
                for (Orders currentOrder : draftOrder) {
                    orderItemToAdd.setOrder(currentOrder);
                    BigDecimal newTotalAmount = BigDecimal.ZERO;
                    for (OrderItem currentOrderItem : currentOrder.getOrderItemList()) {
                        newTotalAmount = newTotalAmount.add(currentOrderItem.getSubtotal());
                    }
                    currentOrder.setTotalAmount(newTotalAmount.add(subtotal));
                    currentOrder.addOrderItem(orderItemToAdd);
                    response.setStatus(orderRepository.update(currentOrder) + "");
                    response.setItemId(orderItemToAdd.getProduct().getId() + "");
                }
            }
        }
        //no draft order before.. so create one
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            CreateOrderRequest createOrderRequest = new CreateOrderRequest();
            createOrderRequest.setOrderDate(sdf.format(Calendar.getInstance().getTime()));
            createOrderRequest.setInvoiceStatus("DRAFT");
            createOrderRequest.setDescription("");
            createOrderRequest.setPaymentMethodId("1");
            createOrderRequest.setUserId("" + userId);

            List<CreateOrderRequest.OrderItem> orderItems = new ArrayList<>();
            CreateOrderRequest.OrderItem itemToAdd = new CreateOrderRequest.OrderItem();
            itemToAdd.setProductId(theItem.getProductId());
            itemToAdd.setQuantity(theItem.getQuantity());
            itemToAdd.setSubtotal(product.getPrice() + "");
            orderItems.add(itemToAdd);

            createOrderRequest.setOrderItemList(orderItems);
            response.setStatus(createOrder(createOrderRequest) + "");
            response.setItemId(itemToAdd.getProductId() + "");
        }
        result.put("result", modelMapper.map(response, AddOrderItemToOrderResponse.class));
        return result;
    }

    @PostMapping(value = "/order/remove-item-from-order",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, RemoveOrderItemFromOrderResponse> removeItemFromOrder(@RequestBody RemoveOrderItemFromOrderRequest orderItem)
            throws ParseException {
        Map<String, RemoveOrderItemFromOrderResponse> result = new HashMap<>();
        RemoveOrderItemFromOrderResponse response = new RemoveOrderItemFromOrderResponse();

        RemoveOrderItemFromOrderRequest.OrderItem theItem = orderItem.getOrderItem();
        int productId = Integer.parseInt(theItem.getProductId());
        int orderId = Integer.parseInt(orderItem.getOrderId());

        Optional<Orders> ordersOptional = orderRepository.findById(orderId);
        Orders currentOrder;
        if (ordersOptional.isPresent()) {
            currentOrder = ordersOptional.get();

            for (Iterator<OrderItem> orderItemIterator = currentOrder.getOrderItemList().iterator();
                 orderItemIterator.hasNext(); ) {
                OrderItem orderedItem = orderItemIterator.next();
                if (productId == orderedItem.getProduct().getId()) {
                    BigDecimal total = currentOrder.getTotalAmount().subtract(orderedItem.getSubtotal());
                    currentOrder.setTotalAmount(total);
                    orderItemIterator.remove();
                }
            }

            response.setStatus(orderRepository.update(currentOrder) + "");
            response.setItemId(productId + "");

            //if orderitem is zero, then remove the order
            if (currentOrder.getOrderItemList().isEmpty()) {
                response.setStatus(orderRepository.delete(currentOrder) + "");
            }
        }
        result.put("result", modelMapper.map(response, RemoveOrderItemFromOrderResponse.class));
        return result;
    }

    //update order wont include update the owner of order
    @PostMapping(value = "/order/update-order",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateOrder(@RequestBody UpdateOrderRequest updateOrderRequest) throws ParseException {
        int orderId = Integer.parseInt(updateOrderRequest.getOrderId());
        Date orderDate = new SimpleDateFormat("dd-MM-yyyy").parse(updateOrderRequest.getOrderDate());
        int paymentMethodId = Integer.parseInt(updateOrderRequest.getPaymentMethodId());
        String invoiceStatus = updateOrderRequest.getInvoiceStatus();
        String description = updateOrderRequest.getDescription();
        List<UpdateOrderRequest.OrderItem> orderItemList = new ArrayList<>(updateOrderRequest.getOrderItemList());

        Map<String, String> result = new HashMap<>();

        Optional<Orders> ordersOptional = orderRepository.findById(orderId);
        Orders currentOrder = null;
        if (ordersOptional.isPresent()) {
            currentOrder = ordersOptional.get();

            BigDecimal totalAmount = new BigDecimal(0);

            Optional<PaymentMethod> paymentMethodOptional = paymentMethodRepository.findById(paymentMethodId);
            PaymentMethod paymentMethod = null;
            if (paymentMethodOptional.isPresent()) {
                paymentMethod = (PaymentMethod) Hibernate.unproxy(paymentMethodOptional.get());
            }

            currentOrder.clearOrderItem();

            for (UpdateOrderRequest.OrderItem orderItemDTO : orderItemList) {
                int quantity = Integer.parseInt(orderItemDTO.getQuantity());
                BigDecimal subtotal = new BigDecimal(orderItemDTO.getSubtotal());
                Optional<Product> productOptional = productRepository.findById(Integer.valueOf(orderItemDTO.getProductId()));
                Product product = null;
                if (productOptional.isPresent()) {
                    product = productOptional.get();
                }

                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(quantity);
                orderItem.setSubtotal(subtotal);
                orderItem.setProduct(product);

                totalAmount = totalAmount.add(subtotal);
                currentOrder.addOrderItem(orderItem);
            }
            currentOrder.getInvoice().setPaymentMethod(null);

            currentOrder.getInvoice().setPaymentMethod(paymentMethod);

            currentOrder.getInvoice().setStatus(invoiceStatus);
            currentOrder.getInvoice().setDescription(description);
            currentOrder.getInvoice().setCreated(orderDate);

            currentOrder.setTotalAmount(totalAmount);
            currentOrder.setOrderDate(orderDate);

            result.put("result", "" + orderRepository.update(currentOrder));
        } else {
            result.put("result", "false");
            result.put("errorMsg", "order not found!");
        }
        return result;
    }

    @PostMapping(value = "/order/find-order-by-id",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, OrderDTO> findOrderById(@RequestBody Map<String, Object> body) {
        int orderId = Integer.parseInt(body.get("orderId").toString());
        Map<String, OrderDTO> result = new HashMap<>();
        Optional orders = orderRepository.findById(orderId);
        result.put("result", modelMapper.map(orders.get(), OrderDTO.class));
        return result;
    }

    @PostMapping(value = "/order/find-order-by-userid",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<OrderDTO>> findOrderByUserId(@RequestBody Map<String, Object> body) {
        Objects.requireNonNull(body.get("userId"), "userId must not be null");
        long userId = Long.parseLong(body.get("userId").toString());
        Map<String, List<OrderDTO>> result = new HashMap<>();
        List<Orders> ordersList = orderRepository.getFinishedOrderByUserId(userId);
        /*result.put("result", ordersList.stream()
                .map(orders -> modelMapper.map(orders, OrderDTO.class))
                .collect(Collectors.toList()));*/
        modelMapper.addConverter(orderConverter);
        result.put("result", ordersList.stream()
                .map(orders -> modelMapper.map(orders, OrderDTO.class))
                .collect(Collectors.toList()));
        return result;
    }

    @PostMapping(value = "/order/get-userorder-need-to-pay",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<OrderDTO>> getUserOrderNeedToPay(@RequestBody Map<String, Object> body) {
        Objects.requireNonNull(body.get("userId"), "userId must not be null");
        long userId = Long.parseLong(body.get("userId").toString());
        Map<String, List<OrderDTO>> result = new HashMap<>();
        List<Orders> ordersList = orderRepository.getUserOrderNeedTooPay(userId);

        modelMapper.addConverter(orderConverter);
        result.put("result", ordersList.stream()
                .map(orders -> modelMapper.map(orders, OrderDTO.class))
                .collect(Collectors.toList()));
        return result;
    }

    @PostMapping(value = "/order/get-order-details-by-id",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, GetOrderDetailsResponse> getOrderDetailsById(@RequestBody Map<String, Object> body) {
        int orderId = Integer.parseInt(body.get("orderId").toString());
        Map<String, GetOrderDetailsResponse> result = new HashMap<>();
        Optional orders = orderRepository.findById(orderId);

        result.put("result", modelMapper.map(orders.get(), GetOrderDetailsResponse.class));
        return result;
    }

    @PostMapping(value = "/order/delete-order-by-id",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, RemoveOrderItemFromOrderResponse> deleteOrdersById(@RequestBody Map<String, Object> body) {
        int orderId = Integer.parseInt(body.get("orderId").toString());
        Map<String, RemoveOrderItemFromOrderResponse> result = new HashMap<>();
        RemoveOrderItemFromOrderResponse response = new RemoveOrderItemFromOrderResponse();

        Optional<Orders> ordersOptional = orderRepository.findById(orderId);
        if (ordersOptional.isPresent()) {
            Orders currentOrder = ordersOptional.get();
            response.setStatus(orderRepository.delete(currentOrder) + "");
            response.setItemId("test");
        }
        result.put("result", modelMapper.map(response, RemoveOrderItemFromOrderResponse.class));
        return result;
    }

    @PostMapping(value = "/order/pay-order",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, PayingOrderResponse> paymentOrder(@RequestBody PayingOrderRequest payingOrderRequest) {
        int orderId = Integer.parseInt(payingOrderRequest.getOrderId());
        int userId = Integer.parseInt(payingOrderRequest.getUserId());
        int paymentMethodId = Integer.parseInt(payingOrderRequest.getPaymentMethodId());
        Map<String, PayingOrderResponse> result = new HashMap<>();
        PayingOrderResponse response = new PayingOrderResponse();

        Optional<Orders> ordersOptional = orderRepository.findById(orderId);
        Orders currentOrder = null;
        if (ordersOptional.isPresent()) {
            currentOrder = ordersOptional.get();
        }

        List<Balance> balanceList = balanceRepository.findBalanceByUserId(userId + "");
        for (Iterator<Balance> balanceIterator = balanceList.iterator(); balanceIterator.hasNext(); ) {
            Balance balance = balanceIterator.next();
            if (balance.getBalanceType().getId() == paymentMethodId) {
                if (balance.getBalance().compareTo(currentOrder.getTotalAmount()) >= 0) {
                    balance.setLastUsage(Calendar.getInstance().getTime());
                    BigDecimal newBalance = balance.getBalance().subtract(currentOrder.getTotalAmount());
                    balance.setBalance(newBalance);
                    currentOrder.getInvoice().setStatus("PAID");
                    boolean status = balanceRepository.update(balance) & orderRepository.update(currentOrder);
                    response.setStatus(status + "");
                    if (status) {
                        response.setMessage("success");
                    } else {
                        response.setMessage("failing njing");
                    }
                } else {
                    response.setStatus(false + "");
                    response.setMessage("duit kurang");
                }


                response.setOrderId(currentOrder.getId() + "'");
            }
        }

        result.put("result", modelMapper.map(response, PayingOrderResponse.class));
        return result;
    }
}
