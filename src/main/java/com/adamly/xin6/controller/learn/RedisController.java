package com.adamly.xin6.controller.learn;

import com.adamly.xin6.controller.BaseController;
import com.adamly.xin6.response.CommonReturnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @author adamly
 * @version 1.0
 * @date 2019/12/10 11:03
 */
@RestController
@RequestMapping("/learnredis")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class RedisController extends BaseController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public final static Logger log= LoggerFactory.getLogger(RedisController.class);

    @RequestMapping("/stringhash")
    public CommonReturnType testStringAndHash(){
//        redisTemplate.opsForValue().set("key1","value1");
//        redisTemplate.opsForValue().set("int_key","1");
        stringRedisTemplate.opsForValue().set("int","1");
        stringRedisTemplate.opsForValue().increment("int",5);
        Jedis jedis=(Jedis)stringRedisTemplate.getConnectionFactory().getConnection()
                .getNativeConnection();
        jedis.decr("int");

        Map<String,String> hash=new HashMap<String,String>();
        hash.put("hk1","hv1");
        hash.put("hk2","hv2");
        stringRedisTemplate.opsForHash().putAll("hash",hash);
        stringRedisTemplate.opsForHash().put("hash","hk3","hv3");
        BoundHashOperations hashOperations=stringRedisTemplate.boundHashOps("hash");
        hashOperations.delete("hk1","hk2");
        hashOperations.put("hk4","hv4");
        System.out.println("StringAndHash:");
//        log.trace("trace level");
//        log.debug("debug level");
        log.info("info level12345");
//        log.warn("warn level");
//        log.error("error level");
        return CommonReturnType.create("testStringAndHash");
    }

    @RequestMapping("/list")
    public CommonReturnType testList(){
        stringRedisTemplate.opsForList().leftPushAll("list1",
                "v1","v2","v3","v4","v5");
        stringRedisTemplate.opsForList().rightPushAll("list2",
                "v1","v2","v3","v4","v5");
        BoundListOperations listOperations=stringRedisTemplate.boundListOps("list2");
        Long size=listOperations.size();
        Object result2=listOperations.index(1);
        List list=listOperations.range(0,size-2);

        listOperations.leftPush("v0");
        Object result1= listOperations.rightPop();
        System.out.println("list:"+result1+"++"+result2+"++"+size+"+\n+"+list);
        return CommonReturnType.create("testList");
    }

    @RequestMapping("/set")
    public CommonReturnType testSet(){
        stringRedisTemplate.opsForSet().add("set1",
                "v1","v1","v2","v3","v4","v5");
        stringRedisTemplate.opsForSet().add("set2",
                "v3","v7","v9","v10","v5");
        BoundSetOperations setOperations=stringRedisTemplate.boundSetOps("set1");
        setOperations.add("v2","v7","v8");
        setOperations.remove("v4","v5");
        Long size=setOperations.size();
        Set set=setOperations.members();

        Set inter=setOperations.intersect("set2");
        setOperations.intersectAndStore("set2","inter");
        Set diff=setOperations.diff("set2");
        setOperations.diffAndStore("set2","diff");
        Set union=setOperations.union("set2");
        setOperations.unionAndStore("set2","union");
        System.out.println("set:"+size+"+\n+"+set+"+\n+"+size+"+\n+"+inter+"+\n+"+diff+"+\n+"+union);
        return CommonReturnType.create("testList");
    }

    @RequestMapping("/zset")
    public CommonReturnType testZset(){
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet=new HashSet<>();
        for(int i=1;i<=9;i++){
            typedTupleSet.add(new DefaultTypedTuple<String>("zvalue"+i,i*0.1));
        }
        stringRedisTemplate.opsForZSet().add("zset1",typedTupleSet);
        BoundZSetOperations zSetOperations=stringRedisTemplate.boundZSetOps("zset1");
        zSetOperations.add("zvalue10",0.26);
        zSetOperations.remove("zvalue7");
        double score=zSetOperations.score("zvalue8");

        Set<String> setRange=zSetOperations.range(1,6);
        Set<ZSetOperations.TypedTuple<String>> zsetRange=zSetOperations.rangeWithScores(1,6);
        Set<String> setScore=zSetOperations.rangeByScore(0.2,0.6);
        Set<ZSetOperations.TypedTuple<String>> zsetScore=zSetOperations.rangeByScoreWithScores(0.2,0.6);
        RedisZSetCommands.Range range=new RedisZSetCommands.Range();
        range.gt("zvalue3");range.lte("zvalue8");
        Set<String> setLex=zSetOperations.rangeByLex(range);
        System.out.println("zset:"+score+"+\n+"+setRange+"+\n+"+zsetRange+"+\n+"+setScore+"+\n+"+zsetScore+"+\n+"+setLex);
        return CommonReturnType.create("testList");
    }

    @RequestMapping("/transaction")
    public CommonReturnType testTransaction(){
        redisTemplate.opsForValue().set("key1","value1");
        List list = (List)redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
            operations.watch("key1");
            operations.multi();
            operations.opsForValue().set("key2", "value2");
//            operations.opsForValue().increment("key1",2);
            Object value2 = operations.opsForValue().get("key2");
            System.out.println("value2:" + value2);
            operations.opsForValue().set("key3", "vakue3");
            Object value3 = operations.opsForValue().get("key3");
            System.out.println("value3:" + value3);
            return operations.exec();
            }
        });
//        List list = (List)redisTemplate.execute( (RedisOperations operations) -> {
//            operations.watch("key1");
//            operations.multi();
//            operations.opsForValue().set("key2", "vakue2");
////            operations.opsForValue().increment("key1",2);
//            Object value2 = operations.opsForValue().get("key2");
//            System.out.println("value2:" + value2);
//            operations.opsForValue().set("key3", "vakue3");
//            Object value3 = operations.opsForValue().get("key3");
//            System.out.println("value3:" + value3);
//            return operations.exec();
//        });
        System.out.println("list:"+list);
        return CommonReturnType.create("testTransaction");
    }

    @RequestMapping("/pipeline")
    public CommonReturnType testPipeline(){
        Long start=System.currentTimeMillis();
        List list =(List)redisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for (int i = 1; i <= 10; i++) {
//                    100000
                    operations.opsForValue().set("pipeline_" + i, "value_" + i);
                    String value = (String) operations.opsForValue().get("pipeline_" + i);
                    if (i == 10) {
                        System.out.println("命令只是进入队列，所以值为空【" + value + "】");
                    }
                }
                return null;
            }
        });
//        List list = (List)redisTemplate.executePipelined((RedisOperations operations) -> {
//            for (int i = 1; i <= 100000; i++) {
//                operations.opsForValue().set("pipeline_" + i, "value_" + i);
//                String value = (String) operations.opsForValue().get("pipeline_" + i);
//                if (i == 100000) {
//                    System.out.println("命令只是进入队列，所以值为空【" + value + "】");
//                }
//            }
//            return null;
//        });
        Long end =System.currentTimeMillis();
        System.out.println("time: "+(end-start)+" ms");
        return CommonReturnType.create("testPipeline");
    }

    @RequestMapping("/msg")
    public CommonReturnType testMsg(){
        redisTemplate.convertAndSend ("topic1", "msg" );
        stringRedisTemplate.convertAndSend("topic1", "msg1" );
        return CommonReturnType.create("testMsg");
    }

    @RequestMapping("/lua1")
    public CommonReturnType testLua1(){
        DefaultRedisScript<String> rs= new DefaultRedisScript<String>();
        rs.setScriptText("return 'Hello,RedisLua!' ");
        rs.setResultType(String.class);
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        String str = (String) redisTemplate.execute(rs,stringSerializer,stringSerializer,null);
        System.out.println("testLua1:"+str);
        return CommonReturnType.create("testLua1");
    }

    @RequestMapping("/lua2")
    public CommonReturnType testLua2(){
        DefaultRedisScript<Long> rs= new DefaultRedisScript<Long>();
        String lua = "redis.call('set',KEYS[1],ARGV[1]) \n"
                +"redis.call('set',KEYS[2],ARGV[2]) \n"
                +"local str1 = redis.call('get',KEYS[1])\n"
                +"local str2 = redis.call('get',KEYS[2])\n"
                +" if str1 == str2 then\n"
                +"return 1\n"
                +"end \n"
                +"return 0\n";
        System.out.println("testLua2:"+lua);
        rs.setScriptText(lua);
        rs.setResultType(Long.class);
        List<String> keyList =new ArrayList<>();
        keyList.add("lkey1");
        keyList.add("lkey2");
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        Long result = (Long) redisTemplate.execute(rs,stringSerializer,stringSerializer,keyList,"lvalue1","lvalue2");
        System.out.println("testLua2:"+result);
        return CommonReturnType.create("testLua2");
    }

}
















