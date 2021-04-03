package Luhn;

// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray


import java.util.Scanner;
// More packages may be imported in the space below

import java.io.*;

class CustomerSystem{
    public static void main(String[] args){
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below


        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code
                
                enterCustomerInfo();

                String postalCode = validatePostalCode("t00dis", reader);

                System.out.println(postalCode);
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile();
            }
            else if(userInput.equals(exitCondition)){
                //Conditional that makes sure that when selecting 9, the last else statement isn't called
                //else if this conditional is met, skip the last else statement.
                System.out.println("Thank you for your services, the program will now terminate.");
            }
            else{
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition));         // Exits once the user types 
        
        reader.close();
        System.out.println("Program Terminated");
    }
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("3. Report on total Sales (Not done in this part)\n")
        .concat("4. Check for fraud in sales data (Not done in this part)\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void enterCustomerInfo() {
    }
    /*
    * This is the method to check if the postal code is validated or not
    * If not validated, it will ask for another proper postal code

    * postalCode is the string value of the postal code inputted by the user
    */
    public static String validatePostalCode(String postalCode, Scanner reader){
        //The conditonals for the postal code is that it must be at least 3 characters and must be in the postal_codes.csv
        
        //Find variables
        int length = postalCode.length();

        String file = "postal_codes.csv";
        String line = "";

        boolean works = false;
        
        
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(file));

            while((line = csvReader.readLine()) != null){
                
                if(length > 2 && (line.substring(0,3).equals((postalCode.substring(0,3)).toUpperCase()))){
                    //The user has inputted a correct postal code.
                    works = true;
                }
            

            }

            if(works == false){
                System.out.print("Invalid Postal Code. Please input another postal code: ");
        
                postalCode = reader.nextLine();
    
                //Loop this method until a proper postal code is given.
                postalCode = validatePostalCode(postalCode, reader);

            }

            csvReader.close();

        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch(IOException e){
            System.out.println("IOException error");
        }


        return postalCode;
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void validateCreditCard(){
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void generateCustomerDataFile(){
    }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/
}