package org.pumpitup.entities;

import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@RequiredArgsConstructor
@ToString
public class Item {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
    private final ItemConfiguration configuration = new ItemConfiguration();

    private final Integer id = ID_GENERATOR.getAndIncrement();
    @NonNull
    private ItemType type;

    public void addConfiguration(ItemConfigurationAttributeValue value) {
        if (!this.type.hasConfigurationAttribute(value.attribute())) {
            throw new RuntimeException("Attribute " + value.attribute().name() + " is not allowed for ItemType " + this.type.getName());
        }

        this.configuration.add(value);
    }
}
