
package GroceryStore;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record BillResponse(
    Long id,
    BigDecimal totalAmount,
    LocalDateTime createdAt,
    String customerName,
    String customerPhone,
    List<BillItemResponse> items
) {
    public record BillItemResponse(
        Long productId,
        String productName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
    ) {
    }
}
