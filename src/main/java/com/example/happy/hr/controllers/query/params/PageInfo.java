package com.example.happy.hr.controllers.query.params;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PageInfo {
    private final Integer pageNum;
    private final Integer pageSize;
}
