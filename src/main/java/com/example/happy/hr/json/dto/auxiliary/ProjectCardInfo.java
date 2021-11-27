package com.example.happy.hr.json.dto.auxiliary;

import com.example.happy.hr.domain.wrappers.ProjectCardWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectCardInfo {
    private ProjectCardWrapper info;
    private String url;
}
