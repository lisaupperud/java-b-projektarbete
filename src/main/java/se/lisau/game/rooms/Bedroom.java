package se.lisau.game.rooms;

import se.lisau.game.util.ScannerUtil;

public class Bedroom implements Room {
    private boolean lightsOn = false;
    @Override
    public void roomDescription() {
        if(!lightsOn){
            roomTask();
        } else {
            System.out.println("        -- the room is empty! the burglar is in the hallway --");
        }


    }

    @Override
    public void roomTask() {
            System.out.println("                -- turn on the light? y/n --");
            String turnOnLight = ScannerUtil.getUserInput();
            if (turnOnLight.equalsIgnoreCase("y")) {
                turnOnLights();
                System.out.println("                   -- lights are on --");
                System.out.println("   -- the burglar is searching for something to steal! --");
                System.out.println("  -- the sudden light scares him and he runs towards you --");
                System.out.println(" ");
            } else if (turnOnLight.equalsIgnoreCase("n")) {
                System.out.println("          -- lights are off and you see nothing --");
            } else {
                System.out.println("-- invalid input --");
            }

    }
    public void turnOnLights(){
        if(!lightsOn){
            lightsOn = true;
        }
    }
}
