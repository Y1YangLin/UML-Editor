package Gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class Frame extends JFrame{
	private Canvas canvas;
	private Tool_list tool_list;
	private Mode_bar mode_bar;
	
	
	Frame(){
		canvas = Canvas.getInstance(); // ³æ¨Ò¼Ò¦¡
		canvas.setBorder(BorderFactory.createLineBorder(Color.black));
		tool_list = new Tool_list();
		mode_bar = new Mode_bar();
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(canvas,BorderLayout.CENTER);
		this.getContentPane().add(tool_list,BorderLayout.NORTH);
		this.getContentPane().add(mode_bar,BorderLayout.WEST);
		
		
		
	}
	
	public static void main(String [] args) {
		Frame window = new Frame();
		window.setTitle("UML編輯器");
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setSize(1000,800);
		
		window.setLocationRelativeTo(null); // null ´N¦b¿Ã¹õ¤¤¶¡
		window.setVisible(true);
	}
	
}
