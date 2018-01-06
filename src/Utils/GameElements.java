package Utils;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class GameElements {
	public static enum Esper{
		SIREN("Siren", "https://www.corhm.com/siren.html"),
		IFRIT("Ifrit", "https://www.corhm.com/ifrit.html"),
		GOLEM("Golem", "https://www.corhm.com/golem.html"),
		SHIVA("Shiva", "https://www.corhm.com/shiva.html"),
		ODIN("Odin", "https://www.corhm.com/odin.html");
		
		private String name, link;
		
		private Esper(String name, String link){
			this.name= name;
			this.link = link;
		}

		public String getName(){ return name; }
		public String getLink(){ return link; }
	}
	
	public interface Unit{
		public String getName();
		public int getStar();
		public boolean isLimited();
		public Date getReleaseDate();
		public String getUnitCrystal();
	}
	public static enum Units5 implements Unit{
		Lightning("Lightning", 5, false), Delita("Delita", 5, false), Ramza("Ramza", 5, false), DarkKnightCecil("Dark Knight Cecil", 5, false), Luneth("Luneth", 5, false), Gilgamesh("Gilgamesh", 5, false), Noctis("Noctis", 5, false), DarkFina("Dark Fina", 5, false), Ace("Ace", 5, false), Marie("Marie", 5, false), Emperor("Emperor", 5, false), Olive("Olive", 5, false), Queen("Queen", 5, false), Orlandeau("Orlandeau", 5, false), Fryevia("Fryevia", 5, false), TranceTerra("Trance Terra", 5, false), Aileen("Aileen", 5, false), Reberta("Reberta", 5, false), Rem("Rem", 5, false), Wilhelm("Wilhelm", 5, false), Fohlen("Fohlen", 5, false), SeabreezeDarkFina("Seabreeze Dark Fina", 5, false), Tidus("Tidus", 5, false), Zargabaath("Zargabaath", 5, false), Lunera("Lunera", 5, false), MercenaryRamza("Mercenary Ramza", 5, false), KnightDelita("Knight Delita", 5, false), VeritasoftheDark("Veritas of the Dark", 5, false), VeritasoftheFlame("Veritas of the Flame", 5, false), VeritasoftheLight("Veritas of the Light", 5, false), OnionKnight("Onion Knight", 5, false), Prishe("Prishe", 5, false), Ayaka("Ayaka", 5, false), Nyx("Nyx", 5, false),
		DemonRain("Demon Rain", 5,true), DracuLasswell("Dracu Lasswell", 5,true), Elza("Elza", 5,true), Maxwell("Maxwell", 5,true), WhiteKnightNoel("White Knight Noel", 5,true), Yun("Yun", 5,true), Randi("Randi", 5,true), Yshtola("Y'shtola", 5,true), Kelsus("Kelsus", 5,true), Vargas("Vargas", 5,true), _2B("2B", 5,true), A2("A2", 5,true);
		private String name;
		private int star;
		private boolean limited;
		
		private Units5(String name, int star, boolean limited){
			this.name = name;
			this.star = star;
			this.limited = limited;
		}
		
		public String getName(){ return name; }
		public int getStar(){ return star; }
		public boolean isLimited(){ return limited; }
		public Date getReleaseDate(){ return new Date(); }
		public String getUnitCrystal(){ return Constants.RAINBOW_EMOTE; }
		public static Unit getRandom(){ 
			Unit u = values()[SharedMethods.randomInt(1, values().length)-1];
			return u.isLimited() ? getRandom() : u;
		}
		public Units5[] notLimitedValues(){ return (Units5[])Arrays.asList(values()).stream().filter(u -> !u.isLimited()).collect(Collectors.toList()).toArray(); }
	}
	public static enum Units4 implements Unit{
		Chizuru("Chizuru", 4, false), WarriorofLight("Warrior of Light", 4, false), Garnet("Garnet", 4, false), Gaffgarion("Gaffgarion", 4, false), Agrias("Agrias", 4, false), Rosa("Rosa", 4, false), Refia("Refia", 4, false), Snow("Snow", 4, false), Vanille("Vanille", 4, false), Hope("Hope", 4, false), Mercedes("Mercedes", 4, false), Luka("Luka", 4, false), Seven("Seven", 4, false), Leon("Leon", 4, false), Shine("Shine", 4, false), Nine("Nine", 4, false), Soleil("Soleil", 4, false), Xon("Xon", 4, false), Setzer("Setzer", 4, false), Sozhe("Sozhe", 4, false), Zyrus("Zyrus", 4, false), King("King", 4, false), Grace("Grace", 4, false), Ilias("Ilias", 4, false), Amelia("Amelia", 4, false), BeachTimeFina("Beach Time Fina", 4, false), Rikku("Rikku", 4, false), Ashe("Ashe", 4, false), Rasler("Rasler", 4, false), Bran("Bran", 4, false), Helena("Helena", 4, false), Meliadoul("Meliadoul", 4, false), Orran("Orran", 4, false), VeritasoftheEarth("Veritas of the Earth", 4, false), Victoria("Victoria", 4, false), VeritasoftheHeavens("Veritas of the Heavens", 4, false), VeritasoftheWaters("Veritas of the Waters", 4, false), Desch("Desch", 4, false), Aria("Aria", 4, false), Werei("Werei", 4, false), Goken("Goken", 4, false), Silvia("Silvia", 4, false), Crowe("Crowe", 4, false), Glauca("Glauca", 4, false),
		Juggler("Juggler", 4,true), Thief("Thief", 4,true), Fencer("Fencer", 4,true), WhiteWitchFina("White Witch Fina", 4,true), Karl("Karl", 4,true), Seria("Seria", 4,true), Tilith("Tilith", 4,true), SantaRoselia("Santa Roselia", 4,true), DangerousAriana("Dangerous Ariana", 4,true), Ling("Ling", 4,true), Primm("Primm", 4,true), CupidArtemios("Cupid Artemios", 4,true), Minfilia("Minfilia", 4,true), Yda("Yda", 4,true), Papalymo("Papalymo", 4,true), Moogle("Moogle", 4,true), _9S("9S", 4,true), Adam("Adam", 4,true), Eve("Eve", 4,true), ChicAriana("Chic Ariana", 4,true); 

		private String name;
		private int star;
		private boolean limited;
		
		private Units4(String name, int star, boolean limited){
			this.name = name;
			this.star = star;
			this.limited = limited;
		}
		
		public String getName(){ return name; }
		public int getStar(){ return star; }
		public boolean isLimited(){ return limited; }
		public Date getReleaseDate(){ return new Date(); }
		public String getUnitCrystal(){ return Constants.GOLD_EMOTE; }
		public static Unit getRandom(){ 
			Unit u = values()[SharedMethods.randomInt(1, values().length)-1];
			return u.isLimited() ? getRandom() : u;
		}
		public Units4[] notLimitedValues(){ return (Units4[])Arrays.asList(values()).stream().filter(u -> !u.isLimited()).collect(Collectors.toList()).toArray(); }
	}
	public static enum Units3 implements Unit{
		Vivi("Vivi", 3, false), Penelo("Penelo", 3, false), Maria("Maria", 3, false), Sabin("Sabin", 3, false), Shadow("Shadow", 3, false), Krile("Krile", 3, false), Kain("Kain", 3, false), Edgar("Edgar", 3, false), Fran("Fran", 3, false), Shantotto("Shantotto", 3, false), Rydia("Rydia", 3, false), Cyan("Cyan", 3, false), Clyne("Clyne", 3, false), Anzelm("Anzelm", 3, false), Luna("Luna", 3, false), Bedile("Bedile", 3, false), Garland("Garland", 3, false), Exdeath("Exdeath", 3, false), Kuja("Kuja", 3, false), CloudofDarkness("Cloud of Darkness", 3, false), Cecil("Cecil", 3, false), Terra("Terra", 3, false), Bartz("Bartz", 3, false), Firion("Firion", 3, false), Zidane("Zidane", 3, false), Vaan("Vaan", 3, false), Duane("Duane", 3, false), Cerius("Cerius", 3, false), Roselia("Roselia", 3, false), Medius("Medius", 3, false), Miyuki("Miyuki", 3, false), Russell("Russell", 3, false), Golbez("Golbez", 3, false), Galuf("Galuf", 3, false), Xiao("Xiao", 3, false), Artemios("Artemios", 3, false), Locke("Locke", 3, false), Leo("Leo", 3, false), Gilbert("Gilbert", 3, false), Celes("Celes", 3, false), Kefka("Kefka", 3, false), Rakshasa("Rakshasa", 3, false), Hayate("Hayate", 3, false), Tellah("Tellah", 3, false), Lenna("Lenna", 3, false), Amarant("Amarant", 3, false), Lani("Lani", 3, false), Freya("Freya", 3, false), Charlotte("Charlotte", 3, false), Ludmille("Ludmille", 3, false), Alma("Alma", 3, false), Mustadio("Mustadio", 3, false), Edge("Edge", 3, false), Arc("Arc", 3, false), Ingus("Ingus", 3, false), Faris("Faris", 3, false), Sazh("Sazh", 3, false), Fang("Fang", 3, false), Elle("Elle", 3, false), Trey("Trey", 3, false), Jack("Jack", 3, false), Guy("Guy", 3, false), Shera("Shera", 3, false), Cinque("Cinque", 3, false), Eight("Eight", 3, false), Ovelia("Ovelia", 3, false), Lawrence("Lawrence", 3, false), Aiden("Aiden", 3, false), Gau("Gau", 3, false), Heltich("Heltich", 3, false), Ulrica("Ulrica", 3, false), Sice("Sice", 3, false), Abel("Abel", 3, false), Jean("Jean", 3, false), Camille("Camille", 3, false), SummerLid("Summer Lid", 3, false), Wakka("Wakka", 3, false), Ruggles("Ruggles", 3, false), Timothy("Timothy", 3, false), Sara("Sara", 3, false), Kupipi("Kupipi", 3, false), Kamui("Kamui", 3, false), Yuri("Yuri", 3, false), Libertus("Libertus", 3, false),
		MagitekArmorTerra("Magitek Armor Terra", 3,true), BlackCatLid("Black Cat Lid", 3,true), Popoi("Popoi", 3,true), CupidLuna("Cupid Luna", 3,true), Thancred("Thancred", 3,true), _21O("21O", 3,true);
		
		private String name;
		private int star;
		private boolean limited;
		
		private Units3(String name, int star, boolean limited){
			this.name = name;
			this.star = star;
			this.limited = limited;
		}
		
		public String getName(){ return name; }
		public int getStar(){ return star; }
		public boolean isLimited(){ return limited; }
		public Date getReleaseDate(){ return new Date(); }
		public String getUnitCrystal(){ return Constants.BLUE_EMOTE; }
		public static Unit getRandom(){ 
			Unit u = values()[SharedMethods.randomInt(1, values().length)-1];
			return u.isLimited() ? getRandom() : u;
		}
		public Units3[] notLimitedValues(){ return (Units3[])Arrays.asList(values()).stream().filter(u -> !u.isLimited()).collect(Collectors.toList()).toArray(); }
	}
	
	public static enum Banners{
		AyakaBanner("Ayaka Banner", "", Units5.Ayaka, Units4.Goken, Units4.Silvia, Units3.Kamui, Units3.Yuri),
		FF151("Final Fantasy XV", "", Units5.Nyx, Units4.Crowe, Units4.Glauca, Units3.Libertus),
		FestivalAutumnMoon("Festival of the Autumn Moon", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/2/2a/Gacha-Festival_of_the_Autumn_Moon.png/430px-Gacha-Festival_of_the_Autumn_Moon.png?version=9b8f505aa91de74f9b297ff5786ee930", Units5.Orlandeau, Units4.Luka, Units4.Mercedes, Units3.Exdeath),
		FF11("Final Fantasy XI", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/3/37/Gacha-FF11-Prishe.png/430px-Gacha-FF11-Prishe.png", Units5.Prishe, Units4.Werei, Units3.Shantotto, Units3.Kupipi),
		DeveloperChoice("Developer's Choice", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/a/a1/Gacha-Dev_Choice-Gilgamesh.png/430px-Gacha-Dev_Choice-Gilgamesh.png", Units5.Gilgamesh, Units4.Chizuru, Units3.Abel),
		FF3("Final Fantasy III", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/b/bb/Gacha-FF3-Onion_Knight.png/430px-Gacha-FF3-Onion_Knight.png", Units5.OnionKnight, Units4.Desch, Units4.Aria, Units3.Sara),
		VeritasBanner2("Veritas Banner 2", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/3/3a/Gacha-FFBE-Veritas_of_the_Light.png/430px-Gacha-FFBE-Veritas_of_the_Light.png", Units5.VeritasoftheLight, Units4.VeritasoftheHeavens, Units4.VeritasoftheWaters, Units3.Charlotte, Units3.Hayate),
		MalboroKillers("Malboro Killers", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/e/ec/Gacha-Malboro_Killers.png/430px-Gacha-Malboro_Killers.png", Units5.TranceTerra, Units4.Soleil, Units4.WarriorofLight, Units3.Kefka, Units3.CloudofDarkness),
		Nier("NieR: Automata", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/b/bb/Gacha-NieR_Automata.png/430px-Gacha-NieR_Automata.png", Units5.A2, Units5._2B, Units4.Eve, Units4._9S, Units3._21O),		
		VeritasBanner1("Veritas Banner 1", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/7/76/Gacha-Final_Fantasy_Brave_Exvius-Veritas_of_the_Dark.png/430px-Gacha-Final_Fantasy_Brave_Exvius-Veritas_of_the_Dark.png", Units5.VeritasoftheDark, Units5.VeritasoftheFlame, Units4.VeritasoftheEarth, Units4.Victoria, Units3.Timothy),
		Tactics3("Tactics 3", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/e/e5/Gacha-Final_Fantasy_Tactics-Mercenary_Ramza.png/430px-Gacha-Final_Fantasy_Tactics-Mercenary_Ramza.png", Units5.MercenaryRamza, Units5.KnightDelita, Units4.Gaffgarion, Units4.Agrias, Units4.Meliadoul, Units4.Orran, Units3.Alma),
		AwakenedWarriors2("Awakened Warriors 2", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/4/45/Gacha-Awakened_Warriors-Luneth.png/430px-Gacha-Awakened_Warriors-Luneth.png", Units5.Luneth, Units5.DarkKnightCecil, Units4.Refia, Units4.Rosa, Units3.Cecil, Units3.Exdeath),
		LuneraBanner("Lunera Banner", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/f/fc/Gacha-Final_Fantasy_Brave_Exvius-Lunera.png/430px-Gacha-Final_Fantasy_Brave_Exvius-Lunera.png", Units5.Lunera, Units4.Bran, Units4.Helena, Units3.Ruggles),
		FF12("Final Fantasy XII", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/6/6a/Gacha-Final_Fantasy_XII.png/430px-Gacha-Final_Fantasy_XII.png", Units5.Zargabaath, Units4.Ashe, Units4.Rasler, Units3.Vaan),
		FF10("Final Fantasy X", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/2/24/Gacha-Final_Fantasy_X.png/430px-Gacha-Final_Fantasy_X.png", Units5.Tidus, Units4.Rikku, Units3.Wakka),
		Summer("Summer Banner", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/3/36/Gacha-Final_Fantasy_Brave_Exvius-Summer.png/430px-Gacha-Final_Fantasy_Brave_Exvius-Summer.png", Units5.SeabreezeDarkFina, Units4.BeachTimeFina, Units3.SummerLid),
		BraveFrontier("Brave Frontier", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/a/a5/Gacha-Brave_Frontier-2.png/430px-Gacha-Brave_Frontier-2.png", Units5.Vargas, Units5.Elza, Units4.Tilith, Units4.Seria, Units4.Karl),
		WilhelmBanner("Wilhelm Banner", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/8/85/Gacha-Final_Fantasy_Brave_Exvius.png/430px-Gacha-Final_Fantasy_Brave_Exvius.png", Units5.Wilhelm, Units4.Grace, Units3.Abel, Units3.Jean),
		FFType3("Type-0 3", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/7/70/Gacha-Final_Fantasy_Type-0-3.png/430px-Gacha-Final_Fantasy_Type-0-3.png", Units5.Rem, Units4.King, Units3.Sice),
		DragonKillers("Dragon Killers", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/b/b0/Gacha-Dragon_Killers.png/430px-Gacha-Dragon_Killers.png", Units5.Reberta, Units4.Zyrus, Units3.Firion, Units3.Kain),
		AileenBanner("Aileen Banner", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/d/d0/Gacha-Final_Fantasy_Brave_Exvius-Aileen.png/430px-Gacha-Final_Fantasy_Brave_Exvius-Aileen.png", Units5.Aileen, Units4.Sozhe, Units3.Ulrica, Units3.Heltich),
		FF6("Final Fantasy VI", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/9/98/Gacha-Final_Fantasy_VI.png/430px-Gacha-Final_Fantasy_VI.png", Units5.TranceTerra, Units4.Setzer, Units3.Gau, Units3.Terra, Units3.Kefka),
		AwakenedWarriors1("Awakened Warriors 1", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/5/50/Gacha-Awakened_Warriors.png/430px-Gacha-Awakened_Warriors.png", Units5.Lightning,Units4.Seven,Units4.Mercedes,Units3.Medius),
		EggSeekers("Egg Seekers", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/f/f1/Gacha-The_Egg_Seekers.png/430px-Gacha-The_Egg_Seekers.png", Units5.Fryevia, Units4.Xon, Units3.Aiden),
		Tactics2("Tactics 2", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/3/33/Gacha-Final_Fantasy_Tactics.png/430px-Gacha-Final_Fantasy_Tactics.png", Units5.Orlandeau, Units4.Soleil, Units3.Ovelia, Units3.Lawrence),
		PeopleChoice("People's Choice", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/a/aa/Gacha-People%27s_Choice.png/430px-Gacha-People%27s_Choice.png", Units5.Gilgamesh, Units5.Noctis, Units4.Chizuru, Units4.Refia, Units3.Cecil, Units3.Zidane),
		GilgameshVanquishers("Gilgamesh Vanquishers", "https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/4/43/Gacha-Gilgamesh_Vanquishers.png/430px-Gacha-Gilgamesh_Vanquishers.png", Units5.Ramza,Units4.Snow,Units4.Agrias,Units3.Bartz,Units3.Exdeath),
		FFType2("Type-0 2","https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/a/a5/Gacha-Final_Fantasy_Type-0-2.png/430px-Gacha-Final_Fantasy_Type-0-2.png", Units5.Queen,Units4.Nine,Units3.Cinque,Units3.Eight),
		_20MDownloads("20 Million Downloads","https://exvius.gamepedia.com/media/exvius.gamepedia.com/thumb/0/03/Gacha-Olive_Shine_Shera.png/430px-Gacha-Olive_Shine_Shera.png%22",Units5.Olive,Units4.Shine,Units3.Shera),
		
		
		QuetzBanner("Quetz Banner", "", Units5.TranceTerra, Units5.Fryevia, Units4.Ashe, Units4.Silvia, Units4.Zyrus, Units3.Arc, Units3.Ingus, Units3.Ludmille), //October
		TsukasanBanner("Tsukasan Banner", "", Units5.Ramza, Units5.Zargabaath, Units4.Eve, Units4.Tilith, Units4.WarriorofLight, Units3.Alma, Units3.Exdeath, Units3.SummerLid),
		
		
		
		
		
		
		RareSummon("Rare Summon", ""); //ArrayUtils.addAll(ArrayUtils.addAll(ArrayUtils.addAll(new Unit[]{}, (Unit[])Units5.values()), (Unit[])Units4.values()), (Unit[])Units3.values())
		
		
		private String name, bannerURL;
		private Unit[] bannerUnits;
		private int count5, count4, count3;
		
		private Banners(String name, String url, Unit... bannerUnits){
			this.name= name;
			this.bannerURL = url;
			this.bannerUnits = bannerUnits;
			for(Unit unit : bannerUnits)
				if(unit.getStar() == 5)
					count5++;
				else if(unit.getStar() == 4)
					count4++;
				else if(unit.getStar() == 3)
					count3++;
		}
		
		public String getName(){ return name; }
		public String getBannerURL(){ return bannerURL; }
		public Unit[] getBannerUnits(){ return bannerUnits; }
		public Date getBeginDate(){ return new Date(); }

		public Unit exPull(int minRarity){
			if(minRarity == 5)
				return Units5.getRandom();
			else
				return null;
		}
		public Unit specialPull(){
			float gacha = SharedMethods.randomFloat(0, 100);
			System.out.println("Special: " + gacha);
			if(gacha < 5)
				if(gacha < 3.75 && bannerUnits.length > 0)
					return bannerUnits[SharedMethods.randomInt(0, count5-1)]; //5* banner
				else{
					Unit u = Units5.getRandom();
					while(Arrays.asList(bannerUnits).contains(u))
						u = Units5.getRandom();
					return u; //5* offbanner
				}
			else
				if(gacha < 5+23.75 && bannerUnits.length > 0)
					return bannerUnits[SharedMethods.randomInt(count5, count5+count4-1)]; //4* banner
				else{
					Unit u = Units4.getRandom();
					while(Arrays.asList(bannerUnits).contains(u))
						u = Units4.getRandom();
					return u; //4* offbanner
				}
		}
		public Unit pull(){
			float gacha = SharedMethods.randomFloat(0, 100);
			System.out.println("Regular: " + gacha);
			if(gacha < 1)
				if(gacha < 0.5 && bannerUnits.length > 0 && count5 > 0)
					return bannerUnits[SharedMethods.randomInt(0, count5-1)]; //5* banner
				else{
					Unit u = Units5.getRandom();
					while(Arrays.asList(bannerUnits).contains(u))
						u = Units5.getRandom();
					return u; //5* offbanner
				}
			else if(gacha < (19+1))
				if(gacha < (4.75+1) && bannerUnits.length > 0 && count4 > 0)
					return bannerUnits[SharedMethods.randomInt(count5, count5+count4-1)]; //4* banner
				else{
					Unit u = Units4.getRandom();
					while(Arrays.asList(bannerUnits).contains(u))
						u = Units4.getRandom();
					return u; //4* offbanner
				}
			else
				if(gacha < (20+19+1) && bannerUnits.length > 0 && count3 > 0)
					return bannerUnits[SharedMethods.randomInt(count5+count4, count5+count4+count3-1)]; //3* banner
				else{
					Unit u = Units3.getRandom();
					while(Arrays.asList(bannerUnits).contains(u))
						u = Units3.getRandom();
					return u; //3* offbanner
				}
		}
	}
}
