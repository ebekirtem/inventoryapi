package com.inventory.controller;

import com.inventory.dto.category.CategoryDto;
import com.inventory.dto.category.CreateCategoryDto;
import com.inventory.dto.depot.CreateDepotDto;
import com.inventory.dto.depot.DepotDto;
import com.inventory.service.CategoryService;
import com.inventory.service.DepotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/depot")
public class DepotController {
    private final DepotService depotService;

    public DepotController(DepotService depotService){
        this.depotService=depotService;
    }

    @PostMapping
    public ResponseEntity<DepotDto> saveDepot(@Valid @RequestBody CreateDepotDto createDepotDto){
        DepotDto depotDto = depotService.saveDepot(createDepotDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(depotDto);
    }

    @GetMapping
    public ResponseEntity<List<DepotDto>> getAll(){
        List<DepotDto> depots = depotService.getDepots();
        return ResponseEntity.status(HttpStatus.OK).body(depots);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepotDto> getDepotById(@PathVariable Integer id){
        DepotDto depotDto = depotService.getDepotById(id);
        return ResponseEntity.status(HttpStatus.OK).body(depotDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepotDto> updateDepot(@Valid @RequestBody CreateDepotDto createDepotDto,@PathVariable Integer id){
        DepotDto depotDto = depotService.updateDepot(id,createDepotDto);
        return ResponseEntity.status(HttpStatus.OK).body(depotDto);
    }


}
