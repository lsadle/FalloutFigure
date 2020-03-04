package com.sadlestudios.FalloutFigureApi.entities;

import com.sadlestudios.FalloutFigureApi.models.Figure;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "figures")
public class FigureEntity {
    @Id
    @GeneratedValue
    @Column(name = "FIGURE_ID")
    private UUID figureId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    public FigureEntity() { }
    public FigureEntity(String name, String description, double price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Figure toFigure() {
        return new Figure(this.figureId, this.name, this.description, this.price, this.imageUrl);
    }
}
