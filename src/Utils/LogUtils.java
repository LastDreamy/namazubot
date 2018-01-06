package Utils;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtils extends Logger{
	private static LogUtils log;

	protected LogUtils(String name, String resourceBundleName) {
		super(name, resourceBundleName);
	}
	
	public static LogUtils log(){
		if(log == null) log = new LogUtils("CultBotLogger", null);
		return log;
	}

	public void error(String msg){
		log.log(Level.SEVERE, msg);
	}
	public void warn(String msg){
		log.log(Level.WARNING, msg);
	}
	public void info(String msg){
		log.log(Level.INFO, msg);
	}
	public void error(String msg, Throwable e){
		log.log(Level.SEVERE, msg, e);
	}
	public void warn(String msg, Throwable e){
		log.log(Level.WARNING, msg, e);
	}
	public void info(String msg, Throwable e){
		log.log(Level.INFO, msg, e);
	}
}
