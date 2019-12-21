package com.payment.demo.entity;
import com.payment.demo.CardAuthorizationInfo;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name="Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paymentId;
    @Column(name = "cardStatus")
    private CardAuthorizationInfo cardStatus;
    @Column(name = "orderId")
    private int orderId;
    @Column(name = "username")
    private String username;


    public Payment() {
    }

    public Payment(CardAuthorizationInfo cardStatus, int orderId, String username) {
        this.cardStatus = cardStatus;
        this.orderId = orderId;
        this.username = username;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public CardAuthorizationInfo getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardAuthorizationInfo cardStatus) {
        this.cardStatus = cardStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
