package br.com.geoschmitt.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class EmailService {
    public static void main(String[] args) {
        var emailService = new EmailService();
        try(var service = new KafkaService(
                EmailService.class.getSimpleName(),
                "ECOMMERCE_SEND_MAIL", emailService::parse,
                String.class,
                Map.of())) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String,String> record){
        System.out.println("------");
        System.out.println("Send email");
        System.out.println("Key::" + record.key());
        System.out.println("Value::" + record.value());
        System.out.println("Partition::" + record.partition());
        System.out.println("Offset::" + record.offset());
        System.out.println("Timestamp::" + record.timestamp());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Email sent");
    }


}
