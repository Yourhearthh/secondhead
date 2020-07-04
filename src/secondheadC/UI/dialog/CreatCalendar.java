package secondheadC.UI.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import secondheadC.Event.Dialog.CalendarEvent;


@SuppressWarnings("serial")
public class CreatCalendar extends JDialog {

	public int toyear =Calendar.getInstance().get(Calendar.YEAR), tomonth = Calendar.getInstance().get(Calendar.MONTH) + 1 ,
			today =Calendar.getInstance().get(Calendar.DATE);
	public Calendar c = Calendar.getInstance();
	public int setmonth,setyear,x,y,firstYear;
	public JPanel dayJP,monthJP,yearJP;
	public JLabel dayjl,monthjl,yearjl,jls[][],jlsYear[][],timeJL;
	public JTable jt;
	public JButton dayjb1,dayjb2,monthjb1,monthjb2,yearjb1,yearjb2;
	public DefaultTableCellRenderer tcr;
	public JDialog aud;
	public JTextField bddata;

	
	@SuppressWarnings("unused")
	public CreatCalendar(JTextField bddata) {
		this(null,"请双击日期获得日期数据",true);
		this.bddata = bddata;
		setSize(400, 200);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("E:\\java\\txtbook\\file\\记事本.png"));
		setLocationRelativeTo(null);
		setMonth();
		setDay();
		setYear();
		setTime();
		CalendarEvent ce=new CalendarEvent(this);
		setVisible(true);
	}
	
	public void setYear(){
		firstYear = setyear-(setyear%10);
		int endyear=firstYear+9, year=firstYear;
		JPanel jp = new JPanel();
		jp.setLayout(null);

		jlsYear = new JLabel[4][4];

		int x, y = 10;
		for (int i = 0; i < 4; i++) {
			x = 0;
			for (int j = 0; j < 4; j++) {
				if(year>endyear){
					break;
				}
				if((i==0&&j<3)||(i==3&&j>0)){
					jlsYear[i][j]=new JLabel("");
				}else{
				jlsYear[i][j]=new JLabel((year++)+"",JLabel.CENTER);
				}
				jlsYear[i][j].setBounds(x, y, 100, 27);
				x += 100;
				jp.add(jlsYear[i][j]);
			}
			y += 27;
		}

		this.yearjl = new JLabel(firstYear+"-"+endyear, JLabel.CENTER);
		yearjl.setBounds(50, 0, 300, 20);
		jp.setBounds(0, 0, 400, 110);

		yearJP = new JPanel();
		yearJP.setLayout(new BorderLayout());
		yearJP.setBounds(0, 0, 400, 140);
		setYearButton(yearJP);
		yearJP.add(yearjl);
		yearJP.add(jp);
		yearJP.setVisible(false);
		this.add(yearJP);
	}
	
	public void updataYear(){
		int endyear=firstYear+9,year=firstYear;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(year>endyear){
					break;
				}
				if((i==0&&j<3)||(i==3&&j>0)){
					jlsYear[i][j]=new JLabel("");
				}else{
				jlsYear[i][j].setText((year++)+"");
				}
			}
		}

		this.yearjl.setText(firstYear+"-"+endyear);
		
	}

	public void setMonth() {
		String s[][] = { {"一月", "二月", "三月", "四月"}, {"五月", "六月", "七月", "八月"},{ "九月", "十月", "十一月", "十二月"} };
		setyear=c.get(Calendar.YEAR);
		JPanel jp = new JPanel();
		jp.setLayout(null);

		jls = new JLabel[3][4];

		int x, y = 10;
		for (int i = 0; i < 3; i++) {
			x = 0;
			for (int j = 0; j < 4; j++) {
				jls[i][j] = new JLabel(s[i][j], JLabel.CENTER);
				jls[i][j].setBounds(x, y, 100, 36);
				x += 100;
				jp.add(jls[i][j]);
			}
			y += 36;
		}
	
		this.monthjl = new JLabel(this.setyear + "年" , JLabel.CENTER);
		monthjl.setBounds(50, 0, 300, 20);
		jp.setBounds(0, 20, 400, 110);

		monthJP = new JPanel();
		monthJP.setLayout(new BorderLayout());
		monthJP.setBounds(0, 0, 400, 140);
		setMonthButton(monthJP);
		monthJP.add(monthjl);
		monthJP.add(jp);
		monthJP.setVisible(false);
		this.add(monthJP);
	}

	public void setDay() {
		c.add(Calendar.DATE, -((c.get(Calendar.DAY_OF_MONTH)) - 1));
		setmonth=c.get(Calendar.MONTH)+1;
		setyear=c.get(Calendar.YEAR);
		int week = c.get(Calendar.DAY_OF_WEEK) - 1, day = 1, endday =getdaycount(setmonth,setyear);
		String date[][] = new String[6][7], w[] = { "日", "一", "二", "三", "四", "五", "六" };

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if(day==today){
					x=i;
					y=j;
				}
				if( (i == 0 && week > j)||(day>endday)) {
					date[i][j] = "";
				} else {
					date[i][j] = day + "";
					day++;
				}
			}
		}

		jt = new JTable();
		DefaultTableModel newTableModel = new DefaultTableModel(date, w) {// 此model可选中不可编辑
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		

	    tcr = new DefaultTableCellRenderer() {
	      private static final long serialVersionUID = 1L;

	      @Override
	      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        JTextField text = new JTextField(value.toString());
	        text.setHorizontalAlignment(JTextField.CENTER);
	        text.setOpaque(true);
	        if (row == x & column == y) {
	          text.setBackground(Color.BLUE);
	          text.setForeground(Color.WHITE);
	        } else {
	          text.setBackground(Color.WHITE);
	          text.setForeground(Color.BLACK);
	        }
	        return text;
	      }
	    };
	    
	    jt.setDefaultRenderer(Object.class, tcr);
		jt.setModel(newTableModel);
		jt.getTableHeader().setReorderingAllowed(false);// 表头不可拖动
		jt.getTableHeader().setResizingAllowed(false);// 列不可拉伸
		jt.setCellSelectionEnabled(true);
		

		dayjl = new JLabel(this.setyear + "年" + this.setmonth + "月" , JLabel.CENTER);
		dayjl.setBounds(50, 0, 300, 20);

		JScrollPane jsp = new JScrollPane(jt);
		jsp.setBounds(0, 0, 400, 110);
		dayJP = new JPanel();
		dayJP.setLayout(new BorderLayout());
		dayJP.setBounds(0, 0, 400, 130);
		setDayButton(dayJP);
		dayJP.add(dayjl, BorderLayout.NORTH);
		dayJP.add(jsp, BorderLayout.CENTER);

		this.repaint();
		this.add(dayJP);

	}
	
	private void setDayButton(JPanel jp){
		dayjb1 = new JButton("←");
		dayjb1.setBounds(10, 0, 50, 15);
		dayjb2 = new JButton("→");
		dayjb2.setBounds(325, 0, 50, 15);
		jp.add(dayjb1);
		jp.add(dayjb2);
	}

	private void setMonthButton(JPanel jp){
		monthjb1 = new JButton("←");
		monthjb1.setBounds(10, 0, 50, 15);
		monthjb2 = new JButton("→");
		monthjb2.setBounds(325, 0, 50, 15);
		jp.add(monthjb1);
		jp.add(monthjb2);
	}
	
	private void setYearButton(JPanel jp){
		yearjb1 = new JButton("←");
		yearjb1.setBounds(10, 0, 50, 15);
		yearjb2 = new JButton("→");
		yearjb2.setBounds(325, 0, 50, 15);
		jp.add(yearjb1);
		jp.add(yearjb2);
	}
	
	public void upDataDay(){
		setmonth=c.get(Calendar.MONTH)+1;
		setyear=c.get(Calendar.YEAR);
		int week = c.get(Calendar.DAY_OF_WEEK) - 1, day = 1, endday =getdaycount(setmonth,setyear);

		String date[][] = new String[6][7], w[] = { "日", "一", "二", "三", "四", "五", "六" };

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if( (i == 0 && week > j)||(day>endday)) {
					date[i][j] = "";
				} else {
					if((setmonth==tomonth)&&(setyear==toyear)&&(day==today)){
						x=i;
						y=j;
					}else if(day==1){
						x=i;
						y=j;
					}
					date[i][j] = day + "";
					day++;
				}
			}
		}
		DefaultTableModel newTableModel = new DefaultTableModel(date, w) {// 此model可选中不可编辑
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		jt.setModel(newTableModel);
		dayjl.setText(this.setyear + "年" + this.setmonth + "月");
		
	}

	private int getdaycount(int month,int year) {
		int day;
		if((month==1)||(month==3)||(month==5)||(month==7)||(month==8)||(month==10)||(month==12)){
			day=31;
		}else if(month!=2){
			day=30;
		}else if((year%400==0)||((year%4==0)&&(year%100!=0))){
			day=29;
		}else{
			day=28;
		}
		return day;
	}

	public void setTime(){
		JPanel timeJP=new JPanel();
		timeJP.setLayout(new BorderLayout());
		timeJL = new JLabel(toyear+"-"+tomonth+"-"+today,JLabel.CENTER);
		timeJL.setToolTipText("转到今日");
		
		timeJP.add(timeJL,BorderLayout.SOUTH);
		this.add(timeJP,BorderLayout.SOUTH);
		
		
		new Thread(){
			public void run(){
				SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
				while(true){
				timeJL.setText(toyear+"-"+tomonth+"-"+today+"  "+ df.format(new Date()));
				}
			}
		}.start();
	}
	
	public CreatCalendar(Object  object, String string, boolean b) {
		super( (Frame) object,string,b);
	}
}
