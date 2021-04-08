package Luhn;

// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray


import java.util.Scanner;
// More packages may be imported in the space below

// Package for input and output of other files. Specifically buffered reader, file reader, and file writer
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
                
                enterCustomerInfo(reader);

                /* The following is a test to make sure that the postal code validation works as planned.
                String postalCode = validatePostalCode("t00dis", reader);

                System.out.println(postalCode);
                */

                /* The following is a test to make sure that the addData method works as planned
                addData("David", "Han", "Thornhill", "L4J7B6", "123456789");
                */
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile(reader);
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
    public static void enterCustomerInfo(Scanner reader) {
      String firstName; //user enters first name
      System.out.println("Please enter your first name: ");

      // nextLine takes information from firstName
      firstName = reader.nextLine();

      // seperating strings

      String lastName; // user enters last name
      System.out.println("Please enter your last name: ");

      // nextLine takes information from lastName
      lastName = reader.nextLine();

      // seperating strings

      String city; //user enters their city
      System.out.println("Please enter which city you live in: ");

      // nextLine takes information from city
      city = reader.nextLine();

      // seperating strings

      String postalCode; //user enters their postal code
      System.out.println("Please enter your postal code: ");

      // nextLine takes information from postalCode 
      postalCode = reader.nextLine();

      //putting the code through the postal code verification, returning the postal code
      //if it was valid, otherwise, keep inputting until a proper input is done
      postalCode = validatePostalCode(postalCode, reader);

      // calling enterCreditCard method to enter and validate credit card
      String creditCardNumber = enterCreditCard(reader);

      //creating the assigned value
      String file = "customerData.csv";  //initializing variables needed for the following code
      String line = "";  
      String lastValue = "0";
      String seperator = ",";
      int lastNumber = 1;  
      String assignedValue = "";

      try {   //a try block to make sure the files we are reading, are actually there 
        BufferedReader csvReader = new BufferedReader(new FileReader(file));
        while((line = csvReader.readLine()) !=null){  //readLine takes information from line
          lastValue = "";
          for(int i = 0; seperator.equals(line.substring(i,(i+1)))==false;i++){
            lastValue = lastValue + line.substring(i,i+1); 
          }
        } 
        if(lastValue.equals("0")){  //if statement for lastValue
          assignedValue = "1";
        }
        else{  //if assignedValue is else, then lastNumber = lastNumber + 1
          lastNumber = Integer.parseInt(lastValue);
          lastNumber = lastNumber + 1;
          assignedValue = Integer.toString(lastNumber); //chaging it back from Integer back to String
        }

        csvReader.close(); //closing csvReader
      }
      catch(FileNotFoundException e){  //error exception
        System.out.println("file not found");
      }
      catch(IOException e){ 
        System.out.println("Input/Output exception");
      }

      addData(assignedValue, firstName, lastName, city, postalCode, creditCardNumber);
    }

    /*
    * This is the method to check if the postal code is validated or not
    * If not validated, it will ask for another proper postal code

    * postalCode is the string value of the postal code inputted by the user
    */
    public static String validatePostalCode(String postalCode, Scanner reader){
        //The conditonals for the postal code is that it must be at least 3 characters and must be in the postal_codes.csv
        
        //The length of the postal code to make sure it's longer than 3 characters
        int length = postalCode.length();
        //The string for the file location
        String file = "postal_codes.csv";
        //The line of the csv file. Will change so it's empty now.
        String line = "";

        //The boolean that determines whether the postal code is valid or not.
        boolean works = false;
        
        //To make sure that the file called is correct and no other errors are made
        try{
            //Reads the csv file one line at a time
            BufferedReader csvReader = new BufferedReader(new FileReader(file));

            //until a line of the csv is empty
            while((line = csvReader.readLine()) != null){
                
                //if the postal code is 3 or more characters long and the first 3 characters are found in the first 3 characters in the line of the csv
                if(length > 2 && (line.substring(0,3).equals((postalCode.substring(0,3)).toUpperCase()))){
                    //The user has inputted a correct postal code.
                    works = true;

                    //Breaks out of the while loop the moment a matching postal code has been found. If not, keep looping through all the lines until
                    //either the end or it does find a matching postal code.
                    break;
                }
            

            }
            //if it's an invalid postal code, input another postal code and validate it again.
            if(works == false){
                System.out.print("Invalid Postal Code. Please input another postal code: ");
        
                postalCode = reader.nextLine();
    
                //Loop this method until a proper postal code is given.
                postalCode = validatePostalCode(postalCode, reader);

            }

            //closes the BufferedReader
            csvReader.close();
        }
        //In case the file inputted is incorrect and doesn't lead anywhere
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
        //In case there's an error when trying to take in an input or output
        catch(IOException e){
            System.out.println("IOException error");
        }

        //Returns the new value of the postal code. The postal code must be all upper case.
        return postalCode.toUpperCase();
    }
    /*
    * This method will validate credit card number:
    * 1. Must be at least 9 digits in length
    * 2. The digits must pass the Luhn algorithm
    */
    public static String enterCreditCard(Scanner reader){
      String creditCardNumber;

      boolean valid;

      System.out.println("Please enter your credit card number (DIGITS ONLY. DON'T ENTER SPACES): "); //user enters credit card number

      do {
        creditCardNumber = reader.nextLine();
        valid = validateCreditCard(creditCardNumber);
      } while(!valid);
      System.out.println("This card is valid"); //the credit card number is valid
      return creditCardNumber;
    }

    private static Boolean validateCreditCard(String creditCardNumber) {
      //Check if it has at least 9 digits
      if (creditCardNumber.length() < 9){
        System.out.print("ERROR: There is less than 9 numbers, it is invalid. Please try again: "); //user tried to trick the program
        return false;
      }

      //The digits must pass the Luhn algorithm.
      //1. reverse the order of the digits
      String rev = "";
      for (int i=0; i < creditCardNumber.length(); i++) {
        char c = creditCardNumber.charAt(i);
        rev = c + rev;
      }
      //2. Perform a partial sum of the odd digits
      int sum1 = 0;
      for (int i=0; i < rev.length(); i+=2) {
        int num = rev.charAt(i);

        if(num < '0' || num > '9') {
          System.out.print("ERROR: " + rev.charAt(i) + " is not a digit. Please re-enter: "); //user tried to trick the program
          return false;
        }
        num -= '0'; 
        sum1 = sum1 + num;
      }
      //3. Take the second, fourth ... and every other even digits in the reversed digits
      //a. Multiply each digit by two and sum the digits, if the answer is greater than 9 then
      // add the 2 digits to form partial sums for the even digits
      int sum2 = 0;
      for (int i=1; i < rev.length(); i+=2) {
        int num = rev.charAt(i);

        if(num < '0' || num > '9') {
          System.out.println("ERROR: " + rev.charAt(i) + " is not a digit. Please re-enter"); //user tried to trick the program
          return false;
        }
        num -= '0';

        int db1 = num*2;
        if(db1 > 9) {
          db1 -=9;
        }
        //3.b) Sum the partial sums of the even digits to form sum2
        sum2 = sum2 + db1;
      }

      //4. If sum1 + sum2 ends in zero then the original number is valid, otherwise it is invalid.
      int total = sum1 + sum2;
      if(total % 10 == 0) {
        return true;
      }
      else{
        System.out.print("ERROR: The credit card is invalid, didn't pass Luhn algorithm. Please try again: "); //user has to re-enter the number
        return false;
      }
    }

    /*
    * This is creating another customer data file that the user can name
    * This will not work if the customer data file name is already used and created.
    */
    public static void generateCustomerDataFile(Scanner reader){

        //Prompt the user to add in the new file name
        System.out.print("What do you want to name your file? Don't forget to add .csv at the very end! ");

        String newFileName = reader.nextLine();

        //Try block to make sure that the files are actual files in the directory
        try{
            //Creates a file variable using the new file name
            File newFile = new File(newFileName);

            //Will be used when reading each line of the customerData.csv
            String line = "";

            //If the new file name isn't already used in the directory and can be created as a file
            //create the file and write data
            if(newFile.createNewFile()) {

                //Buffer read each line of customerData.csv
                BufferedReader csvReader = new BufferedReader(new FileReader("customerData.csv"));
                //An instance of PrintWriter for the new file that's created
                PrintWriter csvWriter = new PrintWriter(newFile);

                //While the buffered line in customerData.csv isn't blank/null, print the line into the new file
                while((line = csvReader.readLine()) != null){
                    csvWriter.append(line + "\n");
                }
                //Confirmation the file has been created to the user.
                System.out.println("File " + newFileName + " has been created and has the customer data.");

                //Closing instances of reader and writer
                csvReader.close();
                csvWriter.close();
            }
            //else, don't create new file and exit out of the method.
            else{
                System.out.println("File name already exists. File was not created.");
            }
            
        }
        //If the file is not found, print the following
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        //If there's an error on the input/output method
        catch(IOException e){
            System.out.println("IO Exception");
        }
        
    }

    /*
    * This method is to push all the data given by the user and transfer it into the customerData.csv.
    * This is after the verification of the postalcode and creditCard in the enterCustomerInfo method
    */
    public static void addData(String assignedValue, String firstName, String lastName, String city, String postalCode, String creditCard){

      //Creating a variable that has the name of the csv file
      File file = new File("customerData.csv");

      //A try/catch to make sure that the file name is a proper directory
      try{

        //Creates a file writer instance of the customerData.csv file
        FileWriter csvWriter = new FileWriter(file, true);

        //append the following information into the file writer instance
        
        csvWriter.append(assignedValue + "," + firstName + "," + lastName + "," + city + "," + postalCode + "," + creditCard + "\n");
  
        //close the file writer
        csvWriter.close();
      }
      //If the file is not a proper file, print the following.
      catch(FileNotFoundException e) {
        System.out.println("File not found");
      }
      //If there's an error with the input/output method, print the following
      catch(IOException e) {
        System.out.println("Input/Output Exception");
      }
      
    }
}