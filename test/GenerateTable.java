import com.nvt.xpersistence.util.GenerateTool;

public class GenerateTable {
    public static void main(String[] args) throws Exception {
        GenerateTool.getInstance()
                .withURL("jdbc:oracle:thin:@localhost:1521:xe")
                .withUser("MUSIC_APPLICATION_SERVER")
                .withPassword("123")
                .generateTable("song");

//        System.out.println(StringUtils.encodeMD5("123456"));
    }
}
