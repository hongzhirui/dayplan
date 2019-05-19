package my;

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

public class MyFrame extends JFrame
{
	JPanel root = new JPanel();
	JTable table = null;
	DefaultTableModel tableModel = new DefaultTableModel();
	
	public MyFrame(String title) {
		super(title);
		
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		//表格初始化
		initTable();
		
		//工具栏初始化
		initToolBar();
		
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
		toolBar.setFloatable(false);
		
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
	}
}