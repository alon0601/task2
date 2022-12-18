package submit;

import main.data.ShowInfo;

import java.util.*;

public class Shows {
    private List<ShowInfo> shows;
    public Shows(){
        shows = new LinkedList<>();
    }

    public ShowInfo getShow(int showID){
        ShowInfo info =  shows.get(showID);
        return info;
    }
}
