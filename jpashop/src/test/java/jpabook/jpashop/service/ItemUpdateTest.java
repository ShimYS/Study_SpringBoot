package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired EntityManager em;

    @Test
    public void updateTest() throws Exception {
        Book book = em.find(Book.class, 1);

        //TX
        book.setName("aaaaa");

        //엔티티 값이 바뀌면 flush할때 dirty checking해서 변경된 값을 db에 반영하고 commit한다.
        //변경감지 == dirty checking
        //TX commit

    }
}
