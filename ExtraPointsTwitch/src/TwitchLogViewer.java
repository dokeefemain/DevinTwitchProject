import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


class FC extends JFrame implements ActionListener{
	private JLabel ftext = new JLabel("Chanel you want to view"), ctext = new JLabel("User you want to check"), ltext = new JLabel("LOGS") ;
	private JTextField f = new JTextField(5), c = new JTextField(5);
	private JButton press = new JButton("SEARCH");
	private JTextArea lotext = new JTextArea(50,50);
	
	FC(String title){
		super (title);
		setLayout (new FlowLayout());
		setBounds (50,50, 750, 500);f.addActionListener( this );
		JScrollPane areaScrollPane = new JScrollPane(lotext);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(225, 225));
		c.addActionListener(this);
		press.addActionListener(this);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		lotext.setLineWrap(true);
		add(ftext);
		add(f);
		add(ctext);
		add(c);
		add(press);
		add(ltext);
		add(lotext);
	}public void paint(Graphics g) {
		Container con = this.getContentPane();
		ftext.setFont(new Font("Helvetica", Font.PLAIN, 20));
		ctext.setFont(new Font("Helvetica", Font.PLAIN, 20));
		ltext.setFont(new Font("Helvetica", Font.PLAIN, 20));
		lotext.setFont(new Font("Helvetica", Font.PLAIN, 15));;
	}
	public static String getStreamerName(String s, String []a) {
		String log = "Could not find name";
		for(int i = 0; i<a.length; i++) {
			int b = a[i].indexOf(" ");
			String name = a[i].substring(0, b);
			if(s.compareTo(name)==0) {
				log = a[i];
			}
		}return log;
	}public static int getCount(String s) {
		if(s.length()==0) {
			return 0;
		}else {
			return 1+getCount(s.substring(s.indexOf(';')+1,s.length()));
		}
	}
	public static ArrayList<String> getLog(String s, String t, int i){
		ArrayList<String> logs = new ArrayList<String>();
		i = i-1;
		s = s.substring(s.indexOf(';'),s.length());
		for(int g = 0; g<=i-1; g++) {
			int e = s.indexOf(";");
			String name=s.substring(0,s.indexOf('>'));
			if(name.compareTo(t)==0) {
				String log = " ";
				String log1 = " ";
				log = s.substring(s.indexOf('>')+2,s.indexOf('[')+1);
				log1 = log.replace('[', ' ');
				logs.add(log1);
			}if(g<i-1) {
				s = s.substring(e+1,s.length());
			}
		}
		return logs;
	}public static String readFileAsString(String fileName) {
	    String text = "";
	    try {
	      text = new String(Files.readAllBytes(Paths.get(fileName)));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return text;
	  }public static String rtoStringg(ArrayList<String> a, int size) {
		  if(size==0) {
			  return "1: "+a.get(0)+" ";
		  }else {
			  return  rtoStringg(a,size-1)+" "+(size+1)+": "+a.get(size);
		  }
	  }
	  public static void insertionSort(String [] A) {
			for (int i = 0; i < A.length; i++) {
				int back = i-1;
				String val = A[i];
				while(back >= 0 && A[back].compareTo(val)<0) {
						A[back+1]=A[back];
						A[back]=val;
						back -= 1;
				}
			}
		}
	public void actionPerformed(ActionEvent evt) {
		String timthetatman = readFileAsString("timthetatman.txt");
		String xqcow = readFileAsString("xqcow.txt");
		String shroud = readFileAsString("shroud.txt");
		String destiny = readFileAsString("destiny.txt");
		String kabajiow = readFileAsString("kabajiow.txt");
		String tfue= readFileAsString("tfue.txt");
		String  dafran = readFileAsString("dafran.txt");
		String sinatraa = readFileAsString("sinatraa.txt");
		xqcow= xqcow.replace(';', ' ');
		xqcow = xqcow.replace('<', ';');
		shroud = shroud.replace(';', ' ');
		shroud = shroud.replace('<', ';');
		destiny = destiny.replace(';', ' ');
		destiny = destiny.replace('<', ';');
		timthetatman = timthetatman.replace(';', ' ');
		timthetatman = timthetatman.replace('<', ';');
		kabajiow = kabajiow.replace(';', ' ');
		kabajiow = kabajiow.replace('<', ';');
		tfue = tfue.replace(';', ' ');
		dafran= dafran.replace(';', ' ');
		sinatraa = sinatraa.replace(';', ' ');
		tfue= tfue.replace('<', ';');
		dafran = dafran.replace('<', ';');
		sinatraa = sinatraa.replace('<', ';');
		String []StreamerNames = {xqcow,timthetatman,shroud,destiny,kabajiow,tfue,dafran,sinatraa};
		insertionSort(StreamerNames);
		String s = f.getText();
		String t = c.getText();
		String Streamerlog = getStreamerName(s, StreamerNames);
		int i = getCount(Streamerlog);
		ArrayList<String> logs = getLog(Streamerlog, t, i);
		lotext.setText(rtoStringg(logs,logs.size()-1));
	}
}public class TwitchLogViewer {
	public static void main(String[] args) {
		FC fc = new FC("TWITCH LOG VIEWER");
		fc.setVisible(true);
	}
}

