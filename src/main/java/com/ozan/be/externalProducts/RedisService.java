package com.ozan.be.externalProducts;

import com.ozan.be.externalProducts.dtos.KasondaPriceResponseDTO;
import com.ozan.be.kasondaApi.dtos.ColorOption;
import com.ozan.be.kasondaApi.dtos.Product;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

  @Autowired private RedisTemplate<String, Object> redisTemplate;

  public boolean keyExists(String key) {
    return Boolean.TRUE.equals(redisTemplate.hasKey(key));
  }

  public Object getKeyValue(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  public void setKeyProductWithTtl(String key, Product product, long ttl) {
    redisTemplate.opsForValue().set(key, product, Duration.ofSeconds(ttl));
  }

  public void setKeyPriceWithTtl(String key, KasondaPriceResponseDTO price, long ttl) {
    redisTemplate.opsForValue().set(key, price, Duration.ofSeconds(ttl));
  }

  public void setKeyColorOptionWithTtl(String key, ColorOption color, long ttl) {
    redisTemplate.opsForValue().set(key, color, Duration.ofSeconds(ttl));
  }
}
