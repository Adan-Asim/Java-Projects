import java.util.*;
import java.io.*;

class BasicData
{
	int id;
	String name;
	String password;

	BasicData(int i, String n, String p)
	{
		id = i;
		name = n;
		password = p;
	}
	
	public int getID()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setID(int ID)
	{
		id = ID;
	}
	
	public void setName(String Name)
	{
		name = Name;
	}
	
	public void setPassword(String Password)
	{
		password = Password;
	}
	
	public void print()
	{
		System.out.println("\nID: " + id );
		System.out.println("Name: " + name );
		System.out.println("Password: " + password );	
	}
}	
	

class EventManagementSystem
{
	static ArrayList<BasicData> Admin;
	static ArrayList<BasicData> Client;

	EventManagementSystem()	
	{
	 try{
		Admin = new ArrayList<BasicData>();
		Client = new ArrayList<BasicData>();

		BufferedReader in_Admin = new BufferedReader (new FileReader("admin.txt"));
		BufferedReader in_Client = new BufferedReader (new FileReader("client.txt"));
	
		String s;
		String id;
		String name;
		String password;
	
		while((s = in_Admin.readLine()) != null)
		{  
           		id = s.substring(3,s.indexOf("Name") - 2);
			name = s.substring(s.indexOf("Name") + 5, s.indexOf("Password") - 2);
			password = s.substring(s.indexOf("Password") + 9, s.length());

			BasicData obj = new BasicData(Integer.parseInt(id), name, password);
			Admin.add(obj);
		}

		while((s = in_Client.readLine()) != null)
		{ 
           		id = s.substring(3,s.indexOf("Name") - 2);
			name = s.substring(s.indexOf("Name") + 5, s.indexOf("Password") - 2);
			password = s.substring(s.indexOf("Password") + 9, s.length());

			BasicData obj = new BasicData(Integer.parseInt(id), name, password);
			Client.add(obj);
		}
		
		in_Admin.close();
		in_Client.close();
	    }
	    catch(Exception e)
	    {
		System.out.println(e);	
	    }	
	}


	public boolean AdminLogin()
	{
		Scanner in = new Scanner(System.in);
		String name;
		String password;

		System.out.print("\nEnter Admin's Name: ");
		name = in.nextLine();
		System.out.print("Enter Admin's Password: ");
		password = in.nextLine();

		for(int i = 0; i<Admin.size();i++)
		{
			if(Admin.get(i).getName().equals(name) && Admin.get(i).getPassword().equals(password))
				return true;
		}
		return false;
	}

	
	public boolean ClientLogin()
	{
		Scanner in = new Scanner(System.in);
		String name;
		String password;

		System.out.print("\nEnter Client's Name: ");
		name = in.nextLine();
		System.out.print("Enter Client's Password: ");
		password = in.next();

		for(int i = 0; i<Client.size();i++)
		{
			if(Client.get(i).getName().equals(name) && Client.get(i).getPassword().equals(password))
				return true;
		}
		return false;
	}

	public void showClientEvents()
	{
	 try{
		BufferedReader in = new BufferedReader (new FileReader("event.txt"));
		String s;
		System.out.println("\nList of Events: ");
	
		while ((s = in.readLine()) != null)
			System.out.println("o "  + s);
	     }
	 catch(Exception e)
	 { }
	}	


	public boolean viewClientDetails()
	{
		System.out.print("\nDetails of All clients:.");
		for(int i = 0; i<Client.size();i++)
				Client.get(i).print();
		if(Client.size() > 0)
			return true;
		return false;
	}

	public boolean searchClientDetails()
	{
		System.out.print("Enter id of the specific client you want to search details of: ");
		Scanner in = new Scanner(System.in);
		int id = in.nextInt();
		
		for(int i = 0; i<Client.size();i++)
		{
			if(Client.get(i).getID() == id)
			{
				Client.get(i).print();
				return true;
			}
		}
		return false;
		
	}

	public boolean removeClient()
	{	
		System.out.print("Enter id of client you want to remove: ");
		Scanner in = new Scanner(System.in);
		int id = in.nextInt();
		
		for(int i = 0; i<Client.size();i++)
		{
			if(Client.get(i).getID() == id)
			{
				Client.remove(i);
				rewriteFile();
				return true;
			}
		}
		return false;
	}

	private static void rewriteFile()
	{
	 try{
		BufferedWriter out_Admin = new BufferedWriter (new FileWriter("admin.txt"));
		BufferedWriter out_Client = new BufferedWriter (new FileWriter("client.txt"));
		
		for(int i = 0; i < Admin.size();i++)
		{
			out_Admin.write("Id:" + Admin.get(i).getID() + ", ");
			out_Admin.write("Name:" + Admin.get(i).getName() + ", ");
			out_Admin.write("Password:" + Admin.get(i).getPassword());
			out_Admin.write("\n");
		}

		for(int i = 0; i < Client.size();i++)
		{
			out_Client.write("Id:" + Client.get(i).getID() + ", ");
			out_Client.write("Name:" + Client.get(i).getName() + ", ");
			out_Client.write("Password:" + Client.get(i).getPassword());
			out_Client.write("\n");
		}
		
		out_Admin.close();
		out_Client.close();

	     }
	 catch(Exception e)
	 { }
	}		
}	


class Task01
{
	public static void main(String args[])
	{
	 try{
		Scanner in = new Scanner(System.in);
		EventManagementSystem ems = new EventManagementSystem();
		int choice = 0;
		
		while(choice !=3)
		{
			System.out.println("\n\n********************************* WELCOME TO THE EVENT MANAGEMENT SYSTEM *********************************** ");
			System.out.println("\n\nDo you want to proceed as an Admin or a Client? ");
			System.out.println("\nChoose wisely: ");
			System.out.println("1 - Admin");	
			System.out.println("2 - Client");
			System.out.println("3 - Exit");
			System.out.print("\nEnter your choice: ");
		
		
			choice = in.nextInt();
			if(choice == 1)
			{
				System.out.println("\nNOTE: Check respective file for getting credentials");
				if(ems.AdminLogin())
				{
					int c = 0;
					while (c != 4)
					{
						System.out.print("\n1 - View details of all clients");
						System.out.print("\n2 - Search details of a specific client");
						System.out.print("\n3 - Delete a client");
						System.out.print("\n4 - Logout");
						System.out.print("\nEnter the function you want to perform: ");
						c = in.nextInt();

						if(c == 1)
						{
							if(!ems.viewClientDetails())
								System.out.println("\nNo client exists");
						}
						else if(c == 2)
						{
							if(!ems.searchClientDetails())
								System.out.println("\nRecord not Found");
						}
						else if(c == 3)
						{	
							if(ems.removeClient())
								System.out.println("\nClient removed successfully");
							else
								System.out.println("\nClient record not Found");
						}
						else if(c == 4);
						else
							System.out.print("\nWrong functionality choosen");
					}
				}
				else
					System.out.println("\nUnable to authenticate, try again");
	
			}
			else if(choice == 2)
			{
				System.out.println("\nNOTE: Check respective file for getting credentials");
				if(ems.ClientLogin())
				{
					ems.showClientEvents();
					System.out.print("\nPress any key and then hit enter to log out: ");
					String key = in.next();
				}
				else
					System.out.println("\nUnable to authenticate, client deleted or perhaps it doesn't exist, but try again.");
				
			}
			else if(choice == 3)
			{
				System.out.println("\nThanks for using, Bye!");
				break;
			}
			else	
 				System.out.println("\nWrong choice, Choose again.");
		}
	    }
	 catch(Exception e)
	 {
		System.out.println("\n*Sorry we encountered an unusual error, please try again*");
	 }	
	}
}	