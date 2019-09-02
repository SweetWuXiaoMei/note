# 代码简洁之道

### 第7章 异常
1. 表明方法错误时，不要使用code，而是使用异常
2. 使用try...catch...finally...
3. 尽量使用非检测异常（继承RuntimeException），可将检测异常转化为非检测异常（继承Exception），
   因为检测异常会破坏开闭原则，如果底层方法抛出异常，那么上层处理的每个方法都需要抛出异常
4. 封装异常，见代码chapter7
5. 将发生异常时的信息，尽量多的保留下来，便于发生排查错误
6. 不用返回null，通过返回空对象或者抛出异常的方式
7. 任何时候都不要传递null参数

### 第8章 边界
1. 在使用第三方api时，应该将它封装起来，例如在引入fastjson解析的包，可以使用自己定义JsonUtil，
   这样如将fastjson替换为jackson时，只需要替换Jsonutil即可
```
错误示例：
Map map = new HashMap();
Sensor sensor = map.get("sensorId");

正确示例：
public class Sensors {
    private Map map;
    
    public Sensor getSensor(String str) {
        return map.get("sensorId");
    }
}
```
2. 在调用第三方代码时，第三方的定义还未定义出来时，可将api信息自己封装成一个接口，待第三方api完成后，在定义
   适配器适配，可使用设计模式(适配器模式)
3. 当使用第三方api时，可学习性测试，即另起一个小项目，进行对api的了解和测试
4. 要善于封装第三方的服务，使用适配器模式或者另外封装成一个类

### 第9章 单元测试
1. 测试代码应该和生产代码一样简洁
2. 测试代码应该尽量保证一个断言语句
3. 测试代码不应该依靠另外一个测试代码
4. 测试代码应该可重复执行