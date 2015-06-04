package test;

import java.util.Timer;
import java.util.TimerTask;

public class ShoutTask extends TimerTask{
	
	public ShoutTask(Timer timer) {
		super();
		this.timer = timer;
	}

	private Timer timer;

	@Override
	public void run() {
		System.out.println();
		System.out.println("Arrrrgh!!!!");
		System.out.println();	
		
		this.timer.cancel();
		this.timer.purge();
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

}
