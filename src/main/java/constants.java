public class constants {

    private static final String JDBCURL= "jdbc:postgresql://localhost:5432/ImmaBoredd";
    private static final String USERNAME= "postgres";
    private static final String PASSWORD= "admin";

    public static String getDbUrl(){
        return JDBCURL;
    }

    public static String getDbUsername(){
        return USERNAME;
    }

    public static String getDbPassword(){
        return PASSWORD;
    }


}
