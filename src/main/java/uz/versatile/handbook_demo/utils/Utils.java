package uz.versatile.handbook_demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String getSimpleDateFormat(Date createdDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        return simpleDateFormat.format(createdDate);
    }
}
