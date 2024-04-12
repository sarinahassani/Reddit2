package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static String MEmail;
    static String Username;
    static int Hashpass;
    //static ArrayList<Account> accountmenu = new ArrayList<>();
    static ArrayList<Subreddit> Submenu = new ArrayList<>();

    public static void main(String[] args) {
        mainmenu();
        System.out.println("Hello world!");
    }

    public static void mainmenu() {
        //AccountManagement manage = new AccountManagement();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1-Sign up");
        System.out.println("2-Log in");
        int n = scanner.nextInt();
        if (n == 1) {
            SignupEmail();
        }
        if (n == 2) {
            Login();
        }
    }

    public static void SignupEmail() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Email Address:");
        MEmail = scanner.nextLine();
        Pattern Email = Pattern.compile("^\\w+([.-]?\\w+)*@[a-zA-Z\\\\d]*\\.[a-zA-Z]{3}$");
        Matcher matcher = Email.matcher(MEmail);
        while (!matcher.find()) {
            System.out.println("Your Email Address is wrong");
            System.out.println("Email Address:");
            MEmail = scanner.nextLine();
            matcher = Email.matcher(MEmail);
        }
        SignupUsername();

    }

    public static void SignupUsername() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Username:");
            Username = scanner.nextLine();
            int check = 0;
            for (int i = 0; i < Account.accountmenu.size(); i++) {
                if (Account.accountmenu.get(i).getUsername().equals(Username)) {
                    System.out.println("The username is invalid");
                    check++;
                    break;
                }
            }
            if (check == 0) {
                SignupPassword();
                break;
            } else {
                SignupUsername();
            }
        }

    }

    public static void SignupPassword() {
        System.out.println("Password:");
        Scanner scanner = new Scanner(System.in);
        String Password = scanner.nextLine();
        Hashpass = Password.hashCode();
        //System.out.println(Hashpass);
        creatAccount();

    }

    public static void creatAccount() {
        Account account = new Account(MEmail, Username, Hashpass, 0);
        Account.accountmenu.add(account);
        //System.out.println(Hashpass);
        account.menu();
    }

    public static void Login() {
        System.out.println("Username:");
        Scanner scanner = new Scanner(System.in);
        int check = 0;
        Username = scanner.nextLine();
        for (int i = 0; i < Account.accountmenu.size(); i++) {
            if (Account.accountmenu.get(i).getUsername().equals(Username)) {
                check++;
                while (true) {
                    System.out.println("Password:");
                    String Password = scanner.nextLine();
                    Hashpass = Password.hashCode();
                    if (Account.accountmenu.get(i).getHashpass() == Hashpass) {
                        Account.accountmenu.get(i).menu();
                        break;
                    } else {
                        System.out.println("The password is wrong!");
                    }
                }
                break;
            }
        }
        if (check == 0) {
            System.out.println("The account not found!");
            mainmenu();
        }
    }
}
