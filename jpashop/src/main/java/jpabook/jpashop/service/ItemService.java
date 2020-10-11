package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * 상품 저장
     * @param item 저장할 상품
     */
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    /**
     * 상품 수정 (변경 감지 기능 사용)
     * @param itemId
     */
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        // 업데이트용 DTO를 만들어서 하는게 좋다. 여기서는 name, price, stockQuantity만 수정

        // id로 영속 엔티티를 가지고 온다.
        Item findItem = itemRepository.findOne(itemId);
        // 값을 수정한다.
//        findItem.change(price, name, stockQuantity); => 이와 같이 의미있는 메서드를 만들어서 할것. set으로 하는건 좋지않다.
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
    }

    /**
     * 전체 상품 조회
     * @return 전체 상품 리스트
     */
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    /**
     * 특정 상품 조회
     * @param itemId 조회할 상품의 아이디
     * @return 조회된 상품
     */
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
