Необходимо реализовать LRU-кэш на основе двусвязного списка.

Задание состоит из двух частей, которые необходимо выполнять последовательно.

### 1. Реализация двусвязного списка
Список имеет структуру вида:  
null <- (prev) front <-> ... <-> elem <-> ... <-> back (next) -> null

Необходимо реализовать следующий интерфейс List:
- int len()                           // длина списка
- ListItem front()                    // первый элемент списка
- ListItem back()                     // последний элемент списка
- ListItem pushFront(Object o)        // добавить значение в начало
- ListItem pushBack(Object o)         // добавить значение в конец
- void remove(ListItem i)             // удалить элемент
- void moveToFront(ListItem i)        // переместить элемент в начало

Считаем, что методы remove и moveToFront вызываются только от существующих в списке элементов.

Элемент списка ListItem:
- Object value        // значение
- ListItem next       // следующий элемент
- ListItem prev       // предыдущий элемент

Сложность всех операций должна быть O(1), т.е. не должно быть мест, где осуществляется полный обход списка.

### 2. Реализация кэша на основе ранее написанного списка
Необходимо реализовать следующий интерфейс Cache:
- boolean set(Key key, Object value)    // добавить значение в кэш по ключу.
- Object get(Key key)                   // получить значение из кэша по ключу.
- clear()                               // очистить кэш.

Структура кэша:
- ёмкость (количество сохраняемых в кэше элементов);
- очередь последних используемых элементов на основе двусвязного списка;
- словарь, отображающий ключ (строка) на элемент очереди.

Элемент кэша хранит в себе ключ, по которому он лежит в словаре, и само значение.
Для чего это нужно понятно из алгоритма работы кэша (см. ниже).

Сложность операций set/get должна быть O(1), при желании clear тоже можно сделать О(1).

Алгоритм работы кэша:
- при добавлении элемента:
    - если элемент присутствует в словаре, то обновить его значение и переместить элемент в начало очереди;
    - если элемента нет в словаре, то добавить в словарь и в начало очереди
      (при этом, если размер очереди больше ёмкости кэша,
      то необходимо удалить последний элемент из очереди и его значение из словаря);
    - возвращаемое значение - флаг, присутствовал ли элемент в кэше.
- при получении элемента:
    - если элемент присутствует в словаре, то переместить элемент в начало очереди и вернуть его значение;
    - если элемента нет в словаре, то вернуть null (работа с кешом похожа на работу с map).

Ожидаются следующие тесты:
- на логику выталкивания элементов из-за размера очереди
(например: n = 3, добавили 4 элемента - 1-й из кэша вытолкнулся);
- на логику выталкивания давно используемых элементов
(например: n = 3, добавили 3 элемента, обратились несколько раз к разным элементам:
изменили значение, получили значение и пр. - добавили 4-й элемент,
из первой тройки вытолкнется тот элемент, что был затронут наиболее давно).

(*) Дополнительное задание: сделать кэш горутино-безопасным.
