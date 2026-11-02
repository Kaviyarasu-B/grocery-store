
package GroceryStore;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount = BigDecimal.ZERO;
    

@Column(length = 100)
private String customerName;

@Column(length = 15)
private String customerPhone;


    private LocalDateTime createdAt;

    @OneToMany(
        mappedBy = "bill",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<BillItem> items = new ArrayList<>();

    public Bill() {
    }

    @PrePersist
    public void setCreationTime() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    

public String getCustomerName() {
    return customerName;
}

public String getCustomerPhone() {
    return customerPhone;
}


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<BillItem> getItems() {
        return items;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    

public void setCustomerName(String customerName) {
    this.customerName = customerName;
}

public void setCustomerPhone(String customerPhone) {
    this.customerPhone = customerPhone;
}


    public void setItems(List<BillItem> items) {
        this.items = items;
    }
}


