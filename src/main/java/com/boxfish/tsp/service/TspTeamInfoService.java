package com.boxfish.tsp.service;

import com.boxfish.tsp.dto.TspTeamInfoOneWeekDto;
import com.boxfish.tsp.entity.TspTeamInfo;
import com.boxfish.tsp.repository.TspTeamInfoRepository;
import com.boxfish.tsp.vo.TspTeamInfoQuery;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by mk .
 */
@Service
public class TspTeamInfoService {

    private Logger logger = LoggerFactory.getLogger(TspTeamInfoService.class);

    @Autowired
    private TspTeamInfoRepository tspTeamInfoRepository;

    public List<TspTeamInfoOneWeekDto> findByCurrentYearAndWeekNum(TspTeamInfoQuery tspTeamInfoQuery) {
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
        return listFinal;
    }

    private static List<List<TspTeamInfo>> getListByGroup(List<TspTeamInfo> list) {
        List<List<TspTeamInfo>> result = new ArrayList<List<TspTeamInfo>>();
        Map<String, List<TspTeamInfo>> map = new TreeMap<String, List<TspTeamInfo>>();
        for (TspTeamInfo bean : list) {
            if (map.containsKey(bean.getCurrentWeek())) {
                List<TspTeamInfo> t = map.get(bean.getCurrentWeek());
                t.add(new TspTeamInfo(bean.getCurrentWeek(), bean.getTeam(),
                        bean.getTeamMemberName(), bean.getPhoneNum(), bean.getRemarks()));
                new ArrayList<TspTeamInfo>().add(new TspTeamInfo(bean.getCurrentWeek(), bean.getTeam(),
                        bean.getTeamMemberName(), bean.getPhoneNum(), bean.getRemarks()));
                map.put(bean.getCurrentWeek(), t);
            } else {
                List<TspTeamInfo> t = Lists.newArrayList();
                t.add(new TspTeamInfo(bean.getCurrentWeek(), bean.getTeam(),
                        bean.getTeamMemberName(), bean.getPhoneNum(), bean.getRemarks()));
                map.put(bean.getCurrentWeek(), t);
            }
        }
        for (Map.Entry<String, List<TspTeamInfo>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }


}
