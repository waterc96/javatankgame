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
		// TODO �Զ����ɵĹ��캯�����
	}
	public XiaoQiu(String title) throws HeadlessException {
		super(title);
		// TODO �Զ����ɵĹ��캯�����
	}

public XiaoQiu(){
	//mp=new mypanle();
	//mp���뵽JFfarme
	this.add(mp);
	super.addKeyListener(mp);
	setSize(400,300);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
//�����Լ������
class mypanle extends JPanel implements KeyListener{
	int x=10; int y=10;
	//Graphics�е���Ҫ�࣬���Ϊ����
	public void paint(Graphics g){
		super.paint(g);
		g.fillOval(x, y, 10, 10);
	}

	@Override
	//��������
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
		//����repaint���������ػ����
		this.repaint();
	}
	//�����ɿ�
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO �Զ����ɵķ������
	}
	//����һ��ֵ�����
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO �Զ����ɵķ������
	}
}