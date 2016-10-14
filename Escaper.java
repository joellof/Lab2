public class Escaper { 
	private Robot robot; 
	public static void main(String[] args) { 
		Escaper escaper = new Escaper(); 
		escaper.createEnviroment(); 
		escaper.moveToEntrance ();

	}//main 

	public void createEnviroment() { 
		RobotWorld  world = RobotWorld.load("src/room.txt"); 
		robot = new Robot(4, 3, Robot.EAST, world);
		robot.setDelay(100);
	}//createEnviroment

	//before: robot is inside the room
	//after:  robot is on the cell representing the the doorjamb
	public void moveToEntrance() {

		findWall();

		while(!atEntrance()) {

			if (checkDirectionRight())
				robot.move();
			else
				scoutWall();
		}
	}// moveToEntrance

        //before:the robot is on one of the cells
	//after: the robot is still on the same cell, returning a boolean to tell if it's in the doorjamb
	private boolean atEntrance(){

		boolean front = robot.frontIsClear();
		robot.turnLeft();
		boolean leftWall = robot.frontIsClear();
		robot.turnLeft();
		boolean back = robot.frontIsClear();
		robot.turnLeft();
		boolean rightWall = robot.frontIsClear();
		robot.turnLeft();
		if(!leftWall && !rightWall || !back && !front)
			return true;
		else
			return false;

	}

	//before: the robot is somewhere in the room
	//after: the robot has reached a wall and turned left
	private void findWall(){

		while(robot.frontIsClear() && !atEntrance())
		{
			robot.move();
		}
		if (!atEntrance())
		robot.turnLeft();
	}

	//before: the robot is somewhere in the room
	//after: the robot has turned right and returns a boolean to tell if front is clear
	private boolean checkDirectionRight(){  

		robot.turnLeft();
		robot.turnLeft();
		robot.turnLeft();
		return robot.frontIsClear();
	}
	
	//before: the robot is moving along the wall
	//        this method is more specifically checking for corners
	//after: the robot has turned left and moved forward if possible, otherwise it has turned left again
	private void scoutWall(){
		robot.turnLeft();
		if(robot.frontIsClear())
			robot.move();
		else
			robot.turnLeft();
	}
}//Escaper
