package commands;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import Utils.BotException;
import Utils.Constants;
import Utils.Constants.Dual;
import Utils.IOUtils;
import Utils.LogUtils;
import Utils.MessageUtils;
import Utils.SharedMethods;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class EmoteCommand extends _BaseCommand{
	public EmoteCommand() {
		super("emo");
	}
	
	private HashMap<String, Dual<String, String>> loadEmotes() throws BotException{
		HashMap<String, Dual<String, String>> emoteMap = new HashMap<String, Dual<String, String>>();
		List<String> list = IOUtils.readFile(IOUtils.EMOTE_FILE);
		for(String s : list){
			String[] words = s.split("\t");
			emoteMap.put(words[0], new Dual<String, String>(words[1], words[2]));
		}
		return emoteMap;
	}
	private void saveEmotes(HashMap<String, Dual<String, String>> emoteMap) throws BotException{
		IOUtils.writeFile(IOUtils.EMOTE_FILE, 
					emoteMap.entrySet().stream().
						map(entry -> entry.getKey() + "\t" + entry.getValue().getValue1() + "\t" + entry.getValue().getValue2())
						.collect(Collectors.toList()));
	}
	
	private int getMaxEmotes(final MessageReceivedEvent event){
		return getMaxEmotes(event, event.getAuthor());
	}
	
	private int getMaxEmotes(final MessageReceivedEvent event, IUser user){
		if(event.getAuthor().getRolesForGuild(event.getGuild()).stream().anyMatch(r -> r.getName().equals("Regulars")))
			return 20;
		int max = 0;
		for(IRole role : user.getRolesForGuild(event.getGuild())){
			switch(""+role.getStringID()){
			case "369965522504384516": //Livre Fragile
				max = max < 50 ? 50 : max; break;
			case "389158118245924864" : //Girouette
				max = max < 50 ? 50 : max; break;
			case "369966041540984834>": //La Bible
				max = max < 50 ? 50 : max; break;
			case "369965819783806976": //Saints Chevaliers
				max = max < 50 ? 50 : max; break;
			case "369965121063354374": //Reine des ninchats
				max = max < 50 ? 50 : max; break;
			case "370574089284222976": //Roi des ninchats
				max = max < 50 ? 50 : max; break;
			case "370947668257800196": //Weebs
				max = max < 50 ? 50 : max; break;

			}
		}
		return max;
	}

	@Override
	protected void doStuff(final MessageReceivedEvent event) {
		IMessage message = event.getMessage();
		try {
			String msg = message.getContent();
			HashMap<String, Constants.Dual<String, String>> emotes = loadEmotes();
			if(msg.contains(" ")){
				String userId = ""+message.getAuthor().getLongID();
				String command = msg.substring(msg.indexOf(" ") + 1).trim();
				String emoteName = command.substring(command.indexOf(" ") + 1).trim();
				if(command.startsWith("add ")){
					String url = Constants.nextWord(emoteName).getValue2();
					emoteName = Constants.nextWord(emoteName).getValue1().toLowerCase();
					if(emotes.get(emoteName) != null){
						MessageUtils.sendMessage(event.getChannel(), "There is already an emote with that name.");
						return;
					}
					int userEmotes = (int)emotes.values().stream().filter(d -> d.getValue1().equals(userId)).count();
					if(userEmotes >= getMaxEmotes(event)){
						MessageUtils.sendMessage(event.getChannel(), "You've reached your maximum of " + userEmotes + " emotes.");
						return;
					}
					emotes.put(emoteName, new Dual<String, String>(userId, url));
					saveEmotes(emotes);
					MessageUtils.sendMessage(event.getChannel(), "Emote \"" + emoteName + "\" ajoutée, tu as " + (getMaxEmotes(event) - userEmotes - 1) + " places restantes.");
				}
				else if(command.startsWith("remove ")){
					emoteName = emoteName.toLowerCase();
					if(emotes.get(emoteName) == null){
						MessageUtils.sendMessage(event.getChannel(), "There is no emote with that name.");
						return;
					}
					if(!emotes.get(emoteName).getValue1().equals(userId)){
						MessageUtils.sendMessage(event.getChannel(), "You can only remove emotes that you've added yourself.");
						return;
					}
					int userEmotes = (int)emotes.values().stream().filter(d -> d.getValue1().equals(userId)).count();
					emotes.remove(emoteName);
					saveEmotes(emotes);
					MessageUtils.sendMessage(event.getChannel(), "Emote \"" + emoteName + "\" removed, you have " + (getMaxEmotes(event) - userEmotes + 1) + " slots left.");
				}
				else if(msg.startsWith("delete ")){
					emoteName = emoteName.toLowerCase();
					if(emotes.get(emoteName) == null){
						MessageUtils.sendMessage(event.getChannel(), "There is no emote with that name.");
						return;
					}
					int userEmotes = (int)emotes.values().stream().filter(d -> d.getValue1().equals(userId)).count();
					emotes.remove(emoteName);
					saveEmotes(emotes);
					MessageUtils.sendMessage(event.getChannel(), "Emote \"" + emoteName + "\" deleted, you have " + (getMaxEmotes(event) - userEmotes + 1) + " slots left.");
				}
				else if(command.startsWith("list")){
					if(!command.startsWith("list "))
						emoteName = event.getAuthor().getDisplayName(event.getGuild());
					List<IUser> user = SharedMethods.getUsersByName(event.getGuild(), emoteName, true);
					if(user.size() != 1){
						MessageUtils.sendMessage(event.getChannel(), "Unable to obtain emotes of specified user.");
						return;
					}
					String emoteList = "";
				@SuppressWarnings("deprecation")
					List<Entry<String, Dual<String, String>>> userEmotes = emotes.entrySet().stream().filter(d -> d.getValue().getValue1().equalsIgnoreCase(user.get(0).getStringID())).collect(Collectors.toList());
					if(userEmotes.size() == 0){
						MessageUtils.sendMessage(event.getChannel(), "Specified user has no registered emotes.");
						MessageUtils.sendMessage(event.getChannel(), "Slots available: " + (getMaxEmotes(event, user.get(0)) - userEmotes.size()) + "/" + getMaxEmotes(event, user.get(0)));
						return;
					}
					for(Entry<String, Dual<String, String>> dual : userEmotes){
						emoteList += dual.getKey() + ", ";
					}
					MessageUtils.sendMessage(event.getChannel(), "Emotes registered by specified user: " + emoteList.substring(0, emoteList.length()-2));
					MessageUtils.sendMessage(event.getChannel(), "Slots available: " + (getMaxEmotes(event, user.get(0)) - userEmotes.size()) + "/" + getMaxEmotes(event, user.get(0)));
				}else{
					emoteName = emoteName.toLowerCase();
					try{
						if(emotes.get(emoteName) != null)
							MessageUtils.sendImage(event.getChannel(), emotes.get(emoteName).getValue2());
						else{
							MessageUtils.sendMessage(event.getChannel(), "!emo " + emoteName);
							
							try{
				                event.getMessage().delete();
				            }
				            catch(Exception e){e.printStackTrace();};
						
						}
					} catch(Exception e){
						MessageUtils.sendMessage(event.getChannel(), emotes.get(emoteName).getValue2());
						
						try{
			                event.getMessage().delete();
			            }
			            catch(Exception e1){e.printStackTrace();};
						
					}
				}
			}
			else{
				String emoteList = "";
				List<Entry<String, Dual<String, String>>> userEmotes = emotes.entrySet().stream().collect(Collectors.toList());
				for(Entry<String, Dual<String, String>> dual : userEmotes){
					emoteList += dual.getKey() + ", ";
				}
				MessageUtils.sendMessage(event.getChannel(), "Nombre d'emotes: " + userEmotes.size() + "\n" + "Emotes : " + emoteList.substring(0, emoteList.length()-2));
			}
		} catch (BotException e) {
			LogUtils.log().error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			MessageUtils.sendMessage(event.getChannel(), "Error using command, refer to \"!help emote\" to see correct command usage.");
		}
	}
	
    protected String getPermissionName(final MessageReceivedEvent event){
		return event.getMessage().getContent().contains(" add ") || event.getMessage().getContent().contains(" remove ") ? 
				Constants.FUNCTION_NAMES.ADD.name : 
				event.getMessage().getContent().contains(" delete ") ? 
						Constants.FUNCTION_NAMES.ADD.name : 
						Constants.FUNCTION_NAMES.EXECUTE.name;
    }
	
	@Override
	protected String help(final MessageReceivedEvent event, Constants.HELP_TYPE helpType) {
		String ret = "";
		if(Constants.HELP_TYPE.SHORT.equals(helpType)){
			ret += "Utilise une emote!";
		}else{
			ret += "```md" + System.lineSeparator();
			ret += "Emote Command" + System.lineSeparator();
			ret += "===============" + System.lineSeparator();
			ret += System.lineSeparator();
			ret += "[*][Usage][*]" + System.lineSeparator();
			ret += "* !emote" + System.lineSeparator();
			ret += ">    This command lists all emotes registed on the bot." + System.lineSeparator();
			ret += "* !emote \"name\"" + System.lineSeparator();
			ret += ">    This command allows you to use the emote associated with the name." + System.lineSeparator();
			ret += "* !emote add \"name\" \"link\"" + System.lineSeparator();
			ret += ">    This command allows you to add an emote." + System.lineSeparator();
			ret += "* !emote remove \"name\"" + System.lineSeparator();
			ret += ">    This command allows you to remove one of your emotes." + System.lineSeparator();
			ret += "* !emote list" + System.lineSeparator();
			ret += ">    This command lists all the emotes added by the person that used the command." + System.lineSeparator();
			ret += "* !emote list \"name\"" + System.lineSeparator();
			ret += ">    This command lists all the emotes added by that user." + System.lineSeparator();
			ret += "```";
		}
		return ret;
	}

}

