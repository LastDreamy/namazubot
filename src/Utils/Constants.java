package Utils;

import java.util.LinkedList;
import java.util.List;

public class Constants {
	public static final class Dual<O1, O2>{
		private O1 value1;
		private O2 value2;
		
		public Dual(O1 v1, O2 v2){
			value1 = v1;
			value2 = v2;
		}
		
		public O1 getValue1() { return value1; }
		public O2 getValue2() { return value2; }
	}
	public static final String BASE_USER_PERMISSION = "Applicant";
	public static final Long CULT_CHAT_CHANNEL_ID = 304968876582633474L;
	
	public static enum FUNCTION_NAMES{
		EXECUTE("execute"), UPDATE("update"), ADD("add"), DELETE("delete");
		
		public String name;
		
		private FUNCTION_NAMES(String s){
			name = s;
		}
	}
	public static enum HELP_TYPE{
		SHORT, LONG;
	}
	public static enum TRIALS{
		FreeDay("Mention Quetz (@Quetz) or whisper him for what unit you want","Free Day"),
		RumbleMalboro("https://www.corhm.com/the-rumble-of-malboro.html", "The Rumble of Malboro", "Rumble Malboro", "Malboro Rumble", "Malboro's Rumble", "Malboro"),
		OctopusTeacher("https://www.corhm.com/the-octopus-teacher.html", "The Octopus Teacher", "Octopus Teacher", "Teacher", "Teachers", "Typhon", "Typhoon", "Ultros", "Orthros"),
		LureEchidna("https://www.corhm.com/the-lure-of-echidna.html", "The Lure of Echidna", "Lure Echidna", "Echidna"),
		BeastsDark("https://www.corhm.com/beasts-of-the-dark.html", "Beasts of the Dark", "Dark Espers"),
		GilgameshOffensive("https://www.corhm.com/gilgameshs-offensive.html", "Gilgamesh's Offensive", "Gilgamesh Offensive", "Gilgamesh"),
		ParadePossessed("https://www.corhm.com/parade-of-the-possessed.html", "Parade of the Possessed", "March of the Beasts", "Parade Possessed", "March Beasts", "Parade", "March"),
		AttackAntenolla("https://www.corhm.com/attack-of-antenolla.html", "Attack of Antenolla", "Antenolla", "Antenola", "Plant", "Flower"),
		SurgingMenace("https://www.corhm.com/surging-menace.html", "Surging Menace", "Surging", "Menace");
		
		private String URL;
		private LinkedList<String> names = new LinkedList<String>();
		
		private TRIALS(String URL, String... names){
			this.URL = URL;
			for(String name : names) this.names.add(name);
		}
		
		public String getURL(){ return URL; }
		public List<String> getNames(){ return names; }
		public String getMainName(){ return names.getFirst(); }
		
		public static TRIALS getTrial(String name){
			for(TRIALS trial : TRIALS.values())
				for(String tName : trial.getNames())
					if(tName.equalsIgnoreCase(name))
						return trial;
			return null;
		}
	}
	
	public static final Dual<String, String> nextWord(String line){
		String word = line.substring(0, line.indexOf(" ")).trim();
		line = line.substring(line.indexOf(" ") + 1).trim();
		return new Dual<String, String>(word, line);
	}
	
	public static final String RAINBOW_EMOTE = "<:rcrystal:364441757326376981>";
	public static final String GOLD_EMOTE = "<:gcrystal:364441757670440980>";
	public static final String BLUE_EMOTE = "<:bcrystal:364441757594812416>";
}
