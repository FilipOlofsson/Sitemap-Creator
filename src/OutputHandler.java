import java.io.*;
import java.sql.SQLException;

public class OutputHandler {

    private String SQL_TABLE;

    private String FILE_NAME;
    private String FILE_EXTENSION;

    private OutputMethod METHOD;

    private Database database;

    /*
        Constructor if using SQL.
     */
    OutputHandler(String SQL_SERVER, String SQL_USERNAME, String SQL_PASSWORD, String SQL_DATABASE, String SQL_TABLE) {
        this.SQL_TABLE = SQL_TABLE;
        this.METHOD = OutputMethod.SQL;
        database = new Database(SQL_SERVER, SQL_USERNAME, SQL_PASSWORD, SQL_DATABASE);
    }

    /*
        Constructor if using TEXT.
     */
    OutputHandler(String FILE_NAME, String FILE_EXTENSION) {
        this.FILE_NAME = FILE_NAME;
        this.FILE_EXTENSION = FILE_EXTENSION;
        this.METHOD = OutputMethod.TEXT;
    }

    /*
        Insert data into Database
     */
    void outputToDatabase(String domain, String link) throws OutputException {
        if(METHOD != OutputMethod.SQL)
            throw new OutputException("Trying to output to SQL when the OutputHandler is not set to SQL.");

        try {
            database.executeQuery("Insert INTO "+SQL_TABLE+"(domain, link) VALUES (\""+domain+"\", \""+link+"\");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        Append data to file.
     */
    void outputToFile(String domain, String link) throws OutputException {
        if(METHOD != OutputMethod.TEXT)
            throw new OutputException("Trying to output to TEXT when the OutputHandler is not set to TEXT.");
        File file = new File(FILE_NAME + "." + FILE_EXTENSION);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(domain + ", " + link+ "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}