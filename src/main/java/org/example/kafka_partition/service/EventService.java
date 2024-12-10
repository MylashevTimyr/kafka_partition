package org.example.kafka_partition.service;

import lombok.RequiredArgsConstructor;
import org.example.kafka_partition.model.WebEvent;
import org.example.kafka_partition.repository.EventRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EventService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final EventRepository eventRepository;

    public void sendEvent(String eventType, String eventData) {
        String topic = "web-logs";
        int partitionCount = kafkaTemplate.partitionsFor(topic).size();
        int partition = new Random().nextInt(partitionCount);

        kafkaTemplate.send(topic, partition, eventType, eventData);

        WebEvent event = new WebEvent(null, eventType, eventData, String.valueOf(partition));
        eventRepository.save(event);

        System.out.printf("Сообщение '%s' с типом '%s' отправлено в Kafka в партицию %d.%n", eventData, eventType, partition);
    }
}
