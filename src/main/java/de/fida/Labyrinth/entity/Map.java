package de.fida.Labyrinth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Map {

    @Id
    private Long mapId;

    private String[] positions = new String[14];
    private String[] events = new String[14];

    private int currentPosition;

    private boolean treasureFound;
    private boolean weaponFound;
    private boolean keyFound;
}
