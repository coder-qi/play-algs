package algs;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 双向链表的实现
 */
public class LinkedList<Element> implements Iterable<Element> {

    private Node<Element> first;
    private Node<Element> last;
    private int size;

    private static class Node<Element> {
        Element element;
        Node prev;
        Node next;

        Node(Node prev, Element element, Node next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 添加元素
     *
     * @param e 添加的元素
     */
    public void add(Element e) {
        Node<Element> l = last;
        Node<Element> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    /**
     * 获取元素
     *
     * @param index 获取的元素索引
     * @return 指定索引的元素
     * @throws ArrayIndexOutOfBoundsException 如果索引越界（index < 0 || index >= size()）
     */
    public Element get(int index) {
        checkIndex(index);
        return getNode(index).element;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    private Node<Element> getNode(int index) {
        if (index < size / 2) {
            Node<Element> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<Element> x = last;
            for (int i = size - 1; i > index; i++) {
                x = x.prev;
            }
            return x;
        }
    }

    /**
     * 将元素存入指定位置
     *
     * @param index 元素存放的索引
     * @param e     存放到指定位置的元素
     * @throws ArrayIndexOutOfBoundsException 如果索引越界（index < 0 || index >= size()）
     */
    public void set(int index, Element e) {
        checkIndex(index);

        getNode(index).element = e;
    }

    /**
     * 移除指定的元素
     *
     * @param e 待移除的元素
     * @return 如果成功移除，则返回true，否则，返回false
     */
    public boolean remove(Element e) {
        if (e == null) {
            for (Node<Element> x = first; x != null; x = x.next) {
                if (x.element == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<Element> x = first; x != null; x = x.next) {
                if (e.equals(x.element)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    private void unlink(Node<Element> x) {
        Node<Element> prev = x.prev;
        Node<Element> next = x.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        size--;
    }

    /**
     * 获取数组的大小
     *
     * @return 数组的大小
     */
    public int size() {
        return size;
    }

    /**
     * 清除所有的元素
     */
    public void clear() {
        for (Node<Element> x = first; x != null; ) {
            Node<Element> next = x.next;
            x.prev = null;
            x.next = null;
            x.element = null;
            x = next;
        }
        first = null;
        last = null;
        size = 0;
    }

    /**
     * 从左往右查找数据的索引
     *
     * @param e 待查找的元素
     * @return 第一次查找到的元素的索引，如果没有查找到指定的元素，则返回-1
     */
    public int indexOf(Element e) {
        int index = 0;
        if (e == null) {
            for (Node<Element> x = first; x != null; x = x.next) {
                if (x.element == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<Element> x = first; x != null; x = x.next) {
                if (e.equals(x.element)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    /**
     * 数组是否为空
     *
     * @return 如果为空，返回true，否则，返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 是否包含指定的元素
     *
     * @param e 待测试的元素
     * @return 如果包含该元素，返回true，否则，返回false
     */
    public boolean contains(Element e) {
        return indexOf(e) != -1;
    }

    @Override
    public String toString() {
        Iterator<Element> it = iterator();
        if (!it.hasNext()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        while (true) {
            Element e = it.next();
            sb.append(e);
            if (!it.hasNext()) {
                return sb.append("]").toString();
            }
            sb.append(",");
        }
    }

    @Override
    public Iterator<Element> iterator() {

        return new Iterator<Element>() {

            Node<Element> x = first;

            @Override
            public boolean hasNext() {
                return x != null;
            }

            @Override
            public Element next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<Element> node = x;
                x = x.next;
                return node.element;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<>();
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
