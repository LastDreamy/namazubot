package commands;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.json.*;
import Utils.BotException;
import Utils.Constants;
import Utils.IOUtils;
import Utils.LogUtils;
import Utils.MessageUtils;
import Utils.SharedMethods;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class LodestoneCommand extends _BaseCommand{
	public LodestoneCommand() {
		super("namazulodestone");
	}

	@Override
	protected void doStuff(final MessageReceivedEvent event) {
		IMessage message = event.getMessage();
		try {
			IUser user = null;
			String msg = message.getContent();
			if(msg.contains(" ")){
				msg = msg.substring(msg.indexOf(" ") + 1).trim();
				if(msg.startsWith("clear")){
					String userId = ""+message.getAuthor().getLongID();
					List<String> list = IOUtils.readFile(IOUtils.LODESTONE_FILE);
					String line = list.stream().filter(str -> str.split("\t")[0].equals(userId)).findFirst().orElse(null);
					if(line != null){
						list.remove(list.indexOf(line));
						IOUtils.writeFile(IOUtils.LODESTONE_FILE, list);
						MessageUtils.sendMessage(event.getChannel(), "I've removed your friend code. There are now " + list.size() + " friend codes recorded.");
					}else{
						MessageUtils.sendMessage(event.getChannel(), "You don't have a code recorded.");
					}
				}
				else if(msg.startsWith("show")){
					String userId = null;
					if(msg.startsWith("show ")){
						String username = msg.substring(msg.indexOf(" ") + 1).trim();
						List<IUser> users = SharedMethods.getUsersByName(event.getGuild(), username, true);
						if(users.size() != 1){
							MessageUtils.sendMessage(event.getChannel(), "That user hasn't registered his friend code yet.");
							return;
						}
						userId = ""+users.get(0).getLongID();
					}else
						userId = ""+message.getAuthor().getLongID();
					List<String> list = IOUtils.readFile(IOUtils.LODESTONE_FILE);
					final String userId2 = userId;
					String line = list.stream().filter(str -> str.split("\t")[0].equals(userId2)).findFirst().orElse(null);
					if(line != null){
						message = MessageUtils.sendMessage(event.getChannel(), "!friend " + line.split("\t")[1]);
						message.delete();
					}
					else
						MessageUtils.sendMessage(event.getChannel(), "You don't have a code recorded.");
					return;
				}
				else if(msg.startsWith("set ")){
					String userId = ""+message.getAuthor().getLongID();
					String code = msg.substring(msg.indexOf(" ") + 1).trim().replace(" ", "");
					String url = "https://fr.finalfantasyxiv.com/lodestone/character/" + code;
					List<String> list = IOUtils.readFile(IOUtils.LODESTONE_FILE);
					String code2 = code;
					String line = list.stream().filter(str -> str.split("\t")[1].equals(code2)).findFirst().orElse(null);
					if(line != null){
						user = event.getGuild().getUserByID(new Long(line.split("\t")[0]));
						if(user.getLongID() != event.getAuthor().getLongID()){
							String name = user.getNicknameForGuild(event.getGuild()) == null ? user.getName() : user.getNicknameForGuild(event.getGuild());
							MessageUtils.sendMessage(event.getChannel(), "That friend code is already set to " + name + ".");
							return;
						}
					}
					line = list.stream().filter(str -> str.split("\t")[0].equals(userId)).findFirst().orElse(null);
					if(line != null){
						list.remove(list.indexOf(line));
						list.add(userId + "\t" + code);
						IOUtils.writeFile(IOUtils.LODESTONE_FILE, list);
						MessageUtils.sendMessage(event.getChannel(), "I've updated your friend code. There are now " + list.size() + " friend codes recorded.");
					}else{
						list.add(userId + "\t" + code);
						IOUtils.writeFile(IOUtils.LODESTONE_FILE, list);
						MessageUtils.sendMessage(event.getChannel(), "I've recorded your friend code. There are now " + list.size() + " friend codes recorded.");
					}
					return;
				}else{
					String username = msg;
					List<IUser> users = SharedMethods.getUsersByName(event.getGuild(), username, true);
					if(users.size() != 1){
						MessageUtils.sendMessage(event.getChannel(), "There's no user with that name.");
						return;
					}
					user = users.get(0);
				}
			}
			else
				user = message.getAuthor();
			List<String> list = IOUtils.readFile(IOUtils.LODESTONE_FILE);
			String userId = ""+user.getLongID();
			String line = list.stream().filter(str -> str.split("\t")[0].equals(userId)).findFirst().orElse(null);
			if(line == null)
				MessageUtils.sendMessage(event.getChannel(), "That user hasn't registered his friend code yet.");
			else{
				String name = user.getNicknameForGuild(event.getGuild()) == null ? user.getName() : user.getNicknameForGuild(event.getGuild());
				MessageUtils.sendMessage(event.getChannel(), name + ": https://fr.finalfantasyxiv.com/lodestone/character/" +  line.split("\t")[1]);
			}
		} catch (BotException e) {
			LogUtils.log().error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	@Override
	protected String help(final MessageReceivedEvent event, Constants.HELP_TYPE helpType) {
		String ret = "";
		if(Constants.HELP_TYPE.SHORT.equals(helpType)){
			ret += "Commands related to friend codes";
		}else{
			ret += "```md" + System.lineSeparator();
			ret += "Friend Code Command" + System.lineSeparator();
			ret += "===============" + System.lineSeparator();
			ret += System.lineSeparator();
			ret += "[*][Usage][*]" + System.lineSeparator();
			ret += "* $fc" + System.lineSeparator();
			ret += ">    Show own friend code." + System.lineSeparator();
			ret += "* $fc \"username\"" + System.lineSeparator();
			ret += ">    Show the friend code of the specified user." + System.lineSeparator();
			ret += "* $fc set \"FriendCode\"" + System.lineSeparator();
			ret += ">    Set the given Friend Code as your own." + System.lineSeparator();
			ret += "* $fc clear" + System.lineSeparator();
			ret += ">    Delete your friend code." + System.lineSeparator();
			ret += "* $fc show" + System.lineSeparator();
			ret += ">    Show the unit you are sharing." + System.lineSeparator();
			ret += "* $fc show \"username\"" + System.lineSeparator();
			ret += ">    Show the the unit the specified user is sharing." + System.lineSeparator();
			ret += "```";
		}
		return ret;
	}
}
