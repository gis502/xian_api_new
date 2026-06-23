package com.gis.xian.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import jakarta.annotation.Resource;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class ExportService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private TransactionTemplate transactionTemplate;

    private static final byte[] UTF8_BOM = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};

    public void streamExportToStream(String tableName, OutputStream outputStream) {
        log.info("COPY 导出开始，表名: {}", tableName);
        long t0 = System.currentTimeMillis();

        try {
            transactionTemplate.execute(status -> {
                AtomicBoolean disconnected = new AtomicBoolean(false);
                jdbcTemplate.execute((Connection con) -> {
                    try {
                        outputStream.write(UTF8_BOM);

                        Class<?> baseConnClass = Class.forName("org.postgresql.core.BaseConnection");
                        Object baseConn = con.unwrap(baseConnClass);
                        Class<?> cmClass = Class.forName("org.postgresql.copy.CopyManager");
                        Object copyManager = cmClass.getConstructor(baseConnClass).newInstance(baseConn);
                        Method copyOut = cmClass.getMethod("copyOut", String.class, OutputStream.class);
                        String sql = "COPY " + tableName + " TO STDOUT WITH (FORMAT CSV, HEADER, ENCODING 'UTF8')";
                        long count = (long) copyOut.invoke(copyManager, sql, outputStream);

                        log.info("COPY 导出完成！{} 行, {} ms", count, System.currentTimeMillis() - t0);
                    } catch (Exception e) {
                        if (isClientDisconnect(e)) {
                            log.info("客户端断开连接，导出中止，表名: {}", tableName);
                            disconnected.set(true);
                        } else {
                            throw new RuntimeException(e);
                        }
                    }
                    return null;
                });
                if (disconnected.get()) {
                    status.setRollbackOnly();
                }
                return null;
            });
        } catch (Exception e) {
            if (isClientDisconnect(e)) {
                log.info("客户端断开连接，导出中止，表名: {}", tableName);
                return;
            }
            log.error("导出失败，表名: {}, 错误: {}", tableName, e.getMessage(), e);
            throw new RuntimeException("导出失败: " + e.getMessage(), e);
        }
    }

    /** 解包异常链，检查是否包含客户端断开连接 */
    private static boolean isClientDisconnect(Throwable e) {
        Throwable cause = e;
        while (cause != null) {
            if (cause instanceof InvocationTargetException) {
                cause = ((InvocationTargetException) cause).getTargetException();
                continue;
            }
            String msg = cause.getMessage();
            if (msg != null) {
                if (msg.contains("Connection reset")
                        || msg.contains("connection reset")
                        || msg.contains("ClientAbortException")
                        || msg.contains("AsyncRequestNotUsableException")
                        || msg.contains("中止了一个已建立的连接")
                        || msg.contains("软件中止")
                        || msg.contains("abort")) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }
}
