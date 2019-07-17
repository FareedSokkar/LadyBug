package Preprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author fareed & ruba
 *This is our LZW compreser class
 *where it compress our String into a ArrayList of Integers (int vector)
 */
public class LZW {
	private Map<String, Integer> TABLE;
	private int table_Size;
	/*
	 * Constructor
	 */
	public LZW() {
		setTABLE( new HashMap<String, Integer>());
		setTable_Size(table_Size);
		for (int i = 0; i < 255 ; i++)
			getTABLE().put("" + (char) i, i);
	}
	public ArrayList<Integer> EncoderList(String src) {
		//String BUilder <----Important
		String initString = "";
		List<Integer> encoded_values = new ArrayList<Integer>();
		for (char symbol : src.toCharArray()) {
			String Str_Symbol = initString + symbol;
			if (getTABLE().containsKey(Str_Symbol))
				initString = Str_Symbol;
			else {
				encoded_values.add(getTABLE().get(initString));
				getTABLE().put(Str_Symbol, getTable_Size()+1);
				initString = "" + symbol;
			}
		}

		if (!initString.equals(""))
			encoded_values.add(TABLE.get(initString));
		return (ArrayList<Integer>) encoded_values;
		
	}
	public int[] Encoder(String src) {
		//String BUilder <----Important
		String initString = "";
		List<Integer> encoded_values = new ArrayList<Integer>();
		for (char symbol : src.toCharArray()) {
			String Str_Symbol = initString + symbol;
			if (getTABLE().containsKey(Str_Symbol))
				initString = Str_Symbol;
			else {
				encoded_values.add(getTABLE().get(initString));
				getTABLE().put(Str_Symbol, getTable_Size()+1);
				initString = "" + symbol;
			}
		}

		if (!initString.equals(""))
			encoded_values.add(TABLE.get(initString));
		int[] array = encoded_values.stream().mapToInt(i->i).toArray();
		return array;
		
	}
	/*public String Decoder(int[] arr) {
		//String BUilder <----Important
		String core="";
		for(int c:arr) {
			getTABLE().
		}
		return null;
	}*/
	public Map<String, Integer> getTABLE() {
		return TABLE;
	}

	public void setTABLE(Map<String, Integer> tABLE) {
		TABLE = tABLE;
	}

	public int getTable_Size() {
		return table_Size;
	}

	public void setTable_Size(int table_Size) {
		this.table_Size = table_Size;
	}
}
