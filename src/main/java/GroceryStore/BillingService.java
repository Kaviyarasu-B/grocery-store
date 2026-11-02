
package GroceryStore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillingService {

    private final BillRepository billRepository;
    private final ProductRepository productRepository;

    public BillingService(
            BillRepository billRepository,
            ProductRepository productRepository) {
        this.billRepository = billRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Bill createBill(
            List<BillRequestItem> requestedItems,
            String customerName,
            String customerPhone) {

        if (requestedItems == null || requestedItems.isEmpty()) {
            throw new IllegalArgumentException(
                "A bill must contain at least one product.");
        }

        // Validate quantities and combine duplicate product IDs.
        java.util.Map<Long, Integer> quantities =
            new java.util.LinkedHashMap<>();

        for (BillRequestItem item : requestedItems) {
            if (item == null
                    || item.getProductId() == null
                    || item.getQuantity() == null
                    || item.getQuantity() <= 0) {
                throw new IllegalArgumentException(
                    "Every product must have a valid ID and positive quantity.");
            }

            quantities.merge(
                item.getProductId(),
                item.getQuantity(),
                Math::addExact
            );
        }

        Bill bill = new Bill();

        // Save customer details with the bill.
        bill.setCustomerName(
            customerName == null || customerName.isBlank()
                ? null
                : customerName.trim()
        );

        bill.setCustomerPhone(
            customerPhone == null || customerPhone.isBlank()
                ? null
                : customerPhone.trim()
        );

        List<BillItem> billItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (var entry : quantities.entrySet()) {
            Product product = productRepository.findById(entry.getKey())
                .orElseThrow(() -> new IllegalArgumentException(
                    "Product not found: " + entry.getKey()));

            int quantity = entry.getValue();

            if (product.getQuantity() == null
                    || product.getQuantity() < quantity) {
                throw new IllegalArgumentException(
                    "Insufficient stock for " + product.getName()
                    + ". Available: " + product.getQuantity());
            }

            BigDecimal unitPrice = product.getPrice()
                .setScale(2, RoundingMode.HALF_UP);

            BigDecimal subtotal = unitPrice.multiply(
                BigDecimal.valueOf(quantity));

            BillItem billItem = new BillItem();
            billItem.setBill(bill);
            billItem.setProduct(product);
            billItem.setQuantity(quantity);
            billItem.setUnitPrice(unitPrice);
            billItem.setSubtotal(subtotal);

            billItems.add(billItem);
            total = total.add(subtotal);

            // Deduct sold quantity from inventory.
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
        }

        bill.setItems(billItems);
        bill.setTotalAmount(
            total.setScale(2, RoundingMode.HALF_UP));

        return billRepository.save(bill);
    }

    @Transactional(readOnly = true)
    public List<BillResponse> getAllBills() {
        return billRepository.findAll().stream()
            .map(bill -> new BillResponse(
                bill.getId(),
                bill.getTotalAmount(),
                bill.getCreatedAt(),
                bill.getCustomerName(),
                bill.getCustomerPhone(),
                bill.getItems().stream()
                    .map(item -> new BillResponse.BillItemResponse(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getSubtotal()
                    ))
                    .toList()
            ))
            .toList();
    }
}
