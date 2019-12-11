package com.adamly.xin6.controller.learn;

import com.adamly.xin6.controller.BaseController;
import com.adamly.xin6.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
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
@RequestMapping("/learnRedis")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class RedisController extends BaseController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/stringAndHash")
    public CommonReturnType testStringAndHash(){
        redisTemplate.opsForValue().set("key1","value1");
        redisTemplate.opsForValue().set("int_key","1");
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
}
















