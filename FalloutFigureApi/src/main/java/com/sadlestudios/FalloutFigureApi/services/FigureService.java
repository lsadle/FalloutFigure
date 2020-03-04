package com.sadlestudios.FalloutFigureApi.services;

import com.sadlestudios.FalloutFigureApi.models.Figure;
import com.sadlestudios.FalloutFigureApi.repositories.FigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
public class FigureService {

    private final FigureRepository figureRepository;

    @Autowired
    public FigureService(FigureRepository figureRepository) {
        this.figureRepository = figureRepository;
    }

    /**
     * Get one Figure by its id
     * @param id The id of the Figure you want
     */
    public Figure GetOne(UUID id) {
        var figure = figureRepository.getOne(id);
        return figure.toFigure();
    }

    /**
     * Gets all Figures
     */
    public List<Figure> GetAll() {
        // Get FigureEntities
        var figures = figureRepository.findAll();
        var returnList = new ArrayList<Figure>();

        // Map FigureEntities to Figures
        returnList.addAll(figures.stream().map(f -> f.toFigure()).collect(Collectors.toList()));
        return returnList;
    }
}
