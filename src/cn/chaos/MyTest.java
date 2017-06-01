package cn.chaos;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class MyTest {

	private Pair pair;
	
	public static void main(String[] args) {
	/*	Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		System.out.println(c.get(Calendar.DAY_OF_MONTH));
		Date xx = new Date();
		xx.setMonth(1);
		c.setTime(xx);
		System.out.println(c.get(Calendar.DAY_OF_MONTH));
		getDaysOfMonth(YearMonth.now());*/
	/*	List<Integer> list = getList();
		List<Integer> list2 = new ArrayList();
		list2.add(1);list2.add(2);list2.add(3);
		list2.addAll(list);
		System.out.println(list2);*/
		Map<String,List<Integer>> data = new HashMap();
		data.put("0",getList() );
		data.put("1",getList() );
		List<Object> asList = Arrays.asList(data.values().toArray());
		System.out.println(asList);
	}
	
	
	public static List<Integer> getList(){
		List<Integer>  xx = new ArrayList<Integer>();
		Random random = new Random();
		for(int i=0;i<10;i++){
			xx.add(random.nextInt(20));
		}
		System.out.println(xx);
		return xx;
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
