package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import af.common.json.AfJSON;

public class MyFrame extends JFrame
{
	JPanel root = new JPanel();
	JTable table = null;
	DefaultTableModel tableModel = new DefaultTableModel();
	List<Student> backupList = null; // 执行过滤时使用
	
	JButton addButton,deleteButton,editButton;
	JTextField searchField = new JTextField();
	
	public MyFrame(String title) {
		super(title);
		
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		//表格初始化
		initTable();
		
		//工具栏初始化
		initToolBar();
		
		loadData();
	}
	
	private void initTable() {
		//创建JTable,直接重写isCellEditable(),设为不可编辑
		table = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		JScrollPane scrollPane = new JScrollPane(table);
		root.add(scrollPane, BorderLayout.CENTER);
		
		//添加到主界面
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setRowHeight(30);
		
		//列设置：添五列
		tableModel.addColumn("学号");
		tableModel.addColumn("姓名");
		tableModel.addColumn ("性别");
		tableModel.addColumn ("出生日期");
		tableModel.addColumn ("手机号");
		
		//列设置：自定义绘制
		table.getColumnModel().getColumn(2).setCellRenderer(new SexColumnRenderer());
		table.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
		table.getColumnModel().getColumn(0).setPreferredWidth(110); // 该列的宽度
	}
	
	private void initToolBar() {
		JToolBar toolBar = new JToolBar();
		root.add(toolBar, BorderLayout.PAGE_START);
		toolBar.setFloatable(false);//不可移动工具栏
		
		//添加按钮
		JButton addButton = createToolButton("添加", "ic_add.png");
		toolBar.add(addButton);
		addButton.addActionListener( (e) -> {
			showAddDialog();
		});
		
		//删除按钮
		JButton deleteButton = createToolButton("删除", "ic_delete.png");
		toolBar.add(deleteButton);
		deleteButton.addActionListener( (e)->{
			onDelete();
		});
		
		//编辑按钮
		JButton editButton = createToolButton("编辑", "ic_edit.png");
		toolBar.add(editButton);
		editButton.addActionListener( (e)->{
			onEdit();
		});
		
		//查询
		toolBar.addSeparator(new Dimension(40,10));//设置分隔
		toolBar.add(new JLabel("查询"));
		toolBar.add(searchField);
		searchField.setMaximumSize(new Dimension(120, 30));
		searchField.addActionListener( (e)->{
			//按回车时触发事件
			onSearch();
		});
	}
	
	private void onSearch() {
		//获取用户输入的过滤条件
		String filter = searchField.getText().trim();
		
		if(filter.length() == 0) {
			//恢复原始数据
			tableModel.setRowCount(0);
			for(Student s : backupList) {
				addTableRow(s);
			}
			backupList = null;
			this.addButton.setEnabled(true);
			this.deleteButton.setEnabled(true);
			this.editButton.setEnabled(true);
			return ;
		}
		
		//首次执行数据备份,放到一个List里
		if(backupList == null) {
			backupList = new ArrayList<>();
			for(int i=0; i<tableModel.getRowCount(); i++) {
				Student item = getTableRow(i);
				backupList.add(item);
			}
		}
		
		//把符合条件的记录显示在表格里
		tableModel.setRowCount(0);//清空
		for(Student s : backupList) {
			if(s.name.indexOf(filter)>=0) {
				addTableRow(s);
			}
		}
		
		//把其他操作按钮禁用
		this.addButton.setEnabled(false);
		this.deleteButton.setEnabled(false);
		this.editButton.setEnabled(false);
		
	}
	private JButton createToolButton (String text, String icon) {
		//图标
		String imagePath = "/icons/"+icon;
		URL imageURL = getClass().getResource(imagePath);
		
		//创建按钮
		JButton button = new JButton(text);
		//button.setActionCommand(action);
		button.setToolTipText(text);
		button.setIcon(new ImageIcon(imageURL));
		button.setFocusPainted(false);
		return button;
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
	
	private void showAddDialog() {
		EditStudentDialog dlg = new EditStudentDialog(this);
		if(dlg.exec()) {
			Student stu = dlg.getValue();
			addTableRow(stu);
		}
		
		saveData();
	}
	
	private void onDelete() {
		//获取选中的行的索引
		int[] rows = table.getSelectedRows();
		if(rows.length == 0)
			return ;
		
		//弹出对话框确认
		int select = JOptionPane.showConfirmDialog(this, "是否确认删除？","确认", JOptionPane.YES_NO_OPTION);
		if(select != 0)//0号按键是"确定"按钮
			return ;
		
		//技巧：从后往前删除
		for(int i = rows.length-1; i>=0; i--) {
			tableModel.removeRow(rows[i]);
		}
		
		saveData();
	}
	
	private void onEdit() {
		//获取选中行的索引
		int[] rows = table.getSelectedRows();
		if(rows.length == 0) return ;
		
		//获得选中的行
		int row = rows[0];//只编辑选中的第一行
		Student s = getTableRow(row);
		
		//弹出编辑对话框
		EditStudentDialog dlg = new EditStudentDialog(this);
		//设置初始值
		dlg.setValue(s);
		if(dlg.exec()) {
			Student stu = dlg.getValue();
			
			//更新到Model
			setTableRow(stu, row);
			
			saveData();
		}
	}
	
	// 获取 表格控件中的一条记录的值
	private Student getTableRow(int row)
	{
		Student s = new Student();
		s.id = (String) tableModel.getValueAt(row, 0);
		s.name = (String) tableModel.getValueAt(row, 1);
		s.sex = (Boolean) tableModel.getValueAt(row, 2);
		s.birthday = (String) tableModel.getValueAt(row, 3);
		s.cellphone = (String) tableModel.getValueAt(row, 4);		
		return s;
	}
	
	// 设置 表格控件中的一条记录的值
	private void setTableRow(Student v, int row)
	{
		tableModel.setValueAt(v.id, row, 0);
		tableModel.setValueAt(v.name, row, 1);
		tableModel.setValueAt(v.sex, row, 2);
		tableModel.setValueAt(v.birthday, row, 3);		
		tableModel.setValueAt(v.cellphone, row, 4);
	}

	//保存数据
	private void saveData() {
		//构造一个JSON函数
		JSONArray array = new JSONArray();
		for(int i=0; i<tableModel.getRowCount(); i++) {
			JSONObject j1 = new JSONObject();
			j1.put("id", tableModel.getValueAt(i, 0));
			j1.put("name", tableModel.getValueAt(i, 1));
			j1.put("sex", tableModel.getValueAt(i, 2));
			j1.put("birthday", tableModel.getValueAt(i, 3));
			j1.put("cellphone", tableModel.getValueAt(i, 4));
			
			array.put(j1);
		}
		
		//将JSON对象保存到文件
		File file = new File("students.json");
		try {
			AfJSON.toFile(array, file, "UTF-8");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
	
	//加载数据
	private void loadData() {
		File file = new File("students.json");
		if(!file.exists())
			return ;
		
		JSONArray array = null;
		try {
			array = (JSONArray) AfJSON.fromFile(file, "UTF-8");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
		
		//显示到表格
		tableModel.setRowCount(0);//清空
		for(int i=0; i<array.length(); i++) {
			JSONObject j1 = array.getJSONObject(i);
			Student s = new Student();
			s.id = j1.getString("id");
			s.name = j1.getString("name");
			s.sex = j1.getBoolean("sex");
			s.cellphone = j1.getString("cellphone");
			s.birthday = j1.getString("birthday");
			addTableRow(s);
		}
	}
}