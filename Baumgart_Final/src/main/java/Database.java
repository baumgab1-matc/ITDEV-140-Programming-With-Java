import java.sql.*;
import java.util.Optional;

public class Database {

    private static Database instance = null;

    private Database() {
    }

    public static Database getInstance() {
       if (instance == null) {
           instance = new Database();
       }

       return instance;
    }

    public boolean createCard(Card card) {
        String insertQuery = "INSERT INTO cards(account_number, pin_hash, balance) VALUES(?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            pstmt.setString(1, card.getNumber());
            pstmt.setString(2, card.getPinHash());
            pstmt.setDouble(3, card.getBalance());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Optional<Card> fetchCardByNumberAndPin(String accountNumber, String pin) {
        String findQuery = String.format("select * from cards where account_number = '%s' && pin_hash = '%s'", accountNumber, pin);
        return getCard(findQuery);
    }

    public Optional<Card> fetchCardByNumber(String accountNumber) {
        String findQuery = String.format("select * from cards where account_number = '%s'", accountNumber);
        return getCard(findQuery);
    }

    private Optional<Card> getCard(String findQuery) {
        Optional<Card> card = Optional.empty();

        try (Connection conn = connect();
             PreparedStatement pstmt  = conn.prepareStatement(findQuery)){
                ResultSet rs  = pstmt.executeQuery();

            if (rs.next()) {
                String number = rs.getString("account_number");
                String hashPin = rs.getString("pin_hash");
                double balance = rs.getDouble("balance");
                int invalidAttempts = rs.getInt("invalid_attempts");
                boolean is_locked = rs.getInt("is_locked") == 1;

                card = Optional.of(new Card(number, hashPin, balance, invalidAttempts, is_locked));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return card;
    }


    public boolean updateCard(Card card) {
        //translate javas boolean to sql bit
        int isLocked = card.isLocked() ? 1 : 0;

        String updateQuery = String.format("UPDATE cards " +
                            "SET balance = '%s', invalid_attempts = '%d', is_locked = '%d' " +
                            "WHERE account_number = '%s'",
                            card.getBalance(), card.getInvalidLoginAttempts(), isLocked, card.getNumber());

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteCard(Card card) {
        String removeQuery = String.format("DELETE FROM cards WHERE account_number = '%s'", card.getNumber());

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(removeQuery)) {
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Connection connect() {
        String url = "jdbc:mysql://localhost:3306/bank_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, "root", "password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
