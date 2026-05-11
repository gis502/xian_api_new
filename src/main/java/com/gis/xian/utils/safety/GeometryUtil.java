package com.gis.xian.utils.safety;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Geometry 工具类
 * 用于 WKB 和 lon/lat 之间的转换
 */
public class GeometryUtil {

    /**
     * 将 WKB 转换为 lon 和 lat
     * @param wkbHex 十六进制 WKB 数据
     * @return [lon, lat] 数组
     */
    public static String[] wkbToLonLat(String wkbHex) {
        if (wkbHex == null || wkbHex.isEmpty()) {
            return null;
        }

        try {
            // 移除 0x 前缀（如果有）
            if (wkbHex.startsWith("0x") || wkbHex.startsWith("0X")) {
                wkbHex = wkbHex.substring(2);
            }

            byte[] bytes = hexStringToByteArray(wkbHex);
            ByteBuffer buffer = ByteBuffer.wrap(bytes);

            // 读取字节序
            byte byteOrder = buffer.get();
            buffer.order(byteOrder == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);

            // 读取几何类型
            int geometryType = buffer.getInt();
            int type = geometryType & 0x0FFFFFFF;

            // Point 类型 = 1
            if (type == 1) {
                double lon = buffer.getDouble();
                double lat = buffer.getDouble();
                return new String[]{String.valueOf(lon), String.valueOf(lat)};
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将 lon 和 lat 转换为 WKB（十六进制）
     * @param lon 经度
     * @param lat 纬度
     * @return 十六进制 WKB 数据
     */
    public static String lonLatToWkb(String lon, String lat) {
        try {
            double lonValue = Double.parseDouble(lon);
            double latValue = Double.parseDouble(lat);

            // 创建 WKB 数据（小端序 Point）
            ByteBuffer buffer = ByteBuffer.allocate(21);
            buffer.order(ByteOrder.LITTLE_ENDIAN);

            // 字节序标识（1 = 小端序）
            buffer.put((byte) 1);

            // 几何类型（1 = Point）
            buffer.putInt(1);

            // 坐标
            buffer.putDouble(lonValue);
            buffer.putDouble(latValue);

            return byteArrayToHexString(buffer.array());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 十六进制字符串转字节数组
     */
    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * 字节数组转十六进制字符串
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
