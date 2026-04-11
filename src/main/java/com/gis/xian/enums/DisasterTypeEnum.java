package com.gis.xian.enums;

import lombok.Getter;

@Getter
public enum DisasterTypeEnum {
    RAINSTORM("rainstorm"),
    EARTHQUAKE("earthquake");

    private final String type;

    DisasterTypeEnum(String type) {
        this.type = type;
    }
}
