import java.util.*;
public class LZW {
	public String Sen;
	Vector  Dic=new Vector();
	Vector <Integer> Tag=new Vector<Integer>();
	public LZW(String s) {
		Sen=s;
	}
	public int Search(String s)
	{
		for(int i=0;i<Dic.size();i++)
		{
			if(Dic.elementAt(i).equals(s))
			{
				return i;
			}
		}
		return -1;
	}
	public void Compress()
	{
		Dic.add(" ");Dic.add("!");Dic.add("\"");
		String shit="#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ ";
				for(int i=0;i<shit.length();i++)
				{
				Dic.add(shit.substring(i, i+1));
				}
		String CheckPoint="";
		for(int i=0;i<Sen.length();i++)
		{			CheckPoint+=Sen.charAt(i);
			if(Search(CheckPoint)>-1)
			{
				String Sub=CheckPoint;
				boolean there=true;
				while(Search(Sub)>-1&&there==true)
				{
					for(int j=i+1;j<Sen.length();j++)
					{
						Sub+=Sen.charAt(j);
						if(Search(Sub)<=-1)
						{
							Dic.add(Sub);
							String SubSub=Sub.substring(0,Sub.length()-1);
							Tag.add(Search(SubSub)+32);
						    CheckPoint="";
						    there=false;
							i=j-1;
							if(j+1==Sen.length()) {
								CheckPoint+=Sen.charAt(j);
								Tag.add(Search(CheckPoint)+32);}
							break;
						}
						if(j==Sen.length()-1)
						{
							if(Search(Sub)<=-1)
							{
								Dic.add(Sub);
								String SubSub=Sub.substring(0,Sub.length());
								Tag.add(Search(SubSub)+32);
							    CheckPoint="";
							    there=false;
								i=j-1;
								break;
							}
							else
							{
								String SubSub=Sub.substring(0,Sub.length());
								Tag.add(Search(SubSub)+32);
							    CheckPoint="";
							    there=false;
								i=j-1;
								break;
							}
						}
						
					}
						break;
				}
			}
		}
		System.out.println(Dic);
		Dic.clear();
		System.out.println(Tag);
	}
	public void DeCompress()
	{
		Dic.add(" ");Dic.add("!");Dic.add("\"");
		String shit="#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ ";
				for(int i=0;i<shit.length();i++)
				{
				Dic.add(shit.substring(i, i+1));
				}
		for(int i=0;i<Tag.size();i++)
		{
			if(Tag.elementAt(i)-32<Dic.size())
			{
				System.out.print(Dic.elementAt(Tag.elementAt(i)-32));
				if(i>0) {
				String Add="";
				String Sub="";
				Sub+=Dic.elementAt(Tag.elementAt(i)-32);
				Sub=Sub.substring(0,1);
				Add+=Dic.elementAt(Tag.elementAt(i-1)-32);
				Add+=Sub;
				Dic.add(Add);
				}
			}
			else
			{
				String Sub="";
				Sub+=Dic.elementAt(Tag.elementAt(i-1)-32);
				String SubSub="";
				SubSub=Sub.substring(0,1);
				String Add="";
				Add+=Sub+SubSub;
				System.out.print(Add);
				Dic.add(Add);
			}
		}
		Dic.clear();
	}
	public static void main(String[] args) {
		String Sentence;
		Sentence=new Scanner(System.in).nextLine();
		LZW obj=new LZW(Sentence);
		obj.Compress();
		obj.DeCompress();
	}

}
