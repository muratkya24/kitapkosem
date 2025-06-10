package com.kitapkosem.servlet;

import java.sql.Timestamp;

public class Review {
    private int id;
    private int bookId;
    private int userId;
    private int rating;
    private String comment;
    private Timestamp reviewDate;

    public Review(int id, int bookId, int userId, int rating, String comment, Timestamp reviewDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    // Getter metodları
    public int getId() { return id; }
    public int getBookId() { return bookId; }
    public int getUserId() { return userId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public Timestamp getReviewDate() { return reviewDate; }
}
 