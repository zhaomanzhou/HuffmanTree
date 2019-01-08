public V put(K key, V value) {      
// 如果 key 为 null，调用 putForNullKey 方法进行处理     
    if (key == null)         
       return putForNullKey(value);      
       // 根据 key 的 keyCode 计算 Hash 值     
       int hash = hash(key.hashCode());      
       // 搜索指定 hash 值在对应 table 中的索引 
       int i = indexFor(hash, table.length);
       // 如果 i 索引处的 Entry 不为 null，通过循环不断遍历 e 元素的下一个元素    
       for (Entry<K,V> e = table[i]; e != null; e = e.next){         
          Object k;          
          // 找到指定 key 与需要放入的 key 相等（hash 值相同         // 通过 equals 比较放回 true）        
         if (e.hash == hash && ((k = e.key) == key || key.equals(k))) { 
             V oldValue = e.value;           
             e.value = value;              
             e.recordAccess(this);          
             return oldValue;        
         }   
      }      
      // 如果 i 索引处的 Entry 为 null，表明此处还没有 Entry   
      modCount++;    
      // 将 key、value 添加到 i 索引处   
      addEntry(hash, key, value, i);     
  return null; 
}```

>上面程序中用到了一个重要的内部接口：Map.Entry，每个 Map.Entry 其实就是一个 key-value 对。从上面程序中可以看出：当系统决定存储 HashMap 中的 key-value 对时，完全没有考虑 Entry 中的 value，仅仅只是根据 key 来计算并决定每个 Entry 的存储位置。这也说明了前面的结论：我们完全可以把 Map 集合中的 value 当成 key 的附属，当系统决定了 key 的存储位置之后，value 随之保存在那里即可。
上面方法提供了一个根据 hashCode() 返回值来计算 Hash 码的方法：hash()，这个方法是一个纯粹的数学计算，其方法如下：
```JAVA
static int hash(int h) { 
    h ^= (h >>> 20) ^ (h >>> 12); 
    return h ^ (h >>> 7) ^ (h >>> 4); 
 }```

>对于任意给定的对象，只要它的 hashCode() 返回值相同，那么程序调用 hash(int h) 方法所计算得到的 Hash 码值总是相同的。接下来程序会调用 indexFor(int h, int length) 方法来计算该对象应该保存在 table 数组的哪个索引处。indexFor(int h, int length) 方法的代码如下：
```JAVA
static int indexFor(int h, int length) { 
    return h & (length-1); 
}```

>这个方法非常巧妙，它总是通过 h&
(table.length -1) 来得到该对象的保存位置——而 HashMap 底层数组的长度总是 2 的 n 次方，这一点可参看后面关于 HashMap 构造器的介绍。
当 length 总是 2 的倍数时，h& (length-1)
将是一个非常巧妙的设计：
假设 h=5,length=16, 那么 h & length - 1 将得到 5；
如果 h=6,length=16, 那么 h & length - 1 将得到 6 ……
如果 h=15,length=16, 那么 h & length - 1 将得到 15；
但是当 h=16 时 , length=16 时，那么 h & length - 1 将得到 0 了；
当 h=17 时 , length=16 时，那么 h & length - 1 将得到 1 了……
这样保证计算得到的索引值总是位于 table 数组的索引之内。

>根据上面 put 方法的源代码可以看出，当程序试图将一个 key-value 对放入 HashMap 中时，程序首先根据该 key 的 hashCode() 返回值决定该 Entry 的存储位置：如果两个 Entry 的 key 的 hashCode() 返回值相同，那它们的存储位置相同。如果这两个 Entry 的 key 通过 equals 比较返回 true，新添加 Entry 的 value 将覆盖集合中原有 Entry 的 value，但 key 不会覆盖。如果这两个 Entry 的 key 通过 equals 比较返回 false，新添加的 Entry 将与集合中原有 Entry 形成 Entry 链，而且新添加的 Entry 位于 Entry 链的头部——具体说明继续看 addEntry() 方法的说明。
当向 HashMap 中添加 key-value 对，由其 key 的 hashCode() 返回值决定该 key-value 对（就是 Entry 对象）的存储位置。当两个 Entry 对象的 key 的 hashCode() 返回值相同时，将由 key 通过 eqauls() 比较值决定是采用覆盖行为（返回 true），还是产生 Entry 链（返回 false）。
上面程序中还调用了 addEntry(hash, key, value, i); 代码，其中 addEntry 是 HashMap 提供的一个包访问权限的方法，该方法仅用于添加一个 key-value 对。下面是该方法的代码：
```JAVA
void addEntry(int hash, K key, V value, int bucketIndex) { 
    // 获取指定 bucketIndex 索引处的 
    Entry Entry<K,V> e = table[bucketIndex];     // ① 
    // 将新创建的 Entry 放入 bucketIndex 索引处，并让新的 Entry 指向原来的 Entry 
    table[bucketIndex] = new Entry<K,V>(hash, key, value, e); 
    // 如果 Map 中的 key-value 对的数量超过了极限 
    if (size++ >= threshold) 
    // 把 table 对象的长度扩充到 2 倍。 
        resize(2 * table.length);    // ②
}

。