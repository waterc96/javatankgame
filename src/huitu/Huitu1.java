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
 //覆盖jpanel中的paint方法
public void paint(Graphics g){
	//Graphics绘图的重要类。理解为一支画笔
	super.paint(g);
	//调用父类完成初始化	
	//g.drawOval(10, 10, 30, 30);
	//在面板上画出图片
	/*Image im=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/shanghai.jpg"));
	//显示出来
	g.drawImage(im, 90, 90, 400, 300, this);*/
	//g.setFont(new Font("隶书",Font.BOLD,50));
	//g.drawString("德玛西亚", 30, 40);
}
}