package commands;
import java.util.Arrays;
import java.util.List;
import main.Main;
import Utils.BotException;
import Utils.Constants;
import Utils.Constants.HELP_TYPE;
import Utils.IOUtils;
import Utils.MessageUtils;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public abstract class _BaseCommand{
	public static final List<_BaseCommand> COMMANDS = Arrays.asList(
			new CleverBotCommand(),
			new SimpleCommand("simpleTest", "This is a simple reply."),
			new NamazuCommand(),
			new EmbedCommand(),
			new SummonSimulatorCommand(),
			new SummonSimulatorQuickfire(),
			new PermissionCommand(),
			new EmoteCommand(),
			new HelpCommand(),
			new LodestoneCommand(),
			new EightBallCommand(),
			new CountdownCommand(),
			new DiceCommand(),
			new RPSCommand(),
			new PFCCommand(),
			new ParrotCommand()
			);
	public static final String BOT_PREFIX = null;
	protected final String command;

	public _BaseCommand(final String command){
		this.command = command;
		System.out.println("Command " + command + " has been registered.");
	}
	
    @EventSubscriber
    public final void onMessageReceived(final MessageReceivedEvent event ) {
    	  
    	if (this.command.equals("CleverBot") && event.getMessage().getMentions().contains(Main.getClient().getOurUser())) {
            COMMANDS.get(0).doStuff(event);
            return;
        }
    	if(event.getMessage().getContent().startsWith(Main.BOT_PREFIX + command + " ") || event.getMessage().getContent().equals(Main.BOT_PREFIX + command))
    		doStuff(event);
    }

    protected abstract void doStuff(final MessageReceivedEvent event);

	protected String help(MessageReceivedEvent event, HELP_TYPE helpType) {
		// TODO Auto-generated method stub
		return null;
	}
}