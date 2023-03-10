import java.util.*;
class Bank
{
    String Name;
    long Account_Number;
    double Available_Balance;
    int Pin;
    Bank(String name,long Ac,double am,int P)
    {
        this.Name=name;
        this.Account_Number=Ac;
        this.Available_Balance=am;
        this.Pin=P;
    }
}
public class rrBank
{
    public static void main(String[] args)
    {
        String admin="admin";
        System.out.println("\nAdmin Password\t\t: "+admin);
        List<Bank>banks=new ArrayList<>();
        List<Long>accountNumber=new ArrayList<>();
        List<Integer>createPassword=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        boolean boo=true;
        while (boo)
        {
            System.out.println("\n1 Create Account \n2 Account Info\n3 Deposit\n4 Withdraw\n5 Admin \n6 Exit ");
            int choice= scanner.nextInt();
            scanner=new Scanner(System.in);


            switch (choice) {
                case 1:
                    createAccount(banks, scanner, accountNumber, createPassword);
                    break;
                case 2:
                    accountInfo(banks, scanner);
                    break;
                case 3:
                    deposit(banks, scanner);
                    break;
                case 4:
                    withdraw(banks, scanner);
                    break;
                case 5:
                    admin(banks,scanner,admin);
                    break;
                case 6:
                    boo=false;
                    break;
                default:
                    System.out.println("Valid inputs are like 1 to 6 ");
            }
        }
    }
    public static void createAccount(List<Bank>banks,Scanner scanner,List<Long>accountNumber,List<Integer>createPassword)
    {
        System.out.print("Name \t\t\t\t:");
        String name = scanner.nextLine();
        int amount=0;
        int count=1;
        boolean b=true;
        while (b)
        {
            System.out.print("Deposit \n(Amount Min Rs 500) : ");
            amount = scanner.nextInt();
            if (amount>=500)
            {
                b=false;
            }
            else if(count>3)
            {
                System.out.println("---------------------------You crossed Maximum Attempt----------------------------\n");
                b=false;
                return;
            }
            count++;
        }

        long accountNo = getAccountNo(accountNumber);
        int password = getPassword(createPassword, scanner);
        banks.add(new Bank(name, accountNo, amount, password));
        System.out.println("Account Holder Name : " + name);
        System.out.println("Account Number\t\t: " + accountNo);
        System.out.println("Password\t\t\t: " + password);
        System.out.println("Balance\t\t\t\t: " + amount + "\n");
        System.out.println("*********************** Account Created Successfully **************************");

    }
    public static void admin(List<Bank>banks,Scanner s,String admin)
    {

        System.out.println("Enter Admin Password");
        String password= s.nextLine();
        if(admin.equalsIgnoreCase(password))
        {
            if (banks.size() == 0)
            {
                System.out.println("*********************** Users Data is Empty **************************\n");
                return;
            }
            for (Bank b:banks)
            {
                System.out.println("Account Holder Name : "+b.Name);
                System.out.println("Account Number\t\t: "+b.Account_Number);
                System.out.println("Password\t\t\t: "+b.Pin);
                System.out.println("Balance\t\t\t\t: "+b.Available_Balance+"\n");
            }
        }
        else
        {
            System.out.println("---------------------------Enter Valid Admin Password----------------------------\n");
        }

    }
    public static void withdraw(List<Bank>banks,Scanner scanner)
    {
        if(banks.isEmpty())
        {
            System.out.println("---------------------------Create Account----------------------------\n");
            return;
        }
        System.out.println("Enter Account Number");
        long ac=scanner.nextLong();
        System.out.println("Enter Password");
        int password= scanner.nextInt();
        for (Bank b:banks)
        {
            if(b.Account_Number==ac && b.Pin==password )
            {
                boolean boo=true;
                while (boo)
                {
                    System.out.println("Enter Withdraw Amount");
                    int amount=scanner.nextInt();
                    if(b.Available_Balance>=amount)
                    {
                        b.Available_Balance-=amount;
                        System.out.println("Available Balance\t\t\t\t: "+b.Available_Balance);
                        System.out.println("*********************** Please Collect Your Cash **************************\n");
                        return;
                    }
                    else
                    {
                        System.out.println("\nInsufficient Balance");
                        System.out.println("\nCheck Balance 1 Exit 2" );
                        int c=scanner.nextInt();
                        if(c==1)
                        {
                            System.out.println("Available Balance\t\t\t\t: "+b.Available_Balance);
                            System.out.println("Continue to Withdraw 1 Exit 2" );
                            int d=scanner.nextInt();
                            if(d==1)
                            {
                                boo=true;
                            }
                            else
                            {
                                boo=false;
                                return;
                            }
                        }
                        else
                        {
                            boo=false;
                            return;
                        }
                    }
                }
            }
        }

    }
    public static void accountInfo(List<Bank>banks,Scanner scanner)
    {
        if(banks.isEmpty())
        {
            System.out.println("---------------------------Create Account----------------------------\n");
            return;
        }
        System.out.println("Enter Account Number");
        long ac=scanner.nextLong();
        System.out.println("Enter Password");
        int password= scanner.nextInt();
        for (Bank b:banks)
        {
            if(b.Account_Number==ac && b.Pin==password )
            {
                System.out.println("Account Holder Name : "+b.Name);
                System.out.println("Account Number\t\t: "+b.Account_Number);
                System.out.println("Available Balance\t: "+b.Available_Balance);
                return;
            }
        }
        System.out.println("---------------------------Invalid password or Invalid Account Number----------------------------\n");
    }
    public static void deposit(List<Bank>banks,Scanner scanner)
    {
        if(banks.isEmpty())
        {
            System.out.println("---------------------------Create Account----------------------------\n");
            return;
        }
        System.out.println("Enter Account Number");
        long ac=scanner.nextLong();
        System.out.println("Enter Password");
        int password= scanner.nextInt();
        for (Bank b:banks)
        {
            if(b.Account_Number==ac && b.Pin==password )
            {
                System.out.println("Account Holder Name : "+b.Name);
                System.out.println("Account Number\t\t: "+b.Account_Number);
                System.out.println("Confirm 1 No 2");
                int choice=scanner.nextInt();
                if(choice==1)
                {
                    System.out.println("Enter deposit Amount");
                    int amount=scanner.nextInt();
                    b.Available_Balance+=amount;
                    System.out.println("*********************** Deposited Successfully **************************\n");
                    return;
                }
                else
                {
                    return;
                }
            }
        }
        System.out.println("---------------------------Invalid password or Invalid Account Number----------------------------\n");
    }
    public static int getPassword( List<Integer>createPassword,Scanner sc)
    {
        boolean b=true;
        String pass;
        int password=0;
        while (b)
        {
            System.out.println("Enter only 4 digits password");
            pass=sc.next();
            int le=pass.length();
            if(le==4)
            {
                password= Integer.parseInt(pass);
                createPassword.add(password);
                b=false;
            }
        }
        return password;
    }
    public static long getAccountNo(List<Long>accountNo)
    {
        ArrayList<Long>rand=new ArrayList<>();
        Random r=new Random();
        long ra=Math.abs(r.nextLong());
        rand.add(ra);
        for (int i = 0; i < rand.size(); i++)
        {
            for (int j = 0; j < accountNo.size() ; j++)
            {
                if((accountNo.get(i)==rand.get(i)) )
                {
                    ra+=1;
                    accountNo.add(ra);
                    rand.remove(i);
                    return ra;
                }
            }
        }
        accountNo.add(ra);
        rand.remove(0);
        return ra;
    }
}
