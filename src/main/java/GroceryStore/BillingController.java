
package GroceryStore;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bills")
public class BillingController {

    private final BillingService billingService;
   

    public BillingController(
            BillingService billingService,
            BillRepository billRepository) {
        this.billingService = billingService;
    }


@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public Bill createBill(
        @Valid @RequestBody CreateBillRequest request) {
    try {
        return billingService.createBill(
            request.getItems(),
            request.getCustomerName(),
            request.getCustomerPhone()
        );
    } catch (IllegalArgumentException e) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (ArithmeticException e) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, "Invalid or excessive quantity.");
    }
}


    @GetMapping
    public List<BillResponse> getAllBills() {
        return billingService.getAllBills();
    }
}
