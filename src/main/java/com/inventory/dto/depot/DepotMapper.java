package com.inventory.dto.depot;


import com.inventory.model.Category;
import com.inventory.model.Depot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepotMapper {

    DepotDto depotToDepotDto(Depot depot);
    Depot depotDtoToDepot(DepotDto depotDto);

    Depot createDepotDtoToDepot(CreateDepotDto createDepotDto);


}

