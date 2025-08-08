package com.makemyrides.backend.config;

import com.makemyrides.backend.dto.RideResponseDTO;
import com.makemyrides.backend.dto.UserResponseDTO;
import com.makemyrides.backend.dto.VehicleResponseDTO;
import com.makemyrides.backend.entity.Ride;
import com.makemyrides.backend.entity.User;
import com.makemyrides.backend.entity.Vehicle;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
