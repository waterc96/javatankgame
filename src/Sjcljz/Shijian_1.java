
package Sjcljz;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Shijian_1 extends JFrame implements ActionListener{
	mypaint mp=null;
	JButton jb1=null;
	JButton jb2=null;
public static void main(String args[]) {
	Shijian_1 pp=new Shijian_1();
}
public Shijian_1() {
	mp=new mypaint();
	jb1=new JButton("��ɫ");
	jb2=new JButton("��ɫ");
	this.add(jb1,BorderLayout.NORTH);
	mp.setBackground(Color.black);
	this.add(mp);
	this.add(jb2,BorderLayout.SOUTH);
	//ע�������ָ��action����
		jb1.addActionListener(this);
		jb1.setActionCommand("aa");
		jb2.addActionListener(this);
		jb2.setActionCommand("bb");
	this.setSize(200, 150);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
}

@Override
public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("aa")) {
		System.out.println("�����˺�ɫ��ť");
		mp.setBackground(Color.BLACK);
	}else if(e.getActionCommand().equals("bb")){
		System.out.println("������˺�ɫ��ť");
		mp.setBackground(Color.red);
	}
	
}
}
class mypaint extends JPanel{
	
}