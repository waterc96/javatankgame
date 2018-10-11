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





//子弹类
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		while(true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
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
		//System.out.println("子弹横坐标x="+x+"子弹纵坐标y="+y);
		//子弹何时死亡
		//判断子弹是否到达边缘
		if(x<-10||x>405||y<-10||y>305) {
			this.isLive=false;
			break;
		}
		}
		
		
	}
	
	
	
}
class tank{
	//tank的横纵坐标
	int x=0;
	int y=0;
	//0表示上，1表示下，2表示左，3表示右
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
//把敌人tank做成一个线程
class diji extends tank implements Runnable{
	//定义一个向量，可以访问到MyPanel上所有敌人的坦克
		Vector<diji> ets=new Vector<diji>();

	//定义一个向量，存放敌人tank
	Vector<zidan> ss=new Vector<zidan>();
	 int times=0;

	public boolean isLive;
	 public  void getets(Vector<diji> vv) {
		 this.ets=vv;
	 }
	public diji(int x, int y) {
		super(x, y);
		// TODO 自动生成的构造函数存根
	}
	//判断是否碰到了别的敌人tank
	public boolean Touchotherdiji() {
		boolean b=false;
		switch(this.fangxiang){
		case 0:
			//我的坦克向上
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				diji et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.fangxiang==0||et.fangxiang==1)
					{
						//左点
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
			//坦克向右
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				diji et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.fangxiang==0||et.fangxiang==1)
					{
						//上点
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//下点 
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
			//坦克向下
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				diji et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.fangxiang==0||et.fangxiang==1)
					{
						//我的左点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
						//我的右点
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
			//向左
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				diji et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.fangxiang==0||et.fangxiang==1)
					{
						//我的上一点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//下一点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.fangxiang==3||et.fangxiang==2)
					{
						//上一点
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
				// TODO 自动生成的 catch 块
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
						// TODO 自动生成的 catch 块
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
						// TODO 自动生成的 catch 块
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
						// TODO 自动生成的 catch 块
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
						// TODO 自动生成的 catch 块
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
						//没有子弹
						//添加
						switch(this.fangxiang)
						{
						case 0:
							//创建一颗子弹
							 s=new zidan(x+10,y,0);
							//把子弹加入向量
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
						
						//启动子弹
						Thread t=new Thread(s);
						t.start();
					}
				}
			}
			//让tank产生随机的方向
			this.fangxiang=(int)(Math.random()*4);
			//判断tank是否死亡
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
	//减少生命值
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
	//父类构造函数初始化子类成员变量
	Vector<zidan> ss=new Vector<zidan>();
	//创建一颗子弹
	zidan s=null;
	public mytank(int x,int y){
		
		super(x,y);
	}
	//射击
	public void shotEnemy() {
		switch(this.fangxiang) {
		case 0:
			//创建一颗子弹
		s=new zidan(x+10,y,0);
		//将子弹加入到向量
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
		//启动子弹
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
	//记录每关有多少敌人
	private static int enNum=20;
	//设置我有多少可以用的人
	private static int myLife=3;
	//记录总共消灭了多少敌人
	private static int allEnNum=0;
	//从文件中恢复记录点
	static Vector<Node>  nodes=new Vector<Node>();
	
	private static FileWriter fw=null;
	private static BufferedWriter bw=null;
	private static FileReader fr=null;
	private static BufferedReader br=null;
	
	private  Vector<diji> ets=new Vector<diji>();
	
	
	
	//完成读取认为
	public Vector<Node> getNodesAndEnNums()
	{
		try {
			fr=new FileReader("d:\\myRecording.txt");
			br=new BufferedReader(fr);
			String n="";
			//先读取第一行
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
				//后打开则先关闭
				br.close();
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		return nodes;
		
	}
	
	
	//保存击毁敌人的数量和敌人坦克坐标,方向
	
	public  void keepRecAndEnemyTank()
	{
		try {
			
			//创建
			fw=new FileWriter("d:\\myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
			
			System.out.println("size="+ets.size());
			//保存当前活的敌人坦克的坐标和方向
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				diji et=ets.get(i);
				
				if(et.isLive)
				{
					//活的就保存
					String recode=et.x+" "+et.y+" "+et.fangxiang;
					
					//写入
					bw.write(recode+"\r\n");
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
		
			//关闭流
			try {
				//后开先关闭
				bw.close();
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
	
	
	//从文件中读取，记录
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
				//后打开则先关闭
				br.close();
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
	
	//把玩家击毁敌人坦克数量保存到文件中
	public static void keepRecording()
	{
		try {
			
			//创建
			fw=new FileWriter("d:\\myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
		
			//关闭流
			try {
				//后开先关闭
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
	
	//减少敌人数
	public static void reduceEnNum()
	{
		enNum--;
	}
	//消灭敌人
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
		//这是缓冲
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
