package com.redhat.emeatigers.storagedays;


import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, String> {
}