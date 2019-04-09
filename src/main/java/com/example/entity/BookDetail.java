package com.example.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "detail")
public class BookDetail {
    // @GeneratedValue(strategy = GenerationType.AUTO) 自增

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Column(length = 32)
    private String id;

    @Column
    private Integer numberOfPages;

    @OneToOne(mappedBy = "bookDetail") // mappedBy的意思就是“被映射”，即mappedBy这方不用管关联关系，关联关系交给另一方处理
    private Book book;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
