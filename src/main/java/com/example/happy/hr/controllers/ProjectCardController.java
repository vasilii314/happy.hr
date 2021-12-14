package com.example.happy.hr.controllers;

import com.example.happy.hr.controllers.query.params.PageInfo;
import com.example.happy.hr.controllers.query.params.ProjectRegistryFilter;
import com.example.happy.hr.json.dto.ProjectCardDto;
import com.example.happy.hr.json.dto.auxiliary.ProjectCardInfo;
import com.example.happy.hr.json.dto.auxiliary.SortInfo;
import com.example.happy.hr.services.ProjectCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cards")
@AllArgsConstructor
@Slf4j
public class ProjectCardController {

    private ProjectCardService projectCardService;

    @PostMapping("/new")
    public ResponseEntity<?> createProjectCard(@RequestBody @Valid ProjectCardDto projectCardDto) {
        log.info("Request on POST /api/cards/new");
        return ResponseEntity.status(201).body(projectCardService.save(projectCardDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCard(@RequestBody @Valid ProjectCardDto projectCardDto) {
        log.info("Request on PUT /api/cards/{id}");
        log.info("Updating card {}", projectCardDto);
        projectCardService.save(projectCardDto);
        return ResponseEntity.status(204).build();
    }

    @GetMapping(value = "/registry", produces = "application/json")
    public ResponseEntity<List<ProjectCardInfo>> getRegistryPage(@RequestParam(required = false) String projName,
                                                                 @RequestParam(required = false) String projClient,
                                                                 @RequestParam(required = false) String cardAuthor,
                                                                 @RequestParam(required = false) String cardStatus,
                                                                 @RequestParam(required = false) Integer page,
                                                                 @RequestParam(required = false) String field,
                                                                 @RequestParam(required = false) String sort) {

        log.info("Request on GET /api/cards/registry");

        SortInfo sortInfo = null;

        if (field != null && sort != null) {
            sortInfo = new SortInfo(field, sort);
        }

        return ResponseEntity.ok(
                projectCardService
                        .getProjectCardPage(
                                new ProjectRegistryFilter(projName, projClient, cardAuthor, cardStatus),
                                new PageInfo(page == null || page < 0 ? 1 : page, 10),
                                sortInfo
                        )
        );
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProjectCardDto> getProjectCardById(@PathVariable Integer id) {
        log.info("Request on GET /api/cards/{id}");
        return ResponseEntity.ok(projectCardService.getProjectCardById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Integer id) {
        log.info("Request on DELETE /api/cards/{id}");
        projectCardService.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/archive/{id}")
    public ResponseEntity<ProjectCardInfo> archiveCard(@PathVariable Integer id) {
        log.info("Request on PUT /api/cards/archive/{id}");
        return ResponseEntity.ok(projectCardService.archiveById(id));
    }

    @DeleteMapping("/archive/{id}")
    public ResponseEntity<ProjectCardInfo> unarchiveCard(@PathVariable Integer id) {
        log.info("Request on DELETE /api/cards/archive/{id}");
        return ResponseEntity.ok(projectCardService.unarchiveById(id));
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, Long>> getProjCardsNum(@RequestParam(required = false) String projName,
                                                             @RequestParam(required = false) String projClient,
                                                             @RequestParam(required = false) String cardAuthor,
                                                             @RequestParam(required = false) String cardStatus) {
        log.info("Request on GET /api/cards/total");
        return ResponseEntity.ok(
                projectCardService
                        .count(
                                new ProjectRegistryFilter(projName, projClient, cardAuthor, cardStatus)
                        )
        );
    }
}
