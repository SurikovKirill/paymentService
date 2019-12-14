package com.payment.demo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.payment.demo.dto.UserDetailsDto;
import com.payment.demo.entity.Payment;
import com.payment.demo.exceptions.BadFormatCardStatusException;
import com.payment.demo.repos.PaymentRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.print.DocFlavor;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final RabbitTemplate rabbitTemplate;
    private static final String ORDER_EXCHANGE = "paymentOrderStatus";
    private static final String ORDER_KEY = "payData";
    public PaymentController(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }
    private static final Logger LOGGER=LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private PaymentRepository paymentRepo;

    @RequestMapping(value="{orderId}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public @ResponseBody Payment performPayment(@PathVariable(value = "orderId") Integer orderId, @RequestBody String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserDetailsDto user = objectMapper.readValue(data, UserDetailsDto.class);
            LOGGER.info("Called perfomedPayment method with parameter order_id = {}, and inputs: CAI = {}, username = {}", orderId, user.getStatus(), user.getUsername());
            Payment payment = new Payment(user.getStatus(), orderId, user.getUsername());
            paymentRepo.save(payment);
            rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_KEY, orderId.toString()+user.getStatus());
            return payment;
        }
        catch (InvalidFormatException e){
            LOGGER.error("BadFormat");
            return new Payment();
        }
        //TODO
        // сервису order id и статус карты, order возвращает {id, totalCost, username, status}
        // тип данных orderDto
        // return orderDto
    }
}
