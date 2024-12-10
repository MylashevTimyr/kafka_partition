package org.example.kafka_partition.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "web_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String eventType;
    private String eventData;
    private String partition;
}
