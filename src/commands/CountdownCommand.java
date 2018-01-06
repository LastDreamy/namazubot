package commands;
import Utils.MessageUtils;
import Utils.*;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class CountdownCommand extends _BaseCommand{
    public CountdownCommand() {
        super("cd");
    }

	@Override
	protected void doStuff(MessageReceivedEvent event) {
		int i;
		for(i=3;i>0;i--){ 
		    MessageUtils.sendMessage(event.getChannel(), Integer.toString(i));
		    SharedMethods.sleep(1000L);
		}
		MessageUtils.sendMessage(event.getChannel(),"Go!");
		
	}
}
