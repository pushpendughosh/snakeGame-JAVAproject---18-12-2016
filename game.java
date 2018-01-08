import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

class Snakepix
{
	public int x,y;
	
	void setxy (Byte motion) //1 for up, 2 for down, 3 for left, 4 for right
	{	
		if(motion==1)
			y-=10;
		else if(motion==2)
			y+=10;
		else if(motion==3)
			x-=10;
		else if(motion==4)
			x+=10;
			
		if(x>300)
			x-=300;
		else if(y>300)
			y-=300;
		else if(x<0)
			x+=300;
		else if(y<0)
			y+=300;
	}
}

class Food
{
	public int x,y;
}

class GameCompo implements ActionListener
{
	JFrame framer;
	JPanel playboy =new JPanel();
	Snakepix[] mysnake = new Snakepix[900];
	JPanel[] snakeboxes = new JPanel[900];
	JPanel eye1 = new JPanel();
	JPanel eye2 = new JPanel();
	int snakelength = 5;
	Byte move=4;
	boolean gameend=false; boolean ate = false; boolean bonus=false; boolean pause=false;
	int i;
	int time=220; int timeout; int round=1;//decides speed of snake
	Food food = new Food();
	JPanel theFood = new JPanel();
	int score=0; int top=24;
	int indexmenuhit=1; int abouthit=1; int indexinst=1;
	
	JLabel scorecard = new JLabel("Score : ");
	JLabel showscore = new JLabel("0");
	JLabel timeron = new JLabel(" ");
	JLabel showtime = new JLabel(" ");
	JPanel end = new JPanel();
	JPanel endpanel = new JPanel();
	JLabel gameover = new JLabel(" ");
	JLabel analysis = new JLabel(" ");
	JLabel youscored = new JLabel(" ");
	JLabel scoreAnalysis1 = new JLabel("Score Analysis");
	JLabel scoreAnalysis2 = new JLabel("");
	
	JButton mainMenu=new JButton("Menu"); 
	JButton about = new JButton("About");
	JPanel menuBox=new JPanel(); 
	JPanel aboutBox=new JPanel();
	JLabel aboutdet1=new JLabel();
	JLabel aboutdet2=new JLabel();
	JLabel aboutdet3=new JLabel();
	JLabel aboutdet4=new JLabel();
	JLabel aboutdet5=new JLabel();
	JButton newgame=new JButton("New Game");
	JButton settings=new JButton("Game Type");
	JButton exit=new JButton("Exit");
	JButton resume= new JButton("Resume");
	JButton resumeabout = new JButton("<<");
	JButton howtoplay = new JButton("How to play");
	JPanel insBox=new JPanel();
	JLabel about1=new JLabel();
	JLabel about2=new JLabel();
	JLabel about3=new JLabel();
	JLabel about4=new JLabel();
	JLabel about5=new JLabel();
	JButton resumeback = new JButton("<<");
	int totaltime=0;
	
	GameCompo()
	{
		//just initial defination
			for(i=0;i<snakelength;i++)
				mysnake[i]=new Snakepix(); 
				
			for(i=0;i<snakelength;i++)
				snakeboxes[i]=new JPanel();
			
			mysnake[0].x=151;
			mysnake[0].y=151;
			
			for(i=1;i<snakelength;i++)
			{
				mysnake[i].x=(mysnake[0].x)-(10*i);
				mysnake[i].y=mysnake[0].y;
			}
			
			framer=new JFrame ("Snake Game");
			
			framer.setVisible(true);
			framer.setSize(300,322+16+top);
			framer.setLayout(null);
			framer.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			framer.add(playboy);
			
			framer.add(mainMenu);
			framer.add(about);
			
			mainMenu.setFocusable(false);
			about.setFocusable(false);
			
			playboy.setLayout(null);
			playboy.setSize(300,300);
			playboy.setBackground(Color.lightGray);
			playboy.setBounds(0,0+top,300,300);
			
			for(i=0;i<snakelength;i++)
			{
				snakeboxes[i].setSize(8,8);
				snakeboxes[i].setBackground(Color.black);
				playboy.add(snakeboxes[i]);
			}
			snakeboxes[0].setBackground(Color.darkGray);
			eye1.setBackground(Color.red);
			eye2.setBackground(Color.red);
			playboy.add(eye1);
			playboy.add(eye2);
			
			mainMenu.setBounds(2,2,60,20);
			about.setBounds(62,2,60,20);
			
			mainMenu.addActionListener(this);
			about.addActionListener(this);
			
			theFood.setSize(6,6);
			theFood.setBackground(Color.red);
			playboy.add(theFood);
			scorecard.setBounds(5,300+top,50,15);
			framer.add(scorecard);
			showscore.setBounds(55,300+top,60,15);
			framer.add(showscore);
			
			timeron.setBounds(210,300+top,70,15);
			framer.add(timeron);
			showtime.setBounds(280,300+top,16,15);
			framer.add(showtime);

	}
	
	public void actionPerformed(ActionEvent e) 
		{

			if (e.getSource() == mainMenu)
			{
				pause=true;	
				if(indexmenuhit==1)
					{
						aboutBox.setVisible(false);
						insBox.setVisible(false);
						playboy.setVisible(false);
						menuBox.setVisible(true);
						menuboxinit();
						indexmenuhit++;
					}
				else
				{
					aboutBox.setVisible(false);
					insBox.setVisible(false);
					playboy.setVisible(false);
					menuBox.setVisible(true);
				}
			} 
			
			if (e.getSource() == about)
			{
				pause=true;
				if(abouthit==1)
					{
						aboutBox.setVisible(true);
						menuBox.setVisible(false);
						insBox.setVisible(false);
						playboy.setVisible(false);
						aboutboxinit();
						abouthit++;
					}
				else
				{
					aboutBox.setVisible(true);
					menuBox.setVisible(false);
					insBox.setVisible(false);
					playboy.setVisible(false);
				}

			}
			 
			if (e.getSource() == resume)
			{
				menuBox.setVisible(false);
				playboy.setVisible(true);
				pause=false;
			} 
			
			if (e.getSource() == newgame)
			{
				//
			} 
			
			if (e.getSource() == settings)
			{
				//
			} 
			
			if (e.getSource() == exit)
			{
				System.exit(0);			
			}
			
			if (e.getSource() == resumeabout)
			{
				aboutBox.setVisible(false);
				playboy.setVisible(true);
				pause=false;
		
			}
			if (e.getSource() == resumeback)
			{
				aboutBox.setVisible(true);
				insBox.setVisible(false);
				pause=false;
		
			}
			if (e.getSource()==howtoplay)
			{
				pause=true;
				if(indexinst==1)
					{
						playboy.setVisible(false);
						aboutBox.setVisible(false);
						insBox.setVisible(true);
						menuBox.setVisible(false);
						inst();
						indexinst++;
					}
				else
				{
					playboy.setVisible(false);
					aboutBox.setVisible(false);
					insBox.setVisible(true);
					menuBox.setVisible(false);
				}
			}
					
		}
	
	public void inst()
	{
				insBox.setLayout(null);
				insBox.setSize(200,200);
				insBox.setBackground(Color.lightGray);
				insBox.setBounds(50,50,200,200);
				framer.add(insBox);
				
				insBox.add(resumeback);
				insBox.add(about1);
				insBox.add(about2);
				insBox.add(about3);
				insBox.add(about4);
				insBox.add(about5);
				
				resumeback.setBounds(10,150,40,40);
				about1.setBounds(10,15,180,20);
				about2.setBounds(10,45,180,20);
				about3.setBounds(10,65,180,20);
				about4.setBounds(10,85,180,20);
				about5.setBounds(10,105,180,20);
				
				about1.setText("HOW TO PLAY?");
				about2.setText("Just press the UP, DOWN");
				about3.setText("LEFT, RIGHT arrow keys");
				about4.setText("and help the hungry snake");
				about5.setText("eat some food.");
				
				about1.setHorizontalAlignment(SwingConstants.CENTER);
				about2.setHorizontalAlignment(SwingConstants.CENTER);
				about3.setHorizontalAlignment(SwingConstants.CENTER);
				about4.setHorizontalAlignment(SwingConstants.CENTER);
				about5.setHorizontalAlignment(SwingConstants.CENTER);
				
				resumeback.addActionListener(this);
	}	
	
	public void menuboxinit()
	{
				menuBox.setLayout(null);
				menuBox.setSize(110,210);
				menuBox.setBackground(Color.gray);
				menuBox.setBounds(95,75,110,200);
				framer.add(menuBox);
				
				menuBox.add(newgame);
				menuBox.add(settings);
				menuBox.add(exit);
				menuBox.add(resume);
				
				resume.setBounds(10,50,90,20);
				newgame.setBounds(10,80,90,20);
				settings.setBounds(10,110,90,20);
				exit.setBounds(10,140,90,20);
				
				newgame.addActionListener(this);
				settings.addActionListener(this);
				resume.addActionListener(this);
				exit.addActionListener(this);
	}
	
	public void aboutboxinit()
	{
				aboutBox.setLayout(null);
				aboutBox.setSize(200,200);
				aboutBox.setBackground(Color.lightGray);
				aboutBox.setBounds(50,50,200,200);
				framer.add(aboutBox);
				
				aboutBox.add(resumeabout);
				aboutBox.add(aboutdet1);
				aboutBox.add(aboutdet2);
				aboutBox.add(aboutdet3);
				aboutBox.add(aboutdet4);
				aboutBox.add(aboutdet5);
				aboutBox.add(howtoplay);
				
				resumeabout.setBounds(10,150,40,40);
				aboutdet1.setBounds(10,15,180,20);
				aboutdet2.setBounds(10,35,180,20);
				aboutdet3.setBounds(15,70,180,20);
				aboutdet4.setBounds(16,93,180,20);
				aboutdet5.setBounds(15,113,137,20);
				howtoplay.setBounds(70,158,115,24);
				
				aboutdet1.setText("Designed and developed by");
				aboutdet2.setText("Pushpendu Ghosh");
				aboutdet3.setText("Language :  JAVA");
				aboutdet4.setText("Requires  :  JAVA 3.6.0");
				aboutdet5.setText("and above");
				
				aboutdet1.setHorizontalAlignment(SwingConstants.CENTER);
				aboutdet2.setHorizontalAlignment(SwingConstants.CENTER);
				aboutdet3.setHorizontalAlignment(SwingConstants.LEFT);
				aboutdet4.setHorizontalAlignment(SwingConstants.LEFT);
				aboutdet5.setHorizontalAlignment(SwingConstants.RIGHT);
				
				resumeabout.addActionListener(this);
				howtoplay.addActionListener(this);
	}
	
	class Input implements KeyListener //keyboard input
	{
		public Input()
		{
			framer.setFocusable(true);
			framer.addKeyListener(this);
		}
		
			@Override
    	public void keyTyped(KeyEvent e) {}

    		@Override
    	public void keyPressed(KeyEvent e) {

        		if ((e.getKeyCode() == KeyEvent.VK_UP)&&move!=2) 
            		move=1;
        		
        		if ((e.getKeyCode() == KeyEvent.VK_DOWN)&&move!=1) 
            		move=2;
        	
        		if ((e.getKeyCode() == KeyEvent.VK_RIGHT)&&move!=3)
            		move=4;
        	
        		if ((e.getKeyCode() == KeyEvent.VK_LEFT)&&move!=4) 
            		move=3;
    	}

    		@Override
    	public void keyReleased(KeyEvent e){}
	}
	
	class tottime implements Runnable{
		public void run()
		{	
			do{
				while(pause==true)
				{
					try{
					Thread.sleep(500);
					}
					catch(Exception e){}
				}
				try{
					Thread.sleep(1000);
					}
				catch(Exception e){}
				totaltime++;
				//System.out.println(totaltime);
				
			}while(gameend==false);
		}					
	}	
				
	class Bonus implements Runnable
	{
		public void run()
		{
			do{	
				System.out.print("");
				if(bonus==true)
				{
					do{
						timeron.setText("Timer : 00:");
						showtime.setText(Integer.toString(timeout));
						timeout--;
						try{
							Thread.sleep(400);
						}
						catch(Exception e)
							{
								//
						}
						
						while(pause==true)
						{
							try{
								Thread.sleep(500);
							}
							catch(Exception e){}
						}
				
						if(timeout==0){
							bonus=false;
							ate=true; 
						}
					}while(bonus==true);
					
					timeron.setText(" ");
					showtime.setText(" ");

				}
			}while(gameend==false);
		}
	}

	class GameDisplay implements Runnable
	{
		GameDisplay(){}
		
		public void getrandom()
		{
			Random ran = new Random();
			int ranx,rany;
			boolean gotit=true;
			do{
				ranx = (ran.nextInt(30))*10+2; 
				rany = (ran.nextInt(30))*10+2;
			
				gotit=true;
				for(i=0;i<snakelength;i++)
					{
						
						if((((mysnake[i].x)+1)==ranx)&&(((mysnake[i].y)+1)==rany)) {
							gotit=false;
							break;
							}
					}
			}while(gotit==false);
			
			food.x=ranx;
			food.y=rany;
		}
		
		public void run()
		{	
			getrandom();
			theFood.setBounds(food.x,food.y,6,6);
			do{	
				
				
				if(ate==true)
				{
					getrandom();
					if(bonus==true)
						score+=timeout*2;
					else if((round%5)!=0)
						score+=(4+round);
					showscore.setText(Integer.toString(score));
					time=time-((int)(29.81/round));
					round++;
					snakelength++;
					ate=false;
					theFood.setBounds(food.x,food.y,6,6);
					
					//for new pixel
					mysnake[snakelength-1]=new Snakepix();
					snakeboxes[snakelength-1]=new JPanel();
							
					for(i=snakelength-1;i>0;i--)
					{
						mysnake[i].x=mysnake[i-1].x;
						mysnake[i].y=mysnake[i-1].y;
					}
					mysnake[0].setxy(move);
					snakeboxes[snakelength-1].setSize(8,8);
					snakeboxes[snakelength-1].setBackground(Color.black);
					playboy.add(snakeboxes[snakelength-1]);
					
					if(round%5==0)
					{
						theFood.setBounds(food.x-2,food.y-2,10,10);
						timeout=30;
						bonus=true;
					}
					else
					{
						theFood.setBounds(food.x,food.y,6,6);
						bonus=false;
					}
				}
					
				for(i=0;i<snakelength;i++)
					snakeboxes[i].setBounds(mysnake[i].x,mysnake[i].y,8,8);
				
				if(move==3||move==4){
				eye1.setBounds(mysnake[0].x+2,mysnake[0].y-1,4,4);
				eye2.setBounds(mysnake[0].x+2,mysnake[0].y+5,4,4);
				}
				else{
				eye1.setBounds(mysnake[0].x-1,mysnake[0].y+2,4,4);
				eye2.setBounds(mysnake[0].x+5,mysnake[0].y+2,4,4);
				}

				
				try{
					Thread.sleep(time);
				}
				catch(Exception e){}
									
				while(pause==true)
				{
					try{
					Thread.sleep(500);
					}
					catch(Exception e){}
				}
				
				for(i=snakelength-1;i>0;i--)
				{
					mysnake[i].x=mysnake[i-1].x;
					mysnake[i].y=mysnake[i-1].y;
				}
				mysnake[0].setxy(move);
				
				for(i=snakelength-1;i>0;i--)
				{
					if((mysnake[0].x==mysnake[i].x)&&(mysnake[0].y==mysnake[i].y)){
						gameend=true;
						break;
					}
				}
				
				for(i=snakelength-1;i>0;i--)
				{
					if((mysnake[0].x==mysnake[i].x)&&(mysnake[0].y==mysnake[i].y)){
						gameend=true;
						break;
					}
				}

				
				if(((mysnake[0].x+8)>food.x)&&((mysnake[0].y+8)>food.y)&&((mysnake[0].x)<food.x)&&((mysnake[0].y)<food.y))
					ate=true;
			
			}while(gameend==false);
			
			if(gameend==true)
			{
				StringBuilder finalscore=new StringBuilder("");
				finalscore.append("You scored : ");
				finalscore.append(score);
				
				StringBuilder gameanalysis=new StringBuilder("");
				gameanalysis.append(" Time played : ");
				gameanalysis.append(totaltime);
				
				end.setLayout(null);
				endpanel.setLayout(null);
				endpanel.add(gameover);
				endpanel.add(youscored);
				//endpanel.add(analysis1);
				gameover.setText("GAME OVER");
				youscored.setText(finalscore.toString());
				//analysis1.setText(gameanalysis.toString());
				gameover.setHorizontalAlignment(SwingConstants.CENTER);
				//analysis1.setHorizontalAlignment(SwingConstants.LEFT);
				youscored.setHorizontalAlignment(SwingConstants.CENTER);
				gameover.setBounds(20,40,140,20);
				//analysis1.setBounds(20,90,140,20);
				//analysis2.setBounds(20,112,140,20);
				youscored.setBounds(20,62,140,20);
			
				framer.add(end);
				end.add(endpanel);
				endpanel.setBounds(60,80,180,115);
				endpanel.setBackground(Color.lightGray);
				end.setBounds(0,0,300,338);
			}
		}
	}
}


class game
{
	public static void main (String Args[])
	{	
		GameCompo obj = new GameCompo();
		GameCompo.Input keyinput = obj.new Input();
		GameCompo.GameDisplay disp = obj.new GameDisplay();
		GameCompo.Bonus bonustimer = obj.new Bonus();
		GameCompo.tottime timer = obj.new tottime();
		
		Thread thegame=new Thread(disp);
		Thread forbonus=new Thread(bonustimer);
		Thread timerstart=new Thread(timer);
		
		timerstart.start();
		thegame.start();
		forbonus.start();
	}
}

	
