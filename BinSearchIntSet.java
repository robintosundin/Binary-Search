/**
 * Implements basic functionality for an ADT with integer elements utilizing Binary Search for good performance.
 * @author Robin Sundin
 * @version 1.0
 */
public class BinSearchIntSet implements IntSet{
	private int[] list;
	private int nonNullElements = 0; // Actual elements in list that are not null
	public BinSearchIntSet(){
		list = new int[0];
	}

	private void extendCap(){
		int[] tmp = new int[(list.length+1)*2];
		for(int i=0;i<list.length;i++){
			tmp[i]=list[i];
		}
		list = tmp;
	}
	private int indexOfKey(int[] list, int key){
		int lo=0;
		int hi=nonNullElements - 1; // indexing starts from 0
		while(lo <= hi){
			int mid = lo + (hi-lo)/2;
			if(key<list[mid])
				hi=mid-1;
			else if(key>list[mid])
				lo=mid+1;
			else return mid;
		}
		return -1; // Return -1 if key is not found in list
	}
	public void add(int element){
		if(contains(element))
			return;
		if(nonNullElements>=list.length) // If not enough capacity, extend it
			extendCap();
		for(int i=0;i<nonNullElements;i++){
			if(element<list[i]){
				for(int k=nonNullElements;k>i;k--) // shifts elements in list to right
					list[k]=list[k-1];
				nonNullElements++;
				list[i]=element;
				return;
			}	
		}
		nonNullElements++; // One element is added
		list[nonNullElements-1]=element;
		return;
	}
	public boolean contains(int element){
		return indexOfKey(list,element)!=-1; //indexOfKey only returns -1 if element not in list
	}

	public void remove(int element){
		int removeIndex = indexOfKey(list,element);
		if(nonNullElements<=0)
			return;
		if(!contains(element))
			return;
		for(int k=removeIndex;k<nonNullElements-1;k++) // Shifts elements in list to the left
			list[k]=list[k+1];
		nonNullElements--; // One element has been removed
		return;
	}
}