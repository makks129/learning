package test.test8;

public class Penis implements Comparable<Penis> {

	private int length;

	public Penis(int length) {
		this.length = length;
	}

	@Override
	public int compareTo(Penis otherPenis) {
		return length - otherPenis.length;
	}

}
