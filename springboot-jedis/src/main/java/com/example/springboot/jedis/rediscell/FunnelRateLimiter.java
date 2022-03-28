package com.example.springboot.jedis.rediscell;



import java.util.HashMap;
import java.util.Map;

public class FunnelRateLimiter {
    private Map<String, Funnel> funnelMap = new HashMap<>();

    public static void main(String[] args) {
        FunnelRateLimiter limiter = new FunnelRateLimiter();
        for (int i = 0; i< 20;i++){
            // 流水速率 quota/s 设置为 0.1，一分钟最多 6 个。
            System.out.println(limiter.isActionAllowed("Harry", "reply", 6, 0.1f));
        }
    }


    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s:%s", actionKey, userId);
        Funnel funnel = funnelMap.get(key);
        if (null == funnel) {
            funnel = new Funnel(capacity, leakingRate);
            funnelMap.put(key, funnel);
        }
        return funnel.watering(1);
    }
}
