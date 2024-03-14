package org.pumpitup.entities;

import lombok.NonNull;

// This entity might be an overkill, but also allows to setup acceptable enumerations of value for given
// attributes or for more efficient filtering. If they need to be customized at order-time, we can still allow user to either add new
// records or to simply extend solution to allow for attribute to either relate to existing Value enumeration
// or to simply have serialized record right in configuration Map representation.
public record ItemConfigurationAttributeValue(@NonNull String value, @NonNull ItemConfigurationAttribute attribute) {
    @Override
    public String toString() {
        return "[" + attribute + "=" + value + "]";
    }
}
