package xiancheng;
//��ʾͨ���̳�thread�������߳�
public class Xiancheng1 {

	public static void main(String[] args) {
		cat cat=new cat();
	//�����߳�,�ᵼ��run����������
		cat.start();
		dog dog= new dog();
		Thread th=new Thread(dog);
		th.start();
	}

}
class cat extends Thread{
//��дrun����
	int times=0;
	public void run(){
		while(true) {
			//����һ�롣���߳̽���blocked״̬�����ͷ���Դ
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
		System.out.println("hello �߳�");
		times++;
		if(times==10) {
			break;
		}
	}
	}
}
class dog implements Runnable{
	int times=0;
	public void run() {
		while(true) {
			//����һ�롣���߳̽���blocked״̬�����ͷ���Դ
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
		System.out.println("hay �̳߳�");
		times++;
		if(times==10) {
			break;
		}
		}
	}
}