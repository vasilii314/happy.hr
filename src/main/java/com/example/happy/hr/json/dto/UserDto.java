package com.example.happy.hr.json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Integer id;

    @Length(max = 150)
    private String surname;

    @Length(max = 150)
    private String name;

    @Length(max = 150)
    private String patronymic;
}
