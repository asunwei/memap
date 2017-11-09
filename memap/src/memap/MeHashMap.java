package memap;

import java.util.Objects;

public class MeHashMap<K, V> {
	Node<K,V>[] table;
	
	static class Node<K,V>{
		private int hash;
		private K key;
		private V value;
		private Node<K,V> next;
		public K getKey() {
			return key;
		}
		public void setKey(K key) {
			this.key = key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		public Node<K, V> getNext() {
			return next;
		}
		public void setNext(Node<K, V> next) {
			this.next = next;
		}
		public int getHash() {
			return hash;
		}
		public void setHash(int hash) {
			this.hash = hash;
		}
		public Node(int hash, K key, V value, Node<K, V> next) {
			super();
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}
		@Override
		public String toString() {
			return "Node [key=" + key + ", value=" + value + ", next=" + next + ", hash=" + hash + "]";
		}
	}
	
	public void put(K key,V value) {
		//获取hash值
		//int hash = key == null? 0 :key.hashCode();
		int hash = Objects.hashCode(key);
		
		//指定数组长度，初始化数组
		int length = 16;
		
		if(table == null) {
			table = new Node[length];
		}
		//根据key，算出key的哈希值
		int i = hash % length;
		//判断table[i]是否存在
		if(null == table[i]) {
			table[i] = new Node<K,V>(hash,key,value,null);
		} else {
			Node<K,V> node = table[i];
			//判断table[i].key 是否等于传入的key, 新传入的value替换hash位置的value
			if((hash == node.hash) && (node.key == key ||(key != null && node.key.equals(key)))){
				node.value = value;
			} else {
				//遍历链表上的next
				for(int count = 0;;count++) {
					//此hash值位置为空，
					if (null == node.next) {
						node.next = new Node<K,V>(hash,key,value,null);
						break;
					}
					if((node.next.hash == hash) && ((node.key == key ||(key != null && node.key.equals(key))))){
						node.next.value = value;
						break;
					}
					node = node.next;
				}
			}
		}
	}
	public V get(K key) {
		return null;
	}
}
