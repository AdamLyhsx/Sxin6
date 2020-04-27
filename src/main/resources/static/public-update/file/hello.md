# hello
## hello

> 引用
>
> > 引用

>引用

**********

**hello**
*hello*
***hello***
~~hello~~

==哈哈哈哈哈哈哈哈哈哈或或==

-----------------------------------

- hello1
- hello2
+ hello3
+ hello4
* hello5
* hello6

1. hello1
   - hello1.1
   - hello1.2
   - hello1.3
2. hello2
3. hello3


`System.out.println("hello")`

```java
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public CommonReturnType getGoods(@RequestParam("id")Integer id) throws BusinessException {
        GoodsModel goodsModel=goodsService.getGoodsById(id);
        return CommonReturnType.create(conventVOFromModel(goodsModel));
    }
```

------------
![玻璃金字塔](https://up.enterdesk.com/edpic/46/68/bc/4668bc6790fc423669a65c9fae1472c9.jpg "回形针")

[我的博客](https://blog.csdn.net/NULLmk404/article/details/88982273)

-------
姓名|年龄|爱好
---|:--:|---:
ppx|12|皮
ppy|25|跑步


```flow
st=>start: 开始
op=>operation: My Operation
cond=>condition: Yes or No?
e=>end
st->op->cond
cond(yes)->e
cond(no)->op
&```






```

# hello

## hello

>| 为   | 分为非 | 二维 |
>| ---- | ------ | ---- |
>|      |        |      |
>|      |        |      |
>|      |        |      |
>
>hello
>hello

# hello

> hello
>
> >  hello



**hello**

*hello*

***hello***

~~hello~~

==hello==

-------------------------------------

- hello1
- hello2
- hello3
- 1. helo3.1
  2. hello3.2
  3. hello3.3



```java
//    创建商品的接口
    @RequestMapping(value="/create",method = RequestMethod.POST,consumes = {CONTENT_TYPE_FORMD})
    public CommonReturnType createGoods(@RequestParam(name="title")String title,
                                        @RequestParam(name="price")BigDecimal price,
                                        @RequestParam(name="stock")Integer stock,
                                        @RequestParam(name="description")String description,
                                        @RequestParam(name="imgUrl")String imgUrl) throws BusinessException {
//       调用商品创建服务
        int a;
        Integer stok;
        GoodsModel goodsModel=new GoodsModel();
        goodsModel.setTitle(title);
        goodsModel.setPrice(price);
        goodsModel.setStock(stock);
        goodsModel.setDescription(description);
        goodsModel.setImgUrl(imgUrl);
        GoodsModel goodsModelFromReturn=goodsService.createGoods(goodsModel);
//        model->vo
        return CommonReturnType.create(conventVOFromModel(goodsModel));
    }
```



`return CommonReturnType.create(conventVOFromModel(goodsModel));`



[baidu](baidu.com)

[google](google.com)



|   h   |   好   | h     |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |



| hhh  | hh   | hhhh |
| ---- | ---- | ---- |
| h    | dsvd | dsv  |











