# java8 lambda表达式

### lambda表达式

lambda表达式可以将方法作为参数传递给另外一个参数，也可以作为一个参数的返回值


### 函数式接口

FunctionInterface注解相当于是Overwrite注解，提供一个接口只有一个抽象方法的检查，
如果一个接口只有抽象方法，但是没有生命为FuctionInterface的话，也会当成是函数式接口

函数式接口的本质：
* java中的lambda表达式是对象，继承Object，实现函数式接口的对象
* 如果声明两个抽象方法的接口，其中的一个方法是Object类的方法（例如toString），
  由于子类实现这个接口时，会有默认Object类的实现方法，所以相当于只有一个抽象方法，此时这个接口也是函数式接口。总结：一个接口具有覆盖Object类的方法时，函数式接口的方法不会增加，也就是说会被计为函数式接口
  
### 常用的函数式接口
* Supplier用于获取一个值
* Function用于传递一个值，返回另外一个值
* Predicate用于传递一个值，然后返回true/false
* Consumer用于传递一个值，不返回值
* BiFunction用于传递两个值，返回另外一个值，是Function的加强版
* BiPredicate用于传递两个值，返回true/false
* BiConsumer用于传递两个值，不返回值

### 方法引用
* 对象方法引用  
```
    Student student = new Student();
    Supplier supplier = student::getName;
    supplier.get();
```
* 静态方法引用
```
    Supplier supplier = Student::staticMethod;
    supplier.get();
```
* 类的对象方法引用
```
    List<String> list = Lists.newArrayList("hello", "world", "hello world");
    list.stream().map(String::toUpperCase).forEach(System.out::println);
```
* 构造方法引用  
```
    Supplier<Student> studen = Student::new
```

### optional最佳实践


### flatMap的应用
flatMap是将多个流合并成一个流
```
    List<String> words = new ArrayList<String>();
    words.add("your");
    words.add("name");
    
    public static Stream<Character> characterStream(String s){  
        List<Character> result = new ArrayList<>();  
        for (char c : s.toCharArray()) 
            result.add(c);
        return result.stream();  
    }
      
    Stream<Stream<Character>> result = words.map(w -> characterStream(w));  
      
    Stream<Character> letters = words.flatMap(w -> characterStream(w));  
```
如果使用的是map方法，返回的是[ ...['y', 'o', 'u', 'r'], ['n', 'a', 'm', 'e']]
如果使用的是flatMap方法，返回的是['y', 'o', 'u', 'r', 'n', 'a', 'm', 'e']
这是map和flatMap的区别

### 分组
分组是将普通的集合进行分组，并且可以进行聚合计算，demo见GroupTest


### 分区
分区是分组的特例，分区的结果只有两种可能
```
    Map<Boolean, List<String>> collect2 = lists.stream().collect(Collectors.partitioningBy(str -> str.length() > 5));
```


### Collectors.toList方法理解

