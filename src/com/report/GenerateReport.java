package com.report;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GenerateReport {

	static float incomingTradeAmount = 0 ;
	static float OutgoingTradeAmount = 0;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		parseData();
	}
	
public static void  parseData(){
		
		
		String csvFile = "C:\\Users\\DELL\\workspace\\GenerateProject\\src\\com\\report\\data.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

		
		
        try {

            br = new BufferedReader(new FileReader(csvFile));
			//Entity dataEntity = new Entity();
			
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);
				
				
				DataEntity dataEntity = new DataEntity();
				
				dataEntity.setEntity(data[0]);
				dataEntity.setBuyOrSell(data[1]);
				dataEntity.setAgreedFx(data[2]);
				dataEntity.setCurrency(data[3]);
				dataEntity.setInstructiondate(data[4]);
				dataEntity.setSettlementDate(data[5]);
				dataEntity.setUnits(data[6]);
				dataEntity.setPrice(data[7]); 
				
				//System.out.println(entity + buyOrSell + agreedFx + currency + instructionDate + settlementDate + units + price);
				
				//TODOo calculate the ranking for buy and sell
				//need t 
				if(data[3].equalsIgnoreCase("AED")
					|| data[3].equalsIgnoreCase("SAR") ){
					
					Calendar calendar = Calendar.getInstance();
					calendar.setFirstDayOfWeek(Calendar.SUNDAY);
					if(calendar.get(Calendar.DAY_OF_WEEK) <=5){
						
						Date now = new Date(dataEntity.getSettlementDate());
						Calendar settleDate = Calendar.getInstance();
						settleDate.clear();						
						settleDate.setTime(now);
						
						System.out.println(" >>>>>> if" );
						
						if(settleDate.get(Calendar.DAY_OF_WEEK) == 6){
							settleDate.add(Calendar.DAY_OF_WEEK, 2);
						}else if(settleDate.get(Calendar.DAY_OF_WEEK) == 7){
							settleDate.add(Calendar.DAY_OF_WEEK, 1);
						}
						
						CalculateAmount(dataEntity, settleDate);
						
						/*Date currentDate = new Date();
						Date settlementedDate = settleDate.getTime();
						System.out.println(currentDate);
						System.out.println(settlementedDate);
						
						
						
						System.out.println("date compare " + settlementedDate.compareTo(currentDate));
						if(currentDate.compareTo(settlementedDate) ==0){
							OutgoingTradeAmount = OutgoingTradeAmount + 
							(Float.parseFloat(dataEntity.getPrice())*Float.parseFloat(dataEntity.getUnits())*Float.parseFloat(dataEntity.getAgreedFx()) );
						}else{
							incomingTradeAmount = incomingTradeAmount +
							( Float.parseFloat(dataEntity.getPrice())*Float.parseFloat(dataEntity.getUnits())*Float.parseFloat(dataEntity.getAgreedFx()) ) ;
						}
						*/
						
						
						System.out.println(" OutgoingTradeAmount " + OutgoingTradeAmount);
						System.out.println(" incomingTradeAmount " + incomingTradeAmount);
						
					}
					
				}else{
					Calendar calendar = Calendar.getInstance();
					calendar.setFirstDayOfWeek(Calendar.MONDAY);
					if(calendar.get(Calendar.DAY_OF_WEEK) <=5){
						Date now = new Date(dataEntity.getSettlementDate());
						Calendar settlementDate = Calendar.getInstance();
						settlementDate.setTime(now);
						
						if(settlementDate.get(Calendar.DAY_OF_WEEK) == 7){
							settlementDate.add(Calendar.DAY_OF_WEEK, 2);
						}else if(settlementDate.get(Calendar.DAY_OF_WEEK) == 1){
							settlementDate.add(Calendar.DAY_OF_WEEK, 1);
						}
						
						CalculateAmount(dataEntity, settlementDate);
						
						
						System.out.println(" OutgoingTradeAmount " + OutgoingTradeAmount);
						System.out.println(" incomingTradeAmount " + incomingTradeAmount);
						
					}
				}
				
				/*if(calendar.get(Calendar.DAY_OF_WEEK)== 7  ||
				calendar.get(Calendar.DAY_OF_WEEK) == 0 ){
					
					//data[5] = calendar.add(Calendar.DATE, 1); 
					
				}*/
				
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
	}

private static void CalculateAmount(DataEntity dataEntity,
		Calendar settlementDate) {
	Date currentDate = new Date();
	Date settlementedDate = settlementDate.getTime();
	
	System.out.println(currentDate);
	System.out.println(settlementedDate);
	
	if(currentDate.equals(settlementedDate)){
		OutgoingTradeAmount = OutgoingTradeAmount + 
		(Float.parseFloat(dataEntity.getPrice())*Float.parseFloat(dataEntity.getUnits())*Float.parseFloat(dataEntity.getAgreedFx()) );
	}else{
		incomingTradeAmount = incomingTradeAmount +
		( Float.parseFloat(dataEntity.getPrice())*Float.parseFloat(dataEntity.getUnits())*Float.parseFloat(dataEntity.getAgreedFx()) ) ;
	}
}


}
