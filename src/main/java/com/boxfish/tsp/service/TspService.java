package com.boxfish.tsp.service;

import com.boxfish.tsp.common.SerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by mk .
 */
@Service
public class TspService {

    @Autowired
    private SerProperties serProperties;

    private Logger logger = LoggerFactory.getLogger(TspService.class);

}
