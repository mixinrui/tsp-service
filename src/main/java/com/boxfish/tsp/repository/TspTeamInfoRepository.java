package com.boxfish.tsp.repository;

import com.boxfish.tsp.entity.TspTeamInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mk on 16/12/29.
 */

@Component
public interface TspTeamInfoRepository extends JpaRepository<TspTeamInfo,Long> {

    public List<TspTeamInfo> findByCurrentYearAndWeekNum(String currentYear, String weekNum);

}
