package com.gis.xian.utils.safety;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeometryUtil {

    // 匹配 WKT 中的坐标对: "lon lat" 或 "lon,lat"
    private static final Pattern WKT_POINT = Pattern.compile(
        "POINT\\s*\\(\\s*(-?[\\d.]+)\\s+(-?[\\d.]+)\\s*\\)", Pattern.CASE_INSENSITIVE);
    private static final Pattern WKT_COORD = Pattern.compile(
        "(-?[\\d.]+)\\s+(-?[\\d.]+)");

    public static String[] wkbToLonLat(String value) {
        if (value == null || value.isEmpty()) return null;

        try {
            if (isWkt(value)) {
                return parseWkt(value);
            }

            String hex = value;
            if (hex.startsWith("0x") || hex.startsWith("0X")) {
                hex = hex.substring(2);
            }
            if (!hex.matches("^[0-9A-Fa-f]+$") || hex.length() % 2 != 0) {
                return null;
            }

            byte[] bytes = hexStringToByteArray(hex);
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            byte byteOrder = buffer.get();
            buffer.order(byteOrder == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);

            int geometryType = buffer.getInt();
            int type = geometryType & 0x0FFFFFFF;

            if (type == 1) {
                double lon = buffer.getDouble();
                double lat = buffer.getDouble();
                return new String[]{String.valueOf(lon), String.valueOf(lat)};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isWkt(String s) {
        return s.matches("^[A-Za-z].*");
    }

    private static String[] parseWkt(String wkt) {
        // POINT(108.948 34.263)
        Matcher pm = WKT_POINT.matcher(wkt);
        if (pm.find()) {
            return new String[]{pm.group(1), pm.group(2)};
        }

        // SRID=4326;...
        int semi = wkt.indexOf(';');
        if (semi > 0) {
            wkt = wkt.substring(semi + 1).trim();
            pm = WKT_POINT.matcher(wkt);
            if (pm.find()) {
                return new String[]{pm.group(1), pm.group(2)};
            }
        }

        // MULTIPOLYGON / POLYGON / LINESTRING / MULTIPOINT — 提取所有坐标算质心
        List<double[]> coords = new ArrayList<>();
        Matcher cm = WKT_COORD.matcher(wkt);
        while (cm.find()) {
            double lon = Double.parseDouble(cm.group(1));
            double lat = Double.parseDouble(cm.group(2));
            coords.add(new double[]{lon, lat});
        }

        if (!coords.isEmpty()) {
            double sumLon = 0, sumLat = 0;
            for (double[] c : coords) { sumLon += c[0]; sumLat += c[1]; }
            int n = coords.size();
            return new String[]{String.valueOf(sumLon / n), String.valueOf(sumLat / n)};
        }
        return null;
    }

    public static String lonLatToWkb(String lon, String lat) {
        try {
            double lonValue = Double.parseDouble(lon);
            double latValue = Double.parseDouble(lat);

            ByteBuffer buffer = ByteBuffer.allocate(21);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            buffer.put((byte) 1);
            buffer.putInt(1);
            buffer.putDouble(lonValue);
            buffer.putDouble(latValue);

            return byteArrayToHexString(buffer.array());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
