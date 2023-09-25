package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ShipRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ShipRepository extends CrudRepository<ShipRecord, String> {
}
