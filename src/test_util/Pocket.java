package test_util;

import java.util.List;

import net.sf.json.JSONObject;
import test_entity.ThresholdTab;

public class Pocket {

	static int temValue;
	static int humValue;
	static String realtimeData;

	static String LED_R;
	static String LED_G;
	static String LED_B;

	static String command;

	static JSONObject jsonObject; // 扩展多用户后使用json来存储层级关系
	// static List<ThresholdTab> thresholdTabs;

	static String[] usernames;
	static String[] passwords;

	public static String getLED_R() {
		return LED_R;
	}

	public static void setLED_R(String lED_R) {
		LED_R = lED_R;
	}

	public static String getLED_G() {
		return LED_G;
	}

	public static void setLED_G(String lED_G) {
		LED_G = lED_G;
	}

	public static String getLED_B() {
		return LED_B;
	}

	public static void setLED_B(String lED_B) {
		LED_B = lED_B;
	}

	public static String getCommand() {
		return command;
	}

	public static void setCommand(String command) {
		Pocket.command = command;
	}

	public static int getTemValue() {
		return temValue;
	}

	public static void setTemValue(int temValue) {
		Pocket.temValue = temValue;
	}

	public static int getHumValue() {
		return humValue;
	}

	public static void setHumValue(int humValue) {
		Pocket.humValue = humValue;
	}

	public static String[] getUsernames() {
		return usernames;
	}

	public static void setUsernames(String[] usernames) {
		Pocket.usernames = usernames;
	}

	public static String[] getPasswords() {
		return passwords;
	}

	public static void setPasswords(String[] passwords) {
		Pocket.passwords = passwords;
	}

	public static String getRealtimeData() {
		return realtimeData;
	}

	public static void setRealtimeData(String realtimeData) {
		Pocket.realtimeData = realtimeData;
	}

	public static JSONObject getJsonObject() {
		return jsonObject;
	}

	public static void setJsonObject(JSONObject jsonObject) {
		Pocket.jsonObject = jsonObject;
	}

}
