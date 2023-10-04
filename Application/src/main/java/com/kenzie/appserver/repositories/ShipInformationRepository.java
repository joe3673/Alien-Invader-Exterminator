package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ShipInformationRecord;
import com.kenzie.appserver.service.model.ShipInformation;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ShipInformationRepository extends CrudRepository<ShipInformationRecord, String> {
}
