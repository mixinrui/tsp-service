package com.boxfish.tsp.service;

import com.boxfish.tsp.dto.TspTeamInfoOneWeekDto;
import com.boxfish.tsp.repository.TspWeekInfoConfRepository;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;


/**
 * Created by mk .
 */
@Service
public class TspWeekDictionaryService {

    private Logger logger = LoggerFactory.getLogger(TspWeekDictionaryService.class);

    @Autowired
    private TspWeekInfoConfRepository tspWeekInfoConfRepository;

    public Map getTspWeekDictionaryMap(){
        Map map = Maps.newHashMap();
        map.put("weekList",this.tspWeekInfoConfRepository.findAll());
        map.put("currentWeek",this.getCurrentWeek());
        return map;
    }


    public String getCurrentWeek(){
        Calendar cal = Calendar.getInstance();
        Date curDate = new Date(System.currentTimeMillis());
        cal.setTime(curDate);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return TspTeamInfoOneWeekDto.Week.getEnum(w).name();
    }

}
