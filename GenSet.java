/**
 * Enforce a minimum of performing 3 operations on a generic set.
 */
public interface GenSet<E> {
    public void add(E element);
    public boolean contains(E element);
    public void remove(E element); 
}