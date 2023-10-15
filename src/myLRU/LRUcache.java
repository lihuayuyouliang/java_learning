package myLRU;

import java.util.HashMap;
import java.util.Map;

public class LRUcache {
    class LinkedNode{
        int key;
        String value;
        LinkedNode next;
        LinkedNode pre;
        public LinkedNode(){}
        public LinkedNode(int key,String value){
            this.key=key;
            this.value=value;
        }
    }
    private int size;
    private int cap;
    private LinkedNode head;
    private LinkedNode tail;
    Map<Integer,LinkedNode> map=new HashMap<>();
    public LRUcache(int cap){
        this.size=0;
        this.cap=cap;
        head=new LinkedNode();
        tail=new LinkedNode();
        head.next=tail;
        tail.pre=head;
    }
    public String get(int key){
        LinkedNode node=map.get(key);
        if (node==null){
            return "不存在";
        }
        moveToHead(node);
        return node.value;
    }
    public void put(int key,String value){
        LinkedNode node=map.get(key);
        if (node!=null){
            node.value=value;
            moveToHead(node);
        }else {
            LinkedNode newNode=new LinkedNode(key,value);
            map.put(key,newNode);
            addToHead(newNode);
            size++;
            if (size>cap){
                LinkedNode tailNode=removeTail();
                map.remove(tailNode.key);
                size--;
            }
        }
    }
    public void moveToHead(LinkedNode node){
        removeNode(node);
        addToHead(node);
    }
    public LinkedNode removeTail(){
        LinkedNode node=tail.pre;
        removeNode(node);
        return node;
    }
    public void removeNode(LinkedNode node){
        node.pre.next=node.next;
        node.next.pre=node.pre;
    }
    public void addToHead(LinkedNode node){
        head.next.pre=node;
        node.next=head.next;
        head.next=node;
        node.pre=head;
    }
    public static void main(String[] args) {
        LRUcache lrUcache=new LRUcache(5);
        lrUcache.put(0,"北京");
        lrUcache.put(1,"武汉");
        lrUcache.put(2,"南京");
        lrUcache.get(0);
        lrUcache.get(2);
        lrUcache.put(3,"成都");
        lrUcache.put(4,"厦门");
        lrUcache.put(5,"深圳");
    }
}