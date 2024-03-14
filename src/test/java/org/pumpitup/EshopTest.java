package org.pumpitup;

import org.junit.jupiter.api.Test;
import org.pumpitup.entities.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EshopTest {

    @Test
    void sampleOrderTest() {

        Eshop imaginaryEshop = new Eshop();

        // Setup sample eshop records
        Category apparel = new Category("Apparel");
        ItemType tshirt = new ItemType(apparel, "T-shirt");

        ItemConfigurationAttribute colorAttribute = new ItemConfigurationAttribute("color");
        ItemConfigurationAttributeValue blackColor = new ItemConfigurationAttributeValue("black", colorAttribute);
        ItemConfigurationAttributeValue redColor = new ItemConfigurationAttributeValue("red", colorAttribute);

        ItemConfigurationAttribute sizeAttribute = new ItemConfigurationAttribute("size");
        ItemConfigurationAttributeValue lSize = new ItemConfigurationAttributeValue("L", sizeAttribute);

        tshirt.addConfigurationOption(colorAttribute);
        tshirt.addConfigurationOption(sizeAttribute);

        Item blackLtshirt = new Item(tshirt);
        blackLtshirt.addConfiguration(blackColor);
        blackLtshirt.addConfiguration(lSize);


        imaginaryEshop.eshopStock.add(blackLtshirt);


        // Create order
        Order o1 = new Order();
        ItemConfiguration myFavoriteTshirtDesign = new ItemConfiguration();
        myFavoriteTshirtDesign.add(blackColor);
        myFavoriteTshirtDesign.add(lSize);
        o1.addItem(tshirt, myFavoriteTshirtDesign);

        ItemConfiguration awfulTshirtDesign = new ItemConfiguration();
        awfulTshirtDesign.add(redColor);
        System.out.println(awfulTshirtDesign);

        o1.addItem(tshirt, awfulTshirtDesign);


        /* Attempt to fulfill the order:
            We receive a map linking ordered item to specific items in stock that match criteria
            If such item isn't in stock, the item wrapped in Optional is missing
         */
        var fulfilment = imaginaryEshop.fulfillOrder(o1);


        // Items that can be used to fulfill order (at least partially in this example)
        assertEquals(List.of(blackLtshirt), fulfilment.values().stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList()));


        // Simple example of extracting details of items not in stock
        var outOfStockItems = fulfilment.entrySet().stream().filter(o -> o.getValue().isEmpty()).map(Map.Entry::getKey).map(OrderedItem::configuration).toList();
        assertEquals(List.of(awfulTshirtDesign), outOfStockItems);
    }
}