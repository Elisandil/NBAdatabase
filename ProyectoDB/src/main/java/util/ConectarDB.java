package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarDB {
    private final String route = "jdbc:mysql://localhost:3306/";
    private String dbName;
    private String username;
    private String password;
    private Connection connection;

    public ConectarDB(String archivo) throws SQLException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();

            String[] data = linea.split(";");
            if (data.length < 3) {
                throw new SQLException("El archivo de configuración está incompleto.");
            }

            dbName = data[0];
            username = data[1];
            password = data[2];

            connection = DriverManager.getConnection(
                    route + dbName,
                    username,
                    password);

            System.out.println("Conexión exitosa a la base de datos: " + dbName);
        } catch (IOException e) {
            throw new SQLException("Error al leer el archivo de configuración: " + e.getMessage(), e);
        } catch (SQLException e) {
            throw new SQLException("Error al conectar a la base de datos: " + e.getMessage(), e);
        }
    }
    
    public ConectarDB() throws SQLException {
        dbName = "nbadb";
        username = "root";
        password = "";
        
        try {
            connection = DriverManager.getConnection(
                    route + dbName, 
                    username, 
                    password);
            
            System.out.println("Conexión establecida");
        } catch (SQLException e) {
            throw new SQLException("Error a la hora de conectar a la BBDD: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
