package com.sadlestudios.FalloutFigureApi.models;

import lombok.Data;
import java.util.UUID;

@Data
public class Figure {
    private UUID figureId;
    private String name;
    private String description;
    private double price;
    private String imageUrl;

    public Figure() { }
    public Figure(UUID figureId, String name, String description, double price, String imageUrl) {
        this.figureId = figureId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
