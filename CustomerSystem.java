    package Luhn;

    import java.io.FileNotFoundException;

// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray




    import java.util.Scanner;
    // More packages may be imported in the space below

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
            
            /* The following is a test to make sure that the credit card validation works as planned. */
            String creditCard = validateCreditCard(reader);
            System.out.println(creditCard);
        }
        else if (userInput.equals(generateCustomerOption)) {
            // Only the line below may be editted based on the parameter list and how you design the method return
            generateCustomerDataFile();
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
        String firstName; // user enters first name
        System.out.println("Please enter your first name: ");
        
        firstName = reader.nextLine();
        
        // seperating strings
        
        String lastName; // user enters last name
        System.out.println("Please enter your last name: ");
        
        lastName = reader.nextLine();
        
        // seperating strings
        
        String city; // user enters their city
        System.out.println("Please enter which city you live in: ");
        
        city = reader.nextLine();
        
        // seperating strings
        
        String postalCode; // user enters their postal code
        System.out.println("Please enter your postal code: ");
        
        postalCode = reader.nextLine();
        
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("City: " + city);
        System.out.println("Postal code: " + postalCode);
        System.out.println();
        
        //creating the assigned value
        String file = "customerData.csv";
        String line = "";
        String lastValue = "";
        String seperator = ",";
        int lastNumber = 0;
        String assignedValue;
        
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(file));
            while((line = csvReader.readLine()) !=null){
                lastValue = "";
                for(int i = 0; seperator.equals(line.substring(i,(i+1)))==false;i++){
                    lastValue = lastValue + line.substring(i,+1);
                }
            }
            lastNumber = Integer.parseInt(lastValue); 
            assignedValue = lastNumber + 1; 
            assignedValue = Integer.toString(lastNumber + 1);
        }
        catch(FileNotFoundException e){
            System.out.println("file not found");
        }
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void validatePostalCode(){
    }
    
    /*
    * This method will validate credit card number: 
    * 1. Must be atleast 9 digits in length
    * 2. The digits must pass the Luhn algorithm
    */
    public static String validateCreditCard(Scanner reader){
        String creditCardNumber;
        
        Boolean valid;
        
        System.out.println("Please enter your credit card number (DIGITS ONLY. DON'T ENTER SPACES): "); //user enters credit card number
        
        do {
        creditCardNumber = reader.nextLine();
        valid = validateCreditCard(creditCardNumber);
        
        } while(!valid); 
        System.out.println("This card is valid"); //the credit card number is valid
        return creditCardNumber;
    }
    
    private static Boolean validateCreditCard(String creditCardNumber) {
        // Check if it has at least 9 digits
        if (creditCardNumber.length() < 9 ){
        System.out.print("ERROR: There is less than 9 numbers, it is invalid. Please try again: "); //user tried to trick the program
        return false;
        }
        
        // The digits must pass the Luhn algorithm.
        // 1. reverse the order of the digits 
        String rev = "";
        for (int i=0; i < creditCardNumber.length(); i++) {
        char c = creditCardNumber.charAt(i);
        rev = c + rev;
        }
        // 2. Perform a partial sum of the odd digits 
        int sum1 = 0; 
        for (int i=0; i < rev.length(); i+=2) {
        int num = rev.charAt(i); 
        
        if (num < '0' || num > '9') {
            System.out.print("ERROR: " + rev.charAt(i) + " is not a digit. Please re-enter: "); //user tried to trick the program
            return false;
        }
        num -= '0'; // -'0' is to convert from ascii code
        sum1 = sum1 +  num;
        }
        // 3. Take the second, fourth ... and every other even digits in the reversed digits
        // a. Multiply each digit by two and sum the digits, if the answer is greater than 9 then 
        //    add the 2 digits to form partial sums for the even digits
        int sum2 = 0;
        for (int i=1; i < rev.length(); i+=2) {
        int num = rev.charAt(i);  
        
        if (num < '0' || num > '9') {
            System.out.println("ERROR: " + rev.charAt(i) + " is not a digit. Please re-enter"); //user tried to trick the program
            return false;
        }
        num -= '0'; // -'0' is to convert from ascii code
        
        int dbl = num*2;
        if (dbl > 9) {
            dbl -=9;
        }
        // 3.b) Sum the partial sums of the even digits to form sum2
        sum2 = sum2 + dbl;
        }
        
        // 4. If sum1 + sum2 ends in zero then the original number is valid, otherwise it is invalid.
        int total = sum1 + sum2;
        if (total % 10 == 0) {
        return true;
        }
        else {
        System.out.print("ERROR: The credit card is invalid, didn't pass Luhn algorithm. Please try again: "); //user has to re-enter number
        return false;
        }
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