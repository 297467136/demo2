package com.example.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "t_book")
public class Book {

    /**
     * @NotNull 和 @NotEmpty  和@NotBlank 区别
     *
     * @NotEmpty 用在集合类上面
     * @NotBlank 用在String上面
     * @NotNull 用在基本类型上(byte、short、int、long、float、double、char、boolean)
     */

    /**
     * 注解参考链接：http://blog.java1234.com/blog/articles/336.html
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Column(length = 32)
    private String id;

    @Column(length = 18)
    @NotBlank(message = "书名不能为空")
    //@Min(value=18,message="书名必须大于18个字！")
    private String bookname;

    // 设置级联操作 DELETE 慎用，会删除主外键关系
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},fetch=FetchType.LAZY,targetEntity = BookDetail.class)
    // name=关联表的名称+“_”+ 关联表主键的字段名
    // 属性referencedColumnName标注的是所关联表中的字段名，若不指定则使用的所关联表的主键字段名作为外键。
    @JoinColumn(name="detail_id",referencedColumnName = "id")
    private BookDetail bookDetail;

    public BookDetail getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(BookDetail bookDetail) {
        this.bookDetail = bookDetail;
    }

    //    @Digits(integer=1000,fraction=2)
//    @NotNull(message="价格不能为null！")
//    @Column
//    private Double price;
//
//    @Future(message = "必须大于当前时间")
//    @NotNull(message = "时间不能为null")
//    @Column
//    private Date date;


//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
}
