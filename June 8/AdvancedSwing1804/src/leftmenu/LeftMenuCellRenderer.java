package leftmenu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

// 左侧菜单项的自定义显示 
public class LeftMenuCellRenderer extends JLabel implements ListCellRenderer<LeftMenuItem>
{
	public LeftMenuCellRenderer(){
		this.setPreferredSize(new Dimension(150, 40));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setForeground(new Color(0x333333));
		this.setBorder(BorderFactory.createEmptyBorder(10,4,10,4));
		
		this.setOpaque(true); // 注意：别漏了这一句，不然背景色没效果
	}
	
	
	@Override
	public Component getListCellRendererComponent(JList list, LeftMenuItem value, int index, boolean isSelected,boolean cellHasFocus)
	{
		String text = value.text;
		this.setText(text);
		
	    if (isSelected) {
	    	this.setBackground(new Color(255,255,255,60));
	    	this.setForeground(new Color(0x66B4FF));
        } else {
        	this.setBackground(new Color(255,255,255,20));
        	this.setForeground(new Color(0xF1F1F1));        	
        }
	    
//	    if (isSelected) {
//	    	this.setBackground(list.getSelectionBackground());
//	    	this.setForeground(list.getSelectionForeground());
//        } else {
//        	this.setBackground(list.getBackground());
//        	this.setForeground(list.getForeground());
//        }
	    
	    return this;
	}

}
