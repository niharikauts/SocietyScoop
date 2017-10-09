package com.example.niharika.yetanotherproj;

import android.graphics.Bitmap;

/**
 * Created by NIHARIKA on 17-03-2016.
 */
public class Config {

    public static final String OFFERS_DATA_URL = "http://societyscoop.comli.com/offerdata.php";

    public static final String EVENTS_DATA_URL = "http://societyscoop.comli.com/eventdata.php";

    public static final String CONTACTS_DATA_URL = "http://societyscoop.comli.com/contactdata.php";

    public static final String POLL_DATA_URL = "http://societyscoop.comli.com/polldata.php";
    public static final String MENU_DATA_URL = "http://societyscoop.comli.com/menudata.php";

    public static final String TAG_IMAGE_URL = "image";
    public static final String TAG_TITLE = "title";
    public static final String TAG_DESC = "desc";

    public static final String TAG_NAME = "name";
    public static final String TAG_DESG = "designation";
    public static final String TAG_PNUM = "pnum";

       public static final String TAG_ITEM = "item";
    public static final String SHARED_PREF_NAME = "login";
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    public static final String Uname_SHARED_PREF = "uname";

    public static String[] titles;
    public static String[] descs;
    public static String[] imageurls;
    public static Bitmap[] bitmaps;

    public static final String GET_URL = "http://societyscoop.comli.com/eventdata.php";



    public Config(int i) {
        titles = new String[i];
        descs = new String[i];
        imageurls = new String[i];
        bitmaps = new Bitmap[i];
    }
}
