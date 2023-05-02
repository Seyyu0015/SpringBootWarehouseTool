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
public class RequirementDisplay {
    private int id;
    private String type;
    private String itemname;
    private String warehousename;
    private int number;
    private String username;
    private Date datetime;
    private String state;
    private String newwarehousename;

}
