package xiancheng;
/*�����߳�ͬʱ����*/
public class Xiancheng2 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
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
			System.out.println("����һ���̣߳����������"+times+"hello worle");
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
		System.out.println("��ǰ����ǣ�"+res);
		if(times==n) {
			System.out.println("���ս���ǣ�"+res);
			break;
		}
		
	}
	}
	}