package my;


public class Game
{
	//规格
	public int NN = 10;
	
	//角色：空，小孩，箱子，障碍物，目标
	public enum Role{EMPTY, CHILD, BOX, STONE, TARGET};
	
	//网络数据
	public Cell[][] grid = new Cell[NN][NN];
	public Cell child;
	public Cell box;
	public int status = 0;//游戏状态：0：游戏进行中，100：已通关
	
	//单元格的定义（每个单元格可以为空，也可以有一个角色
	public static class Cell{
		int row;
		int col;
		Role type = Role.EMPTY;
	}
	
	public Game() {
		initMap();
	}
	
	//初始化地图
	public void initMap() {
		//创建地图
		for(int row=0; row<NN; row++) {
			for(int col=0; col<NN; col++) {
				grid[row][col] = new Cell();
				grid[row][col].row = row;
				grid[row][col].col = col;
			}
		}
		
		//设置围墙
		for(int row=0; row<NN; row++) {
			for(int col=0; col<NN; col++) {
				if(row == 0 || row == NN-1 || col == 0 || col == NN-1) {
					grid[row][col].type = Role.STONE;
				}
			}
		}
		
		//设置目的地
		grid[3][3].type = Role.TARGET;
		
		//设置箱子
		grid[6][7].type = Role.BOX;
		box = grid[6][7];
		
		
		//设置小孩
		grid[7][2].type = Role.CHILD;
		child = grid[7][2];
		
		//设置障碍物
		grid[6][4].type = Role.STONE;
		grid[2][6].type = Role.STONE;
		grid[3][5].type = Role.STONE;
	}
	
	//获取一个位置
	public Cell getCell(int row, int col) {
		return grid[row][col];
	}
	
	//计算下一步的位置：dx水平方向移动几格，dy竖直方向移动几格
	public Cell next(Cell cell, int dx, int dy) {
		int nextRow = cell.row + dy;
		int nextCol = cell.col + dx;
		return getCell(nextRow, nextCol);
	}
	
	//探测是否可以移动
	public boolean canMove(int dx, int dy) {
		if(status == 100) return false;//已通关
		Cell nextCell = next(child, dx, dy);
		
		if(nextCell.type == Role.EMPTY || nextCell.type == Role.TARGET) {
			return true;
		}
		
		if(nextCell.type == Role.BOX) {
			//前方为箱子
			Cell next2 = next(nextCell, dx, dy);
			if(next2.type == Role.EMPTY || next2.type == Role.TARGET) {
				return true;
			}
		}
		
		return false;
	}
	
	//移动
	public void move(int dx, int dy) {
		Cell nextCell = next(child, dx, dy);
		if(nextCell.type == Role.EMPTY || nextCell.type == Role.TARGET) {
			nextCell.type = Role.CHILD;
			child.type = Role.EMPTY;
			child = nextCell;
		}
		
		if(nextCell.type == Role.BOX) {
			Cell next2 = next(nextCell, dx, dy);
			Role next2Type = next2.type;
			if(next2Type == Role.EMPTY || next2Type == Role.TARGET) {
				next2.type = Role.BOX;
				box.type = Role.EMPTY;
				box = next2;
				
				nextCell.type = Role.CHILD;
				child.type = Role.EMPTY;
				child = nextCell;
			}

			if(next2Type == Role.TARGET) {
				status = 100;
			}
		}
//		nextCell.type = Role.BOX;
//		box.type = Role.EMPTY;
//		box = nextCell;
	}

	public int getStatus() {
		return status;
	}
}
