package views;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ManageView extends JPanel
{
	//Model : 负责数据
	DefaultTableModel tableModel = new DefaultTableModel();
	
	//View : 负责显示
	JTable table = new JTable(tableModel);
	
	public ManageView() {
		// 添加到主界面
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true); // 整行选择
				
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);

		// 初始化设置：添加5列
		tableModel.addColumn("学号");
		tableModel.addColumn("姓名");
		tableModel.addColumn("性别");
		tableModel.addColumn("出生日期");
		tableModel.addColumn("手机号");

		// 添加数据行
		Student stu = new Student();
		stu.id = "20190001";
		stu.name = "li";
		stu.sex = true;
		stu.cellphone = "13810012345";
		stu.birthday = "1982-2-2";
		addTableRow(stu);

		Student stu2 = new Student();
		stu2.id = "20190002";
		stu2.name = "wang";
		stu2.sex = false;
		stu2.cellphone = "14788889999";
		stu2.birthday = "1982-2-3";
		addTableRow(stu2);
	}

	private void addTableRow(Student item)
	{
		// java.util.Vector 是个范型 ，表示数组
		Vector<Object> rowData = new Vector<>();
		rowData.add(item.id);
		rowData.add(item.name);
		rowData.add(item.sex);
		rowData.add(item.birthday);
		rowData.add(item.cellphone);
		tableModel.addRow(rowData); // 添加一行

		// Object[] rowData = new Object[5];
		// rowData[0] = item.id;
		// rowData[1] = item.name;
		// rowData[2] = item.sex;
		// rowData[3] = item.birthday;
		// rowData[4] = item.cellphone;
		// tableModel.addRow( rowData );
	}
}
