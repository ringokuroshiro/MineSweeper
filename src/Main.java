import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame{
	
	private JPanel contentPane;
	private JButton[][] btn = new JButton[9][9];
	private Mine mine = new Mine(9,9);

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
		
		//Mineをランダムに配置する
		Random random = new Random();
		int count = 0;
		while( count < 10) {
			int randomX = random.nextInt(9);
			int randomY = random.nextInt(9);
			if( mine.getMine (randomX, randomY) == false) {
				mine.setMine(randomX, randomY);
				count ++;
			}
		}
		
		//ボタンの処理
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
				 button.addMouseListener(new MouseListener(){
					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						if ( e.getModifiers() == MouseEvent.BUTTON3_MASK ) {
							setFlags(x, y);
						}else {
							mekuru(x, y);
						}
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				    });
				

			}
		
		}
		
	}
	public void mekuru(int x, int y) {
		if ( mine.getMine(x, y) == true ) {
			btn[x][y].setOpaque(true);
			ImageIcon mine = new ImageIcon("./src/mine.png"); 
			btn[x][y].setIcon(mine);
		}else {
			btn[x][y].setOpaque(true);
			btn[x][y].setBackground(Color.white);	
			if(mine.countMine(x,y) > 0) {
				btn[x][y].setText(String.valueOf(mine.countMine(x,y)));
			}else {
				for (int i = x - 1; i <= x + 1; i ++) {
					if ( i < 0 || i >= mine.getWidth()) {
						continue;
					}
					for ( int j = y - 1; j <= y + 1; j ++) {
						if ( j < 0 || j >= mine.getHeight()) {
							continue;
						}
						mekuru(i,j);
					}
				}
			}
		}	
	}
	
	public void setFlags(int x, int y) {
		ImageIcon flag = new ImageIcon("./src/flag.png"); 
		btn[x][y].setIcon(flag);
	}
}

