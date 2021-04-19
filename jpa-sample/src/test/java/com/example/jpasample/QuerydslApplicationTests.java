package com.example.jpasample;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.example.jpasample.domain.Book;
import com.example.jpasample.domain.Delivery;
import com.example.jpasample.domain.Member;
import com.example.jpasample.domain.Order;
import com.example.jpasample.domain.OrderItem;
import com.example.jpasample.domain.QMember;
import com.example.jpasample.domain.QOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Commit
public class QuerydslApplicationTests {
  @Autowired
  EntityManager em;

  @Test
  @DisplayName("회원 조회 후 추가")
  void contextLoads() {
    Member member = new Member();
    member.setName("김민지");
    // em.persist(member);

    JPAQueryFactory query = new JPAQueryFactory(em);
    QMember qMember = QMember.member;

    // Member result = query.selectFrom(qMember).fetchOne();
    List<Member> list = query.selectFrom(qMember).where(qMember.name.eq("김민지")).fetch();

    if (list.size() > 0) {

    } else {
      em.persist(member);
      list = query.selectFrom(qMember).where(qMember.name.eq("김민지")).fetch();
    }
    Assertions.assertThat(list).hasSizeGreaterThan(0);
    // Assertions.assertThat(result.getId()).isEqualTo(member.getId());
  }

  @Test
  @DisplayName("회원, 아이템, 주문 추가하기")
  void InsertTest() throws Exception {
    Member member = Member.createMember("차나리", "서울", "가로수길", "73923");
    em.persist(member);

    Book book1 = Book.createBook("JPA1 BOOK", 10000, 100);
    em.persist(book1);

    Book book2 = Book.createBook("JPA2 BOOK", 20000, 200);
    em.persist(book2);

    OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
    OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

    Delivery delivery = Delivery.createDelivery(member.getAddress());
    List<OrderItem> orderItems = new ArrayList<>();
    orderItems.add(orderItem1);
    orderItems.add(orderItem2);

    Order order = Order.createOrder(member, delivery, orderItems);
    em.persist(order);

    JPAQueryFactory query = new JPAQueryFactory(em);
    QOrder qorder = QOrder.order;

    List<Order> orderList = query.selectFrom(qorder).fetch();

    Assertions.assertThat(orderList).hasSizeGreaterThan(0);
  }
}
