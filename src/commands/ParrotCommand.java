package commands;
import Utils.MessageUtils;
import main.Main;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class ParrotCommand extends _BaseCommand{
	public ParrotCommand() {
		super("parrot");
	}

	@Override
	protected void doStuff(final MessageReceivedEvent event) {
		IMessage message = event.getMessage();
		String msg = message.getContent();
		if(msg.contains(" ")){
			msg = msg.substring(msg.indexOf(" ") + 1);
            try{
                event.getMessage().delete();
            }
            catch(Exception e){};
            
            MessageUtils.sendMessage(event.getChannel(), msg);
		}else
			MessageUtils.sendMessage(event.getChannel(), "Parrot Usage: '" + Main.BOT_PREFIX + "parrot MSG'");
	}
}

