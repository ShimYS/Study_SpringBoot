package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }


    /* 검색조건이 2개 모두 null이 아니라는 가정에서 나온 쿼리 => 동적쿼리가 아님 */
    public List<Order> findAll(OrderSearch orderSearch) {
        return em.createQuery("select o from Order o join o.member m" +
                " where o.status = :status " +
                " and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                //.setFirstResult(100) // 100번째부터 검색 (페이징처리시 사용할 수 있다)
                .setMaxResults(1000)    // 최대 1000건 검색
                .getResultList();
    }

    /* JPA Criteria (JPA 표준 스펙) */
    // 표준스펙이긴 하나 유지보수성, 가독성이 너무 안좋아서 실무에서 사용하지 않는다.
    // 따라치기도 싫을정도로 코드 상태가.... 하.... 고민 많이 했다....


    /* 동적쿼리는 Querydsl을 사용한다!! */

}
