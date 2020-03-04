package com.sadlestudios.FalloutFigureApi.controllers;

import com.sadlestudios.FalloutFigureApi.models.Figure;
import com.sadlestudios.FalloutFigureApi.services.FigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("figure")
public class FigureController {

    private final FigureService figureService;

    @Autowired
    public FigureController(FigureService figureService) {
        this.figureService = figureService;
    }

    @GetMapping("/{id}")
    public Figure Get(@PathVariable("id")UUID id) {
        return figureService.GetOne(id);
    }

    @GetMapping("/")
    public List<Figure> Get() {
        return figureService.GetAll();
    }
}
