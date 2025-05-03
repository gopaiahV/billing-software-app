package in.gv.billingsoftware1.controller;



import com.razorpay.RazorpayException;
import in.gv.billingsoftware1.io.OrderResponse;
import in.gv.billingsoftware1.io.PaymentRequest;
import in.gv.billingsoftware1.io.PaymentVerificationRequest;
import in.gv.billingsoftware1.io.RazorpayOrderResponse;
import in.gv.billingsoftware1.service.OrderService;
import in.gv.billingsoftware1.service.RazorpayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final RazorpayService razorpayService;
    private final OrderService orderService;

    @PostMapping("/create-order")
    @ResponseStatus(HttpStatus.CREATED)
    public RazorpayOrderResponse createRazorpayOrder(@RequestBody PaymentRequest request) throws RazorpayException {
        return razorpayService.createOrder(request.getAmount(), request.getCurrency());
    }

    @PostMapping("/verify")
    public OrderResponse verifyPayment(@RequestBody PaymentVerificationRequest request) {
        return orderService.verifyPayment(request);
    }
}

