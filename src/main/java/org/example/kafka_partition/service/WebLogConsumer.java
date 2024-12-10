package org.example.kafka_partition.service;

import lombok.RequiredArgsConstructor;
import org.example.kafka_partition.model.WebEvent;
import org.example.kafka_partition.repository.EventRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebLogConsumer {

    private final EventRepository eventRepository;

    @KafkaListener(topics = "web-logs", groupId = "web-log-group")
    public void consume(String eventData,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        System.out.printf("Сообщение получено: %s из партиции: %d%n", eventData, partition);

        WebEvent event = new WebEvent(null, "Processed", eventData, "Partition " + partition);
        eventRepository.save(event);

        System.out.printf("Сообщение '%s' сохранено в базе данных.%n", eventData);
    }
}
