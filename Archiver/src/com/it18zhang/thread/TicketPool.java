package com.it18zhang.thread;

public class TicketPool {
	private int tickNo = 100;
	
	public synchronized int getTicket() {
		
		int tmp = tickNo;
		if (tmp == 0) {
			return 0;
		}
		tickNo--;
		return tmp;
	}
}
