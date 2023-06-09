package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Seyyu
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Requirement {
    private int id;
    private String type;
    private int itemid;
    private int warehouseid;
    private int number;
    private String userid;
    private Date datetime;
    private String state;
    private int newwarehouseid;

}
