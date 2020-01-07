import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class JedisTest {

    Jedis jedis;

    @Before
    public void before() {
        jedis = new Jedis("localhost", 6379);
        jedis.auth("123456");
    }

    @After
    public void after() {
        jedis.close();
    }

    /**
     * 测试连接
     */
    @Test
    public void test() {
        System.out.println(jedis.echo("hi"));
        System.out.println(jedis.ping());
        System.out.println(jedis.info());
    }
    // key
    @Test
    public void test01() {
        System.out.println(jedis.keys("*"));
    }
    // string类型
    @Test
    public void test02() {
        jedis.set("jedis", "Value");
        System.out.println(jedis.get("jedis"));
        System.out.println(jedis.strlen("root"));
    }

    // list类型
    @Test
    public void test03() {
        jedis.lpush("list", "a", "b");
        System.out.println(jedis.lrange("list", 0, -1));
    }

    // set
    @Test
    public void test04() {
        jedis.sadd("set", "手提电脑", "平板电脑", "手机");
        Set<String> set = jedis.smembers("set");
        for (String str : set) {
            System.out.println(str);
        }
    }

    // hash
    @Test
    public void test05() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "皮皮虾");
        map.put("age", "18");
        map.put("gender", "女");
        jedis.hmset("food:1", map);
        List<String> hvals = jedis.hvals("food:1");
        for (String str : hvals) {
            System.out.println(str);
        }

    }

    // sortedset
    @Test
    public void test06() {
        jedis.zadd("music", 1999, "朱丽叶");
        jedis.zadd("music", 1996, "love");
        jedis.zadd("music", 1997, "罗密欧");
        Set<String> musics = jedis.zrevrange("music", 0, -1);
        System.out.println(musics);
        for (String str : musics) {
            System.out.println(str);
        }
    }

}
