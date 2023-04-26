package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    private int id;
    private int itemid;
    private int warehouseid;
    private int itemnumber;

}
