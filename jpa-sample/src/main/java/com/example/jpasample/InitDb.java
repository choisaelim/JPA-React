package com.example.jpasample;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import com.example.jpasample.domain.Book;
import com.example.jpasample.domain.Delivery;
import com.example.jpasample.domain.Item;
import com.example.jpasample.domain.Member;
import com.example.jpasample.domain.Order;
import com.example.jpasample.domain.OrderItem;
import com.example.jpasample.type.Address;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitDb {
  private final InitService initService;

  @PostConstruct
  public void init() throws Exception {
    initService.dbInit1();
    initService.dbInit2();
  }

  @Component
  @Transactional
  @RequiredArgsConstructor
  static class InitService {
    private final EntityManager em;

    public void dbInit1() throws Exception {
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
    }

    public void dbInit2() throws Exception {
      Member member = Member.createMember("고민수", "경기도", "노원로", "13926");
      em.persist(member);

      Book book1 = Book.createBook("SPRING1 BOOK", 10000, 100);
      em.persist(book1);

      Book book2 = Book.createBook("SPRING2 BOOK", 20000, 200);
      em.persist(book2);

      OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
      OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

      Delivery delivery = Delivery.createDelivery(member.getAddress());
      List<OrderItem> orderItems = new ArrayList<>();
      orderItems.add(orderItem1);
      orderItems.add(orderItem2);
      Order order = Order.createOrder(member, delivery, orderItems);
      em.persist(order);
    }

  }
}
