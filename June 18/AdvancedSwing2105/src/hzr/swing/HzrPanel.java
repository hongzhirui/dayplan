package hzr.swing;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;



public class HzrPanel extends JPanel
{
	public HzrPanel layout( LayoutManager manager){
		this.setLayout(manager);
		return this;
	}
	
	///////////// padding /////////////
	public HzrPanel padding( int size){
		return padding(size, size, size, size);
	}
	
	public HzrPanel padding( int top, int left, int bottom, int right){
		HzrBorder.addPadding(this, top,left, bottom,right);
		return this;
	}

	////////////// margin ////////////////
	public HzrPanel margin( int size){
		return margin(size, size, size, size);
	}
	
	public HzrPanel margin( int top, int left, int bottom, int right){
		HzrBorder.addMargin(this, top,left, bottom,right);
		return this;
	}
	
	//////////// max size ///////////
	public HzrPanel maxSize(int w, int h){
		this.setMaximumSize(new Dimension(w, h));
		return this;
	}
	
	public HzrPanel maxWidth(int w){
		Dimension size = this.getMaximumSize();
		if(size == null)
			size = new Dimension(0,0);
		size.width = w;
		this.setMaximumSize( size);
		return this;
	}
	
	public HzrPanel maxHeight(int h){
		Dimension size = this.getMaximumSize();
		if(size == null)
			size = new Dimension(0,0);
		size.height = h;
		this.setMaximumSize( size);
		return this;
	}
	
	//////////// perferred size ///////////
	public HzrPanel preferredSize(int w, int h){
		this.setPreferredSize(new Dimension(w, h));
		return this;
	}
	
	public HzrPanel preferredWidth(int w){
		Dimension size = this.getPreferredSize();
		if(size == null)
			size = new Dimension(0,0);
		size.width = w;
		this.setPreferredSize( size);
		return this;
	}
	
	public HzrPanel preferredHeight(int h){
		Dimension size = this.getPreferredSize();
		if(size == null)
			size = new Dimension(0,0);
		size.height = h;
		this.setPreferredSize( size);
		return this;
	}

	//////////// min size ///////////
	public HzrPanel minSize(int w, int h){
		this.setMinimumSize(new Dimension(w, h));
		return this;
	}
	
	public HzrPanel minWidth(int w){
		Dimension size = this.getMinimumSize();
		if(size == null)
			size = new Dimension(0,0);
		size.width = w;
		this.setMinimumSize( size);
		return this;
	}
	
	public HzrPanel minHeight(int h){
		Dimension size = this.getMinimumSize();
		if(size == null)
			size = new Dimension(0,0);
		size.height = h;
		this.setMinimumSize( size);
		return this;
	}
	
}
