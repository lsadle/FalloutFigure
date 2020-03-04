package com.sadlestudios.FalloutFigureApi.repositories;

import com.sadlestudios.FalloutFigureApi.entities.OrderEntity;
import com.sadlestudios.FalloutFigureApi.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findByUserId(UUID userId);
}
