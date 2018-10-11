package xiancheng;

public class Xiancheng3 {

	public static void main(String[] args) {
		// T定义三个售票窗口
		piao p1=new piao();
		piao p2=new piao();
		piao p3=new piao();
		Thread t2=new Thread(p1);
		Thread t3=new Thread(p1);
		Thread t4=new Thread(p1);
		
		t2.start();
		t3.start();
		t4.start(); 

	}

}
class piao implements Runnable{
	private static int nums=10;
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			//认为if else要保证其原子性（同步代码块）
			synchronized(this) {
			if(nums>0) {
				//Thread.currentThread().getName()得到当前线程名字
				System.out.println(Thread.currentThread().getName()+1+"正在售出第"+nums+"张票");
				nums--;
			}else {
				break;
			}
		}
		}
	}
	}