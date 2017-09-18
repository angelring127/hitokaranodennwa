package kpu.ac.kr.hitokaradennwatwo;

/**
 * Created by angel on 2017-09-18.
 */

public class DataModel {

    public int icon;
    public String name;

    // Constructor
    // The drawer items are stored in the form of a ListView. Hence we need to use an Adapter Class to
    // provide that data to the activity class

    public DataModel(int icon, String name){

        this.icon = icon;
        this.name = name;
    }
}
