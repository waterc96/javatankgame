package huitu;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tankgame2 extends JFrame{
	mypanle mp=null;
	public static void main(String[] args) {
		Tankgame2 Tankgame2=new Tankgame2();
	}
	public Tankgame2(){
		mp=new mypanle();
		this.add(mp);
		this.setSize(400,300);
		this.setVisible(true);
		
	}
}
class mypaint extends JPanel implements MouseListener{
	public void paint(Graphics p) {
		super.paint(p);
	}
	//���
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	//
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
}
