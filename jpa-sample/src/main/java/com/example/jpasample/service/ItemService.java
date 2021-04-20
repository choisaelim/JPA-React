package com.example.jpasample.service;

import java.util.List;

import com.example.jpasample.domain.Item;
import com.example.jpasample.repository.ItemRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> getItem() {
        return itemRepository.findAll();
    }

    public Item getItem(Long id) {
        return itemRepository.getOne(id);
    }

}
