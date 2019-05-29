package af.swing.thread;

import javax.swing.SwingUtilities;

public abstract class AfShortTask extends Thread
{
	// 参数列表
	public Object[] args;
	// 任务出错信息
	public Exception err;
	
	// 传入参数，并启动线程
	public void execute(Object...args)
	{
		this.args = args; // 记录参数到this.args
		start(); // 启动线程
	}

	@Override
	public void run()
	{
		// 处理任务
		try
		{
			doInBackground();
		} catch (Exception e)
		{
			this.err = e;
			System.out.println("** AfShortTask: " + e.getMessage());
		}

		// 歇息 片刻
		try
		{
			sleep(100);
		} catch (Exception e)
		{
		}

		// 显示结果 ( 更新 UI )
		SwingUtilities.invokeLater(() -> {
			done( );
		});
	}

	// 处理任务
	protected abstract void doInBackground() throws Exception;

	// 任务完成后 
	// 如果this.err != null则说明doInBackground()里出错了
	protected abstract void done();

}
