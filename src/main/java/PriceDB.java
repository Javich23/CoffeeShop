import java.sql.*;

public class PriceDB {
    public double findPrice(SizeType size, DrinkType type){
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/coffeeshop";
        try {
            Class.forName(className);
        } catch
        (ClassNotFoundException e){
                throw new RuntimeException(e);
            }
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, "javier", "YU_oppdivide!20");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            double price = 0;
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT price FROM coffee_shop WHERE drink_size = ? AND drink_type = ?");
                statement.setString(1, String.valueOf(size));
                statement.setString(2, String.valueOf(type));
                ResultSet results = statement.executeQuery();

                results.next();
                price = results.getDouble("price");
                System.out.printf("The price of a %s %s is $%.2f\n", size, type, price);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return price;
        }
    }
