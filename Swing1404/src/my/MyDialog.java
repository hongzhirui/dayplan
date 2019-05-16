package my;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfRowLayout;

// 创建一个自定义的对话框类
public class MyDialog extends JDialog
{
	public JTextField textField = new JTextField(20);
	public JButton okButton = new JButton("确定");
	
	//默认是取消
	private boolean retValue = false;
	
	public MyDialog(JFrame owner)
	{
		super(owner, "测试", true);
		
		// 设置一个容器
		AfPanel root = new AfPanel();
		this.setContentPane(root);
		root.setLayout(new AfColumnLayout(10));
		root.padding(10);
		
		// 中间面板
		AfPanel mainPanel = new AfPanel();
		root.add(mainPanel, "1w"); // 占居中间区域
		mainPanel.setLayout(new AfColumnLayout(10));		
		mainPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		mainPanel.padding(10);
		
		mainPanel.add(new JLabel("请输入名字"), "20px");
		mainPanel.add(textField, "20px");
		
		// 底下
		AfPanel buttonPanel = new AfPanel();
		root.add(buttonPanel, "30px"); // 底部区域 30px
		buttonPanel.setLayout(new AfRowLayout(10));
		buttonPanel.add(new JLabel(), "1w"); // 占位
		buttonPanel.add(okButton, "auto"); // 按钮靠右显示
		
		
		// 此处 使用 Lambda 表达式 的写法，参考3.5 节
		okButton.addActionListener( (e)->{
			retValue = true;
			setVisible(false); // MyDialog.this.setVisible(false)
		});	
		
	}
	
	// 返回值为true:表示用户点了"确定"
	public boolean exec()
	{
		// 相对owner居中显示
		Rectangle frmRect = this.getOwner().getBounds();
		int width = this.getWidth();
		int height = this.getHeight();
		int x = frmRect.x + (frmRect.width - width)/2;
		int y = frmRect.y + (frmRect.height - height)/2;
		this.setBounds(x,y, width, height);
		
		// 显示窗口 ( 阻塞 ，直接对话框窗口被关闭)
		this.setVisible(true);
		
		return retValue;
	}
	
	//获取用户的输入
	public String getValue() {
		return textField.getText().trim();
	}
}
