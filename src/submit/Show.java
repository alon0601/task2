package submit;

import main.data.ShowInfo;

public class Show {
    private ShowInfo showInfo;
    public int[] reserveMemberChairs;

    public Show(ShowInfo showInfo,int size){
        this.showInfo = showInfo;
        this.reserveMemberChairs = new int[size];
    }
    public ShowInfo getShowInfo(){
        return this.showInfo;
    }
    public void setReserveMemberChairs(int start, int end){
        if(start >= 0 && end < reserveMemberChairs.length){
            for(int i = start; i < end; i++){
                this.reserveMemberChairs[i] = 1;
            }
        }
    }
}
