package com.robotdr.hotel_api.rest;


import com.robotdr.hotel_api.domain.Visitor;
import com.robotdr.hotel_api.dto.VisitorDto;
import com.robotdr.hotel_api.servis.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorService visitorService;

    @GetMapping("/visitors")
    public ResponseEntity<List<VisitorDto>> findAll() {
        return ResponseEntity.ok(visitorService.findAll());
    }

    @GetMapping("/visitors/{id}")
    public ResponseEntity<Visitor> findById(@PathVariable Long id) {
        return visitorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/visitors")
    public ResponseEntity<Void> save(@RequestBody Visitor visitor) {
        visitorService.save(visitor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
