package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import hzr.swing.HzrActivity;
import hzr.swing.HzrBorder;
import hzr.swing.HzrColumnLayout;
import hzr.swing.HzrImageView;
import hzr.swing.HzrMarginLayout;
import hzr.swing.HzrPanel;
import hzr.swing.HzrToaster;

public class Step2Activity extends HzrActivity
{
	HzrImageView photoField = new HzrImageView();
	File imageFile; // 选中的图片文件
	
	public Step2Activity(){
		this.setLayout(new BorderLayout());
		
		initTop("头像设置");
			
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
				
		center.setPreferredSize(new Dimension(200, 240));
		center.add(photoField, "200px");
		
		JButton button = new JButton ("选择图片");
		center.add(button, "1w");		
		
		JPanel wrapper = new JPanel();
		wrapper.setLayout(new HzrMarginLayout());
		wrapper.add(center, new HzrMarginLayout.Margin(20,-1,-1,-1));
		wrapper.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0xCCCCCC)));
		this.add(wrapper, BorderLayout.CENTER);
		
		button.addActionListener( (e)->{
			openFile();
		});
	}
	
	private void initBottom(){
		JPanel bottom = new JPanel();
		this.add(bottom, BorderLayout.PAGE_END);
		
		JButton prevButton = new JButton("上一步");
		bottom.add(prevButton);		
		prevButton.addActionListener( (e)->{
			back();
		});
		
		JButton nextButton = new JButton("下一步");
		bottom.add(nextButton);		
		nextButton.addActionListener( (e)->{
			saveData();
			startActivity(Step3Activity.class, null);
		});
		

	}
	
	@Override
	public void onCreate(Object intent)
	{
		
	}

	@Override
	public void onStart(){
		Resume r = (Resume) context.getParam("resume", null);
		
		if(r.photo!=null)
		{
			openFile(r.photo);
		}
	}
	
	private void openFile(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter 
			= new FileNameExtensionFilter("图片文件", "jpg", "jpeg", "png");
		chooser.setFileFilter(filter);

		int ret = chooser.showOpenDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION)
		{
			// 结果为：已经存在的一个文件
			File file = chooser.getSelectedFile();
			openFile(file);
		}
	}
	
	private void openFile(File file){
		try {
			Image image = ImageIO.read(file);
			imageFile = file;
			photoField.setImage( image );
			
			saveData();
		}catch(Exception e){
			HzrToaster.show(this, HzrToaster.WARN, e.getMessage());
		}
	}

	private void saveData(){
		Resume r = (Resume) context.getParam("resume", null);
		
		r.photo = imageFile;
	}
	
}
