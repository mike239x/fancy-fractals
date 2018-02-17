import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class frack extends Applet{
	final JColorChooser change = new JColorChooser();
	Color colofpoint;
	Color colofthispoint;
	Color coloffrac;
	Color color;
	int red,green,blue;
	Scanner incolor;
	PrintWriter outcolor;
	final JFileChooser doit = new JFileChooser();;
	File aaa;
	final JOptionPane say = new JOptionPane();
	PrintWriter out;
	Scanner in;
	final JLabel angle = new JLabel("angle:");
	final JSpinner 	angg = new JSpinner();
	final JLabel digitation = new JLabel("dig:");
	final JSpinner digg1 = new JSpinner();
	final JLabel div=new JLabel("/");
	final JSpinner digg2 = new JSpinner();
	final JLabel number = new JLabel("num:");
	final JSpinner numm = new JSpinner();
	final JButton start = new JButton("start");
	final JButton delete = new JButton("delete");
	final JMenuBar menu = new JMenuBar();
	final JMenu menuu = new JMenu("File");
	final JMenuItem save = new JMenuItem("save");
	final JMenuItem load = new JMenuItem("load");
	final JMenuItem info = new JMenuItem("info");
	final JMenuItem about = new JMenuItem("about");
	final JMenuItem exit = new JMenuItem("exit");
	final JMenu changee = new JMenu("change");
	final JMenuItem colpoint = new JMenuItem("color of points");
	final JMenuItem colthispoint = new JMenuItem("color of current point");
	final JMenuItem colfrac = new JMenuItem("color of fractal");

	Dot[][] dots = new Dot[300][300];
	boolean[][] answer = new boolean[900][900];
	boolean[][] copyanswer = new boolean[900][900];
	boolean unsame = true;
	int many=0;
	boolean find = false;
	boolean result = false; 
	int x1, y1, ooop;




	@Override
	public void init() {

		setLayout(null);

//		this.

		colofpoint = Color.black;
		colofthispoint = Color.magenta;
		coloffrac = Color.green;
		try {
			incolor = new Scanner(new File("savecolor.bmp"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		red = incolor.nextInt();
		green = incolor.nextInt();
		blue = incolor.nextInt();
		colofpoint = new Color(red,green,blue);
		red = incolor.nextInt();
		green = incolor.nextInt();
		blue = incolor.nextInt();
		colofthispoint = new Color(red,green,blue);
		red = incolor.nextInt();
		green = incolor.nextInt();
		blue = incolor.nextInt();
		coloffrac = new Color(red,green,blue);
		incolor.close();

		for(int k=0;k<300;k++){
			for(int l=0;l<300;l++){
				dots[k][l]= new Dot();
			}
		}


		add(angle);
		angle.setBounds(1, 1, 45, 25);

		add(angg);
		angg.setBounds(47, 1, 40, 25);

		angg.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				result=false;
				if(angg.getValue().hashCode()==181){
					angg.setValue(-179);
				}
				if(angg.getValue().hashCode()==-180){
					angg.setValue(180);
				}
				if(numm.getValue().hashCode()!=0){
					for(int k=0;k<300;k++){
						for(int l=0;l<300;l++){
							if(numm.getValue().hashCode()==dots[k][l].num){
								dots[k][l].ang=angg.getValue().hashCode();
								find=true;
								break;

							}
						}
						if(find){
							break;
						}
					}
					find=false;
				}
				repaint();
			}
		});

		angg.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				result=false;
				if(!e.isControlDown()){
					if(angg.getValue().hashCode()<176){
						angg.setValue(angg.getValue().hashCode()+5);
					}else{
						angg.setValue(angg.getValue().hashCode()-360+5);
					}
				}else{
					if(angg.getValue().hashCode()>-176){
						angg.setValue(angg.getValue().hashCode()-5);
					}else{
						angg.setValue(angg.getValue().hashCode()+360-5);
					}
				}
				if(numm.getValue().hashCode()!=0){
					for(int k=0;k<300;k++){
						for(int l=0;l<300;l++){
							if(numm.getValue().hashCode()==dots[k][l].num){
								dots[k][l].ang=angg.getValue().hashCode();
								find=true;
								break;

							}
						}
						if(find){
							break;
						}
					}
					find=false;
				}
				repaint();
			}
		});


		add(digitation);
		digitation.setBounds(88, 1, 30, 25);


		add(digg1);
		digg1.setBounds(119, 1, 45, 25);
		digg1.setValue(1);

		digg1.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				result=false;
				if(!e.isControlDown()){
					if(digg1.getValue().hashCode()+5<digg2.getValue().hashCode()){
						digg1.setValue(digg1.getValue().hashCode()+5);
					}else{
						digg1.setValue(digg2.getValue().hashCode());
					}
				}else{
					if(digg1.getValue().hashCode()>5){
						digg1.setValue(digg1.getValue().hashCode()-5);
					}else{
						digg1.setValue(1);
					}
				}
				if(numm.getValue().hashCode()!=0){
					for(int k=0;k<300;k++){
						for(int l=0;l<300;l++){
							if(numm.getValue().hashCode()==dots[k][l].num){
								dots[k][l].dig1=digg1.getValue().hashCode();
								find=true;
								break;

							}
						}
						if(find){
							break;
						}
					}
					find=false;
				}
				repaint();
			}
		});

		digg1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				result=false;
				if(digg1.getValue().hashCode()==0){
					digg1.setValue(1);
				}
				if(digg1.getValue().hashCode()>digg2.getValue().hashCode()){
					digg1.setValue(digg2.getValue().hashCode());
				}
				if(numm.getValue().hashCode()!=0){
					for(int k=0;k<300;k++){
						for(int l=0;l<300;l++){
							if(numm.getValue().hashCode()==dots[k][l].num){
								dots[k][l].dig1=digg1.getValue().hashCode();
								find=true;
								break;

							}
						}
						if(find){
							break;
						}
					}
					find=false;
				}
				repaint();
			}
		});


		add(div);
		div.setBounds(165, 1, 10, 25);

		add(digg2);
		digg2.setBounds(176, 1, 45, 25);
		digg2.setValue(2);

		digg2.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				result=false;
				if(!e.isControlDown()){
					digg2.setValue(digg2.getValue().hashCode()+5);
				}else{
					if(digg2.getValue().hashCode()>digg1.getValue().hashCode()+4){
						digg2.setValue(digg2.getValue().hashCode()-5);
					}else{
						digg2.setValue(digg1.getValue().hashCode());
					}
				}
				if(numm.getValue().hashCode()!=0){
					for(int k=0;k<300;k++){
						for(int l=0;l<300;l++){
							if(numm.getValue().hashCode()==dots[k][l].num){
								dots[k][l].dig2=digg2.getValue().hashCode();
								find=true;
								break;

							}
						}
						if(find){
							break;
						}
					}
					find=false;
				}
				repaint();
			}
		});

		digg2.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				result=false;
				if(digg2.getValue().hashCode()<digg1.getValue().hashCode()){
					digg2.setValue(digg1.getValue().hashCode());
				}
				if(numm.getValue().hashCode()!=0){
					for(int k=0;k<300;k++){
						for(int l=0;l<300;l++){
							if(numm.getValue().hashCode()==dots[k][l].num){
								dots[k][l].dig2=digg2.getValue().hashCode();
								find=true;
								break;

							}
						}
						if(find){
							break;
						}
					}
					find=false;
				}
				repaint();
			}
		});

		add(number);
		number.setBounds(222, 1, 40, 25);

		add(numm);
		numm.setBounds(263, 1, 35, 25);
		numm.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				result=false;
				if(!e.isControlDown()){
					if(numm.getValue().hashCode()<many){
						numm.setValue(numm.getValue().hashCode()+1);
					}else{
						numm.setValue(many);
					}
				}else{
					if(numm.getValue().hashCode()>0){
						numm.setValue(numm.getValue().hashCode()-1);
					}else{
						numm.setValue(0);
					}
				}
				if(numm.getValue().hashCode()!=0){
					for(int k=0;k<300;k++){
						for(int l=0;l<300;l++){
							if(numm.getValue().hashCode()==dots[k][l].num){
								angg.setValue(dots[k][l].ang);
								digg1.setValue(dots[k][l].dig1);
								digg2.setValue(dots[k][l].dig2);
								find=true;
								break;

							}
						}
						if(find){
							break;
						}
					}
					find=false;
				}
				repaint();
			}
		});
		numm.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				result=false;
				if(numm.getValue().hashCode()>many){
					numm.setValue(many);
				}
				if(numm.getValue().hashCode()<0){
					numm.setValue(0);
				}
				if(numm.getValue().hashCode()!=0){
					for(int k=0;k<300;k++){
						for(int l=0;l<300;l++){
							if(numm.getValue().hashCode()==dots[k][l].num){
								angg.setValue(dots[k][l].ang);
								digg1.setValue(dots[k][l].dig1);
								digg2.setValue(dots[k][l].dig2);
								find=true;
								break;

							}
						}
						if(find){
							break;
						}
					}
					find=false;
				}
				repaint();
			}
		});

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				result=false;
				if(e.getX()<900&&e.getY()<927&&e.getY()>27){

					if(dots[e.getX()/3][e.getY()/3-9].num!=0){
						numm.setValue(dots[e.getX()/3][e.getY()/3-9].num);
						angg.setValue(dots[e.getX()/3][e.getY()/3-9].ang);
						digg1.setValue(dots[e.getX()/3][e.getY()/3-9].dig1);
						digg2.setValue(dots[e.getX()/3][e.getY()/3-9].dig2);
						repaint();
					}else{
						many++;
						dots[e.getX()/3][(e.getY())/3-9].num=many;
						dots[e.getX()/3][(e.getY())/3-9].ang=angg.getValue().hashCode();
						dots[e.getX()/3][(e.getY())/3-9].dig1=digg1.getValue().hashCode();
						dots[e.getX()/3][(e.getY())/3-9].dig2=digg2.getValue().hashCode();
						numm.setValue(many);

					}
				}
				repaint();
			}
		});

		add(start);
		start.setBounds(299, 1, 70, 25);
		start.setBackground(Color.yellow);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(many!=0){
					result=true;
					for(int k=0;k<900;k++){
						for(int l=0;l<900;l++){
							answer[k][l]=false;
							copyanswer[k][l]=false;
						}
					}
					answer[450][450]=true;
					//					for(int k = 0 ;k<300;k++){
					//						for(int l = 0 ;l<300;l++){
					//							if(dots[k][l].num!=0){
					//								anser[3*k+1][3*l+1]=true;
					//								find=true;
					//								break;
					//							}
					//						}
					//						if(find){
					//							break;
					//						}
					//					}
					find=false;
					unsame=true;
					while(unsame){
						for(int q=0;q<300;q++){
							for(int w=0;w<300;w++){
								if(dots[q][w].num!=0){
									for(int k=0;k<900;k++){
										for(int l=0;l<900;l++){
											if(answer[k][l]){
												y1=(int) Math.round((Math.cos((double)(dots[q][w].ang)/57)*(k-q*3-1)+Math.sin((double)(dots[q][w].ang)/57)*(l-w*3-1))*((double)dots[q][w].dig1/(double)dots[q][w].dig2)+q*3+1);
												x1=(int) Math.round((Math.cos((double)(dots[q][w].ang)/57)*(l-w*3-1)-Math.sin((double)(dots[q][w].ang)/57)*(k-q*3-1))*((double)dots[q][w].dig1/(double)dots[q][w].dig2)+w*3+1);
												if((x1>-1)&&(x1<900)&&(y1>-1)&&(y1<900)){
													copyanswer[y1][x1]=true;
												}
												// TODO 
												/* x'=cos*(x-x1)+sin*(y-y1)+x1;
												 * y'=cos*(y-y1)-sin*(x-x1)+y1;
												 * (int) Math.round(k-0.5);
												 * Math.cos(angg.getValue().hashCode());
												 * Math.sin(angg.getValue().hashCode());
												 * 
												 */
											}
										}
									}
								}
							}
						}
						unsame=false;
						for(int k=0;k<900;k++){
							for(int l=0;l<900;l++){
								unsame|=(answer[k][l]!=copyanswer[k][l]);
							}
						}
						for(int k=0;k<900;k++){
							for(int l=0;l<900;l++){
								answer[k][l]=copyanswer[k][l];
								copyanswer[k][l]=false;
							}
						}

						//repaint();
					}
				//	result = true;
					repaint();
				}else{
					result=false;
					JOptionPane.showMessageDialog(null, "there's no dots!", "error", 0);
				}
			}
		});

		add(delete);
		delete.setBounds(370, 1, 80, 25);
		delete.setBackground(Color.yellow);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(numm.getValue().hashCode()!=0){
					result=false;
					for(int k = 0 ;k<300;k++){
						for(int l = 0 ;l<300;l++){
							if(dots[k][l].num>=numm.getValue().hashCode()){
								if(dots[k][l].num>numm.getValue().hashCode()){
									dots[k][l].num = (byte) (dots[k][l].num - 1);
								}else{
									dots[k][l].num=0;
								}
							}
						}
					}
					many-=1;
					numm.setValue(numm.getValue().hashCode()-1);
				}
			}
		});

		changee.add(colpoint);
		changee.add(colthispoint);
		changee.add(colfrac);
		menuu.add(save);
		menuu.add(load);
		menuu.add(changee);
		menuu.add(info);
		menuu.add(about);
		menuu.add(exit);
		menu.add(menuu);
		add(menu);
		menu.setBounds(451, 1, 40, 25);

		colpoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				color = change.showDialog(null, "chose it", colofpoint);
				if(color!=null&&color!=Color.white&&color!=colofpoint&&color!=coloffrac){
					colofpoint=color;
					repaint();
				}
				try {
					outcolor = new PrintWriter(new File("savecolor.bmp"));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				outcolor.println(colofpoint.getRed());
				outcolor.println(colofpoint.getGreen());
				outcolor.println(colofpoint.getBlue());
				outcolor.println(colofthispoint.getRed());
				outcolor.println(colofthispoint.getGreen());
				outcolor.println(colofthispoint.getBlue());
				outcolor.println(coloffrac.getRed());
				outcolor.println(coloffrac.getGreen());
				outcolor.println(coloffrac.getBlue());
				outcolor.close();
			}
		});

		colthispoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				color = change.showDialog(null, "chose it", colofthispoint);
				if(color!=null&&color!=Color.white&&color!=colofpoint&&color!=coloffrac){
					colofthispoint=color;
					repaint();
				}
				try {
					outcolor = new PrintWriter(new File("savecolor.bmp"));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				outcolor.println(colofpoint.getRed());
				outcolor.println(colofpoint.getGreen());
				outcolor.println(colofpoint.getBlue());
				outcolor.println(colofthispoint.getRed());
				outcolor.println(colofthispoint.getGreen());
				outcolor.println(colofthispoint.getBlue());
				outcolor.println(coloffrac.getRed());
				outcolor.println(coloffrac.getGreen());
				outcolor.println(coloffrac.getBlue());
				outcolor.close();
			}
		});

		colfrac.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				color = change.showDialog(null, "chose it", coloffrac);
				if(color!=null&&color!=Color.white&&color!=colofpoint&&color!=coloffrac){
					coloffrac=color;
					repaint();
				}
				try {
					outcolor = new PrintWriter(new File("savecolor.bmp"));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				outcolor.println(colofpoint.getRed());
				outcolor.println(colofpoint.getGreen());
				outcolor.println(colofpoint.getBlue());
				outcolor.println(colofthispoint.getRed());
				outcolor.println(colofthispoint.getGreen());
				outcolor.println(colofthispoint.getBlue());
				outcolor.println(coloffrac.getRed());
				outcolor.println(coloffrac.getGreen());
				outcolor.println(coloffrac.getBlue());
				outcolor.close();
			}
		});

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doit.showSaveDialog(null);
				aaa=doit.getSelectedFile();
				if(aaa!=null){
					try {
						out = new PrintWriter(aaa);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					out.println(many);
					find=false;
					for(int zzz = 0;zzz<many;zzz++){
						for(int k=0;k<300;k++){
							for(int l=0;l<300;l++){
								if(zzz+1==dots[k][l].num){
									out.println(k+" "+l);
									out.println(dots[k][l].ang+" "+dots[k][l].dig1+" "+dots[k][l].dig2);
									find=true;
									break;

								}
							}
							if(find){
								break;
							}
						}
						find=false;
					}
					out.println("0");
					out.close();
					
				}
			}
		});

		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doit.showOpenDialog(null);
				aaa=doit.getSelectedFile();
				if(aaa!=null){
					find=true;
					try {
						in = new Scanner(aaa);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					if(in.hasNextInt()){
						ooop=in.nextInt();
						if(ooop>-1){
							for(int zzz = 0;zzz<ooop;zzz++){
								if(find&&in.hasNextInt()){
									x1=in.nextInt();
								}else{
									find=false;
								}
								if(find&&in.hasNextInt()){
									y1=in.nextInt();
								}else{
									find=false;
								}
								if(find&&in.hasNextInt()&&(x1>-1&&y1>-1)){
									x1=in.nextInt();
								}else{
									find=false;
								}
								if(find&&in.hasNextInt()&&(x1<181&&x1>-180)){
									x1=in.nextInt();
								}else{
									find=false;
								}
								if(find&&in.hasNextInt()){
									y1=in.nextInt();
								}else{
									find=false;
								}
								if(find&&in.hasNextInt()&&(x1<y1+1&&x1>0)){
								}else{
									find=false;
								}
							}
						}else{
							find=false;
						}
					}else{
						find=false;
					}
					in.close();
					if(find){
						try {
							in = new Scanner(aaa);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						for(int k=0;k<300;k++){
							for(int l=0;l<300;l++){
								dots[k][l].num = 0;
							}
						}
						many=in.nextInt();
						System.out.println(many);
						find=false;
						for(int zzz = 0;zzz<many;zzz++){
							x1=in.nextInt();
							y1=in.nextInt();
							for(int k=0;k<300;k++){
								for(int l=0;l<300;l++){
									if(x1==k&&y1==l){
										dots[k][l].num=zzz+1;
										dots[k][l].ang=in.nextInt();
										dots[k][l].dig1=in.nextInt();
										dots[k][l].dig2=in.nextInt();
										find=true;
										break;

									}
								}
								if(find){
									break;
								}
							}
							find=false;
						}
						result = true;
						in.close();
						repaint();
					}else{
						JOptionPane.showMessageDialog(null, "error in ridding...", "error", 0);
					}
					
					
				}
			}
		});

		info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//				say.showMessageDialog(null, "sdadadad", "mmm", 0);
				//				0 - "X" (error)
				//				1 - "i" (info)
				//				2 - "!" (can be problems)
				//				3 - "?" ( (-: )
				//				-1 - "" (simple massege)

				say.showMessageDialog(null, "use mouse to create a new point", "info", -1);
			}
		});
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				say.showMessageDialog(null, "asdsadasd", "about us", 1);
			}
		});
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}
	{}


	@Override
	public void paint(Graphics g) {
		//g.fillRect(2, 2, 5, 5);
		//		g.setColor(Color.lightGray);
		//		for(int k = 0 ;k<300;k++){
		//			g.drawLine(k*3+3, 27, k*3+3, 927);
		//			g.drawLine(0, k*3+27, 900, k*3+27);	
		//		}

		for(int k = 0 ;k<300;k++){
			for(int l = 0 ;l<300;l++){
				if(dots[k][l].num!=0){
					g.setColor(Color.lightGray);
					g.drawLine(3*k+1, 27, 3*k+1, 927);
					g.drawLine(1, 3*l+1+27, 901, 3*l+1+27);
					if(3*(k+l)+2>900){
						g.drawLine(900, 3*k+3*l+2-900+27, 3*k+3*l+2-900, 900+27);
					}else{
						g.drawLine(3*k+3*l+2, 27, 1, 3*k+3*l+2+26);
					}
					if(k>l){
						g.drawLine(3*k-3*l, 27, 900, 927-3*k+3*l);
					}else{
						g.drawLine(0, 3*l-3*k+27, 900-3*l+3*k, 927);
					}

				}
			}
		}
		g.setColor(colofpoint);
		for(int k = 0 ;k<300;k++){
			for(int l = 0 ;l<300;l++){
				if(dots[k][l].num!=0){
					if(dots[k][l].num==numm.getValue().hashCode()){
						g.setColor(colofthispoint);
					}
					g.fillRect(k*3, l*3+27, 3, 3);
					if(dots[k][l].num==numm.getValue().hashCode()){
						g.setColor(colofpoint);
					}
				}
			}
		}
		if(result){
			g.setColor(coloffrac);
			for(int k = 0 ;k<900;k++){
				for(int l = 0 ;l<900;l++){
					if(answer[k][l]){
						g.fillRect(k, l+27, 1, 1);
					}
				}
			}
			g.setColor(colofpoint);
			for(int k = 0 ;k<300;k++){
				for(int l = 0 ;l<300;l++){
					if(dots[k][l].num!=0&&answer[3*k][3*l]&&answer[3*k+1][3*l]&&answer[3*k+2][3*l]&&answer[3*k][3*l+1]&&answer[3*k+1][3*l+1]&&answer[3*k+2][3*l+1]&&answer[3*k][3*l+2]&&answer[3*k+1][3*l+2]&&answer[3*k+2][3*l+2]){
						if(dots[k][l].num==numm.getValue().hashCode()){
							g.setColor(colofthispoint);
						}
						g.fillRect(k*3+1, l*3+27+1, 1, 1);
						if(dots[k][l].num==numm.getValue().hashCode()){
							g.setColor(colofpoint);
						}
					}
				}
			}
			g.setColor(Color.black);
		}
		super.paint(g);
	}
}
