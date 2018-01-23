package com.example.anant.jhack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by L on 28-12-2017.
 */


//either  , ArrayList<> ,

public class NineMobilePOJO {

    public static List<String> mob = new ArrayList<>(9);

    public static void flush()
    {
        mob.clear();
    }

    public static void add(String mobileNumber)
    {
        mob.add(mobileNumber);
    }


    public static List<String> get()
    {
        return mob;
    }

}
