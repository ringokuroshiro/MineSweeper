
public class Mine {
	private boolean board [][];

	public Mine(int column, int row) {
		// TODO Auto-generated constructor stub
		board = new boolean[column][row];
	}

	public void setMine(int x,int y){
		//true‚ª”š’e
		board[x][y] = true;	
	}
	
	public boolean getMine(int x, int y) {
		return board[x][y];
	}
	public int getWidth() {return board.length;}
	public int getHeight() {return board[0].length;}
	public int countMine(int x, int y) {
		int count = 0;
		for (int i = x - 1; i <= x + 1; i ++) {
			if ( i < 0 || i >= getWidth()) {
				continue;
			}
			for ( int j = y - 1; j <= y + 1; j ++) {
				if ( j < 0 || j >= getHeight()) {
					continue;
				}
				if ( getMine(i,j) ) {
					count ++;
				}
			}
		}
		return count;
	}
	
//	public int countMine(int x, int y) {
//		int count = 0;
//		
//		if(x > 0 && y > 0 && board[x-1][y-1] == true) {
//			count ++;
//		}
//		if( y > 0 && board[x][y-1] == true ) {
//			count ++;
//		}
//		if( y > 0 && x < 8 && board[x+1][y-1] == true ){
//			count ++;
//		}
//		if ( x > 0 && board[x-1][y] == true) {
//			count ++;
//		}
//		if(  board[x][y] == true) {
//			count ++;
//		}
//		if( x < 8 && board[x+1][y] == true) {
//			count ++;
//		}
//		if( x > 0 &&  y < 8 && board[x-1][y+1] == true) {
//			count ++;
//		}
//		if( y < 8 && board[x][y+1] == true) {
//			count ++;
//		}
//		if ( x < 8 && y < 8 && board[x+1][y+1] == true ) {
//			count ++;
//		}
//		return count;
//	}
	
}
