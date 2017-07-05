package com.boxfish.tsp.controller;

import com.boxfish.tsp.common.JsonResultModel;
import com.boxfish.tsp.service.TspTeamInfoService;
import com.boxfish.tsp.vo.TspTeamInfoQuery;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/tsp/list")
    public JsonResultModel queryOrderList(TspTeamInfoQuery tspTeamInfoQuery, String option) throws InvocationTargetException, IllegalAccessException, JsonProcessingException {
        return JsonResultModel.newJsonResultModel(tspService.findByCurrentYearAndWeekNum(tspTeamInfoQuery, option));
    }

}
