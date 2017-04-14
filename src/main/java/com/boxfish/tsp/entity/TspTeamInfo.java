package com.boxfish.tsp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by mk on 16/7/18.
 */
@Component
@Data
@Entity
@Table(name = "tsp_team_info")
public class TspTeamInfo extends BaseEntity {

    public TspTeamInfo() {
    }

    public TspTeamInfo(String currentWeek, String team, String teamMemberName, String phoneNum, String remarks) {
        this.currentWeek = currentWeek;
        this.team = team;
        this.teamMemberName = teamMemberName;
        this.phoneNum = phoneNum;
        this.remarks = remarks;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currentYear;

    private String weekNum;

    private String currentWeek;

    private String team;

    private String teamMemberName;

    private String phoneNum;

    private String remarks;

}
