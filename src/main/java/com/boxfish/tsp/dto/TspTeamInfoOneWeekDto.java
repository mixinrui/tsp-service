package com.boxfish.tsp.dto;

import com.boxfish.tsp.entity.TspTeamInfo;
import lombok.Data;

import java.util.List;

/**
 * Created by mk on 17/4/14.
 */

@Data
public class TspTeamInfoOneWeekDto {

    public enum Week {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY;

        public static Week getEnum(int value) {
            for (Week week : Week.values()) {
                if (week.ordinal() == value) {
                    return week;
                }
            }
            return null;
        }

        public static Week getEnumString(String value) {
            for (Week week : Week.values()) {
                if (week.name() .equals(value) ) {
                    return week;
                }
            }
            return null;
        }
    }

    private String currentYear;
    private String weekNum;
    private Week currentWeek;
    private List<TspTeamInfo> dataList;
}
