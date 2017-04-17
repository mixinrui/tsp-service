package com.boxfish.tsp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by mk on 17/4/17.
 */

@Component
@Data
@Entity
public class TspWeekDictionary extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String weekKey;

    private String weekDisplayValue;
}
