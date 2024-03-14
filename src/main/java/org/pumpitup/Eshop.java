package org.pumpitup;

import org.pumpitup.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Eshop {

    // Public just for simplicity of the example
    public List<Item> eshopStock = new ArrayList<>();

    public Map<OrderedItem, Optional<Item>> fulfillOrder(Order order) {

        return order.getOrderedItems().stream().collect(
                Collectors.toMap(Function.identity(), (
                        orderedItem -> this.eshopStock.stream().filter(
                                stockItem -> stockItem.getConfiguration().isSupersetOf(orderedItem.configuration())).findAny()
                )));
    }

    public static void main(String[] args) {
        System.out.println("Actual example in tests.");
    }
}