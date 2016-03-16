# Basic Comparisons

- [LinkedList vs ArrayList](#list)
- [HashMap vs. TreeMap vs. HashTable vs. LinkedHashMap](#map)
- [Set vs. List vs. Map](#setlistmap)
- [BST vs. HashTable](#search)
- [BFS vs. DFS](#graph)
- [Graph vs. Tree](#treegraph)


<a name="list" />

## Linkedlist vs. Arraylist vs. Vector

+ Operations
  - **LinkedList**
    + get(index): O(n)
    + add(element): O(1)
    + add(index, element): O(n)
    + remove(index): O(n)
    + Iterator.remove(): O(1), main benefit
    + Iterator.add(element): O(1), main benefit
  - **ArrayList**
    + get(index): O(1), main benefit
    + add(element): O(1) amortized(.add()是add到最后), worst case O(n) because need to resize array and copy(如果当前数组中size不够了 就要新建一个doublesize array并copy)
    + add(index, element): O(n-index)[n减index] amortized, worst case O(n)
    + remove(index): O(n-index), remove last is O(1)
    + Iterator.remove(): O(n-index)
    + Iterator.add(element): O(n-index)

+ Compare
  - Linkedlist
     + O(1) time insertions or removals using iterator, but only sequential access
     + finding a position in the linkedlist takes time O(n)
     + each element of linkedlist have more memory overhead because pointers need to be stored
     + linkedlist also implements queue interface which adds more methods such as offer(), peek(), poll()
  - Arraylist
     + fast random read access in O(1)
     + adding or removing element except at the end require shifting latter elements, if add more elements, need to allocate new array and copy old values
     + iterating over arraylist is technically faster
     + to avoid overhead of resizing, construct arraylist with initial capacity

+ Similarities
  - both are implementation of list interface
  - maintain elements in insertion order
  - non-synchronized(可以被多个线程同时访问), but can be made synchronized explicitly
  - if list is structurally modified after iterator is created, except through iterator's own remove or add method, iterator will throw exception（如果先取了iterator 再用list的remove add方法 然后再调用iterator会报错。数据结构有一个本身的计数器 iterator创建的时候会copy过来 每修改一次会将两个计数器都修改，如果两个计数器不一样，会报错）

+ When to use which
  - frequent addition and deletion: linkedlist
  - frequent search: arraylist
  - less memory to store many elements: arraylist

+ Vector
  - almost identical to arraylist, but is **synchronized**
  - more overhead because of synchronization
  - still use arraylist because they can make it synchronized explicitly



<a name="map" />

## HashMap vs. TreeMap vs. HashTable vs. LinkedHashMap

+ Overview
  - HashMap: implemented as a hash table, no ordering on keys or values
  - TreeMap: implemented based on red-black tree, ordered by key
  - LinkedHashMap: preserves insertion order
  - HashTable: synchronized in constrast to HashMap

+ HashMap
  - operation
    + put(): average O(1), worst O(n) when collision 
    + get(), remove(): O(1)
  - if key is self-defined objects, need to implement equals() and hashCode() [如果key是java自带的object，比如string integer之类的，是已经有hashCode()function的了，所以不用自定义hash function]
  - iteration order not predictable
  - does not allow two identical elements
    + equals(): return true if two references refer to the same object
    + hashCode(): return distinct integers for distinct objects
  - allow null keys and values

+ TreeMap
  - operation
    + put(), get(), remove(): worst O(logn)[查到最底层]
  - sorted by keys
  - object for key has to implement Comparable interface
  - only allow values to be null

+ HashTable
  - HashMap is roughly equivalent to HashTable, except hashmap is not synchronized and permits null

+ LinkedHashMap
  - operation: see arraylist
  - subclass of HashMap
  - linkedlist preserves the insertion order
  - allow null keys and values


<a name="setlistmap" />

## Set vs. List vs. Map

+ Set
  - unordered collection
  - unique objects (!e1.equals(e2))
  - contains at most one null

+ List
  - ordered collection(sequence)
  - access by index and search
  - may contain duplicates
  - user has control over where element inserted

+ Map
  - object that maps keys to values
  - no duplicated allowed
  - a key can map to at most one value


<a name="search" />

## BST vs. HashTable

+ BST
  - insert and retrieve: O(logn)
  - store data sorted
  - no need to know size of input in advance
  - memory efficient: do not reserve more memory than they need to

+ HashTable
  - insert and retrieve: O(1)
  - elements stored unsorted
  - with more inputs, collisions may show up
  - need more space than input data if to avoid collision
  - need to know data size, otherwise might need to resize the table


<a name="graph" />

## BFS vs. DFS

- Overview
  + search algorithms for graphs and trees
  + used for unordered graph and tree
  + graph representation
    - adjacent list: space O(V+E)
    - adjacent matrix: space O(V^2)

- BFS
  + start with root node
  + scan each node in the each level
  + while traversing each level, decide if target is found
  
- DFS
  + start with root node
  + follow on branch as far as possible until target is found or hit a leaf node
  + if hit a leaf node, continue search at the nearest ancestor


- Comparison
  + memory
    - BFS uses a large amount of memory because need to store pointers to a level(serious problem if the tree is very wide)
    - DFS uses less memory because no need to store all child pointers at a level
  + depend on the data you search for
    - look for people alive in family tree: DFS because targets are likely to be on the bottom of the tree
    - look for people who died: BFS
  + implementation
    - BFS: queue

    ```
      procedure BFS(G,v)
         initialize a queue Q
         Q.push(v)
         label v as visited
         while Q is not empty
            v <- Q.pop()
            for all edges from v to w in adjacent(v)
                if w is not visited
                   Q.push(w)
                   label w as visited
    ```

    - DFS: stack
      + recursive

        ```
          procedure DFS(G,v)
             label v as visited
             for all edges from v to w in adjacent(v)
                 if w is not visited
                     DFS(G,w)
        ```
      + iterative

        ```
           procedure DFS(G,v)
              initialize stack S
              S.push(v)
              while S is not empty
                 v <- S.pop()
                 if v is not visited
                    label v as visited
                       for all edges from v to w in adjacent(v)
                           S.push(w)
        ```
  + solution
    - BFS: complete algorithm, give optimal solution
    - DFS: suboptimal solution
  + complexity
    - BFS: worst time O(V+E), worst space O(V)
    - DFS: worst time O(v+E), worst space O(V)


<a name="treegraph" />

## Tree vs. Graph

- tree is restricted form of a graph(directed acyclic graph)
- trees have direction(parent/child relationship)
- tree does not contain cycles
- in trees, a child can only have one parent