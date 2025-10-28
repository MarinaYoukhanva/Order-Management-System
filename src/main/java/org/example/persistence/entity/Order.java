package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.persistence.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany (mappedBy = "order")
    @ToString.Exclude
    private List<OrderItem> orderItems;
}

