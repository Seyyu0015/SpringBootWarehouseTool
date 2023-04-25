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
public class User {
    private int id;
    private String userid;
    private String username;
    private String password;
    private String permission;
}
