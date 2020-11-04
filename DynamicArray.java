
public class DynamicArray<E> implements List<E> {
	
	private E[] array;
	private int size;
	
	public DynamicArray() {
		array = (E[]) new Object[16];
		size = 0;
	}
	
	private void checkIndex(int i, int upper) {
		if(i < 0 || i >= upper) {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return array[i];
	}

	@Override
	public void set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		array[i] = e;
	}

	private void expandArray() {
		E[] temp = (E[]) new Object[array.length*2];
		for(int i = 0; i < array.length; ++i) {
			temp[i] = array[i];
		}
		array = temp;
	}
	
	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size+1);
		
		if(size == array.length) {
			expandArray();
		}
		
		int index = size-1;
		
		//Shift past i
		while(index >= i) {
			array[index+1] = array[index];
		}
		array[i] = e;
		++size;
	}

	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E result = array[i];
		--size;
		for(int j = i; j < size; ++j) {
			array[j] = array[j+1];
		}
		array[size] = null;
		return result;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

}
