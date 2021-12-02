package com.example.happy.hr.controllers;

import com.example.happy.hr.controllers.query.params.PageInfo;
import com.example.happy.hr.controllers.query.params.ProjectRegistryFilter;
import com.example.happy.hr.json.dto.ProjectCardDto;
import com.example.happy.hr.json.dto.auxiliary.ProjectCardInfo;
import com.example.happy.hr.services.ProjectCardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
@AllArgsConstructor
public class ProjectCardController {

    private ProjectCardService projectCardService;

    @PostMapping("/new")
    public ResponseEntity<?> createProjectCard(@RequestBody @Valid ProjectCardDto projectCardDto) {
        projectCardService.save(projectCardDto);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCard(@RequestBody @Valid ProjectCardDto projectCardDto) {
        projectCardService.save(projectCardDto);
        return ResponseEntity.status(204).build();
    }

    @GetMapping( value = "/registry", produces="application/json")
    public ResponseEntity<List<ProjectCardInfo>> getRegistryPage(@RequestParam(required = false) String projClient,
                                                                 @RequestParam(required = false) String cardAuthor,
                                                                 @RequestParam(required = false) String cardStatus,
                                                                 @RequestParam(required = false) Integer page) {
        return ResponseEntity.ok(
                projectCardService
                        .getProjectCardPage(
                                new ProjectRegistryFilter(projClient, cardAuthor, cardStatus),
                                new PageInfo(page == null || page < 0 ? 1 : page, 10)
                        )
        );
    }

    @GetMapping(value = "/{id}", produces="application/json")
    public ResponseEntity<ProjectCardDto> getProjectCardById(@PathVariable Integer id) {
        return ResponseEntity.ok(projectCardService.getProjectCardById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Integer id) {
        projectCardService.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/archive/{id}")
    public ResponseEntity<?> archiveCard(@PathVariable Integer id) {
        projectCardService.archiveById(id);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/archive/{id}")
    public ResponseEntity<?> unarchiveCard(@PathVariable Integer id) {
        projectCardService.unarchiveById(id);
        return ResponseEntity.status(204).build();
    }
}
