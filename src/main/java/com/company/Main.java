package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static JButton addBookButton;
    private static JButton listBooksButton;
    private static JButton exitProgramButton;
    private static JTextField titleInput;
    private static JTextField authorInput;
    private static JTextField numberOfPagesInput;
    private static JTextField ratingInput;
    private static JCheckBox bookTypeCheckBox;
    private static JButton saveBookButton;
    private static JButton modifyBookButton;
    private static JButton deleteBookButton;
    private static JButton backToMenuButton;
    private static JList<String> list;

    private static List<Book> bookList = new ArrayList<>();
    private static  DefaultListModel<String> bookListModel;


    public static void main(String[] args) {
        JPanel jPanel = buildPanel();
    }



    private static JPanel buildPanel() {
        bookList = FileUtility.readFromFile();

        JFrame frame = new JFrame("Books");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        showMenuPage(panel);

        frame.setVisible(true);

        return panel;
    }

    private static void goToAddPage(JPanel panel) {
        addBookButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        panel.removeAll();
                        panel.updateUI();
                        showAddPage(panel);
                    }
                }
        );
    }


    private static void showAddPage(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Title: ");
        titleLabel.setBounds(50, 20, 80, 25);
        panel.add(titleLabel);

        titleInput = new JTextField(20);
        titleInput.setBounds(50, 45, 465, 25);
        panel.add(titleInput);

        JLabel authorLabel = new JLabel("Author: ");
        authorLabel.setBounds(50, 85, 80, 25);
        panel.add(authorLabel);

        authorInput = new JTextField(20);
        authorInput.setBounds(50, 110, 165, 25);
        panel.add(authorInput);

        JLabel numberOfPagesLabel = new JLabel("Number of pages: ");
        numberOfPagesLabel.setBounds(50, 150, 180, 25);
        panel.add(numberOfPagesLabel);

        numberOfPagesInput = new JTextField(20);
        numberOfPagesInput.setBounds(50, 175, 165, 25);
        panel.add(numberOfPagesInput);

        JLabel ratingLabel = new JLabel("How do you rate the book (1 to 5 star rating scale)? ");
        ratingLabel.setBounds(50, 215, 380, 25);
        panel.add(ratingLabel);

        ratingInput = new JTextField(20);
        ratingInput.setBounds(50, 240, 165, 25);
        panel.add(ratingInput);

        bookTypeCheckBox = new JCheckBox(" Is it a book of fiction?(true/false)",false);
        bookTypeCheckBox.setBounds(50, 285, 310, 25);
        panel.add(bookTypeCheckBox);

        saveBookButton = new JButton("Save");
        saveBookButton.setBounds(50,340,100,26);
        saveBookButton.setBackground(Color.ORANGE);
        panel.add(saveBookButton);

        //goToMenuPage(panel);
        saveBookButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(titleInput.getText().equals("")){
                            throw new NoCompletedFieldException("Please complete this field!");
                        }
                        else
                             if(authorInput.getText().equals("")){
                                throw new NoCompletedFieldException("Please complete this field!");
                                }
                             else
                                 if(isNumeric(numberOfPagesInput.getText())==false){
                                     throw new NoCompletedFieldException("Please complete this field with a number!");
                                 }
                                 else
                                     if(ratingInput.getText().equals("")){
                                         throw new NoCompletedFieldException("Please complete this field!");
                                     }
                                     else{
                                        Book book = new Book();
                                        book.setTitle(titleInput.getText());
                                        book.setAuthor(authorInput.getText());
                                        book.setNumberOfPages(Integer.valueOf(numberOfPagesInput.getText()));
                                        book.setRating(Float.valueOf(ratingInput.getText()));
                                        book.setFiction(bookTypeCheckBox.isSelected());

                                        bookList.add(book);

                                         panel.removeAll();
                                         panel.updateUI();
                                         showMenuPage(panel);}
                    }
                }
        );

        backToMenuButton = new JButton("Back to menu");
        backToMenuButton.setBounds(220,340,130,26);
        backToMenuButton.setBackground(Color.ORANGE);
        panel.add(backToMenuButton);
        backToMenuPage(panel);
    }


    private static void showMenuPage(JPanel panel) {
        panel.setLayout(null);

        addBookButton = new JButton("Add book");
        addBookButton.setBounds(220,150,130,26);
        addBookButton.setBackground(Color.ORANGE);
        panel.add(addBookButton);

        listBooksButton = new JButton("List books");
        listBooksButton.setBounds(220,210,130,26);
        listBooksButton.setBackground(Color.ORANGE);
        panel.add(listBooksButton);

        exitProgramButton = new JButton("Exit program");
        exitProgramButton.setBounds(220,270,130,26);
        exitProgramButton.setBackground(Color.ORANGE);

        exitProgramButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        FileUtility.writeToFile(bookList);
                        System.exit(0);
                    }
                }
        );
        panel.add(exitProgramButton);

        goToAddPage(panel);
        goToListPage(panel);
    }

    private static void goToListPage(JPanel panel) {
        listBooksButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel.removeAll();
                        panel.updateUI();
                        showListPage(panel);
                    }
                }
        );
    }

    private static void backToMenuPage(JPanel panel)
    {
        backToMenuButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel.removeAll();
                        panel.updateUI();
                        showMenuPage(panel);
                    }
                }
        );
    }


    private static void showModifyPage(JPanel panel, Book book) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Title: ");
        titleLabel.setBounds(50, 20, 80, 25);
        panel.add(titleLabel);

        titleInput = new JTextField(20);
        titleInput.setBounds(50, 45, 465, 25);
        titleInput.setText(book.getTitle());
        panel.add(titleInput);

        JLabel authorLabel = new JLabel("Author: ");
        authorLabel.setBounds(50, 85, 80, 25);
        panel.add(authorLabel);

        authorInput = new JTextField(20);
        authorInput.setBounds(50, 110, 165, 25);
        authorInput.setText(book.getAuthor());
        panel.add(authorInput);

        JLabel numberOfPagesLabel = new JLabel("Number of pages: ");
        numberOfPagesLabel.setBounds(50, 150, 180, 25);
        panel.add(numberOfPagesLabel);

        numberOfPagesInput = new JTextField(20);
        numberOfPagesInput.setBounds(50, 175, 165, 25);
        numberOfPagesInput.setText(String.valueOf(book.getNumberOfPages()));
        panel.add(numberOfPagesInput);

        JLabel ratingLabel = new JLabel("How do you rate the book (1 to 5 star rating scale)? ");
        ratingLabel.setBounds(50, 215, 380, 25);
        panel.add(ratingLabel);

        ratingInput = new JTextField(20);
        ratingInput.setBounds(50, 240, 165, 25);
        ratingInput.setText(String.valueOf(book.getRating()));
        panel.add(ratingInput);

        if(book.getFiction()==true)
            bookTypeCheckBox = new JCheckBox(" Is it a book of fiction?(true/false)",true);
        else
            bookTypeCheckBox = new JCheckBox(" Is it a book of fiction?(true/false)",false);

        bookTypeCheckBox.setBounds(50, 285, 310, 25);
        panel.add(bookTypeCheckBox);

        saveBookButton = new JButton("Save");
        saveBookButton.setBounds(50,340,100,26);
        saveBookButton.setBackground(Color.ORANGE);
        panel.add(saveBookButton);

        saveBookButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Book book = new Book();
                        if(titleInput.getText().equals("")){
                            throw new NoCompletedFieldException("Please complete this field!");
                        }
                             else
                                 if(authorInput.getText().equals("")){
                                    throw new NoCompletedFieldException("Please complete this field!");
                                 }
                                 else
                                     if(isNumeric(numberOfPagesInput.getText())==false){
                                         throw new NoCompletedFieldException("Please complete this field with a number!");
                                    }
                                    else
                                        if(ratingInput.getText().equals("")){
                                            throw new NoCompletedFieldException("Please complete this field!");
                                        }
                                         else {
                                             book.setTitle(titleInput.getText());
                                             book.setAuthor(authorInput.getText());
                                             book.setNumberOfPages(Integer.valueOf(numberOfPagesInput.getText()));
                                             book.setRating(Float.valueOf(ratingInput.getText()));
                                             book.setFiction(bookTypeCheckBox.isSelected());

                                             //bookList.add(book);
                                             panel.removeAll();
                                             panel.updateUI();
                                             showMenuPage(panel);
                                         }
                    }
                }
        );

        backToMenuButton = new JButton("Back to menu");
        backToMenuButton.setBounds(220,340,130,26);
        backToMenuButton.setBackground(Color.ORANGE);
        panel.add(backToMenuButton);
        backToMenuPage(panel);
    }


    private static void showListPage(JPanel panel) {
         bookListModel = new DefaultListModel<>();
         addBookToJlist();

        list =new JList<>(bookListModel);
        list.setBounds(50,10,300,500);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(list);

        list.getSelectedIndex();

        modifyBookButton = new JButton("Modify book");
        modifyBookButton.setBounds(380,150,130,26);
        modifyBookButton.setBackground(Color.ORANGE);
        panel.add(modifyBookButton);
        modifyBookButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedIndex = list.getSelectedIndex();
                        if (selectedIndex < 0) {
                            throw new NoBookSelectedException("Please select a book!");
                        } else {
                            panel.removeAll();
                            panel.updateUI();
                            showModifyPage(panel,bookList.get(selectedIndex));
                        }
                    }
                }
        );


        deleteBookButton = new JButton("Delete book");
        deleteBookButton.setBounds(380,190,130,26);
        deleteBookButton.setBackground(Color.ORANGE);

        deleteBookButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedIndex = list.getSelectedIndex();

                        if (selectedIndex < 0) {
                            throw new NoBookSelectedException("Please select a book!");
                        } else {
                            bookList.remove(selectedIndex);
                            bookListModel.clear();
                            addBookToJlist();
                        }
                    }
                }
        );
        panel.add(deleteBookButton);

        backToMenuButton = new JButton("Back to menu");
        backToMenuButton.setBounds(380,230,130,26);
        backToMenuButton.setBackground(Color.ORANGE);
        panel.add(backToMenuButton);

        backToMenuPage(panel);
    }

    private static void addBookToJlist() {
        bookList.forEach(book -> bookListModel.addElement(book.getTitle()));
    }

    public static boolean isNumeric(String str) {
        if (str == null)
            return false;
        for (char c : str.toCharArray())
            if (c < '0' || c > '9')
                return false;
        return true;
    }

}
