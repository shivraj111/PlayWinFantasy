package Game.function;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by vash7003 on 2/21/2019.
 */
public class functionLiberary {

    public String dateFormatter(int increaseDay)
    {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, increaseDay);
        Date date =c.getTime();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);

    }
}
