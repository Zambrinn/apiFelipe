package trabalhoFelipe.github.Zambrinn.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trabalhoFelipe.github.Zambrinn.model.Car;
import trabalhoFelipe.github.Zambrinn.model.DTOs.CarRequest;
import trabalhoFelipe.github.Zambrinn.model.DTOs.CarResponse;
import trabalhoFelipe.github.Zambrinn.model.User;
import trabalhoFelipe.github.Zambrinn.repository.CarRepository;
import trabalhoFelipe.github.Zambrinn.repository.UserRepository;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    public CarResponse createCar(CarRequest request) {
        Car car = Car.builder()
                .year(request.year())
                .brand(request.brand())
                .model(request.model())
                .status(request.status())
                .build();

        if (request.userId() != null) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        car.setUser(user);
    }

        Car newCar = carRepository.save(car);
        return convertToDTO(newCar);
    }

    public List<CarResponse> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CarResponse updateCar(UUID id, CarRequest request) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrar o carro com o id: " + id));


        existingCar.setBrand(request.brand());
        existingCar.setModel(request.model());
        existingCar.setYear(request.year());

        Car updatedCar = carRepository.save(existingCar);
        return convertToDTO(updatedCar);
    }

    public void deleteCar(UUID id) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrar o carro com o id: " + id));

        carRepository.delete(existingCar);
    }

    public CarResponse findCarById(UUID id) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrar o carro com o id: " + id));

        return convertToDTO(existingCar);
    }

    public CarResponse convertToDTO(Car car) {
        return new CarResponse(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getStatus(),
                car.getUser() != null ? car.getUser().getId() : null
        );
    }

}