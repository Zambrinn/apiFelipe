package trabalhoFelipe.github.Zambrinn.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import trabalhoFelipe.github.Zambrinn.model.DTOs.CarRequest;
import trabalhoFelipe.github.Zambrinn.model.DTOs.CarResponse;
import trabalhoFelipe.github.Zambrinn.service.CarService;

@RestController
@RequestMapping("api/v1/cars")
public class CarController {
    @Autowired
    public CarService carService;

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@Valid @RequestBody CarRequest request) {
        return ResponseEntity.ok(carService.createCar(request));
    }

    @GetMapping
    public ResponseEntity<List<CarResponse>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}") 
    public ResponseEntity<CarResponse> getCarById(@PathVariable UUID id) {
        return ResponseEntity.ok(carService.findCarById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable UUID id, @Valid @RequestBody CarRequest request) {
        return ResponseEntity.ok(carService.updateCar(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable UUID id) {
        carService.deleteCar(id);
    }
}
