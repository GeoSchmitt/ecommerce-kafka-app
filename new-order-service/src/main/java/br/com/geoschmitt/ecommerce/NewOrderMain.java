package br.com.geoschmitt.ecommerce;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(var orderDispatcher = new KafkaDispatcher<Order>()) {
            try(var emailDispatcher = new KafkaDispatcher<String>()) {
                for (var i = 0; i < 10; i++) {
                    var userId = UUID.randomUUID().toString();
                    var order = new Order(userId, UUID.randomUUID().toString(), new BigDecimal(Math.random() * 5000 + 1));
                    orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);

                    var email = "Thank you for your order, we are processing it!";
                    emailDispatcher.send("ECOMMERCE_SEND_MAIL", userId, email);
                }
            }
        }
    }

}
