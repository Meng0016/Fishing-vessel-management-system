package com.mengship.utils;

import java.util.concurrent.locks.ReentrantLock;

public class IdGenerator {
    private static final long twepoch = 1404741526058L;
    private static final long idBits = 5L;
    private static final long groupIdBits = 5L;
    private static final long sequenceBits = 12L;
    private static final long maxId = 31L;
    private static final long maxGroupId = 31L;
    private static final long sequenceMask = 4095L;
    private static final long idShift = 12L;
    private static final long groupIdShift = 17L;
    private static final long timestampLeftShift = 22L;
    private static long lastTimestamp = -1L;
    private ReentrantLock lock = new ReentrantLock();
    private long sequence = 0L;
    private final long databaseId;
    private final long dbGroupId;
    private static ThreadLocal<IdGenerator> defaultIdGenerator = new ThreadLocal();

    public static IdGenerator getDefault() {
        if (defaultIdGenerator.get() == null) {
            defaultIdGenerator.set(new IdGenerator(Thread.currentThread().getId() % 31L, Thread.currentThread().getId() % 31L));
        }

        return (IdGenerator)defaultIdGenerator.get();
    }

    public IdGenerator(long groupId, long id) {
        if (id <= 31L && id >= 0L) {
            if (groupId <= 31L && groupId >= 0L) {
                this.databaseId = id;
                this.dbGroupId = groupId;
            } else {
                throw new IllegalArgumentException("集群Id不能大于%d或小于0");
            }
        } else {
            throw new IllegalArgumentException("Id不能大于%d或小于0");
        }
    }

    public long nextId() {
        this.lock.lock();

        long var5;
        try {
            long timestamp = this.timeGen();
            if (timestamp < lastTimestamp) {
                throw new RuntimeException("系统时间倒退 " + (lastTimestamp - timestamp) + "毫秒，不允许生成id。");
            }

            if (lastTimestamp == timestamp) {
                this.sequence = this.sequence + 1L & 4095L;
                if (this.sequence == 0L) {
                    timestamp = this.toNextMillis(lastTimestamp);
                }
            } else {
                this.sequence = 0L;
            }

            lastTimestamp = timestamp;
            long nextId = timestamp - 1404741526058L << 22 | this.dbGroupId << 17 | this.databaseId << 12 | this.sequence;
            var5 = nextId;
        } finally {
            this.lock.unlock();
        }

        return var5;
    }

    private long toNextMillis(long lastTimestamp) {
        long timestamp;
        for(timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
            ;
        }

        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}