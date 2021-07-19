import java.util.*;
import java.io.*;

class Account implements Serializable
{
	String name;
	int account_number;
	String pin;
	double Amount;

	Account()
	{
		name = null;
		account_number = 0;
		pin = null;
		Amount = 0;	
	}
	
	Account(String n, int acc, String pi, double amount)
	{
		name = n;
		account_number = acc;
		pin = pi;
		Amount = 1000 + amount;	
	}

	public void setName(String n)
	{
		name = n;
	}

	public void setAccountNumber(int n)
	{
		account_number = n;
	}

	public void setPIN(String p)
	{
		pin = p;
	}

	public void setAmount(double a)
	{
		Amount = a;
	}

	public String getName()
	{
		return name;
	}

	public int getAccountNumber()
	{
		return account_number;
	}

	public String getPIN()
	{
		return pin;
	}

	public double getAmount()
	{
		return Amount;
	}

}

class Bank
{
	ArrayList<Account> AL = new ArrayList<Account>(); 
	
	public void addNewRecord()
	{
		Scanner input = new Scanner(System.in);

		System.out.print("\nEnter name of Account Holder: ");
		String n = input.nextLine();
		System.out.print("Enter an 8 digit Account Number (contact manager for its allocation): ");
		int a = input.nextInt();
		System.out.print("Enter PIN for Account Holder: ");
		String p = input.next();
		System.out.print("Default amount of 1000 is already added to the account, to add more money, write that amount else enter zero: ");
		double am = input.nextDouble();
		
		Account ac = new Account(n, a, p, am);
		AL.add(ac);
		return;
	}
	
	public void transfer()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("\nEnter sender's 8 digit account number: ");
		int s_acc = input.nextInt();
		System.out.print("Enter Sender's pin code: ");
		String s_pin = input.next();

		int sender_index = -1;
		for(int i = 0; i< AL.size(); i++)
		{
			if (AL.get(i).getAccountNumber() == s_acc && AL.get(i).getPIN().equals(s_pin))
				sender_index = i;
		}

		if(sender_index == -1)
		{
			System.out.println("\n Account not Found");
			return;
		}	

		System.out.print("\nEnter receiver's 8 digit account number: ");
		int r_acc = input.nextInt();

		int receiver_index = -1;
		for(int i = 0; i< AL.size(); i++)
		{
			if (AL.get(i).getAccountNumber() == r_acc)
				receiver_index = i;
		}
		
		if(receiver_index == -1)
		{
			System.out.println("\n Receiver's account not Found");
			return;
		}

		System.out.print("\nAmount to be transferred: ");
		double amount = input.nextDouble();
		if(AL.get(sender_index).getAmount() >= amount)
		{
			AL.get(receiver_index).setAmount(AL.get(receiver_index).getAmount() + amount );
			AL.get(sender_index).setAmount(AL.get(sender_index).getAmount() - amount );
			return;
		}
		else 
		{
			System.out.println("\nSender doesnot have this much balance in his account");
			return;
		}	
	}	

	public void withdraw()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("\nEnter User's 8 digit account number: ");
		int p_acc = input.nextInt();
		System.out.print("Enter User's pin code: ");
		String p_pin = input.next();

		int person_index = -1;
		for(int i = 0; i< AL.size(); i++)
		{

			if ((AL.get(i).getAccountNumber() == p_acc) && (AL.get(i).getPIN().equals(p_pin)))
			{
				person_index = i;
			}
		}

		if(person_index == -1)
		{
			System.out.println("\n Account not Found");
			return;
		}	

		System.out.print("\nAmount to be Withdrawn: ");
		double amount = input.nextDouble();
		if(AL.get(person_index).getAmount() >= amount)
		{
			AL.get(person_index).setAmount(AL.get(person_index).getAmount() - amount );
			return;
		}
		else 
		{
			System.out.println("\nThis person doesnot have this much balance in his account");
			return;
		}	
	}

	public void print()
	{
		for(int i = 0; i<AL.size(); i++)
		{
			System.out.println("\nName: " + AL.get(i).getName());
			System.out.println("Account Number: " + AL.get(i).getAccountNumber());
			System.out.println("Balance: " + AL.get(i).getAmount() + "\n");
		}
	}

	public void load()
	{
	 try{
		FileInputStream fis = new FileInputStream("BankRecord.txt");
		ObjectInputStream in = new ObjectInputStream(fis);
		while(true)
		{
			Account temp = (Account) in.readObject(); 
			if(temp == null)
				break;
			AL.add(temp);
		}
		fis.close();
	     }
	 catch(Exception e)
	 {
	 }
	}
	
	public void save()
	{
	 try{
		FileOutputStream fos = new FileOutputStream("BankRecord.txt");
		ObjectOutputStream out = new ObjectOutputStream(fos);
		for(int i = 0; i<AL.size(); i++)
			out.writeObject(AL.get(i));
		fos.close();
	    }
	 catch(Exception e)
	 {
		System.out.println("\nError Saving Data to File");
	 }	
	}
}


class Task1_Driver
{
	public static void main(String args[])
	{
	 
		Bank obj = new Bank();
		obj.load();
	try{	Scanner input = new Scanner(System.in);
		System.out.println("\n**********************Welcome to our Bank*************************");
		int choice = 0;
		while(choice != 5)
		{
			System.out.println("\n1 - Create new Account");
			System.out.println("2 - Transfer money from an existing account to another existing account");
			System.out.println("3 - Withdraw money from existing account");
			System.out.println("4 - Print all exisitng accounts");
			System.out.println("5 - Exit");
			System.out.print("Enter your choice: ");
			choice = input.nextInt();

			if(choice == 1)
			{
				obj.addNewRecord();
				System.out.println("\nAccount Created Successfully");
			}
			else if(choice == 2)
			{
				obj.transfer();
			}
			else if(choice == 3)
			{
				obj.withdraw();
			}
			else if(choice == 4)
				obj.print();
			else if(choice == 5)
			{
				obj.save();
				System.out.println("\nData saved to File \"BankRecord .txt\"");
				System.exit(1);
			}
			else
				System.out.println("\nWrong Input");
		}
	       }
	catch(Exception e)
	{
		System.out.println("\nWe triggered an Error");
	}
	
	finally
	{
		obj.save();
	}
       }
}




