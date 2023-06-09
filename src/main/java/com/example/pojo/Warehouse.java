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
public class Warehouse {
    private int id;
    private String name;
    private String location;
}
