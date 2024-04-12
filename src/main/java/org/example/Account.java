package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Main {

    String email;
    String username;
    int hashpass;
    int num;
    ArrayList<Subreddit> MySubs = new ArrayList<>();
    ArrayList<Subreddit> SubsHead = new ArrayList<>();
    ArrayList<Subreddit> SubsAdmin = new ArrayList<>();
    static ArrayList<Account> accountmenu = new ArrayList<>();
    ArrayList<Post> MyPosts = new ArrayList<>();
    ArrayList<Post> SavedPosts = new ArrayList<>();

    public Account (String email, String username, int hashpass, int num) {
        accountmenu.add(this);
        this.email = email;
        this.username = username;
        this.hashpass = hashpass;
        this.num = num;
    }
    public String getemail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public int getHashpass() {
        return hashpass;
    }
    public void menu() {
        System.out.println("  ");
        System.out.println("Welcom to your account!");
        System.out.println("  ");
        System.out.println("1-Creat new subreddit");
        System.out.println("2-Search");
        System.out.println("3-Login to subreddit");
        System.out.println("4-Profile");
        System.out.println("5-Setting");
        System.out.println("6-Log out");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int n = scanner.nextInt();
            if (n==1) {
                creatSub();
                break;
            }
            else if (n==2) {
                Search();
                break;
            }
            else if (n==3) {
                LoginSub();
                break;
            }
            else if (n==4) {
                Profile();
                break;
            }
            else if (n==5) {
                setting();
                break;
            }
            else if (n==6) {
                mainmenu();
                break;
            }
            else if (n==7) {
                check();
                break;
            }
            else {
                System.out.println("Enter correct input");
            }
        }
    }
    public void check() {
        System.out.println(email);
        System.out.println(username);
        System.out.println(hashpass);
        //System.out.println(getAccountmenu());
    }
    public void creatSub() {
        System.out.println("Name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int check=0;
        if (Submenu.size()!=0) {
            for (Subreddit subreddit : Subreddit.getSubmenu()) {
                if (subreddit.getName().equals(name)) {
                    System.out.println("This subreddit has already been created.");
                    System.out.println("  ");
                    check++;
                    break;
                }
            }
        }
        if (check==0) {
            Subreddit subreddit = new Subreddit(name);
            Submenu.add(subreddit);
            SubsHead.add(subreddit);
            MySubs.add(subreddit);
            System.out.println("Your subreddit has been successfully created");
            System.out.println("  ");
            while (true) {
                System.out.println("1-Back");
                int m = scanner.nextInt();
                if (m == 1) {
                    menu();
                    break;
                }
            }
        }
        else {
            while (true) {
                System.out.println("1-Back");
                int m = scanner.nextInt();
                if (m == 1) {
                    menu();
                    break;
                }
            }
        }
    }
    public void Search() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What are you looking for?");
        System.out.println("1-Subreddit");
        System.out.println("2-User");
        System.out.println("3-Back");
        while (true) {
            int n = scanner.nextInt();
            if (n==1) {
                searchsub();
                break;
            }
            else if (n==2) {
                searchuser();
                break;
            }
            else if (n==3) {
                menu();
                break;
            }
            else {
                System.out.println("Enter correct input");
            }
        }
    }
    public void searchsub() {
        System.out.println("Name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int check=0;
        int check1=0;
        if (Submenu.size()!=0) {
            for (int i=0; i<Submenu.size(); i++) {
                if (Submenu.get(i).getName().equals(name)) {
                    check++;
                    System.out.println("  ");
                    System.out.println("1-Join");
                    System.out.println("2-Back");
                    int n = scanner.nextInt();
                    if (n == 1) {
                        for (int j = 0; j < MySubs.size(); j++) {
                            if (MySubs.get(j).getName().equals(name)) {
                                check1++;
                                System.out.println("  ");
                                System.out.println("You have already joined this subreddit!");
                                System.out.println("  ");
                                while (true) {
                                    System.out.println("1-Back");
                                    int m = scanner.nextInt();
                                    if (m == 1) {
                                        Search();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        if (check1 == 0) {
                            System.out.println("  ");
                            System.out.println("Your join was successful.");
                            System.out.println("  ");
                            MySubs.add(Submenu.get(i));
                            Submenu.get(i).Members.add(this);
                            while (true) {
                                System.out.println("1-Back");
                                int m = scanner.nextInt();
                                if (m == 1) {
                                    Search();
                                    break;
                                }
                            }
                            break;
                        }
                    }

                    if (n==2) {
                        Search();
                        break;
                    }
                }
            }
        }
            if (check==0) {
                System.out.println("  ");
                System.out.println("The subreddit not found");
                System.out.println("  ");
                Search();
            }
    }
    public void searchuser() {
        System.out.println("Name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int check=0;
        for (int i=0; i<accountmenu.size(); i++) {
            if (accountmenu.get(i).getUsername().equals(name)) {
                check++;
                System.out.println("Subreddits:");
                accountmenu.get(i).subreddits2(accountmenu.get(i));
                System.out.println("  ");
                System.out.println("Posts:");
                accountmenu.get(i).posts2(accountmenu.get(i));
                System.out.println("  ");
                break;
            }
        }
        if (check==0) {
            System.out.println("The user not found!");
        }
        while (true) {
            System.out.println("1-Back");
            int m = scanner.nextInt();
            if (m == 1) {
                Search();
                break;
            }
        }
    }
    public void subreddits() {
        for (int i=0; i<MySubs.size(); i++) {
            System.out.println(MySubs.get(i).getName());
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1-Back");
            int m = scanner.nextInt();
            if (m == 1) {
                Profile();
                break;
            }
        }
    }
    public void subreddits2(Account user) {
        for (int i = 0; i < user.MySubs.size(); i++) {
            System.out.println(user.MySubs.get(i).getName());
        }
    }
    public void posts() {
        for (int i=(MyPosts.size()-1); i>=0; i--) {
            MyPosts.get(i).coutpost();
            System.out.println("  ");
            MyPosts.get(i).Comments();
            System.out.println("  ");
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1-Back");
            int m = scanner.nextInt();
            if (m == 1) {
                Profile();
                break;
            }
        }
    }
    public void posts2(Account user) {
        for (int i=(user.MyPosts.size()-1); i>=0; i--) {
            user.MyPosts.get(i).coutpost();
            System.out.println("  ");
            user.MyPosts.get(i).Comments();
            System.out.println("  ");
        }
    }
    public void LoginSub() {
        System.out.println("Subreddit name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int check=0;
        for (int i=0; i<SubsHead.size(); i++) {
            if (SubsHead.get(i).getName().equals(name)) {
                check++;
                SubsHead.get(i).menu1(this);
                break;
            }
        }
        if (check==0) {
            for (int i=0; i<SubsAdmin.size(); i++) {
                if (SubsAdmin.get(i).getName().equals(name)) {
                    check++;
                    SubsAdmin.get(i).menu2(this);
                    break;
                }
            }
        }
        if (check==0) {
            for (int i=0; i<MySubs.size(); i++) {
                if (MySubs.get(i).getName().equals(name)) {
                    check++;
                    MySubs.get(i).menu3(this);
                    break;
                }
            }
        }
        if (check==0) {
            System.out.println("You are not a member of this subreddit!");
            System.out.println("  ");
            menu();
        }
    }
    public void Profile() {
        System.out.println("  ");
        System.out.println("1-My subreddits");
        System.out.println("2-My posts");
        System.out.println("3-Saved posts");
        System.out.println("4-Back");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int n = scanner.nextInt();
            if (n==1) {
                subreddits();
                break;
            }
            else if (n==2) {
                posts();
                break;
            }
            else if (n==3) {
                SavedPosts();
                break;
            }
            else if (n==4) {
                menu();
                break;
            }
            else {
                System.out.println("Enter correct input");
            }
        }
    }
    public void SavedPosts() {
        for (int i=(SavedPosts.size()-1); i>=0; i--) {
            SavedPosts.get(i).coutpost();
            System.out.println("  ");
            SavedPosts.get(i).Comments();
            System.out.println("  ");
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1-Back");
            int m = scanner.nextInt();
            if (m == 1) {
                Profile();
                break;
            }
        }
    }
    public void setting() {
        System.out.println("1-Changing Email");
        System.out.println("2-Changing Username");
        System.out.println("3-Changing Password");
        System.out.println("4-Back");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int n = scanner.nextInt();
            if (n == 1) {
                changeemail();
                break;
            } else if (n == 2) {
                changeusername();
                break;
            } else if (n == 3) {
                changepassword();
                break;
            } else if (n == 4) {
                menu();
                break;
            }
            else {
                System.out.println("Enter correct input");
            }
        }
    }
    public void changeemail() {
        Scanner scanner = new Scanner(System.in);
        String newemail = null;
        int check = 0;
        while (true) {

            System.out.println("Password:");
            String pass = scanner.nextLine();
            int hash = pass.hashCode();
            if (hash == hashpass) {
                System.out.println("New Email:");
                newemail = scanner.nextLine();
                Pattern Email = Pattern.compile("^\\w+([.-]?\\w+)*@[a-zA-Z\\\\d]*\\.[a-zA-Z]{3}$");
                Matcher matcher = Email.matcher(newemail);
                while (!matcher.find()) {
                    System.out.println("  ");
                    System.out.println("Your Email Address is wrong");
                    System.out.println("New Email:");
                    newemail = scanner.nextLine();
                    matcher = Email.matcher(newemail);
                }
                check++;
                break;
            } else {
                System.out.println("The password is wrong!");
                System.out.println("1-Back");
                System.out.println("2-Try again");
                int n = scanner.nextInt();
                if (n == 1) {
                    setting();
                    break;
                }
                if (n == 2) {
                    changeemail();
                }
            }
        }
        if (check != 0) {
            email = newemail;
            System.out.println("  ");
            System.out.println("Your email has been successfully changed");
            System.out.println("    ");
            setting();
        }
    }
    public void changeusername() {
        Scanner scanner = new Scanner(System.in);
        String newusername = null;
        while (true) {
            System.out.println("Password:");
            String pass = scanner.nextLine();
            int hash = pass.hashCode();
            if (hash == hashpass) {
                while (true) {
                    int check=0;
                    System.out.println("New Username:");
                    newusername = scanner.nextLine();
                    for (int i = 0; i < accountmenu.size(); i++) {
                        if (!accountmenu.get(i).getUsername().equals(username)) {
                            if (accountmenu.get(i).getUsername().equals(newusername)) {
                                System.out.println("  ");
                                System.out.println("The username is invalid");
                                System.out.println("  ");
                                check++;
                                break;
                            }
                        }
                    }
                    if (check==0) {
                        break;
                    }
                }
                username = newusername;
                System.out.println("  ");
                System.out.println("Your Username has been successfully changed");
                System.out.println("   ");
                setting();
                break;
            } else {
                System.out.println("The password is wrong!");
                System.out.println("1-Back");
                System.out.println("2-Try again");
                int n = scanner.nextInt();
                if (n == 1) {
                    setting();
                    break;
                }
                if (n==2) {
                    changeusername();
                }
            }
        }
    }
    public void changepassword() {
        Scanner scanner = new Scanner(System.in);
        int newhash = 0;
        int check = 0;
        System.out.println("Password:");
        String pass = scanner.nextLine();
        int hash = pass.hashCode();
        if (hash == hashpass) {
            System.out.println("New Password:");
            String newpass = scanner.nextLine();
            newhash = newpass.hashCode();
            hashpass = newhash;
            System.out.println("  ");
            System.out.println("Your password has been successfully changed");
            System.out.println("  ");
            setting();
        } else {
            System.out.println("The password is wrong!");
            System.out.println("1-Back");
            System.out.println("2-Try again");
            int n = scanner.nextInt();
            if (n == 1) {
                setting();
            } else if (n == 2) {
                changepassword();
            }
        }
    }
}
