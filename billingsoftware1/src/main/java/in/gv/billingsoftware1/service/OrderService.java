package in.gv.billingsoftware1.service;
import in.gv.billingsoftware1.io.OrderRequest;
import in.gv.billingsoftware1.io.OrderResponse;
import in.gv.billingsoftware1.io.PaymentVerificationRequest;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    void deleteOrder(String orderId);

    List<OrderResponse> getLatestOrders();

    OrderResponse verifyPayment(PaymentVerificationRequest request);

    Double sumSalesByDate(LocalDate date);

    Long countByOrderDate(LocalDate date);

    List<OrderResponse> findRecentOrders();
}

