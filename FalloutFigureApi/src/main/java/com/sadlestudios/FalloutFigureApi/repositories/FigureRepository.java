package com.sadlestudios.FalloutFigureApi.repositories;

import com.sadlestudios.FalloutFigureApi.entities.FigureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FigureRepository extends JpaRepository<FigureEntity, UUID> {
    List<FigureEntity> findByFigureIdIsIn(List<UUID> ids);
}
