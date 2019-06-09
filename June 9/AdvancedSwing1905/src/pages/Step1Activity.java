package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import hzr.swing.HzrActivity;
import hzr.swing.HzrBorder;
import hzr.swing.HzrColumnLayout;
import hzr.swing.HzrMarginLayout;
import hzr.swing.HzrPanel;
import hzr.swing.HzrRowLayout;

public class Step1Activity extends HzrActivity
{
	JTextField nameField = new JTextField();
	JComboBox<String> sexField = new JComboBox<>();	
	JTextField birthField = new JTextField();
	JTextField phoneField = new JTextField();
	
	public Step1Activity(){
		this.setLayout(new BorderLayout());
		
		initTop("基础信息");
			
		initCenter();
		
		initBottom();

	}

	private void initTop(String title){		
		JLabel label = new JLabel(title);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));

		this.add(label, BorderLayout.PAGE_START);
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0xCCCCCC)));

		HzrBorder.addPadding(label, 10);		
	}
	
	private void initCenter(){
		HzrPanel center = new HzrPanel();
		center.setLayout(new HzrColumnLayout(10));
		
		if(true){
			HzrPanel row =new HzrPanel();
			row.setLayout(new HzrRowLayout());
			center.add(row, "30px");
			row.padding(4);
			row.add(new JLabel("姓名"), "60px");
			row.add(nameField, "1w");			
		}
		if(true){
			HzrPanel row =new HzrPanel();
			row.setLayout(new HzrRowLayout());
			center.add(row, "30px");
			row.padding(4);
			row.add(new JLabel("性别"), "60px");
			
			sexField.addItem("女");
			sexField.addItem("男");
			row.add(sexField, "1w");			
		}
		if(true){
			HzrPanel row =new HzrPanel();
			row.setLayout(new HzrRowLayout());
			center.add(row, "30px");
			row.padding(4);
			row.add(new JLabel("生日"), "60px");
			row.add(birthField, "1w");			
		}
		if(true){
			HzrPanel row =new HzrPanel();
			row.setLayout(new HzrRowLayout());
			center.add(row, "30px");
			row.padding(4);
			row.add(new JLabel("联系方式"), "60px");
			row.add(phoneField, "1w");			
		}
		
		center.setPreferredSize(new Dimension(400, 9999));
		center.setOpaque(true);
		center.setBackground(new Color(0,0,0,0));
		
		JPanel wrapper = new JPanel();
		wrapper.setLayout(new HzrMarginLayout());
		wrapper.add(center, new HzrMarginLayout.Margin(20,-1,-1,-1));
		wrapper.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0xCCCCCC)));
		this.add(wrapper, BorderLayout.CENTER);
	}
	
	private void initBottom(){
		JPanel bottom = new JPanel();
		this.add(bottom, BorderLayout.PAGE_END);
		JButton next = new JButton("下一步");
		bottom.add(next);
		
		next.addActionListener( (e)->{
			saveData();
			startActivity(Step2Activity.class, null);
		});
	}
	
	@Override
	public void onCreate(Object intent)
	{
		
	}

	@Override
	public void onStart()
	{
		
	}
	
	private void saveData(){
		Resume r = (Resume) context.getParam("resume", null);
		
		r.name = nameField.getText().trim();
		r.sex = sexField.getSelectedIndex() == 1;
		r.birthday = birthField.getText().trim();
		r.cellphone = phoneField.getText().trim();
	}
}
