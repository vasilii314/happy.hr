package com.example.happy.hr.controllers.query.params;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProjectRegistryFilter {
    private final String projClientName;
    private final String cardAuthor;
    private final String cardStatus;
}
