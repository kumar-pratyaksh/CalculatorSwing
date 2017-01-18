import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Calculator extends JFrame implements ActionListener,KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton []buttons = new JButton[20];
	String[] buttonString={"7","8","9","C","4","5","6","/","1",
			"2","3","*",".","0","+","-","√","+/-","%","="};
	JTextArea display;
	boolean function[]=new boolean[4];
	double a,b;

	public Calculator() {
		super("Basic Calculator");
		setSize(300, 350);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		setLayout(new GridLayout(6, 1,0,10));

		display= new JTextArea();
		Font f= new Font("Times new roman", Font.BOLD, 18);
		display.setFont(f);
		display.setEditable(false);
		display.setText("");
		display.setPreferredSize(new Dimension(280, 75));
		display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JPanel displayPanel=new JPanel();
		displayPanel.add(display);
		add(displayPanel);


		for(int i=0;i<20;i++){
			buttons[i]=new JButton();
			buttons[i].setPreferredSize(new Dimension(55, 30));

			buttons[i].setText(buttonString[i]);
			buttons[i].addActionListener(this);
		}
		buttons[18].setEnabled(false);
		FlowLayout fl=new FlowLayout(FlowLayout.CENTER, 10, 0);
		JPanel[] panel =new JPanel[5];
		for(int i=0;i<5;i++)
			panel[i]=new JPanel(fl);
		for(int i=0;i<20;i++)
			panel[i/4].add(buttons[i]);

		for(JPanel p:panel)
			add(p);

		a=0.0;
		b=0.0;
		for(int i=0;i<4;i++)
			function[i]=false;

		setVisible(true);
	}



	private void clear() {
		display.setText("");
		for(int i=0;i<4;i++)
			function[i]=false;
		a=0.0;
		b=0.0;

	}

	private void getSqrt() {
		try {
			double value=Double.parseDouble(display.getText());
			display.setText(Math.sqrt(value)+"");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Incorrect number format");
		}

	}

	private void positiveNegative() {
		try {
			double value=Double.parseDouble(display.getText());
			if(value!=0) {
				value*=-1;
				display.setText(value+"");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Incorrect number format");
		}

	}

	private void getResult() {
		double result=0;
		try {

			b=Double.parseDouble(display.getText());
			if(function[2])
				result=a*b;
			else if(function[3]) {
				if(b==0) {
					JOptionPane.showMessageDialog(this, "Divide by zero error");
					display.setText("");
					return;
				}
				else
					result=a/b;
			}
			else if(function[0])
				result=a+b;
			else if(function[1])
				result=a-b;
			else {
				JOptionPane.showMessageDialog(this, "Enter some numbers and try again");
				display.setText("");
				return;
			}
			for(int i=0;i<4;i++)
				function[i]=false;
			display.setText(result+"");
			a=0;
			b=0;
		}catch (NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Incorrect number format");
		}

	}
	private boolean valid() {
		if(display.getText()==null) {
			JOptionPane.showMessageDialog(this, "Please enter something");
			return false;
		}
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if(source==buttons[0])
			display.append("7");
		else if (source==buttons[1])
			display.append("8");
		else if (source==buttons[2])
			display.append("9");
		else if (source==buttons[3])
			clear();
		else if (source==buttons[4])
			display.append("4");
		else if (source==buttons[5])
			display.append("5");
		else if (source==buttons[6])
			display.append("6");
		else if (source==buttons[7]) {
			if(valid()) {
				a=Double.parseDouble(display.getText());
				display.setText("");
				function[3]=true;
			}
		}else if (source==buttons[8])
			display.append("1");
		else if (source==buttons[9])
			display.append("2");
		else if (source==buttons[10])
			display.append("3");
		else if (source==buttons[11]) {
			if(valid()) {
				a=Double.parseDouble(display.getText());
				display.setText("");
				function[2]=true;
			}
		}else if (source==buttons[12])
			display.append(".");
		else if (source==buttons[13])
			display.append("0");
		else if (source==buttons[14]) {
			if(valid()) {
				a=Double.parseDouble(display.getText());
				display.setText("");
				function[0]=true;
			}
		}else if (source==buttons[15]) {
			if(valid()) {
				a=Double.parseDouble(display.getText());
				display.setText("");
				function[1]=true;
			}
		}else if (source==buttons[16])
			getSqrt();
		else if (source==buttons[17])
			positiveNegative();
		else if (source==buttons[19])
			getResult();
		else {

		}

	}

	public static void main(String args[]){
		Calculator c=new Calculator();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyID=e.getKeyCode();
		if(keyID==59)
			display.append("1");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}