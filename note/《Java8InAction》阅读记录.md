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

