package Backend.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class dateTime {

	private Calendar time = Calendar.getInstance();
	
	public String date() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(time.getTime());
		return date;
	}
	
	public String dateTime() {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = datetimeFormat.format(time.getTime());
		return dateTime;
	}
}
