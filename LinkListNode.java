import java.util.ArrayList;
import java.util.Comparator;

/**
 * 比较器
 * 如果是整型，就进行比较
 * 如果不是整型，就原样输出
 */
class Comparators {
    private static Comparator comparator = null;

    public static Comparator getComparator() {

        if (comparator == null) {
            comparator = (o1, o2) -> {
                if (o1 instanceof LinkListNode && o2 instanceof LinkListNode) {
                    if (((LinkListNode) o1).getData() instanceof Integer && ((LinkListNode) o2).getData() instanceof Integer) {
                        Integer num1 = (Integer) ((LinkListNode) o1).getData();
                        Integer num2 = (Integer) ((LinkListNode) o2).getData();
                        return num1 - num2;
                    }
                }
                return 0;
            };
        }
        return comparator;
    }
}

/*定义一个链表*/
public class LinkListNode<T extends Comparable<T>> {
    /*链表指针指向的下一个节点*/
    private LinkListNode<T> next;
    /*当前链表存储值*/
    private T data;

    public LinkListNode(T data) {
        this.data = data;
    }

    public LinkListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkListNode<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * @param first  第一个无序链表
     * @param second 第二位无序链表
     * @return java.util.LinkedList
     * @desciption merge 将两个无序链表合并成一个有序链表，从小到大排列
     * @author xianchun
     * @date 12:30 2021/3/23
     */
    public static LinkListNode merge(LinkListNode first, LinkListNode second) {

        if (first == null) {
            return second;
        } else if (second == null) {
            return first;
        } else {
            first.next = merge(first.next, second);
            return sort(first);
        }
    }

    /**
     * 将无序单链表转换成有序单链表
     *
     * @param node
     * @return
     */
    public static LinkListNode sort(LinkListNode node) {
        ArrayList<LinkListNode> nodeList = new ArrayList<>();
        LinkListNode tempNode = node;

        // 将Node添加到ArrayList
        while (tempNode != null) {
            nodeList.add(tempNode);
            tempNode = tempNode.next;
        }

        //通过自定义比较器进行比较
        nodeList.sort(Comparators.getComparator());

        //存放排序后的链表
        LinkListNode sorted = nodeList.get(0);
        LinkListNode temp = sorted;
        for (int i = 0; i < nodeList.size(); i++) {
            temp.next = nodeList.get(i);
            temp = temp.next;
        }
        temp.next = null;//一开始我这个地方写的temp=null，结果导致排序无限循环。这边画图理解比较好。

        return sorted;
    }

    /**
     * 输出结果
     *
     * @param list
     */
    public static void print(LinkListNode<Integer> list) {
        while (list != null) {
            System.out.print(list.getData() + " ");
            list = list.next;
        }
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        LinkListNode<Integer> first = new LinkListNode<>(3);
        first.next = new LinkListNode<>(4);
        first.next.next = new LinkListNode<>(7);


        LinkListNode<Integer> second = new LinkListNode<>(5);
        second.next = new LinkListNode<>(1);
        second.next.next = new LinkListNode<>(6);


        //打印
        LinkListNode<Integer> merge = merge(first, second);
        print(merge);

        LinkListNode<String> third = new LinkListNode<>("cc");
        third.next = new LinkListNode<>("aa");
        third.next.next = new LinkListNode<>("bb");

        LinkListNode<String> forth = new LinkListNode<>("dd");
        forth.next = new LinkListNode<>("ee");

        LinkListNode<Integer> merge1 = merge(third, forth);
        print(merge1);

    }


}