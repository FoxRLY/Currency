package com.example.currency.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "currency",
    uniqueConstraints = {
        @UniqueConstraint(name = "UniqueShortName", columnNames = {"shortName"}),
        @UniqueConstraint(name = "UniqueNameAndShortName", columnNames = {"name", "shortName"})
    }
)
public class Currency {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", length = 512, nullable = false)
  private String name;

  @Column(name = "short_name", nullable = false, unique = true)
  private String shortName;

}
