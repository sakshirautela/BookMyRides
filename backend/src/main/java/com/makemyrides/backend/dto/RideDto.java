package com.makemyrides.backend.dto;

import com.makemyrides.backend.service.RideService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.ResponseBody;

public class RideDto {
    private  final RideService rideService;
    private final ModelMapper modelMapper;

    public RideDto(RideService rideService, ModelMapper modelMapper) {
        this.rideService = rideService;
        this.modelMapper = modelMapper;
    }

    public RideDto getRideById(Long id) {
        return  rideService.getRideById(id);
    }

    public RideDto saveRide(RideDto rideDto) {
        RideService ride=modelMapper.map(rideDto,RideService.class);
       return rideService.saveRide(ride);
    }
}
