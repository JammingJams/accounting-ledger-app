package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        //Where I'll put the core(?) stuff
        Scanner uKey = new Scanner(System.in);
       System.out.println("Home screen test welcome");
        homeScreen(uKey);


    }
    //initialization of the Ledger
    public static void displayLedger() {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("src/main/resources/Transactions.csv"));
            String data;
            while((data = bufReader.readLine())!= null) {
                System.out.println(data);

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (IOException e) {
            System.out.println("An error has occurred");

        }

    }
    //Hey I added this line how would I commit this to the branch?

    public static void LedgerList() {

            try {
                BufferedReader br = new BufferedReader(new FileReader("Transactions.csv"));
                //This creates a new instance of a list
                //The hashmap is a container is the dataType! Sting, int, double, boolean, Class
                ArrayList<accountingApp> list = new ArrayList<>();
                String line;
                //This tells the date what to parse into! Then it will convert the date into a string!


                while ((line = br.readLine())!= null) {
                //We need to create an object to add to the list!
                    //Accounting app has all of our data! Therefore we need to call on it to create a new instance
                    accountingApp loadList = new accountingApp();
                    String[] tempArray = line.split(Pattern.quote("|"));
                    //
                    try {
                        //This turns the string into a double!
                        LocalDate dateTemp = LocalDate.parse(tempArray[0]);
                        LocalTime timeTemp = LocalTime.parse(tempArray[1]);
                        String tempDescription = tempArray[2];
                        String tempVendor = tempArray[3];
                        double amountTemp = Double.parseDouble(tempArray[4]);

                        //This sets the double into our object!
                        loadList.setDate(dateTemp);
                        loadList.setTime(timeTemp);
                        loadList.setDescription(tempDescription);
                        loadList.setVendor(tempVendor);
                        loadList.setAmount(amountTemp);

                        //This permanately adds our instance into the list we made earlier
                        list.add(loadList);
                    }
                    catch (NumberFormatException e) {
                        e.printStackTrace();
                    }


                }

            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                e.printStackTrace();

            } catch (IOException e) {
                System.out.println("Something went wrong");
            }


    }
    public static void homeScreen(Scanner uKey){

        boolean run = true;

        while (run) {
            System.out.println("Selection page");
            System.out.println("\t1 Add Deposit");
            System.out.println("\t2 Make payment(debit)");
            System.out.println("\t3 Ledger page");
            System.out.println("\t0 Exit");
            System.out.print("Make a selection ");
            int choice = uKey.nextInt();
            uKey.nextLine();

            switch (choice) {
                case 1:
                    addDeposit(uKey);
                    break;
                case 2:
                    paymentDebit(uKey);
                    break;
                case 3:
                    ledgerScreen(uKey);
                    break;
                case 0:
                    System.out.println("Stop by again, yah?");
                    uKey.close();
                    System.exit(0);
                    break;
                default:
                    waitAndGo(uKey, "Wrong option please try again");
            }

        }

    }


    public static void ledgerScreen (Scanner uKey) {
        System.out.println("Ledger page");
        boolean run = true;
        formattedSpace();
        while (run) {
            System.out.println("\t(1) Display all");
            System.out.println("\t(2) Show deposits");
            System.out.println("\t(3) Show payments");
            System.out.println("\t(4) Show reports");
            System.out.println("\t(0) back to homepage");

            System.out.print("Please select your option ");
            int choice = uKey.nextInt();

            switch (choice) {
                case 1:
                    displayLedger();
                    break;
                case 2:
                    depositDisplay();
                    break;
                case 3:
                    paymentDisplay();
                    break;
                case 4:
                    reports(uKey);
                    break;
                case 0:
                    homeScreen(uKey);
                    break;
                default:
                    waitAndGo(uKey,"invalid inout try again");

            }


        }



    }

    public static void addDeposit(Scanner uKey) {
        System.out.print("Enter the description?: ");
        String description = uKey.nextLine().trim();
        System.out.print("Who is the vendor?: ");
        String vendor = uKey.nextLine().trim();
        System.out.println("what is the amount? ");
        double amount = uKey.nextDouble();

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateConverter = formatter.format(date);

        LocalTime time = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        String timeConverter = timeFormatter.format(time);


        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("src/main/resources/Transactions.csv",true));
            bufWriter.write( String.format("%s|%s|%s|%s|%.2f\n",dateConverter,timeConverter,description,vendor,amount));
            bufWriter.newLine();
            bufWriter.close();

        } catch (IOException e) {
            System.out.println("Something went wrong");

        }

    }

    public static void paymentDebit(Scanner uKey) {
        System.out.print("Enter the description?: ");
        String description = uKey.nextLine().trim();
        System.out.print("Who is the vendor?: ");
        String vendor = uKey.nextLine().trim();
        System.out.println("what is the amount? ");
        double amount = uKey.nextDouble();

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateConverter = formatter.format(date);

        LocalTime time = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        String timeConverter = timeFormatter.format(time);



        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("src/main/resources/Transactions.csv",true));
            bufWriter.write( String.format("%s|%s|%s|%s|%.2f\n",dateConverter,timeConverter,description,vendor,amount));
            bufWriter.newLine();
            bufWriter.close();

        } catch (IOException e) {
            System.out.println("Something went wrong");

        }

    }
    //positive balances
    public static void depositDisplay() {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("src/main/resources/Transactions.csv"));
            String data;
            while((data = bufReader.readLine())!= null) {
                String[] tempArray = data.split(Pattern.quote("|"));
                if(!tempArray[4].contains("-")) {
                    System.out.println(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (IOException e) {
            System.out.println("An error has occurred");

        }


    }

    //Negative balances
    public static void paymentDisplay() {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("src/main/resources/Transactions.csv"));
            String data;
            while((data = bufReader.readLine())!= null) {
                String[] tempArray = data.split(Pattern.quote("|"));
                if(tempArray[4].contains("-")) {
                    System.out.println(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (IOException e) {
            System.out.println("An error has occurred");

        }

    }
    public static void reports(Scanner uKey) {
        System.out.println("What are you looking for specifically?");
        System.out.println("\t(1) Month to date");
        System.out.println("\t(2) Previous month");
        System.out.println("\t(3) Year To Date");
        System.out.println("\t(4) Previous year");
        System.out.println("\t(5) Search by Vendor");
        System.out.println("\t(6) Back to ledger selection");
        System.out.println("\t(0) Back to homepage");
        int choice = uKey.nextInt();

        boolean run = true;

        switch (choice) {
            case 1:
                searchMonthToDate(uKey);
                break;
            case 2:
                //Previous month
                break;
            case 3:
                yearToDate(uKey);
                break;
            case 4:
                //Previous Year
            case 5:
                vendorSearch();
                break;
            case 6:
                ledgerScreen(uKey);
                break;
            case 0:
                homeScreen(uKey);
                break;
            default:
                System.out.println("Wrong input");

        }


    }

    public static void searchMonthToDate(Scanner ukey) {
        System.out.println("What is the month(1-12)?: ");
        int month = ukey.nextInt();
        System.out.println("What is the day(1-31)?: ");
        int day = ukey.nextInt();

        LocalDate currentDate = LocalDate.now();
        LocalDate startMtD = currentDate.withMonth(month).withDayOfMonth(day);



            try {
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Transactions.csv"));

                String line;

                while ((line = br.readLine())!= null) {
                    if (line.trim().isEmpty()) continue;
                    String[] tempArray = line.split(Pattern.quote("|"));
                    LocalDate date = LocalDate.parse(tempArray[0]);
                    LocalTime timeTemp = LocalTime.parse(tempArray[1]);
                    String Description = tempArray[2];
                    String Vendor = tempArray[3];
                    double amount = Double.parseDouble(tempArray[4]);

                    if(!date.isBefore(startMtD) && (!date.isAfter(currentDate))) {
                        System.out.println(line);

                    }
                }

            }  catch (FileNotFoundException e) {
                System.out.println("File not found");

            } catch (IOException e) {
                System.out.println("Something went wrong");
            }

    }
    public static void yearToDate(Scanner ukey) {
        System.out.println("What is the year?: ");
        int year = ukey.nextInt();
        System.out.println("What is the month(1-12)?: ");
        int month = ukey.nextInt();

        LocalDate currentDate = LocalDate.now();
        LocalDate startYtM = currentDate.withYear(year).withMonth(month);



        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Transactions.csv"));

            String line;

            while ((line = br.readLine())!= null) {
                if (line.trim().isEmpty()) continue;
                String[] tempArray = line.split(Pattern.quote("|"));
                LocalDate date = LocalDate.parse(tempArray[0]);
                LocalTime timeTemp = LocalTime.parse(tempArray[1]);
                String Description = tempArray[2];
                String Vendor = tempArray[3];
                double amount = Double.parseDouble(tempArray[4]);

                if(!date.isBefore(startYtM) && (!date.isAfter(currentDate))) {
                    System.out.println(line);

                }
            }

        }  catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (IOException e) {
            System.out.println("Something went wrong");
        }


    }

    public static void vendorSearch() {

    }

    public static void waitAndGo(Scanner ukey, String message) {
        System.out.println(message + "(press ENTER to continue");
        ukey.nextLine();
        formattedSpace();
    }

    public static void formattedSpace() {
        System.out.println("\n\n\n\n");
    }
}
