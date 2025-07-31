package com.example.demo.user;

import com.example.demo.kafka.avro.model.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserCreationConsumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserCreationConsumer.class);

    @KafkaListener(topics="user.created.legacy",groupId = "my-group")
    public void listen(String message){
        LOGGER.info("Recieved message:{}",message);
    }

    @KafkaListener(topics="user.created",groupId="my-group")
    public void listen(UserCreatedEvent event){
        LOGGER.info("Recieved Avro Message: UserId:{},Name:{} ",event.getUserId(),event.getName());
    }
}
