package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired ItemRepository itemRepository;
    @Autowired ItemService itemService;
    @Autowired EntityManager em;
    
    @Test
    public void 상품_등록() throws Exception {
        //given
        Item book = new Book();

        //when
        itemService.saveItem(book);
        
        // then
        em.flush();
        assertEquals(book, itemService.findOne(book.getId()));
    }
    
    @Test
    public void 상품_수량_수정() throws Exception {
        //given
        Item book1 = new Book();
        Item book2 = new Book();
        book1.setStockQuantity(100);
        book2.setStockQuantity(100);

        //when
        itemService.saveItem(book1);
        itemService.saveItem(book2);
        book1.addStock(50);
        book2.removeStock(50);

        // then
        em.flush();
        assertEquals(150, itemService.findOne(book1.getId()).getStockQuantity());
        assertEquals(50, itemService.findOne(book2.getId()).getStockQuantity());
    }
}