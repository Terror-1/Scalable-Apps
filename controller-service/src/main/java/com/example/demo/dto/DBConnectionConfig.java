package com.example.demo.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DBConnectionConfig{
    private int minimumIdle;
    private int maximumPoolSize;
}