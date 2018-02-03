package Game.Player;

import Game.Boats.*;
import Game.Grid.Case;
import Game.Grid.Grid;
import Game.utils.Direction;
import Game.utils.Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Human implements Player {
    private Grid attackGrid = new Grid();
    private Grid defenseGrid = new Grid();
    private List<Boat> boatsList = new ArrayList<>();

    public Human() {
        boatsList.add(new Carrier());
//        boatsList.add(new Cruiser());
//        boatsList.add(new Destroyer());
//        boatsList.add(new Submarine());
//        boatsList.add(new Torpedo());
    }

    @Override
    public void placeBoats() {
        String choice;
        Direction direction;
        int posX,posY;
        System.out.println("\nYou have 5 boats to place: 1 Carrier, 1 Torpedo, 1 Cruiser, 1 Submarine and 1 Destroyer.\nTo place a boat, you have to write a square where to place the stern of the boat and a direction for the boat.\nFormat example (case insensitive): B,6,EAST");
        for (Boat boat : boatsList) {
            Boolean check = false;
            System.out.println("Where do you want to place your "+boat.getName()+" (size="+boat.getSize()+") ?");
            do {
                choice = readChoice("([A-J]{1}),([1-9]|10),(NORTH|WEST|SOUTH|EAST)");
                posX = Integer.valueOf(choice.split(",")[1]);
                posY = Functions.charToIntPosition(choice.split(",")[0]);
                direction = Direction.valueOf(choice.split(",")[2].toUpperCase());
                check = checkBoatPosition(posX,posY,direction,boat.getSize());
                if (!check) System.out.println("Placement error.\nPlease try again.");
            } while (!check);
            //Init boat
            boat.setDirection(direction);
            boat.setPosition(new Case(posX,posY));
            defenseGrid.addBoat(boat);
            defenseGrid.displayGrid();
        }
    }

    @Override
    public List<Boat> getBoatsList() {
        return this.boatsList;
    }

    @Override
    public Case pickTarget() {
        List<Case> targets = getTargets();
        for (Case target: targets) { attackGrid.addTarget(target); }
        showBothGrid();
        System.out.println("What target do you want to fire ?");
        String choice = readChoice("[A-J]{1},([1-9]|10)");
        String[] res = choice.split(",");
        int posX = Integer.valueOf(choice.split(",")[1]);
        int posY = Functions.charToIntPosition(choice.split(",")[0]);
        Case targetChoice = new Case(posX,posY);
        return targetChoice;
    }

    @Override
    public int hit(Case target) {
        Boat boat = defenseGrid.getBoard()[target.getColonne()][target.getLigne()].getBoat();
        if (boat != null) {
            boat.damage();
            if (boat.getHealth() == 0) {
                defenseGrid.removeBoat(boat);
                System.out.println("Sink");
                return 2;
            }
            System.out.println("Hit");
            return 1;
        }
        System.out.println("Miss");
        return 0;
    }

    private String readChoice(String regex) {
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        Pattern ptn = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        while (!ptn.matcher(input).matches()) {
            System.out.println("Format error.");
            System.out.println("Please try again.");
            reader = new Scanner(System.in);
            input = reader.nextLine();
        }
        return input;
    }

    private Boolean checkBoatPosition(int column, int row, Direction direction, int boatSize) {
        Boolean check = false;
        switch (direction) {
            case NORTH:
                if (row-boatSize >= 0) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
                for (int i=0; i<boatSize;i++) {
                    if(this.defenseGrid.getBoard()[row-i][column].getBoat() != null) {
                        check = false;
                        System.out.println("There is a boat overlapse with your "+this.defenseGrid.getBoard()[row-i][column].getBoat().getName()+".");
                        break;
                    }
                }
                break;
            case EAST:
                if (column+boatSize <= 11) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
                for (int i=0; i<boatSize;i++) {
                    if(this.defenseGrid.getBoard()[row][column+i].getBoat() != null) {
                        check = false;
                        System.out.println("There is a boat overlapse with your "+this.defenseGrid.getBoard()[row][column+i].getBoat().getName()+".");
                        break;
                    }
                }
                break;
            case SOUTH:
                if (row+boatSize <= 11) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
                for (int i=0; i<boatSize;i++) {
                    if(this.defenseGrid.getBoard()[row+i][column].getBoat() != null) {
                        check = false;
                        System.out.println("There is a boat overlapse with your "+this.defenseGrid.getBoard()[row+i][column].getBoat().getName()+".");
                        break;
                    }
                }
                break;
            case WEST:
                if (column-boatSize >= 0) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
                for (int i=0; i<boatSize;i++) {
                    if(this.defenseGrid.getBoard()[row][column-i].getBoat() != null) {
                        check = false;
                        System.out.println("There is a boat overlapse with your "+this.defenseGrid.getBoard()[row][column-i].getBoat().getName()+".");
                        break;
                    }
                }
                break;
        }
        return check;
    }

    private List<Case> getTargets() {
        List<Case> targets = new ArrayList<>();
        for (int ligne = 1; ligne < defenseGrid.getSize(); ligne++) {
            for (int colonne = 1; colonne < defenseGrid.getSize(); colonne++) {
                if (defenseGrid.getBoard()[ligne][colonne].getBoat() != null) {
                    int range = defenseGrid.getBoard()[ligne][colonne].getBoat().getRange();
                    targets.add(defenseGrid.getBoard()[ligne][colonne]);
                    for(int k=1;k<=range; k++) {
                        //We check every time if the potential target isn't out of the grid
                        //We don't check duplicate because it won't matter at the end
                        //north
                        if(ligne-k > 0) targets.add(defenseGrid.getBoard()[ligne-k][colonne]);
                        //south
                        if(ligne+k <= 10) targets.add(defenseGrid.getBoard()[ligne+k][colonne]);
                        //east
                        if(colonne+k <= 10) targets.add(defenseGrid.getBoard()[ligne][colonne+k]);
                        //west
                        if(colonne-k > 0) targets.add(defenseGrid.getBoard()[ligne][colonne-k]);
                    }
                }
            }
        }
        return targets;
    }

    @Override
    public void moveBoat() {
        System.out.println("Do you want to move a boat (yes/no)");
        String choice = readChoice("(YES|NO)");
        if(choice.equalsIgnoreCase("yes")) {
            int i =0;

            for (Boat boat : boatsList) {
                System.out.println(i+". "+boat.getName());
                i++;
            }
            int nbOfBoat = i-1;

            System.out.println("Select the boat you want to move and the number of move \"Boat number,(NORTH|WEST|SOUTH|EAST),number of move\"");
            Boolean check = false;
            int nbBoatToMove;
            Direction dirToMove;
            int nbOfMove;
            Boat boatToMove;
            int newPosX = 0;
            int newPosY = 0;
            do {
                String choiceBoat = readChoice("[0-"+nbOfBoat+"],(NORTH|WEST|SOUTH|EAST),[1-2]");
                nbBoatToMove = Integer.parseInt(choiceBoat.split(",")[0]);
                dirToMove = Direction.valueOf(choiceBoat.split(",")[1].toUpperCase());
                nbOfMove = Integer.parseInt(choiceBoat.split(",")[2]);
                boatToMove = boatsList.get(nbBoatToMove);

                switch (dirToMove) {
                    case NORTH:
                        newPosX = boatToMove.getPosition().getColonne();
                        newPosY = boatToMove.getPosition().getLigne()-nbOfMove;
                        break;
                    case SOUTH:
                        newPosX = boatToMove.getPosition().getColonne();
                        newPosY = boatToMove.getPosition().getLigne()+nbOfMove;

                        break;
                    case WEST:
                        newPosX = boatToMove.getPosition().getColonne()-nbOfMove;
                        newPosY = boatToMove.getPosition().getLigne();
                        break;
                    case EAST:
                        newPosX = boatToMove.getPosition().getColonne()+nbOfMove;
                        newPosY = boatToMove.getPosition().getLigne();
                        break;
                }
                check = checkBoatPosition(newPosX,newPosY,boatToMove.getDirection(),boatToMove.getSize());
                if (!check) System.out.println("Placement error.\nPlease try again.");
            } while (!check);

            defenseGrid.removeBoat(boatToMove);
            boatToMove.setPosition(new Case(newPosX, newPosY));
            defenseGrid.addBoat(boatToMove);

        } else if (choice.equalsIgnoreCase("no")) {
            return;
        }
    }

    public void showBothGrid() {

        //Same for both grid
        System.out.println("           Defense grid                        Attack grid");
        int gridSize = attackGrid.getSize();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(j == 10 ? "["+ this.defenseGrid.getBoard()[i][j].getIllustration() +"]" : "["+ this.defenseGrid.getBoard()[i][j].getIllustration() +"]");

            }
            System.out.print(i == 0 ? " " : "  ");

            for (int j = 0; j < gridSize; j++) {
                System.out.print(j == 10 ? "["+ this.attackGrid.getBoard()[i][j].getIllustration() +"]" : "["+ this.attackGrid.getBoard()[i][j].getIllustration() +"]");
            }

            System.out.println("");
        }
    }
}
