package org.pumpitup.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class Order {

    // In reality this field would most likely be a map to also allow ordering of different quantities of items
    private final List<OrderedItem> orderedItems = new ArrayList<>();


    public void addItem(ItemType type, ItemConfiguration configuration) {
        orderedItems.add(new OrderedItem(type, configuration));
    }
}
