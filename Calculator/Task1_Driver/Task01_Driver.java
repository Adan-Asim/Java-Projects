import Calculator.addition.*;
import Calculator.substract.*;
import Calculator.multiply.*;
import Calculator.division.*;
import java.util.*;

class Task01_Driver
{
	public static void main(String args[])
	{
	 int choice = 0;
	 Scanner input = new Scanner(System.in);
	 float n1,n2;	 

	 System.out.println("\nWelcome To Calculator");
	 try
	 {
	       while(choice != 5)
	      {
		System.out.println("\n1 - Addition ");
		System.out.println("2 - Substraction");
		System.out.println("3 - Multiplication");
		System.out.println("4 - Division");
		System.out.println("5 - Exit");
		System.out.println("\nEnter your Choice");
		choice = input.nextInt();

		if(choice == 5)
		{
			System.out.println("\nThanks for using");	
			System.exit(1);
		}

		System.out.print("\nEnter First value(floating point type): ");
		n1 = input.nextFloat();
		System.out.print("Enter Second value(floating point type): ");
		n2 = input.nextFloat();
	
		if(choice == 1)
			System.out.println("Sum: " + Addition.addition(n1, n2));
		else if(choice == 2)
			System.out.println("Substraction: " + Substraction.substraction(n1, n2));
		else if(choice == 3)
			System.out.println("Multiplication: " + Multiply.multiply(n1, n2));
		else if(choice == 4)
			System.out.println("Division: " + Division.division(n1, n2));
		else
			System.out.println("\nWrong Choice of Operation");
	       }
	 }
	 catch(Exception e)
   	 {
		System.out.println("\nError: " + e.toString() + "Occured");
	 }
	}
}