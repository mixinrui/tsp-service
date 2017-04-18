package com.boxfish.tsp.controller;

import com.boxfish.tsp.common.JsonResultModel;
import com.boxfish.tsp.service.TspWeekDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;


/**
 * Created by mk .
 */
@RestController
@CrossOrigin
@RequestMapping("/week/dictionary")
public class TspWeekDictionaryController {

    @Autowired
    private TspWeekDictionaryService tspWeekDictionaryService;

    /**
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonResultModel queryWeekDictionaryList() throws InvocationTargetException, IllegalAccessException {
        return JsonResultModel.newJsonResultModel(tspWeekDictionaryService.getTspWeekDictionaryMap());
    }
}
