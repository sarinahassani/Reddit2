package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Post {
    String subname;
    String username;
    String title;
    String content;
    int sum;
    ArrayList<Comment> comments = new ArrayList<>();
    public Post(String subname, String username, String title, String content, int sum) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.subname = subname;

    }
    public void coutpost() {
        System.out.println("Username: " + username);
        System.out.println("Title: " + title);
        System.out.println("Content: " + content);
        System.out.println("Karma: " + sum);
        //System.out.println("  ");
        System.out.println("Up vote/Down vote - Add comment - Save");
    }
    public void upvote() {
        sum++;
    }
    public void downvote() {
        sum--;
    }
    public void Comments() {
        for (int i=(comments.size()-1); i>=0; i--) {
            System.out.print(comments.size()-i + ": ");
            comments.get(i).coutcomment();
        }
    }
}
