package commands;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import Utils.BotException;
import Utils.Constants;
import Utils.Constants.HELP_TYPE;
import Utils.LogUtils;
import Utils.MessageUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;

public class HelpCommand extends _BaseCommand{	
	public HelpCommand() {
		super("help");
	}

	@Override
	protected void doStuff(final MessageReceivedEvent event) {
		IMessage message = event.getMessage();	
		try {
			String msg = message.getContent();
			if(msg.contains(" ")){
				msg = msg.substring(msg.indexOf(" ") + 1).trim();
				if(msg.equalsIgnoreCase("admin")){
					List<IRole> userRoles = event.getAuthor().getRolesForGuild(event.getGuild());				
					Map<String, Map<String, List<String>>> perms = PermissionCommand.getPermissions();
					List<String> commandsAllowed = new LinkedList<String>();
					for(String command : perms.keySet())
						if(userRoles.stream().map(r -> r.getName()).anyMatch(r -> perms.get(command).get("execute").contains(r)))
							commandsAllowed.add(command);
					String reply = "";
					for(String command : commandsAllowed)
						reply += "\n\t" + _BaseCommand.BOT_PREFIX + command + " - " + _BaseCommand.COMMANDS.stream().filter(c -> c.command.equalsIgnoreCase(command)).findFirst().orElse(null).help(event, HELP_TYPE.SHORT);
					MessageUtils.sendMessage(event.getChannel(), "```" + reply + "```");
					return;
				}else{
					final String commandName = msg;
					_BaseCommand command = _BaseCommand.COMMANDS.stream().filter(c -> c.command.equalsIgnoreCase(commandName)).findFirst().orElse(null);
					if(command == null)
						MessageUtils.sendMessage(event.getChannel(), "There is no command with specified name.");
					else
						MessageUtils.sendMessage(event.getChannel(), command.help(event, HELP_TYPE.LONG));
					return;
				}
			}
			Map<String, Map<String, List<String>>> perms = PermissionCommand.getPermissions();
			List<String> commandsAllowed = new LinkedList<String>();
			for(String command : perms.keySet()){
				if(perms.get(command).get("execute").contains(Constants.BASE_USER_PERMISSION) || perms.get(command).get("execute").contains("Devout"))
					commandsAllowed.add(command);
			}
			String reply = "";
			for(String command : commandsAllowed){
				_BaseCommand cmd = _BaseCommand.COMMANDS.stream().filter(c -> c.command.equalsIgnoreCase(command)).findFirst().orElse(null);//.help(event, HELP_TYPE.SHORT);
				if(cmd != null && cmd.help(event, HELP_TYPE.SHORT) != null && !cmd.help(event, HELP_TYPE.SHORT).equals("null"))
					reply += "\n<" + _BaseCommand.BOT_PREFIX + command + " - " + cmd.help(event, HELP_TYPE.SHORT) + ">";
			}
			MessageUtils.sendMessage(event.getChannel(), "```md" + reply + "\n```");
		} catch (BotException e) { // General BotException, in this case, problems reading/writing
			LogUtils.log().error(e.getMessage(), e);
			e.printStackTrace();
		}// catch (InterruptedException e) { }
	}
	
	@Override
	protected String help(final MessageReceivedEvent event, Constants.HELP_TYPE helpType) {
		return null;
	}
}
