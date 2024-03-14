package org.pumpitup.entities;

import lombok.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@ToString
@RequiredArgsConstructor
public class ItemType {

    @NonNull
    private Category category;
    @NonNull
    private String name;

    private final Set<ItemConfigurationAttribute> configurationAttributes = new HashSet<>();

    public void addConfigurationOption(ItemConfigurationAttribute attribute) {
        this.configurationAttributes.add(attribute);
    }

    public boolean hasConfigurationAttribute(ItemConfigurationAttribute attribute) {
        return this.configurationAttributes.contains(attribute);
    }
}
