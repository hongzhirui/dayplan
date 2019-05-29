package my;

import java.awt.Component;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import af.swing.image.AfImageView;

public class IconColumnRenderer extends AfImageView
					implements TableCellRenderer
{
	Image icFolder, icFile;
	
	public IconColumnRenderer()
	{
		this.setScaleType(AfImageView.FIT_CENTER_INSIDE);
		try {
			icFolder = ImageIO.read( getClass().getResource("/icons/ic_folder_16.png"));
			icFile = ImageIO.read( getClass().getResource("/icons/ic_file_16.png"));
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column)
	{
		// 设置图标
		Boolean isDir = (Boolean)value;
		if(isDir)
		{
			this.setImage( icFolder );
		}
		else
		{
			this.setImage( icFile );
		}
		
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
