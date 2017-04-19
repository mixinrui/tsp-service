package com.boxfish.tsp.service;

import com.boxfish.tsp.common.utils.DateUtil;
import com.boxfish.tsp.common.utils.FormatUtil;
import com.boxfish.tsp.dto.TspTeamInfoOneWeekDto;
import com.boxfish.tsp.entity.TspTeamInfo;
import com.boxfish.tsp.enums.OperateOptionEnum;
import com.boxfish.tsp.repository.TspTeamInfoRepository;
import com.boxfish.tsp.vo.TspTeamInfoQuery;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;


/**
 * Created by mk .
 */
@Service
public class TspTeamInfoService {

    private Logger logger = LoggerFactory.getLogger(TspTeamInfoService.class);

    @Autowired
    private TspTeamInfoRepository tspTeamInfoRepository;

    public Map findByCurrentYearAndWeekNum(TspTeamInfoQuery tspTeamInfoQuery, String option) throws JsonProcessingException {

        Map map = Maps.newHashMap();
        tspTeamInfoQuery = this.initTspTeamInfoQuery(tspTeamInfoQuery, option);
        map.put("monday", DateUtil.getDateFormat(this.getWhichWeekDay(tspTeamInfoQuery, 2)));
        map.put("sunday", DateUtil.getDateFormat(this.getWhichWeekDay(tspTeamInfoQuery, 1)));
        List<TspTeamInfoOneWeekDto> listFinal = Lists.newArrayList();
        List<TspTeamInfo> tspTeamInfoRepositoryList = tspTeamInfoRepository.findByCurrentYearAndWeekNum(tspTeamInfoQuery.getCurrentYear(), tspTeamInfoQuery.getWeekNum());
        List<List<TspTeamInfo>> list = this.getListByGroup(tspTeamInfoRepositoryList);
        for (int i = 0; i < list.size(); i++) {
            List<TspTeamInfo> listTemp = list.get(i);
            TspTeamInfoOneWeekDto tspTeamInfoOneWeekDto = new TspTeamInfoOneWeekDto();
            tspTeamInfoOneWeekDto.setCurrentYear(tspTeamInfoQuery.getCurrentYear());  //2017
            tspTeamInfoOneWeekDto.setWeekNum(tspTeamInfoQuery.getWeekNum());          //21
            tspTeamInfoOneWeekDto.setCurrentWeek(TspTeamInfoOneWeekDto.Week.getEnumString(listTemp.get(0).getCurrentWeek()));
            tspTeamInfoOneWeekDto.setDataList(listTemp);
            listFinal.add(tspTeamInfoOneWeekDto);
        }
        map.put("currentYear", tspTeamInfoQuery.getCurrentYear());
        map.put("weekNum", tspTeamInfoQuery.getWeekNum());
        map.put("dataList", listFinal);
        return map;
    }

    private static List<List<TspTeamInfo>> getListByGroup(List<TspTeamInfo> list) {
        List<List<TspTeamInfo>> result = new ArrayList<List<TspTeamInfo>>();
        Map<String, List<TspTeamInfo>> map = new TreeMap<String, List<TspTeamInfo>>();
        for (TspTeamInfo bean : list) {
            if (map.containsKey(bean.getCurrentWeek())) {
                List<TspTeamInfo> t = map.get(bean.getCurrentWeek());
                t.add(new TspTeamInfo(bean.getCurrentWeek(), bean.getTeam(),
                        bean.getTeamMemberName(), bean.getPhoneNum(), bean.getRemarks(), bean.getTspDate()));
                new ArrayList<TspTeamInfo>().add(new TspTeamInfo(bean.getCurrentWeek(), bean.getTeam(),
                        bean.getTeamMemberName(), bean.getPhoneNum(), bean.getRemarks(), bean.getTspDate()));
                map.put(bean.getCurrentWeek(), t);
            } else {
                List<TspTeamInfo> t = Lists.newArrayList();
                t.add(new TspTeamInfo(bean.getCurrentWeek(), bean.getTeam(),
                        bean.getTeamMemberName(), bean.getPhoneNum(), bean.getRemarks(), bean.getTspDate()));
                map.put(bean.getCurrentWeek(), t);
            }
        }
        for (Map.Entry<String, List<TspTeamInfo>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    private TspTeamInfoQuery initTspTeamInfoQuery(TspTeamInfoQuery tspTeamInfoQuery, String option) throws JsonProcessingException {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        if (Objects.isNull(tspTeamInfoQuery) || StringUtils.isEmpty(tspTeamInfoQuery.getWeekNum()) || StringUtils.isEmpty(tspTeamInfoQuery.getCurrentYear())) {
            calendar.setTime(new Date());
        } else {
            calendar.set(Calendar.YEAR, Integer.parseInt(tspTeamInfoQuery.getCurrentYear()));
            calendar.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(tspTeamInfoQuery.getWeekNum()));
        }
        OperateOptionEnum operateOptionEnum = OperateOptionEnum.getVariable(option);
        calendar.add(Calendar.WEEK_OF_YEAR, operateOptionEnum.getCode());
        tspTeamInfoQuery.setWeekNum(String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)));
        tspTeamInfoQuery.setCurrentYear(String.valueOf(calendar.get(Calendar.YEAR)));

        System.out.println(FormatUtil.toJson(tspTeamInfoQuery));
        return tspTeamInfoQuery;
    }

    private Date getWhichWeekDay(TspTeamInfoQuery tspTeamInfoQuery, int whichDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.YEAR, Integer.parseInt(tspTeamInfoQuery.getCurrentYear())); // 2016年
        calendar.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(tspTeamInfoQuery.getWeekNum())); // 设置为2016年的第10周
        calendar.set(Calendar.DAY_OF_WEEK, whichDay); // 1表示周日，2表示周一，7表示周六
        return calendar.getTime();
    }


}
