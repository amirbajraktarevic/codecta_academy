import Characters.*;
import DisneyLand.*;
import Netflix.Netflix;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Adventure {


    public static void runNetflix(){
        Logger logger = LogManager.getLogger(Adventure.class);
        logger.info("Netflix program has started successfuly!");
        logger.warn("You HAVE to enter your name");

        System.out.println("Welcome to our Netflix catalog!");
        System.out.println("Enter your name: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        System.out.println("Welcome, " + name);


        //listing the genres
        LinkedList<String> genres = new LinkedList<String>();
        genres.add("Action");
        genres.add("Comedy");
        genres.add("Thriller");

        System.out.println("Choose the genre: ");
        for(int i =0; i< genres.size(); i++){
            System.out.println(i+1 + " " + genres.get(i));
        }
        int chosenGenre = input.nextInt();

        switch(chosenGenre) {

            case 1:
                getAction();
                break;

            case 2:
                getComedy();
                break;

            case 3:
                getThriller();
                break;

            default:
                System.out.println("That entry is not valid");
                logger.warn("Invalid input");
                break;
        }


    }

    public static void getAction(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(new File("src/main/resources/netflix.json"));
            TypeReference<List<Netflix>> typeReference = new TypeReference<List<Netflix>>() {};
            List<Netflix> movies = mapper.readValue(inputStream,typeReference);
            for(Netflix m : movies){
                if(m.getGenre().equals("Action")) {
                    System.out.println(m.getTitle());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getComedy(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(new File("src/main/resources/netflix.json"));
            TypeReference<List<Netflix>> typeReference = new TypeReference<List<Netflix>>() {};
            List<Netflix> movies = mapper.readValue(inputStream,typeReference);
            for(Netflix m : movies){
                if(m.getGenre().equals("Comedy")) {
                    System.out.println(m.getTitle());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getThriller(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(new File("src/main/resources/netflix.json"));
            TypeReference<List<Netflix>> typeReference = new TypeReference<List<Netflix>>() {};
            List<Netflix> movies = mapper.readValue(inputStream,typeReference);
            for(Netflix m : movies){
                if(m.getGenre().equals("Thriller")) {
                    System.out.println(m.getTitle());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void runDisney(){
        Logger logger = LogManager.getLogger(Adventure.class);

        logger.info("Disney program started successfuly!");
        logger.warn("Enter your name!");

        //listing all of the disneyland lands
        LinkedList<String> land = new LinkedList<String>();
        land.add("Mickeyland");
        land.add("Adventureland");
        land.add("Fantasyland");

        //listing all of the characters in Mickeyland
        LinkedList<String> mickeyLandCharacters = new LinkedList<String>();
        mickeyLandCharacters.add("Mickey Mouse");
        mickeyLandCharacters.add("Bugs Bunny");
        mickeyLandCharacters.add("Donald Duck");
        mickeyLandCharacters.add("Minnie Mouse");

        // listing all of the characters in Adventureland
        LinkedList<String> adventureLandCharacters = new LinkedList<String>();
        adventureLandCharacters.add("Peter Pan");
        adventureLandCharacters.add("Tinkerbell");

        //listing all of the characters in Fantasyland
        LinkedList<String> fantasyLandCharacters = new LinkedList<String>();
        fantasyLandCharacters.add("Dumbo");
        fantasyLandCharacters.add("Aladdin");

        System.out.println("Enter your name!");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        System.out.println("Welcome" + " " + name);
        System.out.println("Choose your wanted Disney Land: ");
        for (int i = 0; i < land.size(); i++) {
            System.out.println(i + 1 + " " + land.get(i));
        }
        int chosenLand = input.nextInt();

        switch (chosenLand) {
            case 1:
                goDisneyLand(mickeyLandCharacters);
                System.out.println("Choose your character: ");
                int chosenCharacter = input.nextInt();
                playWithMickey(chosenCharacter);
                break;
            case 2:
                goAdventureLand(adventureLandCharacters);
                System.out.println("Choose your character: ");
                int chosenCharacter2 = input.nextInt();
                playWithAdventure(chosenCharacter2);
                break;
            case 3:
                goFantasyLand(fantasyLandCharacters);
                System.out.println("Choose your character: ");
                int chosenCharacter3 = input.nextInt();
                playWithFantasy(chosenCharacter3);
                break;

            default:
                System.out.println("Land not found.");
                System.exit(0);
                logger.error("Entry not found.");
                break;
        }

        System.out.println("Thank you for playing!");
        logger.info("Program ended succesfully");

    }

    public static void goDisneyLand(LinkedList<String> characters){
            MickeyLand mickeyLand = new MickeyLand();
            System.out.println(mickeyLand.welcomeMsg());
        for (int i = 0; i < characters.size(); i++) {
            System.out.println(i + 1 + " " + characters.get(i));
        }
    }

    public static void goAdventureLand(LinkedList<String> characters){
        AdventureLand adventureLand = new AdventureLand();
        System.out.println(adventureLand.welcomeMsg());
        for (int i = 0; i < characters.size(); i++) {
            System.out.println(i + 1 + " " + characters.get(i));
        }
    }

    public static void goFantasyLand(LinkedList<String> characters){
        FantasyLand fantasyLand = new FantasyLand();
        System.out.println(fantasyLand.welcomeMsg());
        for (int i = 0; i < characters.size(); i++) {
            System.out.println(i + 1 + " " + characters.get(i));
        }
    }

    public static void playWithAdventure(int chosenCharacter){
        switch (chosenCharacter){
            case 1:
                DisneyCharacter peter = new PeterPan();
                System.out.println(peter.displayName());
                System.out.println(peter.displayInfo());
                break;
            case 2:
                DisneyCharacter tinkerbell = new Tinkerbell();
                System.out.println(tinkerbell.displayName());
                System.out.println(tinkerbell.displayInfo());
                break;
            default:
                System.out.println("Character not found in Adventureland!");
                break;

        }
    }

    public static void playWithFantasy(int chosenCharacter){
        switch (chosenCharacter){
            case 1:
                DisneyCharacter dumbo = new Dumbo();
                System.out.println(dumbo.displayName());
                System.out.println(dumbo.displayInfo());
                break;
            case 2:
                DisneyCharacter aladdin = new Aladdin();
                System.out.println(aladdin.displayName());
                System.out.println(aladdin.displayInfo());
                break;
            default:
                System.out.println("Character not found in Adventureland!");
                break;

        }
    }

    public static void playWithMickey(int chosenCharacter){

        switch (chosenCharacter){
            case 1:
                DisneyCharacter mickey = new MickeyMouse();
                System.out.println(mickey.displayName());
                System.out.println(mickey.displayInfo());
                break;
            case 2:
                DisneyCharacter bugs = new BugsBunny();
                System.out.println(bugs.displayName());
                System.out.println(bugs.displayInfo());
                break;
            case 3:
                DisneyCharacter donald = new DonaldDuck();
                System.out.println(donald.displayName());
                System.out.println(donald.displayInfo());
                break;
            case 4:
                DisneyCharacter minnie = new MinnieMouse();
                System.out.println(minnie.displayName());
                System.out.println(minnie.displayInfo());
                break;
            default:
                System.out.println("No such character in Mickeyland");
                break;
        }



    }
    public static void main (String[] args) {

        for(String arg : args) {
            if (arg.equals("Disney") || args.equals("disney")) {
                runDisney();
            } else if (arg.equals("Netflix") || args.equals("netflix")) {
                runNetflix();
            } else {

                runDisney();
                runNetflix();
            }
        }


    }
}
