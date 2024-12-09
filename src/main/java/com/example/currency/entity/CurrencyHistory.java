package com.example.currency.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(
    name = "currency_history",
    indexes = {
      @Index(columnList = "currencyId"),
      @Index(columnList = "currencyId, timestamp")
    },
    uniqueConstraints = {
        @UniqueConstraint(columnNames = { "currency_id", "timestamp" } )
    }
)
public class CurrencyHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "currency_id", nullable = false)
  private Long currencyId;

  @Column(name = "price", nullable = false, scale = 3, precision = 20)
  private BigDecimal price;

  @CreationTimestamp
  @Column(name = "timestamp", nullable = false)
  private LocalDate timestamp;

}
