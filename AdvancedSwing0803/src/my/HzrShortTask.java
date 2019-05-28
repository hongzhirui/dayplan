package my;

import javax.swing.SwingUtilities;

public abstract class HzrShortTask extends Thread
{
	//参数列表
	public Object[] args;
	//任务出错信息
	public Exception err;
	
	//传入参数，并启动线程
	public void execute(Object...args) {
		this.args = args;//记录参数到this.args
		start(); //启动线程
	}
	
	@Override
	public void run() {
		//处理任务
		try {
			doInBackground();
		}catch(Exception e) {
			this.err = e;
			System.out.println("** HzrShortTask: " + e.getMessage());
		}
		
		//歇息片刻
		try {sleep(100);}catch(Exception e){}
		
		//显示结果（跟新UI）
		SwingUtilities.invokeLater( ()->{
			done();
		});
	}
	
	//处理任务
	protected abstract void doInBackground() throws Exception;
	
	//任务完成后
	protected abstract void done();
	

}
