package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.LevelHistoryRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface LevelHistoryRepository extends CrudRepository<LevelHistoryRecord, String> {
}
