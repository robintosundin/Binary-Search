import java.util.ArrayList;
/**
 * Implements basic functionality for an ADT utilizing Binary Search for good performance.
 * @author Robin Sundin
 * @version 1.0
 */
public class BinSearchGenSet<E extends Comparable<? super E>> implements GenSet<E>{
	private ArrayList<E> list;
	public BinSearchGenSet(){
		list = new ArrayList<E>();
	}
	private int indexOfKey(ArrayList<E> list, E keyElement){
		int lo=0;
		int hi=list.size() - 1;
		while(lo <= hi){
			int mid = lo + (hi-lo)/2;
			if(keyElement.compareTo(list.get(mid))<0) // keyElement<list[mid]
				hi=mid-1;
			else if(keyElement.compareTo(list.get(mid))>0) // as above but >
				lo=mid+1;
			else if(keyElement.compareTo(list.get(mid))==0) // keyElement==list[mid]
				return mid;
		}
		return -1;
	}
	public boolean contains(E element){
		if(list.isEmpty())
			return false;
		return indexOfKey(list,element)!=-1; //indexOfKey returns -1 if element not in list
	}
	public void add(E element){
		if(contains(element)) return;
		for(int i=0;i<list.size();i++){
			if(element.compareTo(list.get(i))<0){ // finds the proper index to add element at
				list.add(i,element);
				return;
			}	
		}
		list.add(element); // append because element is larger than anything in list
	}
	public void remove(E element){
		if(!contains(element)) return; // If element is not contained in list do nothing
		list.remove(indexOfKey(list,element)); // remove element at its index
	}
}