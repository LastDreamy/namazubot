package commands;
import Utils.MessageUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class NamazuCommand extends _BaseCommand{
	public NamazuCommand() {
		super("namazu");
	}

	@Override
	protected void doStuff(final MessageReceivedEvent event) {
		IMessage message = event.getMessage();
		String msg = message.getContent();
		if(msg.contains("tell me who is the best of them all")){
			msg = msg.substring(msg.indexOf(" ") + 1);
			MessageUtils.sendMessage(event.getChannel(), "You are, master.");
		}else if(msg.contains("summon")){	
			msg = msg.substring(msg.indexOf(" ") + 1);
			MessageUtils.sendMessage(event.getChannel(), "Your lapis balance is 0 you hobo.");
		}else if(msg.contains("hotel?")){	
			msg = msg.substring(msg.indexOf(" ") + 1);
			MessageUtils.sendMessage(event.getChannel(), "trivago");
		}else if(msg.contains("sing")){	
			msg = msg.substring(msg.indexOf(" ") + 1);
			MessageUtils.sendMessage(event.getChannel(), "No.");
		}else if(msg.contains(":fetdoge:")){	
			msg = msg.substring(msg.indexOf(" ") + 1);
			MessageUtils.sendMessage(event.getChannel(), "<:fetdoge:356916825659015178>");
		}else if(msg.contains("pull")){	
			msg = msg.substring(msg.indexOf(" ") + 1);
			MessageUtils.sendMessage(event.getChannel(), "<:bcrystal:364441757594812416> Popoi");
		}else if(msg.contains("mad")){	
			msg = msg.substring(msg.indexOf(" ") + 1);
            try{
                event.getMessage().delete();
            }
            catch(Exception e){};
			MessageUtils.sendMessage(event.getChannel(), "I'll murder you all soon enough.");
		}else
			MessageUtils.sendMessage(event.getChannel(), "Git Gud");
	}
}
