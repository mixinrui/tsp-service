package com.boxfish.tsp.controller;

import com.boxfish.tsp.common.JsonResultModel;
import com.boxfish.tsp.service.TspTeamInfoService;
import com.boxfish.tsp.vo.TspTeamInfoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/tspmanage")
public class TspTeamInfoController {

    @Autowired
    private TspTeamInfoService tspService;

    /**
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/tsp/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonResultModel queryOrderList(Pageable pageable, TspTeamInfoQuery tspTeamInfoQuery) throws InvocationTargetException, IllegalAccessException {
        tspTeamInfoQuery.setCurrentYear("2017");
        tspTeamInfoQuery.setWeekNum("11");
        return JsonResultModel.newJsonResultModel(tspService.findByCurrentYearAndWeekNum(tspTeamInfoQuery));

    }

}
