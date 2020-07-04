package secondheadC.Event.Dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import secondheadC.UI.dialog.CreatCalendar;


public class CalendarEvent {
	private CreatCalendar cc;
	private String s, value;
	private int x, y;

	public CalendarEvent(final CreatCalendar cc) {
		this.cc = cc;

		cc.jt.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mousePressed(MouseEvent e) {
				if ((e.getClickCount() == 2) && (e.BUTTON1 == e.getButton())) {
					s = (String) cc.jt.getValueAt(cc.jt.getSelectedRow(), cc.jt.getSelectedColumn());
					value =cc.setyear + "-" + cc.setmonth + "-" + s;
					cc.bddata.setText(value);
					cc.dispose();
				}else if((e.getClickCount() == 1) && (e.BUTTON1 == e.getButton())){
					x=cc.jt.getSelectedRow();
					y=cc.jt.getSelectedColumn();
					selecedTb();
					cc.jt.setSelectionBackground(Color.RED);
				}
			}
		});

		cc.dayjl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toMonth();
			}
		});

		cc.monthjl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toYear();
			}
		});

		cc.jls[0][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toDay(1);
			}
		});

		cc.jls[0][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toDay(2);
			}
		});

		cc.jls[0][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toDay(3);
			}
		});

		cc.jls[0][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toDay(4);
			}
		});

		cc.jls[1][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toDay(5);
			}
		});

		cc.jls[1][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toDay(6);
			}
		});

		cc.jls[1][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toDay(7);
			}
		});

		cc.jls[1][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toDay(8);
			}
		});

		cc.jls[2][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toDay(9);
			}
		});

		cc.jls[2][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toDay(10);
			}
		});

		cc.jls[2][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toDay(11);
			}
		});

		cc.jls[2][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toDay(12);
			}
		});

		cc.jlsYear[0][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setYear(Integer.valueOf(cc.jlsYear[0][3].getText()));
			}
		});

		cc.jlsYear[1][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setYear(Integer.valueOf(cc.jlsYear[1][0].getText()));
			}
		});

		cc.jlsYear[1][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setYear(Integer.valueOf(cc.jlsYear[1][1].getText()));
			}
		});

		cc.jlsYear[1][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setYear(Integer.valueOf(cc.jlsYear[1][2].getText()));
			}
		});

		cc.jlsYear[1][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setYear(Integer.valueOf(cc.jlsYear[1][3].getText()));
			}
		});

		cc.jlsYear[2][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setYear(Integer.valueOf(cc.jlsYear[2][0].getText()));
			}
		});

		cc.jlsYear[2][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setYear(Integer.valueOf(cc.jlsYear[2][1].getText()));
			}
		});

		cc.jlsYear[2][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setYear(Integer.valueOf(cc.jlsYear[2][2].getText()));
			}
		});

		cc.jlsYear[2][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setYear(Integer.valueOf(cc.jlsYear[2][3].getText()));
			}
		});

		cc.jlsYear[3][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setYear(Integer.valueOf(cc.jlsYear[3][0].getText()));
			}
		});

		cc.yearjb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uptenYear();

			}
		});

		cc.yearjb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				downtenYear();

			}
		});

		cc.monthjb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				upYear();

			}
		});

		cc.monthjb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				downYear();

			}
		});

		cc.dayjb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				upMonth();

			}
		});

		cc.dayjb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				downMonth();

			}
		});

		cc.timeJL.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toNow();
			}
		});

	}

	public void setYear(int year) {
		cc.c.set(Calendar.YEAR, year);
		cc.setyear=cc.c.get(Calendar.YEAR);
		cc.monthjl.setText(cc.setyear + "定");
		cc.dayJP.setVisible(false);
		cc.monthJP.setVisible(true);
		cc.yearJP.setVisible(false);
	}

	public void toNow() {
		cc.c.set(Calendar.YEAR, cc.toyear);
		cc.c.set(Calendar.MONTH, cc.tomonth - 1);
		cc.upDataDay();
		cc.dayJP.setVisible(true);
		cc.monthJP.setVisible(false);
		cc.yearJP.setVisible(false);
	}

	public void uptenYear() {
		cc.firstYear -= 10;
		cc.updataYear();
	}

	public void downtenYear() {
		cc.firstYear += 10;
		cc.updataYear();
	}

	public void upYear() {
		cc.c.add(Calendar.YEAR, -1);
		cc.upDataDay();
		cc.monthjl.setText(cc.setyear + "定");
	}

	public void downYear() {
		cc.c.add(Calendar.YEAR, +1);
		cc.upDataDay();
		cc.monthjl.setText(cc.setyear + "定");
	}

	public void upMonth() {
		cc.c.add(Calendar.MONTH, -1);
		cc.upDataDay();
		cc.jt.setDefaultRenderer(Object.class, cc.tcr);
	}

	public void downMonth() {
		cc.c.add(Calendar.MONTH, 1);
		cc.upDataDay();
		cc.jt.setDefaultRenderer(Object.class, cc.tcr);
	}

	public void toYear() {
		cc.dayJP.setVisible(false);
		cc.monthJP.setVisible(false);
		cc.yearJP.setVisible(true);
		cc.monthjl.setText(cc.setyear + "定");
	}

	public void toMonth() {
		cc.monthjl.setText(cc.setyear + "定");
		cc.dayJP.setVisible(false);
		cc.monthJP.setVisible(true);
		cc.yearJP.setVisible(false);
	}

	public void toDay(int i) {
		cc.c.roll(Calendar.MONTH, i - cc.setmonth);
		cc.upDataDay();
		cc.jt.setDefaultRenderer(Object.class, cc.tcr);
		cc.dayJP.setVisible(true);
		cc.monthJP.setVisible(false);
		cc.yearJP.setVisible(false);
	}
	
	
	private void selecedTb(){
		  DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
		      private static final long serialVersionUID = 1L;

		      @Override
		      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        JTextField text = new JTextField(value.toString());
		        text.setHorizontalAlignment(JTextField.CENTER);
		        text.setOpaque(true);
		        if (row == x & column == y) {
		          text.setBackground(Color.YELLOW);
		          text.setForeground(Color.BLACK);
		        } else {
		          text.setBackground(Color.WHITE);
		          text.setForeground(Color.BLACK);
		        }
		        return text;
		      }
		    };
		    
		    cc.jt.setDefaultRenderer(Object.class, tcr);
	}
}
