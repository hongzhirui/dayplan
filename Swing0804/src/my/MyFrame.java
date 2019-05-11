package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		//创建根容器，设置为边界布局
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		//创建一个盒，添加三个按钮
		Box toolbar = Box.createHorizontalBox();
		root.add(toolbar,BorderLayout.PAGE_START);
		
		JButton openButton = createButton("/images/ic_open.png");
		JButton saveButton = createButton("/images/ic_save.png");
		JButton printButton = createButton("/images/ic_print.png");
		toolbar.add(openButton);
		toolbar.add(saveButton);
		toolbar.add(printButton);
		
		//中间区域，添加一个文本输入控件
		JTextArea content = new JTextArea();//可以输入多行文本的控件
		root.add(content, BorderLayout.CENTER);
		content.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	}
	
	public JButton createButton(String iconPath) {
		//加载资源文件
		URL url = getClass().getResource(iconPath);
		Icon icon = new ImageIcon(url);
		
		//设置图标
		JButton button = new JButton();
		button.setIcon(icon);
		
		return button;
	}

}
