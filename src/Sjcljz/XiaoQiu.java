package Sjcljz;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class XiaoQiu extends JFrame{
	mypanle mp=new mypanle();
	public static void main(String[] args) {
		XiaoQiu XiaoQiu=new XiaoQiu();
	}
	
	public XiaoQiu(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO 自动生成的构造函数存根
	}
	public XiaoQiu(String title) throws HeadlessException {
		super(title);
		// TODO 自动生成的构造函数存根
	}

public XiaoQiu(){
	//mp=new mypanle();
	//mp加入到JFfarme
	this.add(mp);
	super.addKeyListener(mp);
	setSize(400,300);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
//定义自己的面板
class mypanle extends JPanel implements KeyListener{
	int x=10; int y=10;
	//Graphics中的重要类，理解为画笔
	public void paint(Graphics g){
		super.paint(g);
		g.fillOval(x, y, 10, 10);
	}

	@Override
	//键被按下
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			y+=10;
		}else if(e.getKeyCode()==KeyEvent.VK_UP) {
			y-=10;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			x-=10;
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			x+=10;
		}
		//调用repaint函数，来重绘界面
		this.repaint();
	}
	//键被松开
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
	}
	//键的一个值被输出
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
	}
}