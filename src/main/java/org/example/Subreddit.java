package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.zip.ZipFile;

public class Subreddit extends Main{
    String name;
    ArrayList<Account> Members = new ArrayList<>();
    ArrayList<Post> Posts = new ArrayList<>();
    //static ArrayList<Subreddit> submenu = new ArrayList<>();

    public Subreddit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void menu1(Account user) {
        System.out.println("1-Posts");
        System.out.println("2-Members");
        System.out.println("3-Creat post");
        System.out.println("4-Remove member");
        System.out.println("5-Remove post");
        System.out.println("6-Add admin");
        System.out.println("7-Back");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int n = scanner.nextInt();
            if (n == 1) {
                Posts(user, 1);
                break;
            } else if (n == 2) {
                members();
                menu1(user);
                break;
            } else if (n == 3) {
                creatpost(user);
                menu1(user);
                break;
            } else if (n == 4) {
                removemember();
                menu1(user);
                break;
            } else if (n == 5) {
                removepost();
                menu1(user);
                break;
            } else if (n == 6) {
                Addadmin();
                menu1(user);
                break;
            } else if (n == 7) {
                user.menu();
                break;
            } else {
                System.out.println("Enter correct input!");
            }
        }

    }

    public void menu2(Account user) {
        System.out.println("1-Posts");
        System.out.println("2-Members");
        System.out.println("3-Creat post");
        System.out.println("4-Remove member");
        System.out.println("5-Remove post");
        System.out.println("6-Back");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int n = scanner.nextInt();
            if (n == 1) {
                Posts(user, 2);
                break;
            } else if (n == 2) {
                members();
                menu2(user);
                break;
            } else if (n == 3) {
                creatpost(user);
                menu2(user);
                break;
            } else if (n == 4) {
                removemember();
                menu2(user);
                break;
            } else if (n == 5) {
                removepost();
                menu2(user);
                break;
            } else if (n == 6) {
                user.menu();
                break;
            } else {
                System.out.println("Enter correct input!");
            }
        }
    }

    public void menu3(Account user) {
        System.out.println("1-Posts");
        System.out.println("2-Members");
        System.out.println("3-Creat post");
        System.out.println("4-Back");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int n = scanner.nextInt();
            if (n == 1) {
                Posts(user, 3);
                break;
            } else if (n == 2) {
                members();
                menu3(user);
                break;
            } else if (n == 3) {
                creatpost(user);
                menu3(user);
                break;
            } else if (n == 4) {
                user.menu();
                break;
            } else {
                System.out.println("Enter correct input!");
            }
        }
    }
    public void Posts(Account user, int n) {
        Scanner scanner = new Scanner(System.in);
        for (int i=(Posts.size()-1); i>=0; i--) {
            //System.out.println(Posts.size()-i + ": ");
            Posts.get(i).coutpost();
            System.out.println("  ");
            Posts.get(i).Comments();
            System.out.println("  ");
        }
        System.out.println("If you want to vote, comment or save, Enter 1");
        System.out.println("If you want go back to the menu, Enter 2");
        int m = scanner.nextInt();
        if (m==1) {
            postmenu(user, n);
        }
        if (m==2) {
            if (n==1) {
                menu1(user);
            }
            else if (n==2) {
                menu2(user);
            }
            else if (n==3) {
                menu3(user);
            }
        }
    }
    public void postmenu(Account user, int n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("For which post?");
        System.out.println("title:");
        String title = scanner.nextLine();
        System.out.println("1-Add comment");
        System.out.println("2-Up vote");
        System.out.println("3-Down vote");
        System.out.println("4-Withdraw vote");
        System.out.println("5-Save");
        System.out.println("6-Vote for the comment");
        System.out.println("7-Back");
        int p = scanner.nextInt();
        if (p==1) {
            Comment comment = new Comment(user.getUsername(), 0);
            comment.getcomment();
            for (int i=0; i<Posts.size(); i++) {
                if (Posts.get(i).title.equals(title)) {
                    Posts.get(i).comments.add(comment);
                    System.out.println("Done");
                    break;
                }
            }
            while (true) {
                System.out.println("1-Back");
                int m = scanner.nextInt();
                if (m == 1) {
                    postmenu(user, n);
                    break;
                }
            }
        }
        if (p==2) {
            if (user.num != 0) {
                System.out.println("You have already voted");
                while (true) {
                    System.out.println("1-Back");
                    int m = scanner.nextInt();
                    if (m == 1) {
                        postmenu(user, n);
                        break;
                    }
                }
            }
            else if (user.num == 0) {
                user.num = 2;
                for (int i=0; i<Posts.size(); i++) {
                    if (Posts.get(i).title.equals(title)) {
                        Posts.get(i).upvote();
                        System.out.println("Done");
                        break;
                    }
                }
                while (true) {
                    System.out.println("1-Back");
                    int m = scanner.nextInt();
                    if (m == 1) {
                        postmenu(user, n);
                        break;
                    }
                }
            }
        }
        if (p==3) {
            if (user.num != 0) {
                System.out.println("You have already voted");
                while (true) {
                    System.out.println("1-Back");
                    int m = scanner.nextInt();
                    if (m == 1) {
                        postmenu(user, n);
                        break;
                    }
                }
            }
            else if (user.num == 0) {
                user.num = 1;
                for (int i=0; i<Posts.size(); i++) {
                    if (Posts.get(i).title.equals(title)) {
                        Posts.get(i).downvote();
                        System.out.println("Done");
                        break;
                    }
                }
                while (true) {
                    System.out.println("1-Back");
                    int m = scanner.nextInt();
                    if (m == 1) {
                        postmenu(user, n);
                        break;
                    }
                }
            }
        }
        if (p==4) {
            if (user.num == 2) {
                user.num = 0;
                for (int i=0; i<Posts.size(); i++) {
                    if (Posts.get(i).title.equals(title)) {
                        Posts.get(i).downvote();
                        System.out.println("Done");
                        break;
                    }
                }
            }
            if (user.num == 1) {
                user.num = 0;
                for (int i=0; i<Posts.size(); i++) {
                    if (Posts.get(i).title.equals(title)) {
                        Posts.get(i).upvote();
                        System.out.println("Done");
                        break;
                    }
                }
            }
            while (true) {
                System.out.println("1-Back");
                int m = scanner.nextInt();
                if (m == 1) {
                    postmenu(user, n);
                    break;
                }
            }
        }
        if (p==5) {
            for (int i=0; i<Posts.size(); i++) {
                if (Posts.get(i).title.equals(title)) {
                    user.SavedPosts.add(Posts.get(i));
                    System.out.println("Done");
                    break;
                }
            }
            while (true) {
                System.out.println("1-Back");
                int m = scanner.nextInt();
                if (m == 1) {
                    postmenu(user, n);
                    break;
                }
            }
        }
        if (p==6) {

        }
        if (p==7) {
            if (n==1) {
                menu1(user);
            }
            else if (n==2) {
                menu2(user);
            }
            else if (n==3) {
                menu3(user);
            }
        }
    }

    public void members() {
        System.out.println("Members:");
        System.out.println("  ");
        for (int i = 0; i < Members.size(); i++) {
            System.out.println(Members.get(i).getUsername());
        }
    }

    public void creatpost(Account user) {
        String username = user.getUsername();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Title:");
        String title =  scanner.nextLine();
        System.out.println("Content:");
        String content = scanner.nextLine();
        Post post = new Post(this.getName(),username, title, content, 0);
        user.MyPosts.add(post);
        this.Posts.add(post);
        System.out.println("  ");
        System.out.println("Your post created successfully");
        System.out.println("  ");
    }

    public void removemember() {
        System.out.println("Members:");
        System.out.println("  ");
        for (int i = 0; i < Members.size(); i++) {
            System.out.println(Members.get(i).getUsername());
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int check = 0;
            System.out.println("Which member do you want to remove?");
            String nameuser = scanner.nextLine();
            for (int i = 0; i < Members.size(); i++) {
                if (nameuser.equals(Members.get(i).getUsername())) {
                    Members.remove(Members.get(i));
                    Members.get(i).MySubs.remove(this);
                    check++;
                    break;
                }
            }
            if (check != 0) {
                System.out.println("Member removed successfully");
                break;
            } else if (check == 0) {
                System.out.println("The member not found!");
                System.out.println("1-Try again:");
                System.out.println("2-Back");
                    int n = scanner.nextInt();
                    if (n==1) {
                        removemember();
                }
                    if (n==2) {
                        break;
                    }
            }
        }
    }

    public void removepost() {
        for (int i=(Posts.size()-1); i>=0; i--) {
            System.out.println(Posts.get(i).title);
        }
        String userr;
        System.out.println("Which post do you want to delete?");
        System.out.println("Title: ");
        Scanner scanner = new Scanner(System.in);
        String titl = scanner.nextLine();
        for (int i=0; i<Posts.size(); i++) {
            if (Posts.get(i).title.equals(titl)) {
                Posts.remove(Posts.get(i));
                userr = Posts.get(i).username;
                for (int j=0; j<Members.size(); j++) {
                    if (Members.get(i).getUsername().equals(userr)) {
                        Members.get(i).MyPosts.remove(Posts.get(i));
                        break;
                    }
                }
                break;
            }
        }

    }

    public void Addadmin() {
        System.out.println("Members:");
        System.out.println("  ");
        for (int i = 0; i < Members.size(); i++) {
            System.out.println(Members.get(i).getUsername());
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int check = 0;
            System.out.println("Which one is new addmin?");
            String nameuser = scanner.nextLine();
            for (int i = 0; i < Members.size(); i++) {
                if (nameuser.equals(Members.get(i).getUsername())) {
                    Members.get(i).SubsAdmin.add(this);
                    check++;
                    break;
                }
            }
            if (check != 0) {
                System.out.println("Addmin added successfully");
                break;
            } else if (check == 0) {
                System.out.println("The member not found!");
                System.out.println("1-Try again");
                System.out.println("2-Back");
                int n = scanner.nextInt();
                if (n == 1) {
                    Addadmin();
                }
                if (n == 2) {
                    break;
                }
            }
        }
    }
    public static ArrayList<Subreddit> getSubmenu() {
        return Submenu;
    }
}
