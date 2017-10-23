1.

    Java从函数式编程中引入的两个核心思想: 将方法和Lambda作为一等值看，以及在没有可变共享状态时,函数或方法可以有效、安全地并行执行。
    
    注:这里所说地"一等值"是指类似于java中地基本数据类型，可以直接作为方法参数传递地那些类型

2.  
    行为参数化: 就是可以帮助你处理频繁变更的需求的一种软件开发模式，一言以蔽之，它意味着拿出一个代码块准备好却不去执行它。这个代码以后可以被你程序的其他部分调用，这意味着你可以推迟这块代码的执行。例如，你可以将代码作为参数传递给另一个方法，稍后再去执行它/这样，这个方法就基于那块代码被参数化了。

3.  
    Don’t Repeat Yourself - 软件工程原则

4. 在哪里可以使用Lambda表达式:
    
    可以在函数式接口上使用Lambda表达式

5. 什么是函数式接口:

    函数式接口就是只定义一个抽象方法的接口
    
    ```
    // 这就是一个函数式接口
    public interface Comparator<T>{
        int compare(T o1, T o2);
    }
    
    ```
    用函数式接口可以干什么? 
    
    Lambda表达式允许直接以内联的形式为函数式接口的抽象方法提供实现，并把整个表达式作为函数式接口的实例(Lambda表达式是一个函数式接口的一个具体实现实例)。用内部类可以完成同样的事情，但是比较笨拙。
    
    

6. 默认方法:


7. 函数描述符:

    函数式接口的抽象方法签名基本上就是Lambda表达式的签名。我们将这种抽象方法叫做函数描述符。
    
8. Lambda表达式可以被赋给一个变量，或传递给一个接受函数式接口作为参数的方法，当然这个Lambda表达式的签名要和函数式接口的抽象方法一样。

9. @FunctionalInterface注解?
    
    这个注解标注用于表示该接口会被设计成一个函数式接口。如果使用@FunctionalIntercace定义了一个接口，而它却不是函数式接口的话，编译器将返回一个提原因的错误。@FunctionalInter- face不是必需的，但对于为此设计的接口而言，使用它是比较好的做法。

10. 在使用函数式接口传递Lambda的前提是需要自己定义所需的函数式接口


11. Java8定义好的函数式接口:

    Predicate:
    需要表示一个设计类型T的布尔表达式时可以使用这个接口
    
    ```
    @FunctionalInterface
    public interfance Predicate<T>{
        boolean test(T t);
    }
    
    //Predicate函数式接口使用示例
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        
        List<T> results = new ArrayList<>();
        for(T s : list){
            
            if(p.test(s)){
                
                results.add(s);
            }
        
        }
        return results;
    }
    
    ```
    Consumer
    ```
    @FunctionalInterfance
    public interface Consumer<T>{
   
        void accept(T t);
  
    }
    
    // 使用示例
    public static <T> void forEach(List<T> list, Consumer<T> c){
        for(T l : list){
            c.accept(l);
        }
        
    }
    
    //调用forEach方法进行遍历集合，并实现打印
    forEach(
        Arrays.asList(1,2,3,4,5),
        (Integer i) -> System.out.println(i)
    )
    
    ```
    
    Function
   
    ```
    @FunctionalInterface
    public interface Function<T, R>{
        
        R apply(T t);
    
    }
    
    //使用示例
    public static <T, R> List<R> map(List<T> list, Function<T, R> f){
        
        List<R> results = new ArrayList<>();
        
        for(T l : list){
            
            results.add(f.apply(l));
        
        }
     
        return results;
    }
    
    // [7,2,6]
    List<Integer> l = map(Arrays.asList("lambdas","in","action"),
        //Lambda表达式的参数类型要和函数方式接口中声明的方法类型一致
        (String s)->s.length()
    )
    
    
    
    
    
    ```

12. 泛型只能绑定到引用类型

13. Java里有一个将原始类型转换为对应的引用类型的机制。这个机制叫做装箱(Boxing)。相反的操作，将引用类型转换成原始类型，叫做拆箱。 

```
(int 被装箱为Integer)
List<Integer> list = new ArrayList<>(); 
for (int i = 300; i < 400; i++)
{     
list.add(i); 
}
```
但是在性能方面需要付出代价。装箱后的本质就是把原始类型包裹起来，并保存在堆里。因此，装箱后的值需要更多的内存，并需要额外的内存搜索来获取被包裹的原始值。 

使用IntPredicate来避免在输入和输入都是原始类型时的装箱操作

类型检查
--

Lambda表达式可以为函数式接口生成一个实例，但是Lambda表达式本身并不包含它在哪个函数式接口的信息，因此java虚拟机需要对Lambda表达式进行类型检查
从而匹配对应的函数式接口。

Labmda的类型是从Lambda的上下文推断出来的。上下文(比如，接受它传递的方法的参数，或接受它的值的局部变量)中Lambda表达式需要的类型称为目标类型。

- 类型检查的过程:
 ![image](http://op225mmxn.bkt.clouddn.com/Lambda%E8%A1%A8%E8%BE%BE%E5%BC%8F%E7%B1%BB%E5%9E%8B%E6%A3%80%E6%B5%8B%E7%9A%84%E8%BF%87%E7%A8%8B.png)   
   过程如下:
   
   1.  首先找出filter方法的声明
   2.  要求第二个参数是Predicate<Apple>（目标类型）类型
   3.  Predicate<Apple>是一个函数式接口，它定义了一个叫做test的抽象方法
   4.  test方法描述了一个函数描述符，它可以接受一个Apple对象并返回一个boolean
   5.  因此filter的任何实际参数都必须匹配这个要求
 
    由上总结:
 
        在书写Lambda表达式时，表达式必须与函数式接口中定义的抽象方法的类型保持一致：
        
        a. 抽象方法参数个数
        b. 抽象方法参数的类型
        c. 返回值的类型

- 特殊的void兼容规则
    
    如果一个Lambda表达式的内容是一个语句表达式，它就和一个返回void的函数描述符兼容（当然需要参数列表也兼容）
    
    ![image](http://op225mmxn.bkt.clouddn.com/%E7%89%B9%E6%AE%8A%E7%9A%84void%E5%85%BC%E5%AE%B9%E8%A7%84%E5%88%99.png)
    
    由上总结:
        
        当函数式接口中的目标方法的返回值是void时，Lambda表达式的主体内容当是一个表达式的时候不对其表达式的返回结果进行限定。


类型推断
--

### 1.参数类型推断

Java编译器会从上下文(目标类型)推断出用什么函数式接口来配合Lambda表达式，这表明它可以推断出适合Lambda的签名，因此函数描描述符可以通过目标类型
来得到。这样做的好处在于编译器可以了解Lambda表达式的参数类型，这样就可以在Lambda语法中省去标注参数的类型。

```
//这里的参数a,没有显式的声明类型
List<Apple> greenApples = filter(inventory, a-> "green".equals(a.getColor()));

//当Lambda仅有一个类型需要推断的参数时，参数名称两边的符合也可以省略
```


注意点:
    
    省略参数类型有可能会降低代码的可读性
    



### 2.在lambda中使用局部变量

- Lambda表达式只能捕获指派给它们的局部变量一次
    
   ``` 
   
   int portNum = 8000;
   
   Runnable r = ()-> System.out.println(portNum);
   
   portNum = 8080;
   
   ```
   
   上面这段代码将无法编译通过，因为portNum被赋值两次

- 为什么Lambda表达式对局部变量存在限制？

    如果Lambda表达式可以直接访问局部变量，而且Lambda是在一个线程中使用的，则使用Lambda的线程，可能会在分配
    该变量的线程将这个变量回收之后去访问这个变量。从而产生线程安全问题（栈中的数据在线程之间不共享）。因此，Java在访问自由局部变量时，实际上
    是访问的它的副本而不是访问的原始变量。

- 不鼓励使用改变外部变量的典型命令式编程模式
      

- 在java中实例变量和局部变量的区别：
   
    实例变量存储在堆中，局部变量存储在栈中。
    堆中的数据在线程之间是共享的，因此不存在线程不安全的问题
  
方法引用
--

使用方法引用对之前排序的例子进行修改:

``` 
先前:
    
    inventory.sort((Apple a1, Apple a2)-> a1.getWeight().compareTo(a2.getWeight()));

之后(使用方法引用和java.util.Comparator.comparing):
    
    inventory.sort(comparing(Apple::getWeight));


```

方法引用可以被看作仅仅调用特定方法的Lambda的一种快捷写法。
它的基本思想是: 如果一个Lambda所表示的只是“直接调用这个方法”,那最好还是用名称来调用它，而不是去描述如何去调用它。
事实上,方法引用就是根据已有的方法去创建Lambda表达式。

- 书写规则:
    
    目标引用放在分隔符 ::前,方法名称放在后面
    
    ``` 
     APPle :: getWeight 
     
     等价:
     
     (Apple a) -> a.getWeight()
    ```


注意: 只是根据已有的方法实现来创建Lambda表达式，并没有直接调用

- Lambda等价方法引用的例子

    ```
        1. (Apple a)-> a.getWeight() 等价: Apple :: getWeight
        2. () -> Thread.currentThread().dumpStack() 等价: Thread.currentThread()::dumpStack
        3. (Sring str,int i)-> str.substring(i) 等价: String::sustring
        
        
    
    ```

三类方法引用:

1. 指向静态方法的方法引用(例如 Integer的parseInt方法,写作 Integer::parseInt)

2. 指向任意类型实例方法的方法引用(例如String的legnth方法，写作String::length)

3. 指向现有对象的实例方法的引用(假设你有一个局部变量expensiveTransaction用于存放Transaction类型的对象，它支持实例方法getValue),那么
   你就可以写expensiveTransaction::getValue
    
