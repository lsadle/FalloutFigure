package com.sadlestudios.FalloutFigureApi.repositories;

import com.sadlestudios.FalloutFigureApi.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {
}
