package algs;

import java.util.Iterator;

/**
 * 数组类型的集合
 * @param <Element> 数组中存放的元素类型
 */
public class Array<Element> implements Iterable<Element> {

    private Object[] elements;
    private int size;

    /**
     * 构建一个初始化容量为0的数组
     */
    public Array() {
        this(0);
    }

    /**
     * 构造一个指定初始化容量的数组
     * @param capacity 初始化容量
     * @throws IllegalArgumentException 如果初始化容量小于0
     */
    public Array(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity:" + capacity);
        }
        elements = new Object[capacity];
    }

    /**
     * 添加元素
     * @param e 添加的元素
     */
    public void add(Element e) {
        if (size == elements.length) {
            resize(size * 2 + 1);
        }
        elements[size++] = e;
    }

    private void resize(int capacity) {
        Object[] newElements = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * 获取元素
     * @param index 获取的元素索引
     * @return 指定索引的元素
     * @throws ArrayIndexOutOfBoundsException 如果索引越界（index < 0 || index >= size()）
     */
    public Element get(int index) {
        checkIndex(index);
        return (Element) elements[index];
    }

    /**
     * 将元素存入指定位置
     * @param index 元素存放的索引
     * @param e 存放到指定位置的元素
     * @throws ArrayIndexOutOfBoundsException 如果索引越界（index < 0 || index >= size()）
     */
    public void set(int index, Element e) {
        checkIndex(index);
        elements[index] = e;
    }

    /**
     * 获取数组的大小
     * @return 数组的大小
     */
    public int size() {
        return size;
    }

    /**
     * 移除指定位置的元素
     * @param index 要移除的元素的索引
     * @throws ArrayIndexOutOfBoundsException 如果索引越界（index < 0 || index >= size()）
     */
    public void remove(int index) {
        checkIndex(index);

        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        size--;
        elements[size] = null;
        if (size > 0 && size < elements.length / 4) {
            resize(elements.length / 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    /**
     * 清除所有的元素
     */
    public void clear() {
        elements = new Object[0];
        size = 0;
    }

    /**
     * 从左往右查找数据的索引
     * @param e 待查找的元素
     * @return 第一次查找到的元素的索引，如果没有查找到指定的元素，则返回-1
     */
    public int indexOf(Element e) {
        for (int i = 0; i < size(); i++) {
            if (elements[i] == e) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 是否包含指定的元素
     * @param e 待测试的元素
     * @return 如果包含该元素，返回true，否则，返回false
     */
    public boolean contains(Element e) {
        return indexOf(e) != -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<Element> iterator() {
        return new Iterator<Element>() {
            int cursor;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public Element next() {
                return (Element) elements[cursor++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }

    public static void main(String[] args) {
        Array<String> names = new Array<>(3);
        names.add("张三");
        names.add("李四");
        names.add("王五");
        System.out.println(names);

        // 存放"赵六"和"钱七"
        names.add("赵六");
        names.add("钱七");
        System.out.println(names);
    }

}
