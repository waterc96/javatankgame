package huitu;
import javax.swing.*;
import java.awt.*;
public class Huitu1 extends JFrame{
mypanle mp=null;
public static void main(String[] args) {
	Huitu1 x1=new Huitu1();

}
public Huitu1(){
	mp=new mypanle();
	this.add(mp);
	this.setSize(400,300);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
}

}
class mypanle extends JPanel{
 //����jpanel�е�paint����
public void paint(Graphics g){
	//Graphics��ͼ����Ҫ�ࡣ���Ϊһ֧����
	super.paint(g);
	//���ø�����ɳ�ʼ��	
	//g.drawOval(10, 10, 30, 30);
	//������ϻ���ͼƬ
	/*Image im=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/shanghai.jpg"));
	//��ʾ����
	g.drawImage(im, 90, 90, 400, 300, this);*/
	//g.setFont(new Font("����",Font.BOLD,50));
	//g.drawString("��������", 30, 40);
}
}