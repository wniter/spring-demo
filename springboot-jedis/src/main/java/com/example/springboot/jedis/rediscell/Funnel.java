package com.example.springboot.jedis.rediscell;

/**
 * 漏斗容器
 */
public class Funnel {
    //漏斗容量
    int capacity;
    //漏斗流水速率
    float leakingRate;
    //漏斗剩余容量
    int leftQuota;
    //上一次流水时间
    long leakingTs;

    public Funnel(int capacity, float leakingRate) {
        this.capacity = capacity;
        this.leakingRate = leakingRate;
        this.leftQuota = leftQuota;
        this.leakingTs = leakingTs;
    }

    /**
     * 漏斗漏水，释放空间
     */
    void makeWater() {
        long nowTs = System.currentTimeMillis();
        //距离上一次漏斗的时间差
        long deltaTs = nowTs - leakingTs;
        //到这次漏斗时，上一次漏斗一共流了多少水
        int deltaQuota = (int) (deltaTs * leakingRate);
        //间隔时间太长，整数数字太大益处
        if (deltaQuota < 0) {
            this.leftQuota = capacity;
            this.leakingTs = nowTs;
        }
        // 腾出的空间太小，最小单位为 1
        if (deltaQuota < 1) {
            return;
        }
        this.leftQuota += deltaQuota;
        this.leakingTs = nowTs;
        if (this.leftQuota > this.capacity) {
            this.leftQuota = this.capacity;
        }
    }
    /**
     * 往漏斗灌水
     *
     * @param quota 灌入容量
     * @return 是否灌入成功
     */
    boolean watering(int quota) {
        makeWater();
        if (this.leftQuota >= quota) {
            this.leftQuota -= quota;
            return true;
        }
        return false;
    }
}


