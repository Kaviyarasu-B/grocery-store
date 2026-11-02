
package GroceryStore;

public class BillRequestItem {

    private Long productId;
    private Integer quantity;

    public BillRequestItem() {
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
