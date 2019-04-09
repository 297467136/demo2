package com.example.controller;

import com.example.dao.BookDao;
import com.example.entity.Book;
import com.example.entity.BookDetail;
import org.springframework.data.domain.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.xml.namespace.QName;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookDao bookDao;

    /**
     * 查询所有图书
     * @return
     */
    @RequestMapping(value="/list")
    public Object list(){
        return bookDao.findAll();
    }

    @RequestMapping(value="/findBy")
    public Object findBy(String bookname){
        return bookDao.findByBookname(bookname);
    }

    /**
     * 添加图书
     * @param book
     * @return
     */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public Object add(@Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return bindingResult.getFieldError().getDefaultMessage();
        }else{
            BookDetail detail = new BookDetail();
            detail.setNumberOfPages((int)(1+Math.random()*(10-1+1)));
            book.setBookDetail(detail);
            bookDao.save(book);
            return "添加成功！";
        }
    }
    @RequestMapping(value="/del",method= RequestMethod.POST)
    public Object del(String id){
        //bookDao.deleteById(id);

        // 级联删除
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "删除成功";
    }
    @GetMapping(value="/s1")
    public Object s1(String bookname){
//        Book b= bookDao.findBook(bookname);
//        List<Book> s = bookDao.findBook1("遮天","这太难");

        // 分页
        Page<Book> p = bookDao.findAll(PageRequest.of(0, 3,Sort.Direction.DESC, "id"));
        //bookDao.findb


        return p;
    }

    // 多条件查询
    /**
     * public List<WeChatGzUserInfoEntity> findByCondition(Date minDate, Date maxDate, String nickname){
     *         List<WeChatGzUserInfoEntity> resultList = null;
     *         Specification querySpecifi = new Specification<WeChatGzUserInfoEntity>() {
     *             @Override
     *             public Predicate toPredicate(Root<WeChatGzUserInfoEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
     *
     *                 List<Predicate> predicates = new ArrayList<>();
     *                 if(null != minDate){
     *                     predicates.add(criteriaBuilder.greaterThan(root.get("subscribeTime"), minDate));
     *                 }
     *                 if(null != maxDate){
     *                     predicates.add(criteriaBuilder.lessThan(root.get("subscribeTime"), maxDate));
     *                 }
     *                 if(null != nickname){
     *                     predicates.add(criteriaBuilder.like(root.get("nickname"), "%"+nickname+"%"));
     *                 }
     *                 return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
     *             }
     *         };
     *         resultList =  this.weChatGzUserInfoRepository.findAll(querySpecifi);
     *         return resultList;
     *     }
     */
}
