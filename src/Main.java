import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame{
	
	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					frame.setTitle("マインスイーパー");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(9,9));
		setContentPane(contentPane);
		
		JButton[][] btn = new JButton[9][9];
		

		Mine mine = new Mine(9,9);
		
		 ArrayList<Integer> aryX = new ArrayList<Integer>();
		 ArrayList<Integer> aryY = new ArrayList<Integer>();
		 
		for ( int i = 0; i < 10; i++ ) {
			aryX.add(i);	
			aryY.add(i);
		}
		Collections.shuffle(aryX);
		Collections.shuffle(aryY);

		mine.setMine(0,9);
		for ( int j = 0; j < 10; j++ ) {
			mine.setMine(aryX.get(j), aryY.get(j));
		}
		
		
		
		for ( int i = 0; i < 9; i++ ) {
			for ( int j = 0; j < 9; j++ ) {
				btn[i][j] = new JButton();
				contentPane.add(btn[i][j]);
				
			}
		}
		
		for ( int i = 0; i < 9; i++ ) {
			for ( int j = 0; j < 9; j++ ) {

				final int x = i;
				final int y = j;
				final JButton button = btn[i][j];

				
				
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if ( mine.getMine(x, y) == true ) {
							btn[x][y].setOpaque(true);
							btn[x][y].setBackground(Color.black);
						}else {
							btn[x][y].setOpaque(true);
							btn[x][y].setBackground(Color.white);	
							btn[x][y].setText(String.valueOf(mine.countMine(x,y)));
						}		
					}
				});
			}
		
		}	
	}
}

