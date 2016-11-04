package com.opgea.crm.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String dateToString(Date date) {
        String sdate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null) {
            sdate = sdf.format(date);
        }
        return sdate;
    }

    public static Date stringToDate(String sdate) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (sdate != null) {
                date = sdf.parse(sdate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String dateToTime(Date date) {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        if (date != null) {
            time = sdf.format(date);
        }
        return time;
    }

    public static String dateDifference(Date completionDate) {
        if (completionDate != null) {
            Calendar comCalender = Calendar.getInstance();
            comCalender.setTime(completionDate);
            Calendar currentDate = Calendar.getInstance();

            long miliseconds = comCalender.getTimeInMillis() - currentDate.getTimeInMillis();
            long diffDays = miliseconds / (24 * 60 * 60 * 1000);
            return (diffDays != 0) ? diffDays + "/days" : "0";
        }
        return "0";
    }

    public static void main(String[] args) {
        System.out.println(dateDifference(stringToDate("2011-09-02")));
    }
    
    public static String getHHmmss(Long timeMilliSeconds){
        Date date = new Date(timeMilliSeconds);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        return sdf.format(date);
    }
    
    public static synchronized String getDateTimeString(Long timeMilliSeconds){
        Date date = new Date(timeMilliSeconds);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return sdf.format(date);
    }
    
    public static synchronized Date getDateFromYYYYMMDD(String dateString, String seperator){
        
        String valueArray[] = dateString.split(seperator); 
        int year = Integer.parseInt(valueArray[0]);
        int month = Integer.parseInt(valueArray[1]);
        int date = Integer.parseInt(valueArray[2]);
        
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, date);
        Date newDate = cal.getTime();
        System.out.println("Date: "+newDate);
        return newDate;
    }
    
    public static synchronized String getYYYYMMDDFromDate(Date date, String seperator){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy"+seperator+"MM"+seperator+"dd");
        String dateString = dateFormat.format(date);
        return dateString;
    }
    
    public static void main(String args){
        
        System.out.println(DateUtil.getDateTimeString(System.currentTimeMillis()));
    }

}
