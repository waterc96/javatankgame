package huitu;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
public class Tankgame1 extends JFrame implements ActionListener{

	mypanle1 mp=null;
	
	//定义一个开始面板
	startPanle msp=null;
	
	//作出我需要的菜单
	JMenuBar jmb=null;
	//开始游戏
	JMenu jm1=null;
	JMenuItem jmil=null;
	//退出系统
	JMenuItem jmi2=null;
	//存盘退出
	JMenuItem jmi3=null;
	JMenuItem jmi4=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tankgame1 mtg=new Tankgame1();
	}
	
	//构造函数
	public Tankgame1()
	{
		//mp=new MyPanel();
		
		//启动mp线程
		//Thread t=new Thread(mp);
		//t.start();
		//this.add(mp);
		//注册监听
		//this.addKeyListener(mp);
		
		//创建菜单及菜单选项
		jmb=new JMenuBar();
		jm1 =new JMenu("游戏(G)");
		//设置快捷方式
		jm1.setMnemonic('G');
		jmil =new JMenuItem("开始新游戏(N)");
		jmi2 =new JMenuItem("退出游戏(E)");
		jmi3 =new JMenuItem("存盘退出游戏(C)");
		jmi4 =new JMenuItem("继续上局游戏(S)");
		
		//注册监听
		jmi4.addActionListener(this);
		jmi4.setActionCommand("conGame");
		
		//注册监听
		jmi3.addActionListener(this);
		jmi3.setActionCommand("saveExit");
		
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		jmi2.setMnemonic('E');
		//对jmil相应
		jmil.addActionListener(this);
		jmil.setActionCommand("newgame");
		jm1.add(jmil);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jmb.add(jm1);
		
		msp=new startPanle();
		Thread t=new Thread(msp);
		t.start();
		
		this.setJMenuBar(jmb);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(msp);
		this.setSize(600, 500);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//对用户不同的点击作出不同的处理
		if(arg0.getActionCommand().equals("newgame"))
		{
			//创建战场面板
			mp=new mypanle1("newGame");
			
			//启动mp线程
			Thread t=new Thread(mp);
			t.start();
			//先删除旧的开始面板
			this.remove(msp);
			this.add(mp);
			//注册监听
			this.addKeyListener(mp);
			//显示,刷新JFrame
			this.setVisible(true);
			
			
		}else if(arg0.getActionCommand().equals("exit"))
		{
			//用户点击了退出系统的菜单
			//保存击毁敌人数量.
			Recorder.keepRecording();
			
			System.exit(0);
		}//对存盘退出左处理
		else if(arg0.getActionCommand().equals("saveExit"))
		{
			System.out.println("111");
			System.out.println("mp.ets.size="+mp.ets.size());
			//工作
			Recorder rd=new Recorder();
			rd.setEts(mp.ets);
			//保存击毁敌人的数量和敌人的坐标
			rd.keepRecAndEnemyTank();
			
			//退出
			System.exit(0);
		}else if(arg0.getActionCommand().equals("conGame"))
		{
			//创建战场面板
			mp=new mypanle1("con");
		
			
			//启动mp线程
			Thread t=new Thread(mp);
			t.start();
			//先删除旧的开始面板
			this.remove(msp);
			this.add(mp);
			//注册监听
			this.addKeyListener(mp);
			//显示,刷新JFrame
			this.setVisible(true);
		}
	}

}
//提示作用
class startPanle extends JPanel implements Runnable{
	int times=0;
	public void paint(Graphics p){
		super.paint(p);
		p.fillRect(0, 0, 400, 300);
		if(times%2==0) {
		p.setColor(Color.YELLOW);
		Font myfont=new Font("华文新魏",Font.BOLD,30);
		p.setFont(myfont);
		p.drawString("stage:1",150,150);
	}
	}
	@Override
	public void run() {
		while(true) {
		// TODO 自动生成的方法存根
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		times++;
		this.repaint();
	}
	}
	
}
//我的面板
class mypanle1 extends JPanel implements KeyListener,Runnable{
	mytank hero=null;
	//定义敌机
	Vector<diji> ets=new Vector<diji>();
	//定义炸弹集合
	Vector<Bomb> bombs=new Vector<Bomb>();
	int ensize=4;
	Vector<Node> nodes= new Vector<>();
	Image image1=null;
	Image image2=null;
	Image image3=null;
	//构造函数
	public mypanle1(String flag){
		hero=new mytank(20,50);
		if(flag.equals("newGame")) {
		//初始化敌人tank
		for(int i=0;i<ensize;i++) {
			//创建一个敌人的tank对象
			diji et=new diji((i+1)*50,0);
			et.setColor(1);
			et.setFangxiang(1);	
			//将创建的敌人tank向量交给敌人tank
			et.getets(ets);
			//启动敌人tank线程
			Thread t=new Thread(et);
			t.start();
			//给敌人tank添加子弹
			zidan s=new zidan(et.x+10,et.y+30,2);
			//加入给敌人tank
			 et.ss.add(s);
			Thread t2=new Thread(s);
			t2.start();
			ets.add(et);
		}
		}else
		{
			System.out.println("接着玩");
			nodes =new Recorder().getNodesAndEnNums();
			//初始化敌人tank
			for(int i=0;i<ensize;i++) {
				//创建一个敌人的tank对象
				diji et=new diji((i+1)*50,0);
				et.setColor(1);
				et.setFangxiang(1);	
				//将创建的敌人tank向量交给敌人tank
				et.getets(ets);
				//启动敌人tank线程
				Thread t=new Thread(et);
				t.start();
				//给敌人tank添加子弹
				zidan s=new zidan(et.x+10,et.y+30,2);
				//加入给敌人tank
				 et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				ets.add(et);
			}
		}
		try {
			//初始化照片
			 image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
			 image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
			 image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		//初始化照片
		// image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		// image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		 //image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		
		//播放开战声音
			AePlayWave apw=new AePlayWave("./111.wav");
			apw.start();}
	public void showInfo(Graphics p)
	{
		//画出提示信息坦克(该坦克不参与战斗)
		this.drowtank(80,330, p, 0, 0);
		p.setColor(Color.black);
		p.drawString(Recorder.getEnNum()+"", 110, 350);
		this.drowtank(130, 330,p , 0, 1);
		p.setColor(Color.black);
		p.drawString(Recorder.getMyLife()+"", 165, 350);
		
		//画出玩家的总成绩
		p.setColor(Color.black);
		Font f=new Font("宋体",Font.BOLD,20);
		p.setFont(f);
		p.drawString("您的总成绩", 420, 30);
		this.drowtank(420, 60, p, 0, 0);
		p.setColor(Color.black);
		p.drawString(Recorder.getAllEnNum()+"", 460, 80);
	}
	//覆盖jpanel中的paint方法
	public void paint(Graphics p){
		//Graphics绘图的重要类。理解为一支画笔
		super.paint(p);
		p.fillRect(0, 0, 400, 300);
		//调用父类完成初始化	
		this.showInfo(p);
		//画出自己的tank
		if(hero.isLife==true) {
		this.drowtank(hero.getX(), hero.getY(), p, this.hero.fangxiang, 1);
		}//从ss中取出每一颗子弹并画出
		for(int i=0;i<hero.ss.size();i++) {
		//画出子弹
			zidan myzidan=hero.ss.get(i);
		if(myzidan!=null&&myzidan.isLive==true) {
			
			p.draw3DRect(myzidan.x, myzidan.y, 1, 1, false);
		}
		if(myzidan.isLive==false) {
			hero.ss.remove(myzidan);
		}
		}
		//画出炸弹
		for(int i=0;i<bombs.size();i++) {
			//取出炸弹
			Bomb b=bombs.get(i);
			if(b.life>6) {					
					p.drawImage(image1, b.x, b.y, 30, 30, this);
			}else if(b.life>3){
				p.drawImage(image2, b.x, b.y, 30, 30, this);
			}else {
				p.drawImage(image3, b.x, b.y, 30, 30, this);
			}
			//让b的生命值减小
			b.lifeDown();
			//如果炸弹的生命值为0，则去掉炸弹
			if(b.life==0) {
				bombs.remove(b);
			}
				}
		
		//画出敌机
		for(int i=0;i<ets.size();i++) {
			diji et=ets.get(i);
		
		if(et.isLife) {
			this.drowtank(et.getX(), et.getY(), p,et.fangxiang, 0);
			for(int j=0;j<et.ss.size();j++) {
				//取出子弹
				zidan dzidan=et.ss.get(j);
				if(dzidan.isLive) {
				p.draw3DRect(dzidan.x, dzidan.y, 1, 1, false);
			}else {
				et.ss.remove(dzidan);
			}
		  }
		 }
		}
	}
	//敌人的坦克是否击中我
		public void hitMe()
		{
			//取出每一个敌人的坦克
			for(int i=0;i<this.ets.size();i++)
			{
				//取出坦克
				diji et=ets.get(i);
				
				//取出每一颗子弹
				for(int j=0;j<et.ss.size();j++)
				{
					//取出子弹
					zidan enemyShot=et.ss.get(j);
					if(hero.isLife)
					{
						if(this.hitTank(enemyShot, hero))
						{
							
						}
					}
				}
			}
		}
		
		
		//判断我的子弹是否击中敌人的坦克
		public void hitEnemyTank()
		{
			//判断是否击中敌人的坦克
			for(int i=0;i<hero.ss.size();i++)
			{
				//取出子弹 
				zidan myShot=hero.ss.get(i);
				//判断子弹是否有效
				if(myShot.isLive)
				{
					//取出每个坦克，与它判断
					for(int j=0;j<ets.size();j++)
					{
						//取出坦克
						diji et=ets.get(j);
						
						if(et.isLife)
						{
							if(this.hitTank(myShot, et))
							{
								//减少敌人数量
								Recorder.reduceEnNum();
								//增加我的记录
								Recorder.addEnNumRec();
							}
						}
						
					}
				}
			}
		}
		
		//写一个函数专门判断子弹是否击中敌人坦克
		public boolean hitTank(zidan s,tank et)
		{
			boolean b2=false;
			
			//判断该坦克的方向
			switch(et.fangxiang)
			{
			//如果敌人坦克的方向是上或者是下
			case 0:
			case 2:
				if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30)
				{
					//击中
					//子弹死亡
					s.isLive=false;
					//敌人坦克死亡
					et.isLife=false;
					b2=true;
					//创建一颗炸弹,放入Vector
					Bomb b=new Bomb(et.x,et.y);
					//放入Vector
					bombs.add(b);
					
				}
				
				break;
			case 1:
			case 3:
				if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20)
				{
					//击中
					//子弹死亡
					s.isLive=false;
					//敌人坦克死亡
					et.isLife=false;
					b2=true;
					//创建一颗炸弹,放入Vector
					Bomb b=new Bomb(et.x,et.y);
					//放入Vector
					bombs.add(b);
					
				}
			}
			
			return b2;
			
		}
		
	//画出tank的函数
	public void drowtank(int x,int y,Graphics p,int fangxiang,int type) {
		//判断tank类型
		switch(type)
		{
		case 0:p.setColor(Color.yellow);
		break;
		case 1:p.setColor(Color.CYAN);
		break;
		}
		//判断方向
		switch(fangxiang) {
		case 0:
			//上下左右顺序
			//1.左边的矩形
			p.fill3DRect(x, y, 5,30,false);
			//2.画出右边矩形
			p.fill3DRect(x+15, y, 5,30,false);
			//3.画出中间矩形
			p.fill3DRect(x+5, y+5, 10,20,false);
			//4.画出中间的圆
			p.drawOval(x+5, y+10, 8, 8);
			//5.炮筒
			p.drawLine(x+9, y+15, x+9, y);
			break;
		case 1:
			//1.左边的矩形
			p.fill3DRect(x, y, 5,30,false);
			//2.画出右边矩形
			p.fill3DRect(x+15, y, 5,30,false);
			//3.画出中间矩形
			p.fill3DRect(x+5, y+5, 10,20,false);
			//4.画出中间的圆
			p.drawOval(x+5, y+11, 8, 8);
			//5.炮筒
			p.drawLine(x+9, y+15, x+9, y+30);
			break;
			
		case 2:
			//1.左边的矩形
			p.fill3DRect(x, y, 30,5,false);
			//2.画出右边矩形
			p.fill3DRect(x, y+15, 30,5,false);
			//3.画出中间矩形
			p.fill3DRect(x+5, y+5, 20,10,false);
			//4.画出中间的圆
			p.drawOval(x+10, y+5, 8, 8);
			//5.炮筒
			p.drawLine(x+15, y+9, x, y+9);
			break;
		case 3:
			//1.左边的矩形
			p.fill3DRect(x, y, 30,5,false);
			//2.画出右边矩形
			p.fill3DRect(x, y+15, 30,5,false);
			//3.画出中间矩形
			p.fill3DRect(x+5, y+5, 20,10,false);
			//4.画出中间的圆
			p.drawOval(x+10, y+5, 8, 8);
			//5.炮筒
			p.drawLine(x+15, y+9, x+30, y+9);
			break;
		}
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		//设置我tank的方向
	if(arg0.getKeyCode()==KeyEvent.VK_UP) {
		this.hero.setFangxiang(0);
		this.hero.moveup();
	}else if(arg0.getKeyCode()==KeyEvent.VK_DOWN){
		this.hero.setFangxiang(1);
		this.hero.movedown();
	}else if(arg0.getKeyCode()==KeyEvent.VK_LEFT){
		this.hero.setFangxiang(2);
		this.hero.moveleft();
	}else if(arg0.getKeyCode()==KeyEvent.VK_RIGHT){
		this.hero.setFangxiang(3);
		this.hero.moveright();
	}
	if(arg0.getKeyCode()==KeyEvent.VK_J) {
		
		if(this.hero.ss.size()<8) {
		this.hero.shotEnemy();
		}
		
		
	}
		this.repaint();
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		//让画板每隔多长时间重绘一次，刷新一次
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			//判断是否要给敌机加入新的子弹
			this.hitEnemyTank();
			this.hitMe();
			this.repaint();
		}
		
	}
} 

