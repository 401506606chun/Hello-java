package cn.itcast.day07.demo03;

public class Hero {
	private String name;
	private int attackScore;
	
	public Hero(){
		
	}

	public Hero(String name, int attackScore) {
		this.name = name;
		this.attackScore = attackScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAttackScore() {
		return attackScore;
	}

	public void setAttackScore(int attackScore) {
		this.attackScore = attackScore;
	}
	
	
	

}
