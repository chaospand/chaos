package cn.chaos;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyTest {

	private Pair pair;
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		System.out.println(c.get(Calendar.DAY_OF_MONTH));
		Date xx = new Date();
		xx.setDate(1);
		c.setTime(xx);
		System.out.println(c.get(Calendar.DAY_OF_MONTH));
	}
	
	
	public static BigDecimal getSum(List<BigDecimal> data){
		int size = data.size();
		if(size>1){
			BigDecimal b = data.get(size-1);
			data.remove(size-1);
			return b.add(getSum(data));
		}else{
			return data.get(size-1);
		}
		
		
	}
	
	public static void testYearMonth(){
		YearMonth yearMonth = YearMonth.parse("2000-02");
		int day = getDaysOfMonth(yearMonth);
		System.out.println(yearMonth);
		System.out.println(day);
	}
	
	public static int getDaysOfMonth(YearMonth yearMonth) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(yearMonth.getYear(), yearMonth.getMonthValue()-1, 1);  
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.getTime());
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);  
    }  
	
}
