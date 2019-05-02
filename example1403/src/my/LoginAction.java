package my;

public class LoginAction
{
	private String username;
	private String password;
	
	public void setUsername(String username)
	{
		this.username = username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}

	//此类必须有一个函数public int execute()
	public int execute() {
		if(username.endsWith("hongzhirui")) {
			if(password.equals("123456")) {
				System.out.println("登陆成功");
			}
			else {
				System.out.println("密码错误");
			}
		}
		return 0;
	}
}
