package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class MyFrame extends JFrame
{
	//Model:负责存储数据
	DefaultTableModel tableModel = new DefaultTableModel();
	
	//View:负责显示，创建JTabel的时候指定一个Model
	JTable table = new JTable(tableModel);
	
	public MyFrame(String title) {
		super(title);
		
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		table = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int colum) {
				return false;
			}
		};
		
		//添加到主界面
		JScrollPane scrollPane = new JScrollPane(table);//支持滚动
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);//整行选择,默认就是整行选择
		table.setRowHeight(25);
		root.add(scrollPane, BorderLayout.CENTER);
		
		//初始化设置，添加5列
		tableModel.addColumn("学号");
		tableModel.addColumn("姓名");
		tableModel.addColumn("性别");
		tableModel.addColumn("出生日期");
		tableModel.addColumn("手机号");
		
		//列设置：自定义绘制
		table.getColumnModel().getColumn(2).setCellRenderer(new SexColumnRenderer());
		table.getColumnModel().getColumn(0).setCellRenderer(new IDColumnReder());
		table.getColumnModel().getColumn(0).setPreferredWidth(110);//该列的宽度
		
		//添加数据行
		Student stu1 = new Student();
		stu1.id = "20180001";
		stu1.name = "li";
		stu1.sex = true;
		stu1.birthday = "1991-2-2";
		stu1.cellphone = "123456789";
		addTableRow(stu1);
		
		Student stu2 = new Student();
		stu2.id = "20180002";
		stu2.name = "liu";
		stu2.sex = false;
		stu2.birthday = "1993-5-8";
		stu2.cellphone = "156789323";
		addTableRow(stu2);
		
		Student stu3 = new Student();
		stu3.id = "20180003";
		stu3.name = "zhao";
		stu3.sex = true;
		stu3.cellphone = "14222244441";
		stu3.birthday = "1982-5-3";
		addTableRow ( stu3);
	}
	
	//第一列：用于呈现选中状态
	static class IDColumnReder extends JCheckBox implements TableCellRenderer{

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column)
		{
			this.setSelected(isSelected);
			if(value != null)
				this.setText(value.toString());
			
			//背景设置
			this.setOpaque(true);
			if(isSelected) {
				this.setBackground(table.getSelectionBackground());
				this.setForeground(table.getSelectionForeground());
			} else {
	        	this.setBackground(table.getBackground());
	        	this.setForeground(table.getForeground());
	        }
			return this;
		}
		
	}
	
	//负责"性别"这一列的显示
	static class SexColumnRenderer extends JLabel implements TableCellRenderer{
		public SexColumnRenderer() {
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setFont(this.getFont().deriveFont(Font.PLAIN));
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column)
		{
			Boolean sex = (Boolean)value;
			if(sex != null && sex == true)
				this.setText("男");
			else if(sex != null && sex == false)
				this.setText("女");
			else
				this.setText("null");
			// 背景设置
			this.setOpaque(true);
						
			if (isSelected) {
				this.setBackground(table.getSelectionBackground());
				this.setForeground(table.getSelectionForeground());
					    	
			} else {
				this.setBackground(table.getBackground());
				this.setForeground(table.getForeground());
			}
			return this;
		}
	}

	private void addTableRow(Student item) {
		
		//java.util.Vector 是一个泛型，表示数组
//		Vector<Object> rowData = new Vector<>();
//		rowData.add(item.id);
//		rowData.add(item.name);
//		rowData.add(item.sex);
//		rowData.add(item.birthday);
//		rowData.add(item.cellphone);
//		tableModel.addRow(rowData);
		
		Object[] rowData = new Object[5];
		rowData[0] = item.id;
		rowData[1] = item.name;
		rowData[2] = item.sex;
		rowData[3] = item.birthday;
		rowData[4] = item.cellphone;
		tableModel.addRow(rowData);
	}
}
