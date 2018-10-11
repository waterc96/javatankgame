package xiancheng;
/*两个线程同时运行*/
public class Xiancheng2 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		bird b=new bird(10);
		d d=new d(10);
		Thread t1=new Thread(d);
		Thread t2=new Thread(b);
		t1.start();
		t2.start();

	}

}
class d implements Runnable{
	int n=0;
	public d(int n) {
		this.n = n;
	}

	int times=0;
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			times++;
			System.out.println("我是一个线程，正在输出第"+times+"hello worle");
			if(times==n) {
				break;
			}
		}
		
	}
}


class bird implements Runnable{
	int n=0;
	public bird(int n) {
		this.n = n;
	}
	int res=0;
	int times=0;
	public void run() {
	while(true) {
		try {
			Thread.sleep(100);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			}
		res+=(++times);//{res=res+(times+1)};times++;
		System.out.println("当前结果是："+res);
		if(times==n) {
			System.out.println("最终结果是："+res);
			break;
		}
		
	}
	}
	}