package xiancheng;

public class Xiancheng3 {

	public static void main(String[] args) {
		// T����������Ʊ����
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			//��Ϊif elseҪ��֤��ԭ���ԣ�ͬ������飩
			synchronized(this) {
			if(nums>0) {
				//Thread.currentThread().getName()�õ���ǰ�߳�����
				System.out.println(Thread.currentThread().getName()+1+"�����۳���"+nums+"��Ʊ");
				nums--;
			}else {
				break;
			}
		}
		}
	}
	}