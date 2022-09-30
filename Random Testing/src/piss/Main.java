package piss;

public class Main {
    public static void main() {
	long l = 55;
	int i = 44;
	short s = 33;
	byte b = 22;

	i = (int) l;
	s = (short) i;
	b = (byte) s;

	System.out.println("l = " + l);
	System.out.println("i = " + i);
	System.out.println("s = " + s);
	System.out.println("b = " + b);
    }
}
