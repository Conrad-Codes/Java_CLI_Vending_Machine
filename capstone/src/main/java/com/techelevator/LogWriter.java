package com.techelevator;

import java.io.*;
import java.time.LocalDateTime;

public class LogWriter {

    File auditFile = new File("Audit.txt");

    auditFile.createNewFile();


    public LogWriter(String auditFile){
        auditFile.createNewFile();

    }
    //From money added


//
//    try (PrintWriter log = new PrintWriter(new FileOutputStream(auditFile, true))) {
//        log.append(logDateTime() + "MONEY FED:		" + "$" + moneyAdded + ".00" + " $" + moneyProvided);
//    } catch (FileNotFoundException e) {
//        System.out.println("File not found");
//    }


    //From Dispense item
//    try (PrintWriter log = new PrintWriter(new FileOutputStream(auditFile, true))) {
//        log.append(logDateTime() + offering.getName() + " " + offering.getLocation() + "	$" + moneyBeforeDispense + " $" + moneyProvided);
//    } catch (FileNotFoundException e) {
//        System.out.println("File not found");
//    }


//from finish transaction
//    PrintWriter log = null;
//				try {
//        log = new PrintWriter(new FileOutputStream(auditFile, true));
//        log.println("\n");
//        log.println(logDateTime() + "CHANGE GIVEN:		" + "$" + changeProvided + " $" + moneyProvided + "\n *****");
//        log.flush();
//        log.close();
//
//    } catch (
//    FileNotFoundException e)
//
//    {
//        e.printStackTrace();
//    }
//                //From logDateTime method
//                public static String logDateTime() {
//                    LocalDateTime logDate = LocalDateTime.now();
//                    String amPM = "";
//                    int month = logDate.getMonthValue();
//                    int date = logDate.getDayOfMonth();
//                    int year = logDate.getYear();
//                    int hour = logDate.getHour();
//                    if (hour>=12) {
//                        amPM = "PM";
//                        if (hour >=13) {
//                            hour-=12;
//                        }
//                    }else {
//                        amPM = "AM";
//                    }
//                    int minute = logDate.getMinute();
//                    int second = logDate.getSecond();
//                    String dateTimeString = month + "/" + date + "/" + year + " " + hour + ":" + minute + ":" + second + " " + amPM + " ";
//                    return dateTimeString;
//                }
//
}
