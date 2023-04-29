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
public class StorageDisplay {
    private int sdid;
    private int id;
    private String itemname;
    private int totalnumber;
    private String unit;
    private int warehouseid;
    private String warehousename;
    private int number;
    private Date datetime;

}
