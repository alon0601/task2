package submit;

import javafx.util.Pair;
import main.data.ShowInfo;

import java.util.*;
import java.util.stream.Collectors;

public class Shows {
    private final List<ShowInfo> shows;

    private static int showID = 1;
    private final Map<String, Set<Pair<String, Integer>>> citiesHallsMap;//city->{(hall,sits)}
    private final Map<String, Admin> admins;

    public Shows() {
        shows = new LinkedList<>();
        this.citiesHallsMap = new HashMap<>();
        admins = new HashMap<>();
    }

    public ShowInfo getShow(int showID) {
        ShowInfo info = shows.get(showID);
        return info;
    }

    public List<ShowInfo> getShows() {
        return shows;
    }

    public int addShow(String user, String password, ShowInfo showInfo) {
        if (!validAdmin(user, password) || !validShow(showInfo)|| !adminInCityOfShow(user,showInfo) )
            return -1;
        Admin admin = admins.get(user);
        if (!showInfo.city.equals(admin.getCity()))
            return -1;

        shows.add(showInfo);
        return showID++;
    }

    private boolean adminInCityOfShow(String user, ShowInfo showInfo) {
        return admins.get(user).getCity().equals(showInfo.city);
    }

    private boolean validShow(ShowInfo showInfo) {
       return showInfo != null && legalAuditoriumName(showInfo)  && legalDates(showInfo);
    }

    private boolean legalDates(ShowInfo showInfo) {
        return showInfo.lastOrderDate<showInfo.showDate;
    }

    private boolean legalAuditoriumName(ShowInfo showInfo) {
        return citiesHallsMap.containsKey(showInfo.city) && citiesHallsMap.get(showInfo.city).stream().map(Pair::getKey).collect(Collectors.toSet()).contains(showInfo.hall);
    }

    private boolean validAdmin(String user, String password) {
        return admins.containsKey(user) && admins.get(user).getPass().equals(password);
    }

    public void addCity(String city) {
        if (!citiesHallsMap.containsKey(city))
            citiesHallsMap.put(city, new HashSet<>());
    }

    public void addHall(String city, String hall, int sits) {
        if (!citiesHallsMap.containsKey(city) || citiesHallsMap.get(city).stream().map(Pair::getKey).collect(Collectors.toSet()).contains(hall))
            throw new RuntimeException("there is no such city in the system, or hall is already in the system");
        citiesHallsMap.get(city).add(new Pair<>(hall, sits));
    }

    public void addAdmin(String user, String pass, String city) {
        if (admins.containsKey(user)|| !citiesHallsMap.containsKey(city))
            throw new IllegalArgumentException();

        admins.put(user,new Admin(city,user,pass));
    }
}
