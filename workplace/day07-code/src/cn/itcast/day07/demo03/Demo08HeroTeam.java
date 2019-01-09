package cn.itcast.day07.demo03;

import java.util.ArrayList;
public class Demo08HeroTeam {
	public static void main(String[] args) {

		Hero s1 = new Hero("蔡文姬", 70);
		Hero s2 = new Hero("貂蝉", 80);
		Hero s3 = new Hero("狄仁杰", 79);
		Hero s4 = new Hero("东皇", 75);
		Hero s5 = new Hero("韩信", 90);

		ArrayList<Hero> team = new ArrayList<Hero>();
		team.add(s1);
		team.add(s2);
		team.add(s3);
		team.add(s4);
		team.add(s5);

		for (int i = 0; i < team.size(); i++) {
			System.out.println("英雄名称"+team.get(i).getName()+"战斗力"+team.get(i).getAttackScore());
		}
	}

}