package huitu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;





//�ӵ���
class zidan implements Runnable{
	int x;
	int y;
	int fangxiang;
	int speed=3;
	boolean isLive=true;
	
	
	public zidan(int x, int y,int fangxiang) {
		super();
		this.x = x;
		this.y = y;
		this.fangxiang=fangxiang;
		boolean isLive=true;
	}
	public void run() {
		 	try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		while(true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		switch(fangxiang) {	
		case 0:
			y-=speed;
			break;
		case 1:
			y+=speed;
			break;
		case 2:
			x-=speed;
			break;
		case 3:
			x+=speed;
			break;
		}
		//System.out.println("�ӵ�������x="+x+"�ӵ�������y="+y);
		//�ӵ���ʱ����
		//�ж��ӵ��Ƿ񵽴��Ե
		if(x<-10||x>405||y<-10||y>305) {
			this.isLive=false;
			break;
		}
		}
		
		
	}
	
	
	
}
class tank{
	//tank�ĺ�������
	int x=0;
	int y=0;
	//0��ʾ�ϣ�1��ʾ�£�2��ʾ��3��ʾ��
	int fangxiang;
	int speed=2;
	int color;
	boolean isLife=true;
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getFangxiang() {
		return fangxiang;
	}
	public void setFangxiang(int fangxiang) {
		this.fangxiang = fangxiang;
	}
	public  tank(int x,int y){
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
//�ѵ���tank����һ���߳�
class diji extends tank implements Runnable{
	//����һ�����������Է��ʵ�MyPanel�����е��˵�̹��
		Vector<diji> ets=new Vector<diji>();

	//����һ����������ŵ���tank
	Vector<zidan> ss=new Vector<zidan>();
	 int times=0;

	public boolean isLive;
	 public  void getets(Vector<diji> vv) {
		 this.ets=vv;
	 }
	public diji(int x, int y) {
		super(x, y);
		// TODO �Զ����ɵĹ��캯�����
	}
	//�ж��Ƿ������˱�ĵ���tank
	public boolean Touchotherdiji() {
		boolean b=false;
		switch(this.fangxiang){
		case 0:
			//�ҵ�̹������
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				diji et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.fangxiang==0||et.fangxiang==1)
					{
						//���
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
					}
					if(et.fangxiang==3||et.fangxiang==2)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 3:
			//̹������
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				diji et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.fangxiang==0||et.fangxiang==1)
					{
						//�ϵ�
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//�µ� 
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.fangxiang==3||et.fangxiang==2)
					{
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 1:
			//̹������
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				diji et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.fangxiang==0||et.fangxiang==1)
					{
						//�ҵ����
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
						//�ҵ��ҵ�
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
					}
					if(et.fangxiang==3||et.fangxiang==2)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
						
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 2:
			//����
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				diji et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.fangxiang==0||et.fangxiang==1)
					{
						//�ҵ���һ��
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//��һ��
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.fangxiang==3||et.fangxiang==2)
					{
						//��һ��
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		}
		return b;
		
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(50);
				
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			switch(this.fangxiang) {
			case 0:
				for(int i=0;i<30;i++) {
					if(y>0) {
					y-=speed;
					}
					try {
						Thread.sleep(50);
						
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				break;
			case 1:
				for(int i=0;i<30;i++) {
					if(y<300) {
					y+=speed;
					}
					try {
						Thread.sleep(50);
						
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				break;
			case 2:
				for(int i=0;i<30;i++) {
					if(x>0) {
					x-=speed;
					}
					try {
						Thread.sleep(50);
						
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				break;
			case 3:
				for(int i=0;i<30;i++) {
					if(x<400) {
					x+=speed;
					}
					try {
						Thread.sleep(50);
						
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				break;
			}
			times++;
			if(times%2==0)
			{
				if(isLife)
				{
					if(ss.size()<6)
					{
						//System.out.println("et.ss.size()<5="+et.ss.size());
						zidan s=null;
						//û���ӵ�
						//���
						switch(this.fangxiang)
						{
						case 0:
							//����һ���ӵ�
							 s=new zidan(x+10,y,0);
							//���ӵ���������
							ss.add(s);
							break;
						case 1:
							s=new zidan(x+30,y+10,1);
							ss.add(s);
							break;
						case 2:
							 s=new zidan(x+10,y+30,2);
							ss.add(s);
							break;
						case 3:
							s=new zidan(x,y+10,3);
							ss.add(s);
							break;
						}
						
						//�����ӵ�
						Thread t=new Thread(s);
						t.start();
					}
				}
			}
			//��tank��������ķ���
			this.fangxiang=(int)(Math.random()*4);
			//�ж�tank�Ƿ�����
			if(this.isLife==false) {
				break;
			}
			
						
					}
					
				}
		}
class Bomb{
	int x;
	int y;
	int life=9;
	boolean isLive=true;
	public Bomb(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	//��������ֵ
		public void lifeDown()
		{
			if(life>0)
			{
				life--;
			}else{
				this.isLive=false;
			}
			
		}
}


class mytank extends tank{
	//���๹�캯����ʼ�������Ա����
	Vector<zidan> ss=new Vector<zidan>();
	//����һ���ӵ�
	zidan s=null;
	public mytank(int x,int y){
		
		super(x,y);
	}
	//���
	public void shotEnemy() {
		switch(this.fangxiang) {
		case 0:
			//����һ���ӵ�
		s=new zidan(x+10,y,0);
		//���ӵ����뵽����
		ss.add(s);
		break;
		case 1:
		s=new zidan(x+10,y+30,1);
		ss.add(s);
		break;
		case 2:
		s=new zidan(x,y+10,2);
		ss.add(s);
		break;
		case 3:
		s=new zidan(x+30,y+10,3);
		ss.add(s);
		break;
		}
		//�����ӵ�
		Thread t=new Thread(s);
		t.start();
	}
	public void moveup(){
		y-=speed;
	}
	public void movedown(){
		y+=speed;
	}
	public void moveleft(){
		x-=speed;
	}
	public void moveright(){
		x+=speed;
	}
	
}
class Recorder{
	//��¼ÿ���ж��ٵ���
	private static int enNum=20;
	//�������ж��ٿ����õ���
	private static int myLife=3;
	//��¼�ܹ������˶��ٵ���
	private static int allEnNum=0;
	//���ļ��лָ���¼��
	static Vector<Node>  nodes=new Vector<Node>();
	
	private static FileWriter fw=null;
	private static BufferedWriter bw=null;
	private static FileReader fr=null;
	private static BufferedReader br=null;
	
	private  Vector<diji> ets=new Vector<diji>();
	
	
	
	//��ɶ�ȡ��Ϊ
	public Vector<Node> getNodesAndEnNums()
	{
		try {
			fr=new FileReader("d:\\myRecording.txt");
			br=new BufferedReader(fr);
			String n="";
			//�ȶ�ȡ��һ��
			n=br.readLine();
			allEnNum=Integer.parseInt(n);
			while((n=br.readLine())!=null)
			{
				String []xyz=n.split(" "); 
				
				Node node=new Node(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2]));
				nodes.add(node);
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			
			try {
				//������ȹر�
				br.close();
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		return nodes;
		
	}
	
	
	//������ٵ��˵������͵���̹������,����
	
	public  void keepRecAndEnemyTank()
	{
		try {
			
			//����
			fw=new FileWriter("d:\\myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
			
			System.out.println("size="+ets.size());
			//���浱ǰ��ĵ���̹�˵�����ͷ���
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				diji et=ets.get(i);
				
				if(et.isLive)
				{
					//��ľͱ���
					String recode=et.x+" "+et.y+" "+et.fangxiang;
					
					//д��
					bw.write(recode+"\r\n");
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
		
			//�ر���
			try {
				//���ȹر�
				bw.close();
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
	
	
	//���ļ��ж�ȡ����¼
	public static void getRecoring()
	{
		try {
			fr=new FileReader("d:\\myRecording.txt");
			br=new BufferedReader(fr);
			String n=br.readLine();
			allEnNum=Integer.parseInt(n);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			
			try {
				//������ȹر�
				br.close();
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
	
	//����һ��ٵ���̹���������浽�ļ���
	public static void keepRecording()
	{
		try {
			
			//����
			fw=new FileWriter("d:\\myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
		
			//�ر���
			try {
				//���ȹر�
				bw.close();
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
	
	public static int getEnNum() {
		return enNum;
	}
	public static void setEnNum(int enNum) {
		Recorder.enNum = enNum;
	}
	public static int getMyLife() {
		return myLife;
	}
	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}
	
	//���ٵ�����
	public static void reduceEnNum()
	{
		enNum--;
	}
	//�������
	public static void addEnNumRec()
	{
		allEnNum++;
	}
	public static int getAllEnNum() {
		return allEnNum;
	}
	public static void setAllEnNum(int allEnNum) {
		Recorder.allEnNum = allEnNum;
	}


	public  Vector<diji> getEts() {
		return ets;
	}


	public  void setEts(Vector<diji> ets1) {
		
		this.ets = ets1;
		System.out.println("ok");
	}
}
class Node{

	int x;
	int y;
	int fangxiang;
	public Node(int x,int y,int direct)
	{
		this.x=x;
		this.y=y;
		this.fangxiang=direct;
	}

}
class AePlayWave extends Thread {

	private String filename;
	public AePlayWave(String wavfile) {
		filename = wavfile;

	}

	public void run() {

		File soundFile = new File(filename);

		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}

		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		auline.start();
		int nBytesRead = 0;
		//���ǻ���
		byte[] abData = new byte[512];

		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}

	}

	
}
