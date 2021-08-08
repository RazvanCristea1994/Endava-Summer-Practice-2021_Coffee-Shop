package org.fantasticcoffee.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Integer id;

    @Column(nullable = false)
    private String cardHolderName;
    @Transient
    private String cardNumber;
    @Transient
    private LocalDate expiry;
    @Transient
    private String civ;

    public Card(String cardHolderName,
                String cardNumber,
                LocalDate expiry,
                String civ) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiry = expiry;
        this.civ = civ;
    }
}