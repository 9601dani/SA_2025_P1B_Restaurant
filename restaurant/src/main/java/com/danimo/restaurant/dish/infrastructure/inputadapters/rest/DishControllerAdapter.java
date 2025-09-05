package com.danimo.restaurant.dish.infrastructure.inputadapters.rest;

import com.danimo.restaurant.common.infrastructure.annotations.WebAdapter;
import com.danimo.restaurant.dish.application.inputports.CreatingDishInputPort;
import com.danimo.restaurant.dish.application.inputports.FindingDishByIdInputPort;
import com.danimo.restaurant.dish.application.inputports.ListingDishInputPort;
import com.danimo.restaurant.dish.application.inputports.UpdatingDishInputPort;
import com.danimo.restaurant.dish.application.usecases.createdish.CreateDishDto;
import com.danimo.restaurant.dish.application.usecases.updatedish.UpdateDishDto;
import com.danimo.restaurant.dish.domain.Dish;
import com.danimo.restaurant.dish.infrastructure.inputadapters.rest.dto.CreateDishRequestDto;
import com.danimo.restaurant.dish.infrastructure.inputadapters.rest.dto.CreateDishResponseDto;
import com.danimo.restaurant.dish.infrastructure.inputadapters.rest.dto.DishResponse;
import com.danimo.restaurant.dish.infrastructure.inputadapters.rest.dto.DishUpdateRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Dishes", description = "Operaciones relacionadas a los platillos de restaurantes")
@RestController
@RequestMapping("/v1/dishes")
@WebAdapter
public class DishControllerAdapter {
    private final CreatingDishInputPort creatingDishInputPort;
    private final FindingDishByIdInputPort findingDishByIdInputPort;
    private final ListingDishInputPort listingDishInputPort;
    private final UpdatingDishInputPort updatingDishInputPort;

    @Autowired
    public DishControllerAdapter(CreatingDishInputPort creatingDishInputPort, FindingDishByIdInputPort findingDishByIdInputPort,
                                 ListingDishInputPort listingDishInputPort, UpdatingDishInputPort updatingDishInputPort) {
        this.creatingDishInputPort = creatingDishInputPort;
        this.findingDishByIdInputPort = findingDishByIdInputPort;
        this.listingDishInputPort = listingDishInputPort;
        this.updatingDishInputPort = updatingDishInputPort;
    }

    @Operation(
            summary = "Registrar nuevo platillo",
            description = "Devuelve la información del platillo correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Platillo creado"),
            @ApiResponse(responseCode = "404", description = "Platillo no creado")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<CreateDishResponseDto> createDish(@RequestBody CreateDishRequestDto dto){
        CreateDishDto objectAdaptedFromRestToDomain = dto.toDomain();

        Dish newDish = creatingDishInputPort.createDish(objectAdaptedFromRestToDomain);

        CreateDishResponseDto response = CreateDishResponseDto.fromDomain(newDish);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Obtener los platillos",
            description = "Devuelve la información de todos los platillos correspondientes."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Platillos encontrados"),
            @ApiResponse(responseCode = "404", description = "Platillos no encontrados")
    })
    @GetMapping
    @Transactional
    public ResponseEntity<List<DishResponse>> getAllDishes() {
        List<DishResponse> dishes = listingDishInputPort.getAllDishes()
                .stream()
                .map(DishResponse::fromDomain)
                .toList();

        return ResponseEntity.ok(dishes);
    }

    @Operation(
            summary = "Busca el platillo",
            description = "Devuelve el platillo si existe."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Platillo encontrado"),
            @ApiResponse(responseCode = "404", description = "Platillo no encontrado")
    })
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DishResponse> getDishById(@PathVariable String id) {
        Dish dish = findingDishByIdInputPort.findById(id);

        return ResponseEntity.ok(DishResponse.fromDomain(dish));
    }

    @Operation(
            summary = "Editar un platillo",
            description = "Devuelve el platillo actualizado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Platillo actualizado"),
            @ApiResponse(responseCode = "404", description = "Platillo no actualizado")
    })
    @PutMapping
    @Transactional
    public ResponseEntity<DishResponse> updateLocation(@RequestBody DishUpdateRequestDto dto) {
        Dish location = updatingDishInputPort.updateDish(dto.toDomain());
        return ResponseEntity.ok(DishResponse.fromDomain(location));
    }

}
