import java.util.ArrayList;

public class KnightTour {
    static int[][] dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
	
    public static class Position{
		public int x;
		public int y;
		public Position (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Position() {
		
		}
		
		public String toString() {
			return "x :"+x+" y :"+y;
		}
		
	}
	
	public static int[][] travel(Position current, int s, int[][] board){
        board[current.x][current.y] = s;
        
		if(s == 64) {
			return board;
		}
		
        ArrayList<Position> possibleMoves = possible(current, board);
        //怕找不到解，用迴圈有計畫性的瓊舉
        for(int i = 0; i < possibleMoves.size(); i++) {
    		if(!possibleMoves.isEmpty())
    			//找到一個解就傳回board
    			board=travel(warnsdoffRule(possibleMoves, board).get(i), s+1, board);
    			//為了只輸出一個結果
    			if(board!=null) 
    				break;
        	else
    			continue;
        }
        return board;
	}
	
	public static ArrayList<Position> possible(Position current, int[][] board){
		ArrayList<Position> position = new ArrayList<>();
		for(int[] dir : dirs) {
			Position p = new Position(current.x + dir[0], current.y + dir[1]);
            if(isVisitable(board, p))
            	position.add(p);
		}
		return position;
	}
	
    public static boolean isVisitable(int[][] board, Position position) {
        return position.x > -1 && position.x < 8 &&
               position.y > -1 && position.y < 8 &&
               board[position.x][position.y] == 0;
    }
    
    public static ArrayList<Position> warnsdoffRule(ArrayList<Position> possibleMoves, int[][] board){
    	Position temp = new Position();
    	for(int i = possibleMoves.size()-1; i > 0 ; i--)
    		for (int j = 0; j <i; j++) {
    			if (possible(possibleMoves.get(j), board).size()> possible(possibleMoves.get(j+1), board).size()) {
    				temp = possibleMoves.get(j);
    				possibleMoves.remove(j);
    				possibleMoves.add(j+1, temp);
    			}
    		}
    	return  possibleMoves;
    }
    
    public static void printSolution() {
        for(int[] row : KnightTour.travel(new KnightTour.Position(5, 6), 1, new int[8][8])) {
            for(int column : row)
                System.out.printf("%3d", column);
            System.out.println();
        }
    }
}
