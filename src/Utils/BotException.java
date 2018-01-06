package Utils;

public class BotException extends Exception{
	private static final long serialVersionUID = 2445519030930669665L;

	public BotException(String str){
		super(str);
		LogUtils.log().error(str);
	}
	public BotException(Throwable e){
		super(e);
		LogUtils.log().error(e.getMessage(), e);
	}
	public BotException(String str, Throwable e){
		super(str, e);
		LogUtils.log().error(str, e);
	}
}
