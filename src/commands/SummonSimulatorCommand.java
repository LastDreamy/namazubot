package commands;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import Utils.BotException;
import Utils.Constants;
import Utils.GameElements.Banners;
import Utils.GameElements.Unit;
import Utils.IOUtils;
import Utils.MessageUtils;
import Utils.SharedMethods;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class SummonSimulatorCommand extends _BaseCommand{
	private HashMap<IUser, Long> userCooldown = new HashMap<IUser, Long>();
	private IUser currentUser = null;
	
	public SummonSimulatorCommand() {
		super("smn");
	}

	@Override
	protected void doStuff(final MessageReceivedEvent event) {
		final String userName = SharedMethods.getServerName(event.getAuthor(), event.getGuild());
		if(currentUser != null && currentUser.getLongID() == event.getAuthor().getLongID()){
			MessageUtils.sendMessage(event.getChannel(), userName + ", I'm doing a pull for you right now, take it easy!");
			return;
		}
		if(currentUser != null)
			MessageUtils.sendMessage(event.getChannel(), "I'm doing " + SharedMethods.getServerName(currentUser, event.getGuild()) + "'s pull right now, wait a bit for your turn.");
		synchronized(this){
			currentUser = event.getAuthor();
			if(userCooldown.get(currentUser) == null || userCooldown.get(currentUser) < System.currentTimeMillis())
				userCooldown.put(currentUser, System.currentTimeMillis() + (15 * 1000));
			else{
				MessageUtils.sendMessage(event.getChannel(), userName + ", you've recently did a pull, let the salt sink in for a few more seconds(" + (((userCooldown.get(currentUser)-System.currentTimeMillis())/1000)+1) + ").");
				currentUser = null;
				return;
			}
			Unit[] units = getSummonedUnits(event);
			if(units == null){
				MessageUtils.sendMessage(event.getChannel(), "The mentioned banner doesn't exist.");
				userCooldown.remove(currentUser);
				currentUser = null;
				return;
			}
			String ret = units[0].getUnitCrystal();
			for(int i = 1; i < units.length; i++)
				ret += " " + units[i].getUnitCrystal();
			IMessage message = MessageUtils.sendMessage(event.getChannel(), userName + "'s pull is: " + ret);
			
			for(int i = 0; i < units.length; i++){
				SharedMethods.sleep(1300L);
				ret = units[0].getUnitCrystal() + units[0].getName();// + units[0].getUnitCrystal();// + "(" + units[0].getStar() + ")";
				for(int ii = 1; ii < units.length; ii++)
					if(ii <= i)
						ret += " " + units[ii].getUnitCrystal() + units[ii].getName();// + units[ii].getUnitCrystal();// + "(" + units[ii].getStar() + ")";
					else
						ret += " " + units[ii].getUnitCrystal();
				MessageUtils.editMessage(message, userName + "'s pull is: " + ret);
			}
			try {
				//FIXME Temporary
				IOUtils.writeInFile("pulls.txt", Arrays.asList(units).stream().map(u -> u.getName()).collect(Collectors.toList()).toArray(new String[]{}));
			} catch (BotException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentUser = null;
		}
		SharedMethods.sleep(1300L);
	}
	
	private Unit[] getSummonedUnits(final MessageReceivedEvent event){
		List<Unit> units = new LinkedList<Unit>();
		IMessage message = event.getMessage();
		String msg = message.getContent();
		if(msg.contains(" ")){
			msg = msg.substring(msg.indexOf(" ") + 1).trim();
			boolean multiPull = msg.indexOf("10+1") == 0;
			if(multiPull)
				msg = msg.substring(4).trim();
			Banners banner = null;
			if(msg.equals("5*"))
				units.add(Banners.RareSummon.exPull(5));
			else if(!msg.isEmpty()){
				List<Banners> bannerList = Arrays.asList(Banners.values()).stream().collect(Collectors.toList());
				if(msg.equalsIgnoreCase("current") || msg.equalsIgnoreCase("new"))
					banner = bannerList.get(0);
				else if(bannerList.stream().map(b -> b.getName().toLowerCase()).collect(Collectors.toList()).contains(msg.trim().toLowerCase()))
					banner = bannerList.get(bannerList.stream().map(b -> b.getName().toLowerCase()).collect(Collectors.toList()).indexOf(msg.trim().toLowerCase()));
				if(banner == null)//Error banner doesn't exist
					return null;
			}
			else
				banner = Banners.RareSummon;
			if(units.size() == 0)
				if(multiPull){
					units.add(banner.specialPull());
					for(int i = 0; i < 10; i++)
						units.add(banner.pull());
				} else
					units.add(banner.pull());
		}else
			units.add(Banners.RareSummon.pull());
		return units.toArray(new Unit[]{});
	}
	
	@Override
 	protected String help(final MessageReceivedEvent event, Constants.HELP_TYPE helpType) {
		String ret = "";
		if(Constants.HELP_TYPE.SHORT.equals(helpType)){
			ret += "Simulate pulls for salt.";
		}else{
			ret += "```md" + System.lineSeparator();
			ret += "Summon Simulator Command" + System.lineSeparator();
			ret += "===============" + System.lineSeparator();
			ret += System.lineSeparator();
			ret += "[*][Usage][*]" + System.lineSeparator();
			ret += "* $summon" + System.lineSeparator();
			ret += ">    This command does a Rare Summon." + System.lineSeparator();
			ret += "* $summon \"Banner Name\"" + System.lineSeparator();
			ret += ">    This command does a Rare Summon from specified banner." + System.lineSeparator();
			ret += "* $summon 10+1" + System.lineSeparator();
			ret += ">    This command does a 10+1 Rare Summon." + System.lineSeparator();
			ret += "* $summon 10+1 \"Banner Name\"" + System.lineSeparator();
			ret += ">    This command does a 10+1 Rare Summon from specified banner." + System.lineSeparator();
			ret += "```";
		}
		return ret;
	}
}
