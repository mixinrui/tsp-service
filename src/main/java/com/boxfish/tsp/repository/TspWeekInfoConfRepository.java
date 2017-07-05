package com.boxfish.tsp.repository;

import com.boxfish.tsp.entity.TspWeekDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by mk on 16/12/29.
 */

@Component
public interface TspWeekInfoConfRepository extends JpaRepository<TspWeekDictionary,Long> {

}
