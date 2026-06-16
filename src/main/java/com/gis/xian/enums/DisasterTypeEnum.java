package com.gis.xian.enums;

import lombok.Getter;

@Getter
public enum DisasterTypeEnum {
    // 具体灾害类型
    LANDSLIDE("landslide", "滑坡"),
    COLLAPSE("collapse", "崩塌"),
    DEBRIS_FLOW("debris_flow", "泥石流"),
    FLASH_FLOOD("flash_flood", "山洪"),
    WATER_LOGGING("water_logging", "内涝");

    private final String type;
    private final String description;

    DisasterTypeEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }
    
    /**
     * 根据type获取枚举
     */
    public static DisasterTypeEnum getByType(String type) {
        for (DisasterTypeEnum e : values()) {
            if (e.getType().equals(type)) {
                return e;
            }
        }
        return null;
    }
}
