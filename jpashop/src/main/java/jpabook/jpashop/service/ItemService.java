package jpabook.jpashop.service;

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
