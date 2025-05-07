package de.fida.Labyrinth.controller;

import de.fida.Labyrinth.service.MapService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {

    @Autowired
    private MapService mapService;

    //http://localhost:8080/move?direction=east
    @GetMapping("/move")
    private String move(@PathParam("direction") String direction){
        return mapService.move(direction);
    }

    //http://localhost:8080/refresh
    @GetMapping("/refresh")
    private String refresh(){
        return "You awaken at the edge of the LABYRINTH — its walls cold, ancient, and unwelcoming.<br>" +
                "The way back is lost. Only the treasure can grant you escape.<br>" +
                "Possessions: None <br>" +
                "Current position: 0.<br>" +
                "Paths open to the: " + mapService.refresh() + ".";
    }
}
