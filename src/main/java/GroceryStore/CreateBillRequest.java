
package GroceryStore;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

public class CreateBillRequest {

    @Size(max = 100)
    private String customerName;

    @Size(max = 15)
    private String customerPhone;

    @Valid
    private List<BillRequestItem> items;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public List<BillRequestItem> getItems() {
        return items;
    }

    public void setItems(List<BillRequestItem> items) {
        this.items = items;
    }
}
