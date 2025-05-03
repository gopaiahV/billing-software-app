package in.gv.billingsoftware1.service;

import com.razorpay.RazorpayException;
import in.gv.billingsoftware1.io.RazorpayOrderResponse;

public interface RazorpayService {

    RazorpayOrderResponse createOrder(Double amount, String currency) throws RazorpayException;
}
