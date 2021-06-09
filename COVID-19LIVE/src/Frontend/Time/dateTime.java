package Frontend.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class dateTime {
	private Calendar time;
	
	public String date(int i) {
		time = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		time.add(Calendar.DATE, -i);
		String date = dateFormat.format(time.getTime());
		return date;
	}
}
