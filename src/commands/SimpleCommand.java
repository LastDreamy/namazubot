package commands;
import Utils.MessageUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class SimpleCommand extends _BaseCommand{
	private final String message;
	
	public SimpleCommand(final String command, final String message) {
		super(command);
		this.message = message;
	}

	@Override
	protected void doStuff(final MessageReceivedEvent event) {
        MessageUtils.sendMessage(event.getChannel(), message);
	}
}
