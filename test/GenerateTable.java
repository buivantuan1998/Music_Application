import com.nvt.xpersistence.util.GenerateTool;

public class GenerateTable {
    public static void main(String[] args) throws Exception {
        GenerateTool.getInstance()
                .withURL("jdbc:mysql://localhost:9999/sdl_cms")
                .withUser("sdl_bkav")
                .withPassword("bkav@2020!")
                .generateTable("config_notification");

//        System.out.println(StringUtils.encodeMD5("123456"));
    }
}
