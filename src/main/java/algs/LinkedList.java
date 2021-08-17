package algs;

import java.util.Iterator;

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

    }

    /**
     * 获取元素
     *
     * @param index 获取的元素索引
     * @return 指定索引的元素
     * @throws ArrayIndexOutOfBoundsException 如果索引越界（index < 0 || index >= size()）
     */
    public Element get(int index) {
        return null;
    }

    /**
     * 将元素存入指定位置
     *
     * @param index 元素存放的索引
     * @param e     存放到指定位置的元素
     * @throws ArrayIndexOutOfBoundsException 如果索引越界（index < 0 || index >= size()）
     */
    public void set(int index, Element e) {
    }

    /**
     * 移除指定的元素
     *
     * @param e 待移除的元素
     * @return 如果成功移除，则返回true，否则，返回false
     */
    public boolean remove(Element e) {
        return true;
    }

    /**
     * 获取数组的大小
     *
     * @return 数组的大小
     */
    public int size() {
        return size;
    }

    @Override
    public Iterator<Element> iterator() {
        return null;
    }
}
