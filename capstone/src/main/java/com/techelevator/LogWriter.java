package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LogWriter {
    private File auditFile = new File("Audit.txt");


    public LogWriter() {
        try {
            auditFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendMoney(BigDecimal moneyAdded, BigDecimal moneyProvided) {
        PrintWriter log = null;
        try {
            log = new PrintWriter(new FileOutputStream(auditFile, true));
            log.println(logDateTime() + "MONEY FED:		" + "$" + moneyAdded + ".00" + " $" + moneyProvided);
            log.flush();
            log.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }


        public void appendItemDispense(String name, String location, BigDecimal moneyBeforeDispense, BigDecimal moneyProvided) {
            PrintWriter log = null;
            try {
                log = new PrintWriter(new FileOutputStream(auditFile, true));
                log.println(logDateTime() + name + " " + location + "	$" + moneyBeforeDispense + " $" + moneyProvided);
                log.flush();
                log.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }


    public void appendFinishTransaction(BigDecimal changeProvided, BigDecimal moneyProvided) {
        PrintWriter log = null;
        try {
            log = new PrintWriter(new FileOutputStream(auditFile, true));
            log.println(logDateTime() + "CHANGE GIVEN:		" + "$" + changeProvided + " $" + moneyProvided + "\n *****");
            log.flush();
            log.close();

        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
    }

        public String logDateTime() {
            LocalDateTime logDate = LocalDateTime.now();
            String amPM = "";
            int month = logDate.getMonthValue();
            int date = logDate.getDayOfMonth();
            int year = logDate.getYear();
            int hour = logDate.getHour();
            if (hour >= 12) {
                amPM = "PM";
                if (hour >= 13) {
                    hour -= 12;
                }
            } else {
                amPM = "AM";
            }
            int minute = logDate.getMinute();
            int second = logDate.getSecond();
            String dateTimeString = month + "/" + date + "/" + year + " " + hour + ":" + minute + ":" + second + " " + amPM + " ";
            return dateTimeString;
        }
    }

