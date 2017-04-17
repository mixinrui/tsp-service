package com.boxfish.tsp.service;

import com.boxfish.tsp.entity.TspWeekDictionary;
import com.boxfish.tsp.repository.TspWeekInfoConfRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by mk .
 */
@Service
public class TspWeekDictionaryService {

    private Logger logger = LoggerFactory.getLogger(TspWeekDictionaryService.class);

    @Autowired
    private TspWeekInfoConfRepository tspWeekInfoConfRepository;

    public List<TspWeekDictionary> getTspWeekDictionaryList(){
        return tspWeekInfoConfRepository.findAll();
    }



}
