package wangai.collins.leadersboard.util;

public class StringUtil {
    public static String getHoursString(int hours){

        return ("Learning Hours, " + String.valueOf(hours)+" ");
    }

    public static String getSkillString(int skillIq){
        return (String.valueOf(skillIq)+ " "+" Skill IQ score, ");
    }
}
