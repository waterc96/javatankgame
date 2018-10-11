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
	
	//����һ����ʼ���
	startPanle msp=null;
	
	//��������Ҫ�Ĳ˵�
	JMenuBar jmb=null;
	//��ʼ��Ϸ
	JMenu jm1=null;
	JMenuItem jmil=null;
	//�˳�ϵͳ
	JMenuItem jmi2=null;
	//�����˳�
	JMenuItem jmi3=null;
	JMenuItem jmi4=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tankgame1 mtg=new Tankgame1();
	}
	
	//���캯��
	public Tankgame1()
	{
		//mp=new MyPanel();
		
		//����mp�߳�
		//Thread t=new Thread(mp);
		//t.start();
		//this.add(mp);
		//ע�����
		//this.addKeyListener(mp);
		
		//�����˵����˵�ѡ��
		jmb=new JMenuBar();
		jm1 =new JMenu("��Ϸ(G)");
		//���ÿ�ݷ�ʽ
		jm1.setMnemonic('G');
		jmil =new JMenuItem("��ʼ����Ϸ(N)");
		jmi2 =new JMenuItem("�˳���Ϸ(E)");
		jmi3 =new JMenuItem("�����˳���Ϸ(C)");
		jmi4 =new JMenuItem("�����Ͼ���Ϸ(S)");
		
		//ע�����
		jmi4.addActionListener(this);
		jmi4.setActionCommand("conGame");
		
		//ע�����
		jmi3.addActionListener(this);
		jmi3.setActionCommand("saveExit");
		
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		jmi2.setMnemonic('E');
		//��jmil��Ӧ
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
		//���û���ͬ�ĵ��������ͬ�Ĵ���
		if(arg0.getActionCommand().equals("newgame"))
		{
			//����ս�����
			mp=new mypanle1("newGame");
			
			//����mp�߳�
			Thread t=new Thread(mp);
			t.start();
			//��ɾ���ɵĿ�ʼ���
			this.remove(msp);
			this.add(mp);
			//ע�����
			this.addKeyListener(mp);
			//��ʾ,ˢ��JFrame
			this.setVisible(true);
			
			
		}else if(arg0.getActionCommand().equals("exit"))
		{
			//�û�������˳�ϵͳ�Ĳ˵�
			//������ٵ�������.
			Recorder.keepRecording();
			
			System.exit(0);
		}//�Դ����˳�����
		else if(arg0.getActionCommand().equals("saveExit"))
		{
			System.out.println("111");
			System.out.println("mp.ets.size="+mp.ets.size());
			//����
			Recorder rd=new Recorder();
			rd.setEts(mp.ets);
			//������ٵ��˵������͵��˵�����
			rd.keepRecAndEnemyTank();
			
			//�˳�
			System.exit(0);
		}else if(arg0.getActionCommand().equals("conGame"))
		{
			//����ս�����
			mp=new mypanle1("con");
		
			
			//����mp�߳�
			Thread t=new Thread(mp);
			t.start();
			//��ɾ���ɵĿ�ʼ���
			this.remove(msp);
			this.add(mp);
			//ע�����
			this.addKeyListener(mp);
			//��ʾ,ˢ��JFrame
			this.setVisible(true);
		}
	}

}
//��ʾ����
class startPanle extends JPanel implements Runnable{
	int times=0;
	public void paint(Graphics p){
		super.paint(p);
		p.fillRect(0, 0, 400, 300);
		if(times%2==0) {
		p.setColor(Color.YELLOW);
		Font myfont=new Font("������κ",Font.BOLD,30);
		p.setFont(myfont);
		p.drawString("stage:1",150,150);
	}
	}
	@Override
	public void run() {
		while(true) {
		// TODO �Զ����ɵķ������
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
//�ҵ����
class mypanle1 extends JPanel implements KeyListener,Runnable{
	mytank hero=null;
	//����л�
	Vector<diji> ets=new Vector<diji>();
	//����ը������
	Vector<Bomb> bombs=new Vector<Bomb>();
	int ensize=4;
	Vector<Node> nodes= new Vector<>();
	Image image1=null;
	Image image2=null;
	Image image3=null;
	//���캯��
	public mypanle1(String flag){
		hero=new mytank(20,50);
		if(flag.equals("newGame")) {
		//��ʼ������tank
		for(int i=0;i<ensize;i++) {
			//����һ�����˵�tank����
			diji et=new diji((i+1)*50,0);
			et.setColor(1);
			et.setFangxiang(1);	
			//�������ĵ���tank������������tank
			et.getets(ets);
			//��������tank�߳�
			Thread t=new Thread(et);
			t.start();
			//������tank����ӵ�
			zidan s=new zidan(et.x+10,et.y+30,2);
			//���������tank
			 et.ss.add(s);
			Thread t2=new Thread(s);
			t2.start();
			ets.add(et);
		}
		}else
		{
			System.out.println("������");
			nodes =new Recorder().getNodesAndEnNums();
			//��ʼ������tank
			for(int i=0;i<ensize;i++) {
				//����һ�����˵�tank����
				diji et=new diji((i+1)*50,0);
				et.setColor(1);
				et.setFangxiang(1);	
				//�������ĵ���tank������������tank
				et.getets(ets);
				//��������tank�߳�
				Thread t=new Thread(et);
				t.start();
				//������tank����ӵ�
				zidan s=new zidan(et.x+10,et.y+30,2);
				//���������tank
				 et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				ets.add(et);
			}
		}
		try {
			//��ʼ����Ƭ
			 image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
			 image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
			 image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		//��ʼ����Ƭ
		// image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		// image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		 //image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		
		//���ſ�ս����
			AePlayWave apw=new AePlayWave("./111.wav");
			apw.start();}
	public void showInfo(Graphics p)
	{
		//������ʾ��Ϣ̹��(��̹�˲�����ս��)
		this.drowtank(80,330, p, 0, 0);
		p.setColor(Color.black);
		p.drawString(Recorder.getEnNum()+"", 110, 350);
		this.drowtank(130, 330,p , 0, 1);
		p.setColor(Color.black);
		p.drawString(Recorder.getMyLife()+"", 165, 350);
		
		//������ҵ��ܳɼ�
		p.setColor(Color.black);
		Font f=new Font("����",Font.BOLD,20);
		p.setFont(f);
		p.drawString("�����ܳɼ�", 420, 30);
		this.drowtank(420, 60, p, 0, 0);
		p.setColor(Color.black);
		p.drawString(Recorder.getAllEnNum()+"", 460, 80);
	}
	//����jpanel�е�paint����
	public void paint(Graphics p){
		//Graphics��ͼ����Ҫ�ࡣ���Ϊһ֧����
		super.paint(p);
		p.fillRect(0, 0, 400, 300);
		//���ø�����ɳ�ʼ��	
		this.showInfo(p);
		//�����Լ���tank
		if(hero.isLife==true) {
		this.drowtank(hero.getX(), hero.getY(), p, this.hero.fangxiang, 1);
		}//��ss��ȡ��ÿһ���ӵ�������
		for(int i=0;i<hero.ss.size();i++) {
		//�����ӵ�
			zidan myzidan=hero.ss.get(i);
		if(myzidan!=null&&myzidan.isLive==true) {
			
			p.draw3DRect(myzidan.x, myzidan.y, 1, 1, false);
		}
		if(myzidan.isLive==false) {
			hero.ss.remove(myzidan);
		}
		}
		//����ը��
		for(int i=0;i<bombs.size();i++) {
			//ȡ��ը��
			Bomb b=bombs.get(i);
			if(b.life>6) {					
					p.drawImage(image1, b.x, b.y, 30, 30, this);
			}else if(b.life>3){
				p.drawImage(image2, b.x, b.y, 30, 30, this);
			}else {
				p.drawImage(image3, b.x, b.y, 30, 30, this);
			}
			//��b������ֵ��С
			b.lifeDown();
			//���ը��������ֵΪ0����ȥ��ը��
			if(b.life==0) {
				bombs.remove(b);
			}
				}
		
		//�����л�
		for(int i=0;i<ets.size();i++) {
			diji et=ets.get(i);
		
		if(et.isLife) {
			this.drowtank(et.getX(), et.getY(), p,et.fangxiang, 0);
			for(int j=0;j<et.ss.size();j++) {
				//ȡ���ӵ�
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
	//���˵�̹���Ƿ������
		public void hitMe()
		{
			//ȡ��ÿһ�����˵�̹��
			for(int i=0;i<this.ets.size();i++)
			{
				//ȡ��̹��
				diji et=ets.get(i);
				
				//ȡ��ÿһ���ӵ�
				for(int j=0;j<et.ss.size();j++)
				{
					//ȡ���ӵ�
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
		
		
		//�ж��ҵ��ӵ��Ƿ���е��˵�̹��
		public void hitEnemyTank()
		{
			//�ж��Ƿ���е��˵�̹��
			for(int i=0;i<hero.ss.size();i++)
			{
				//ȡ���ӵ� 
				zidan myShot=hero.ss.get(i);
				//�ж��ӵ��Ƿ���Ч
				if(myShot.isLive)
				{
					//ȡ��ÿ��̹�ˣ������ж�
					for(int j=0;j<ets.size();j++)
					{
						//ȡ��̹��
						diji et=ets.get(j);
						
						if(et.isLife)
						{
							if(this.hitTank(myShot, et))
							{
								//���ٵ�������
								Recorder.reduceEnNum();
								//�����ҵļ�¼
								Recorder.addEnNumRec();
							}
						}
						
					}
				}
			}
		}
		
		//дһ������ר���ж��ӵ��Ƿ���е���̹��
		public boolean hitTank(zidan s,tank et)
		{
			boolean b2=false;
			
			//�жϸ�̹�˵ķ���
			switch(et.fangxiang)
			{
			//�������̹�˵ķ������ϻ�������
			case 0:
			case 2:
				if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30)
				{
					//����
					//�ӵ�����
					s.isLive=false;
					//����̹������
					et.isLife=false;
					b2=true;
					//����һ��ը��,����Vector
					Bomb b=new Bomb(et.x,et.y);
					//����Vector
					bombs.add(b);
					
				}
				
				break;
			case 1:
			case 3:
				if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20)
				{
					//����
					//�ӵ�����
					s.isLive=false;
					//����̹������
					et.isLife=false;
					b2=true;
					//����һ��ը��,����Vector
					Bomb b=new Bomb(et.x,et.y);
					//����Vector
					bombs.add(b);
					
				}
			}
			
			return b2;
			
		}
		
	//����tank�ĺ���
	public void drowtank(int x,int y,Graphics p,int fangxiang,int type) {
		//�ж�tank����
		switch(type)
		{
		case 0:p.setColor(Color.yellow);
		break;
		case 1:p.setColor(Color.CYAN);
		break;
		}
		//�жϷ���
		switch(fangxiang) {
		case 0:
			//��������˳��
			//1.��ߵľ���
			p.fill3DRect(x, y, 5,30,false);
			//2.�����ұ߾���
			p.fill3DRect(x+15, y, 5,30,false);
			//3.�����м����
			p.fill3DRect(x+5, y+5, 10,20,false);
			//4.�����м��Բ
			p.drawOval(x+5, y+10, 8, 8);
			//5.��Ͳ
			p.drawLine(x+9, y+15, x+9, y);
			break;
		case 1:
			//1.��ߵľ���
			p.fill3DRect(x, y, 5,30,false);
			//2.�����ұ߾���
			p.fill3DRect(x+15, y, 5,30,false);
			//3.�����м����
			p.fill3DRect(x+5, y+5, 10,20,false);
			//4.�����м��Բ
			p.drawOval(x+5, y+11, 8, 8);
			//5.��Ͳ
			p.drawLine(x+9, y+15, x+9, y+30);
			break;
			
		case 2:
			//1.��ߵľ���
			p.fill3DRect(x, y, 30,5,false);
			//2.�����ұ߾���
			p.fill3DRect(x, y+15, 30,5,false);
			//3.�����м����
			p.fill3DRect(x+5, y+5, 20,10,false);
			//4.�����м��Բ
			p.drawOval(x+10, y+5, 8, 8);
			//5.��Ͳ
			p.drawLine(x+15, y+9, x, y+9);
			break;
		case 3:
			//1.��ߵľ���
			p.fill3DRect(x, y, 30,5,false);
			//2.�����ұ߾���
			p.fill3DRect(x, y+15, 30,5,false);
			//3.�����м����
			p.fill3DRect(x+5, y+5, 20,10,false);
			//4.�����м��Բ
			p.drawOval(x+10, y+5, 8, 8);
			//5.��Ͳ
			p.drawLine(x+15, y+9, x+30, y+9);
			break;
		}
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		//������tank�ķ���
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
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		//�û���ÿ���೤ʱ���ػ�һ�Σ�ˢ��һ��
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
			//�ж��Ƿ�Ҫ���л������µ��ӵ�
			this.hitEnemyTank();
			this.hitMe();
			this.repaint();
		}
		
	}
} 

