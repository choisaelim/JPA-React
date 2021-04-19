package com.example.jpasample.domain;

import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.jpasample.type.Address;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MEMBER")
@Getter
@Setter
public class Member {
  @Id
  @GeneratedValue
  @Column(name = "MEMBER_ID")
  private Long id;

  private String name;

  @Embedded
  private Address address;

  @OneToMany(mappedBy = "member")
  private List<Order> orders;

  public static Member createMember(String name, String city, String street, String zipcode) {
    Member member = new Member();
    member.setName(name);
    member.setAddress(new Address(city, street, zipcode));
    return member;
  }
}
