package org.pumpitup.entities;

import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
public class ItemConfiguration {
    private final Set<ItemConfigurationAttributeValue> configuration = new HashSet<>();

    public void add(ItemConfigurationAttributeValue value) {
        this.configuration.add(value);
    }

    public boolean isSupersetOf(ItemConfiguration configurationSubset) {
        return this.configuration.containsAll(configurationSubset.configuration);
    }
}
