package commands;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import Utils.BotException;
import Utils.Constants;
import Utils.IOUtils;
import Utils.MessageUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class PermissionCommand extends _BaseCommand{
	public PermissionCommand() {
		super("permission");
	}

	public static Map<String, Map<String, List<String>>> getPermissions() throws BotException{
		final Map<String, Map<String, List<String>>> map = new HashMap<String, Map<String, List<String>>>();
		final List<String> permissions = IOUtils.readFile(IOUtils.PERMISSION_FILE);
		for(String str : permissions){
			if(str == null || str.length() == 0) continue;
			String[] arr = str.split(" ");
			String command = arr[0].trim(), function = arr[1].trim();
			if(!map.containsKey(command))
				map.put(command, new HashMap<String, List<String>>());
			if(!map.get(command).containsKey(function))
				map.get(command).put(function, new LinkedList<String>());
			String role = arr[2];
			for(int i = 3; i < arr.length; i++)
				role = role + " " + arr[i];
			map.get(command).get(function).add(role.trim());
		}
		return map;
	}
	public static Map<String, List<String>> getPermissions(final String command) throws BotException{
		try{
			return getPermissions().get(command);
		} catch(NullPointerException e){
			return new HashMap<String, List<String>>();
		}
	}
	public static List<String> getPermissions(String command, String function) throws BotException{
		try{
			return getPermissions(command).get(function);
		} catch(NullPointerException e){
			return new LinkedList<String>();
		}
	}
	
	public static void savePermissions(Map<String, Map<String, List<String>>> map) throws BotException{
		List<String> list = new ArrayList<String>();
		for(String command : map.keySet())
			for(String function : map.get(command).keySet())
				for(String group : map.get(command).get(function))
					list.add(command + " " + function + " " + group);
		IOUtils.writeFile(IOUtils.PERMISSION_FILE, list);
	}

	@Override
	protected void doStuff(final MessageReceivedEvent event) { //$permission command {function | help} 
		IMessage message = event.getMessage();		
		try {
			String msg = message.getContent();
			if(msg.contains(" ")){
				msg = msg.substring(msg.indexOf(" ") + 1).trim();
				String command = msg.split(" ")[0];
				String function = msg.split(" ")[1];
				String role = msg.substring(msg.indexOf(" ", msg.indexOf(" ") + 1)+1);
				if(role.charAt(0) != '+' && role.charAt(0) != '-'){
					MessageUtils.sendMessage(event.getChannel(), "You need to use a + or - before the role to add/remove.");
					return;
				}
				boolean add = role.charAt(0) == '+';
				role = role.substring(1);
				Map<String, Map<String, List<String>>> map = getPermissions();

				if(add){
					if(!map.containsKey(command))
						map.put(command, new HashMap<String, List<String>>());
					if(!map.get(command).containsKey(function))
						map.get(command).put(function, new LinkedList<String>());
					map.get(command).get(function).add(role);
				}else{
					map.get(command).get(function).remove(role);
				}
				savePermissions(map);
				MessageUtils.sendMessage(event.getChannel(), "Permission to command '" + command + "/" + function + "' was " + (add ? "added to" : "removed from") + " role '" + role + "'");
			}
			else{
				MessageUtils.sendMessage(event.getChannel(), "Please use the correct format: '" + _BaseCommand.BOT_PREFIX + super.command + " <command> <function> [+,-]<role>");
			}
		} catch (BotException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected String help(final MessageReceivedEvent event, Constants.HELP_TYPE helpType) {
		String ret = "";
		if(Constants.HELP_TYPE.SHORT.equals(helpType)){
			ret += "Manage Permissions for commands";
		}else{
			ret += "```md" + System.lineSeparator();
			ret += "Permission Command" + System.lineSeparator();
			ret += "===============" + System.lineSeparator();
			ret += System.lineSeparator();
			ret += "[*][Usage][*]" + System.lineSeparator();
			ret += "* !permission \"command\" \"function\" +\"GroupName\"" + System.lineSeparator();
			ret += ">    Add specified of the group to use the function of specified command." + System.lineSeparator();
			ret += "* !permission \"command\" \"function\" -\"GroupName\"" + System.lineSeparator();
			ret += ">    Remove permission of the specified group to use the function of specified command." + System.lineSeparator();
			ret += "```";
		}
		return ret;
	}
}
