package com.example.happy.hr.json.dto.auxiliary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SortInfo {
    private String field;
    private String sort;
}
