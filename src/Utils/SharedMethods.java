package Utils;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

public class SharedMethods {
	private static final Random random = new Random();
	
	public static List<IUser> getUsersByName(IGuild guild, String name, boolean includeNicknames) {
		return guild.getUsers().stream()
				.filter(u -> includeNicknames ? u.getDisplayName(guild).equalsIgnoreCase(name) : u.getName().equalsIgnoreCase(name))
				.collect(Collectors.toList());
	}
	public static String getServerName(IUser user, IGuild guild){
		if(user == null) return null;
		if(guild == null) return user.getName();
		String nick = user.getNicknameForGuild(guild);
		if(nick == null) return user.getName();
		return nick;
	}
	
	public static final float randomFloat(int min, int max){
		return (random.nextFloat() * (max-min)) + min;
	}
	
	public static final int randomInt(int min, int max){
		if(max-min == 0) return min;
		return random.nextInt(max-min+1) + min;
	}
	
	public static final void sleep(Long sleep){
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	public static int random(int min,int max){
        Random rand=new Random();
        return rand.nextInt(max-min+1)+min;
    }
}
