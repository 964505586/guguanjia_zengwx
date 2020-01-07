import com.alibaba.druid.pool.DruidDataSource;
import com.dfbz.config.SpringMybatisConfig;
import com.dfbz.mapper.AppVersionMapper;
import com.dfbz.service.AppVersionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CacheConfigTest
 * @Description:
 * @author: zwx
 * @Date: 2020/1/4 19:37
 * @version: V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatisConfig.class)
public class CacheConfigTest {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Test
    public void testRedisConnectionFactory(){
        Jedis jedis = (Jedis) redisConnectionFactory.getConnection().getNativeConnection();
        System.out.println(jedis.keys("*"));
    }

    @Test
    public void testRedistTemplate(){
//        System.out.println(redisTemplate.keys("*"));
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("redisTemplate","test");
        valueOperations.set("张无忌","张无忌测试");
        System.out.println("----------------------------");
        System.out.println(redisTemplate.keys("*"));
        System.out.println(redisTemplate.hasKey("redisTemplate"));
        String val1 = (String) valueOperations.get("redisTemplate");
        String val2 = (String) valueOperations.get("张无忌");
        System.out.println(val1);
        System.out.println(val2);
        System.out.println(redisTemplate.hasKey("num"));
        System.out.println(valueOperations.get("num"));
    }

    @Test
    public void testRedisTemplate2(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Lettuce");
        strings.add("redis");
        redisTemplate.opsForList().leftPushAll("redisList",strings);
        List<Object> redisList = redisTemplate.opsForList().range("redisList", 0, -1);
        for (Object o : redisList) {
            System.out.println(o);
        }
    }

}
