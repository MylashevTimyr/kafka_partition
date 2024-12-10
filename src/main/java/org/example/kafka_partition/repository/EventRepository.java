package org.example.kafka_partition.repository;


import org.example.kafka_partition.model.WebEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<WebEvent, Long> {
}
