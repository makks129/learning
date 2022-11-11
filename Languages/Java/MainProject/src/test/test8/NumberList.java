package test.test8;

public class NumberList<T extends Number> {

	private T[] objects;

	public NumberList(T[] objects) {
		this.objects = objects;
	}

	public T get(int index) {
		return objects[index];
	}

}
