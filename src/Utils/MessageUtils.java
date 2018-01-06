package Utils;
import main.Main;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class MessageUtils {
    public static final String BOT_PREFIX = "$";

    public static final IMessage sendWhisper(final IUser user, final String message){
    	return sendMessage(Main.getClient().getOrCreatePMChannel(user), message);
    }
    public static final IMessage sendMessage(final IChannel channel, final String message){
    	try{
    		return channel.sendMessage(message);
    	} catch(Exception e){
    		//channel.toggleTypingStatus();
    		SharedMethods.sleep(1000L);
    		return sendMessage(channel, message);
    	}
    }
    public static final IMessage sendImage(final IChannel channel, final String imageURL){
//		channel.toggleTypingStatus();
//		Constants.sleep(2000L);
    	try{
    		return channel.sendMessage(new EmbedBuilder().withImage(imageURL).build());
    	} catch(Exception e){
    		//channel.toggleTypingStatus();
    		SharedMethods.sleep(1000L);
    		return sendImage(channel, imageURL);
    	}
    }
	public static final IMessage editMessage(IMessage message, String msg) {
    	try{
    		return message.edit(msg);
    	} catch(Exception e){
    		//channel.toggleTypingStatus();
    		SharedMethods.sleep(1000L);
    		return editMessage(message, msg);
    	}
	}
}
