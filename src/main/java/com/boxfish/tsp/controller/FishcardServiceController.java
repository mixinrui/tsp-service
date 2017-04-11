package com.boxfish.tsp.controller;

import com.boxfish.tsp.common.JsonResultModel;
import com.boxfish.tsp.service.TspService;
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
@RequestMapping("/ts")
public class FishcardServiceController {

    @Autowired
    private TspService tspService;

    /**
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/orderlist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonResultModel queryOrderList() throws InvocationTargetException, IllegalAccessException {
        return JsonResultModel.newJsonResultModel("1111111111");
    }

}
