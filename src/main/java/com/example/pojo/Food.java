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
public class Food {
    private int id;
    private String food;
    private double value;
    private int user1ate;
    private int user2ate;
    private int user3ate;
    private int user4ate;
    private int user5ate;

}
