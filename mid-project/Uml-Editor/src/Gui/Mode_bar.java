package Gui;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import Mode.CreateLineMode;
import Mode.CreateObjMode;
import Mode.Mode;
import Mode.SelectMode;
import Shape.AssoLine;

public class Mode_bar extends JToolBar{
	private int mode_num = 6;
	private Color mycolor = Color.BLACK;
	private JButton holdbtn = null;
	private Canvas canvas;
	
	public Mode_bar() {
		// TODO Auto-generated constructor stub
		canvas = Canvas.getInstance();
		this.setLayout(new GridLayout(mode_num,1,2,2));
		this.setBackground(null);
		this.setFloatable(false);
		
		ImageIcon selectIcon = new ImageIcon("img/select.png");
		ToolBtn selectBtn = new ToolBtn("select",selectIcon,new SelectMode());
		this.add(selectBtn);
		
		ImageIcon assoIcon = new ImageIcon("img/asso.png");
		ToolBtn assoBtn = new ToolBtn("association", assoIcon, new CreateLineMode("association"));
		this.add(assoBtn);
		
		ImageIcon geneIcon = new ImageIcon("img/gene.png");
		ToolBtn geneBtn = new ToolBtn("generalization", geneIcon, new CreateLineMode("generalization"));
		this.add(geneBtn);
		
		ImageIcon compIcon = new ImageIcon("img/comp.png");
		ToolBtn compBtn = new ToolBtn("composition", compIcon, new CreateLineMode("composition"));
		this.add(compBtn);
		
		ImageIcon classIcon = new ImageIcon("img/class.png");
		ToolBtn classBtn = new ToolBtn("class", classIcon, new CreateObjMode("class"));
		this.add(classBtn);
		
		ImageIcon usecaseIcon = new ImageIcon("img/usecase.png");
		ToolBtn usecaseBtn = new ToolBtn("association", usecaseIcon, new CreateObjMode("usecase"));
		this.add(usecaseBtn);
		
		
	}
	
	
	private class ToolBtn extends JButton{
		Mode toolMode;
		
		public ToolBtn(String toolName,ImageIcon img,Mode toolMode) {
			// TODO Auto-generated constructor stub
			this.toolMode = toolMode;
			setToolTipText(toolName);
			setIcon(img);
			
			setFocusable(false);
			setBackground(Color.white);
			setBorderPainted(false);
			setRolloverEnabled(true);
			
			this.addActionListener(new ToolModeListener());
			
			
			
			
		}
		class ToolModeListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(holdbtn != null) {
					holdbtn.setBackground(Color.white);
				}
				
				holdbtn = (JButton)e.getSource();
				holdbtn.setBackground(mycolor);
				canvas.current_Mode = toolMode;
				canvas.setCurrentMode();
				canvas.reset();
				canvas.repaint();
				
				
			}
			
		}
	}
	
}

