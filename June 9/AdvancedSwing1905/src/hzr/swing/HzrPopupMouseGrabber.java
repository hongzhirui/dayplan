package hzr.swing;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Popup;
import javax.swing.SwingUtilities;

public class HzrPopupMouseGrabber implements 
				AWTEventListener, WindowListener, ComponentListener 
{
	private Component owner;
	private Component content;
	private Popup popup;
	private Window ownerWindow; // owner所在的窗口

	// owner: 触发者控件 ( 例如，点击按钮时弹出窗口，则按钮为owner)
	// content: 弹出窗口的内容面板
	// popup: 弹出的窗口
	public HzrPopupMouseGrabber(Component owner, Component content, Popup popup)
	{
		this.owner = owner;
		this.content = content;
		this.popup = popup;
	}
	
	public void installListeners()
	{
		// 监测主窗口内发生的鼠标事件 (Swing高级, 11.2讲 )
		Toolkit tk = Toolkit.getDefaultToolkit();
		tk.addAWTEventListener(this, AWTEvent.MOUSE_EVENT_MASK |
                AWTEvent.MOUSE_MOTION_EVENT_MASK |
                AWTEvent.MOUSE_WHEEL_EVENT_MASK |
                AWTEvent.WINDOW_EVENT_MASK );
		
		// 获取owner的顶层窗口
		ownerWindow = SwingUtilities.getWindowAncestor(owner);
		if(ownerWindow == null)
			System.out.println("必须先把控件加到 JFrame或JDialog里!");
		
		// 窗口事件: 当主窗口发生变化、最小化等事件时，应关闭 Popup 窗口
		ownerWindow.addWindowListener(this);
		
		// 当 owner 控件发生变化时，应关闭 Popup 窗口
		// 注：这个监听器可以不加，影响不大
		owner.addComponentListener(this);
	}
	
	public void uninstallListeners()
	{
		owner.removeComponentListener( this );
		ownerWindow.removeWindowListener(this);
		Toolkit.getDefaultToolkit().removeAWTEventListener(this);
	}
	
	public void cancelPopupMenu()
	{
		this.uninstallListeners();
		popup.hide();
	}

	// 判断控件 comp是否在 Popup窗口里面
	public boolean isInPopup(Component comp)
	{
		// 判断是否在同一Window中
		Window w1 = SwingUtilities.getWindowAncestor(content);;
		Window w2 = SwingUtilities.getWindowAncestor(comp);;
		return w1 == w2;
//		Component container = this.content;
//		while(true)
//		{
//			if(comp == container ) return true;
//			if(comp == null) return false;
//			
//			comp = comp.getParent();
//		}
	}
	
	///////////////// AWTEventListener /////////
	@Override
	public void eventDispatched(AWTEvent event)
	{
		//System.out.println(" eventDispatched ...");
		if( event instanceof MouseEvent)
		{
			int eventID = event.getID() ;
			if(eventID == MouseEvent.MOUSE_PRESSED)
			{
				MouseEvent e = (MouseEvent) event;
	            Component comp = e.getComponent();
	            if(! isInPopup( comp))
	            {
	            	//System.out.println(" Now cancelPopupMenu ...");
	            	cancelPopupMenu();
	            }
			}            
		}
	}

	//////////////// ComponentListener //////////
	public void componentResized(ComponentEvent e)
	{
		cancelPopupMenu();
	}

	public void componentMoved(ComponentEvent e)
	{
		cancelPopupMenu();
	}

	public void componentShown(ComponentEvent e)
	{
		//cancelPopupMenu();
	}

	public void componentHidden(ComponentEvent e)
	{
		cancelPopupMenu();
	}

	///////////// WindowListener ///////////////
	public void windowClosing(WindowEvent e)
	{
		cancelPopupMenu();
	}

	public void windowIconified(WindowEvent e)
	{
		cancelPopupMenu();
	}

	public void windowClosed(WindowEvent e)
	{
		cancelPopupMenu();
	}

	public void windowDeactivated(WindowEvent e)
	{
		System.out.println("windowDeactivated");
		cancelPopupMenu();
	}

	public void windowOpened(WindowEvent e)
	{
	}

	public void windowDeiconified(WindowEvent e)
	{
	}

	public void windowActivated(WindowEvent e)
	{
	}

}
