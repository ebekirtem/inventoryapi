package com.inventory.service;

import com.inventory.dto.category.CategoryDto;
import com.inventory.dto.category.CategoryMapper;
import com.inventory.dto.category.CreateCategoryDto;
import com.inventory.dto.depot.CreateDepotDto;
import com.inventory.dto.depot.DepotDto;
import com.inventory.dto.depot.DepotMapper;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.model.Category;
import com.inventory.model.Depot;
import com.inventory.repository.CategoryRepository;
import com.inventory.repository.DepotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepotService {

    private final DepotRepository depotRepository;

    private final DepotMapper depotMapper;

    public DepotService(DepotRepository depotRepository, DepotMapper depotMapper){
        this.depotRepository=depotRepository;
        this.depotMapper=depotMapper;
    }
    @Transactional
    public DepotDto saveDepot(CreateDepotDto createDepotDto){
        Depot depot = depotMapper.createDepotDtoToDepot(createDepotDto);
        Depot savedDepot=depotRepository.save(depot);
        return depotMapper.depotToDepotDto(savedDepot);
    }

    @Transactional(readOnly = true)
    public List<DepotDto> getDepots(){
        List<Depot> depots = depotRepository.findAll();
        return depots.stream().map(depot -> depotMapper.depotToDepotDto(depot)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Depot getDepot(Integer id){
        Depot depot = depotRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Depot not found by id:"+id));
        return depot;
    }
    @Transactional(readOnly = true)
    public DepotDto getDepotById(Integer id){
         Depot depot=getDepot(id);
         return depotMapper.depotToDepotDto(depot);
    }

    @Transactional(readOnly = true)
    public Depot getDepotByName(String name){
        Depot depot=depotRepository.findByName(name).orElseThrow(()->new ResourceNotFoundException("Depot not found with name:"+name));
        return depot;
    }

    @Transactional
    public DepotDto updateDepot(Integer id,CreateDepotDto createDepotDto){
        Depot depot = getDepot(id);
        depot.setName(createDepotDto.getName());
        depot.setCity(createDepotDto.getCity());
        depot.setAddress(createDepotDto.getAddress());
        depot.setRegion(createDepotDto.getRegion());
        Depot updatedDepot=depotRepository.save(depot);
        return depotMapper.depotToDepotDto(updatedDepot);
    }
}
