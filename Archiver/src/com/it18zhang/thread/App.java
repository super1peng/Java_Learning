package com.it18zhang.thread;

public class App {
	
	public static void main(String[] args) {
		TicketPool pool = new TicketPool();
		Saler s1 = new Saler("Bob", pool);
		Saler s2 = new Saler("Tom", pool);
		
		s1.start();
		s2.start();
	}
	
}
