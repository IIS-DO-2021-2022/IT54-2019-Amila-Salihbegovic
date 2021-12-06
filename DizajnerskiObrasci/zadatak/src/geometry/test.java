package geometry;

import java.util.Arrays;

public class test {

	public static void main(String[] args) {
		//Point
		
		Point p1= new Point();
		p1.setX(3);
		p1.setY(5);
		p1.setSelected(true);
		
		System.out.println("X koordinate tacke p1 je: " + p1.getX());
		System.out.println("Y koordinate tacke p1 je: "+ p1.getY());
		System.out.println("Tacka je selektovana? "+ p1.isSelected());
		
		Point p2=new Point();
		p2.setX(13);
		p2.setY(15);
		p2.setSelected(true);
		
		System.out.println("Distanca izmedju tacaka p1 i p2 je: "+ p1.distance(p2.getX(), p2.getY()));
		p1.setX(p2.getX());
		System.out.println("Distanca izmedju tacaka p1 i p2 je: "+ p1.distance(p2.getX(), p2.getY()));
		
		//Line
		
		Line l1= new Line();
		l1.setstartpoint(p1);
		l1.setendpoint(p2);
		l1.setSelected(true);
		
		p1.setX(33);
		
		l1.getstartpoint().setX(55);
		
		System.out.println("X startPoint l1: " + l1.getstartpoint().getX());
		System.out.println("x p1: " + p1.getX());
		
		Line l2= new Line();
		l2.setstartpoint(l1.getstartpoint());
		l2.setendpoint(new Point());
		l2.getendpoint().setY(p1.getX());
		
		//Rectangle
		
		rectangle r1= new rectangle();
		r1.setwidth(5);
		r1.setheight(10);
		r1.setupperletf(p2);
		System.out.println("Aria r1: " + r1.area());
		
		rectangle r2=new rectangle();
		System.out.println("Circumference r2: " + r2.circumference());
		
		//Circle
		
		circle c1=new circle();
		c1.setcenter(p2);
		System.out.println("\n\nX center c1: "+c1.getcenter().getX() + 
				"\n Y center c1: "+ c1.getcenter().getX() +
				"\n selected c1: "+ c1.isSelected() + 
				"\n Radisu c1: " + c1.getradius());
		
		
		
		
		try {
			c1.setradius(10);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Radius c1: " + c1.getradius());
		c1.setSelected(c1.getcenter().getX() > r1.getupperleft().getY());
		System.out.println("Selected c1: " + c1.isSelected());
		
		
		System.out.println(p1);
		System.out.println(l1);
		System.out.println(r1);
		System.out.println(c1);
		
		System.out.println("Vezbe 7: ");
		p1.moveBy(3, 5);
		r1.moveTo(2, 23);
		System.out.println(p1);
		System.out.println(r1);
		
		Shape d1=new Donut(p1, 23, 43);
		System.out.println(d1);
		Shape l5=l1;
		Shape c9=c1;
		Shape r7=r1;
		
		Shape[] shapes = {d1, l5, c9, r7};
		
		System.out.println("\n");
		for(int i=0; i<shapes.length; i++) {
			System.out.println(shapes[i]);
		}
		for(int i=0; i<shapes.length; i++) {
			shapes[i].moveBy(21, 23);
			System.out.println(shapes[i]);
		}
		
		int [] ints= {3,5,4,2,1};
		System.out.println("\n");
		for(int i=0; i<ints.length; i++) {
			System.out.println(ints[i]);
		}
		Arrays.sort(ints);
		for(int i=0; i<ints.length; i++) {
			System.out.println(ints[i]);
		}
		
	}

}
