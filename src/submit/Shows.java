package submit;

import javafx.util.Pair;
import main.data.ShowInfo;

import java.util.*;
import java.util.stream.Collectors;

public class Shows {
    private final List<ShowInfo> shows;
    private final Map<String,Set<Pair<String,Integer>>> citiesHallsMap;//city->{(hall,sits)}
    public Shows(){
        shows = new LinkedList<>();
        this.citiesHallsMap = new HashMap<>();
    }

    public ShowInfo getShow(int showID){
        ShowInfo info =  shows.get(showID);
        return info;
    }

    public void addCity(String city){
        if (!citiesHallsMap.containsKey(city))
            citiesHallsMap.put(city,new HashSet<>());
    }

    public void addHall(String city, String hall, int sits) {
        if (!citiesHallsMap.containsKey(city) || citiesHallsMap.get(city).stream().map(Pair::getKey).collect(Collectors.toSet()).contains(hall))
            throw new RuntimeException("there is no such city in the system, or hall is already in the system");
        citiesHallsMap.get(city).add(new Pair<>(hall,sits));
    }
}
