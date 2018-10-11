package xiancheng;
//演示通过继承thread来开发线程
public class Xiancheng1 {

	public static void main(String[] args) {
		cat cat=new cat();
	//启动线程,会导致run函数的运行
		cat.start();
		dog dog= new dog();
		Thread th=new Thread(dog);
		th.start();
	}

}
class cat extends Thread{
//重写run函数
	int times=0;
	public void run(){
		while(true) {
			//休眠一秒。是线程进入blocked状态，并释放资源
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		System.out.println("hello 线程");
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
			//休眠一秒。是线程进入blocked状态，并释放资源
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		System.out.println("hay 线程程");
		times++;
		if(times==10) {
			break;
		}
		}
	}
}