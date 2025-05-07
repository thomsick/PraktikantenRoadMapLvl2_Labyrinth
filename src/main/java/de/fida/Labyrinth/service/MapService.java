package de.fida.Labyrinth.service;

import de.fida.Labyrinth.entity.Map;
import de.fida.Labyrinth.repo.MapRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MapService {

    @Autowired
    private MapRepo mapRepo;

    public String move(String direction){
        Map ourMap = mapRepo.findById(13L).get();
        String[] positions = ourMap.getPositions();
        String[] events = ourMap.getEvents();
        String event = "";
        int currentPosition = ourMap.getCurrentPosition();

        if (direction.equals("east")){
            currentPosition++;
        }
        if (direction.equals("west")){
            currentPosition--;
        }

        ourMap.setCurrentPosition(currentPosition);
        mapRepo.save(ourMap);
        return event + "<br>" +
                "Paths open to the: " + positions[currentPosition] + "." ;
    }

    public String refresh(){
        Map ourMap;
        if (mapRepo.findById(13L).isPresent()){
            ourMap = mapRepo.findById(13L).get();
        } else {
            ourMap = new Map();
            ourMap.setMapId(13L);
        }
        String[] positions = ourMap.getPositions();
        String[] events = ourMap.getEvents();

        positions[0] = "east";
        positions[1] = "east, west, south";
        positions[2] = "east, west";
        positions[3] = "south, east, west";
        positions[4] = "east, west";
        positions[5] = "east, west, north";
        positions[6] = "east,west";
        positions[7] = "north, west";

        positions[8] = "north";
        positions[9] = "north";
        positions[10] = "north";
        positions[11] = "south";
        positions[12] = "south";
        positions[13] = "south";

        List<String> texte = Arrays.asList(
                "Fortune's cruel joke — a rusted blade, half-buried in grime. Still, it cuts... and perhaps that's all that matters.",
                "Your hand closes around something cold — a key, etched with cryptic runes. It hums with forgotten purpose, and dread.",
                "Greed has no patience. You seize the treasure, and in doing so, awaken whatever slumbered beside it. The walls seem to breathe now. Flee — if you dare.",
                "The floor groans... then collapses. You plummet into the void, spat out at the maze's beginning."
        );
        events[9] = texte.get(0);
        events[10] = texte.get(1);
        events[12] = texte.get(2);
        events[13] = texte.get(3);

        String text1 = "A grotesque silhouette blocks your path, its breath a symphony of rot. No passage here. Not yet.";
        String text2 = "A great door stands inert — bound in silence, sealed by time. Your journey ends at its threshold… for now.";
        events[8] = text1;
        events[11] = text2;

        ourMap.setCurrentPosition(0);
        ourMap.setTreasureFound(false);
        ourMap.setWeaponFound(false);
        ourMap.setKeyFound(false);

        ourMap.setPositions(positions);
        mapRepo.save(ourMap);

        return positions[ourMap.getCurrentPosition()];
    }
}