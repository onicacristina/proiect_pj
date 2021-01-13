package com.company;

public class Book {

    private String title;
    private String author;
    private Boolean fiction;
    private Integer numberOfPages;
    private  Float rating;

    public Book(String title, String author, Boolean fiction, Integer numberOfPages, Float rating) {
        this.title = title;
        this.author = author;
        this.fiction = fiction;
        this.numberOfPages = numberOfPages;
        this.rating = rating;
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getFiction() {
        return fiction;
    }

    public void setFiction(Boolean fiction) {
        this.fiction = fiction;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", fiction=" + fiction +
                ", numberOfPages=" + numberOfPages +
                ", rating=" + rating +
                '}';
    }
}
