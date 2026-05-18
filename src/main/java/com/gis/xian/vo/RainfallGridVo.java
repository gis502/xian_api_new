package com.gis.xian.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RainfallGridVo {
    public RainfallGridVo() {
        this.cesiumConfig = new CesiumConfig();
    }

    private Long id;
    private String pngPath;
    private LocalDateTime queryTime;
    private Double resolution;
    private Integer stationCount;
    private CesiumConfig cesiumConfig;

    @Data
    public static class CesiumConfig {
        public CesiumConfig() {
            this.rectangle = new Rectangle();
        }

        private Rectangle rectangle;
        private Double width;
        private Double height;

        @Data
        public static class Rectangle {
            public Rectangle() {
                this.west = 0.0;
                this.south = 0.0;
                this.east = 0.0;
                this.north = 0.0;
            }
            private Double west;
            private Double south;
            private Double east;
            private Double north;
        }
    }
}
