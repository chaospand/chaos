package cn.chaos.poiDeal;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      POIexcelMake make = new POIexcelMake("xx");
      make.createRow();
      make.createCell(26,1).setCellValue("1231");
      
      make.createRow();
      make.createCell(1,4).setCellValue("序号");
      make.createCell(1,4).setCellValue("部门");
      make.createCell(1,4).setCellValue("姓名");
      make.createCell(6,2).setCellValue("应发项目");
      make.createCell(13, 1).setCellValue("应扣项目");
      for(int i=0;i<4;i++){
    	  make.createCell();
      }
      
      make.createRow();
      make.createCell(1, 2).setCellValue("住房补贴");
      make.createCell(2, 2).setCellValue("公积金");
      make.createCell(10, 1).setCellValue("社保");
      make.createCell(1, 3).setCellValue("个人所得税");
      make.createCell(1, 3).setCellValue("请假扣款");
      make.createCell(1, 3).setCellValue("用工总成本");
      make.createCell(1, 3).setCellValue("入工资卡费用合计");
      
      make.createRow();
      make.createCell(1, 2).setCellValue("基本工资");
      make.createCell(1, 2).setCellValue("岗位工资");
      make.createCell(1, 2).setCellValue("业务补贴");
      make.createCell(1, 2).setCellValue("健康补贴");
      make.createCell(1, 2).setCellValue("年功工资");
      make.createCell(1, 2).setCellValue("合计");
      
      make.createCell(2, 1).setCellValue("养老");
      make.createCell(2, 1).setCellValue("医疗");
      make.createCell(2, 1).setCellValue("失业");
      make.createCell(2, 1).setCellValue("生育");
      make.createCell(2, 1).setCellValue("工伤");
      
      make.createRow();
      make.createCell().setCellValue("公司");
      
      make.createCell().setCellValue("公司");
      make.createCell().setCellValue("个人");
      make.createCell().setCellValue("公司");
      make.createCell().setCellValue("个人");
      make.createCell().setCellValue("公司");
      make.createCell().setCellValue("个人");
      make.createCell().setCellValue("公司");
      make.createCell().setCellValue("个人");
      make.createCell().setCellValue("公司");
      make.createCell().setCellValue("个人");
      make.createCell().setCellValue("公司");
      make.createCell().setCellValue("个人");
      
      
      HSSFWorkbook hssfWorkbook = make.getHssfWorkbook();
      FileOutputStream stream = null;
      try {
		File file = new File("D:/ps","xx.xls");
		stream = new FileOutputStream(file);
		file.deleteOnExit();
		hssfWorkbook.write(stream);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
