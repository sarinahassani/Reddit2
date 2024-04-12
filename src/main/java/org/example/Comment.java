package org.example;

import java.util.Scanner;

public class Comment {
    String comment;
    String username;
    int like;
    public Comment (String username, int like) {
        this.username = username;
        this.like = like;
    }
    public void getcomment() {
        System.out.println("Comment:");
        Scanner scanner = new Scanner(System.in);
        comment = scanner.nextLine();
    }
    public void upvote() {
        like++;
    }
    public void downvote() {
        like--;
    }
    public void coutcomment() {
        System.out.println(username + ": ");
        System.out.println(comment);
        System.out.println("Karma: " + like);
        System.out.println("  ");
    }
}
