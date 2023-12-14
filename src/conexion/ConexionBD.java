package conexion;
import java.sql.*;

public class ConexionBD {
	public String driver="com.mysql.cj.jdbc.Driver";
	public String cadena="jdbc:mysql://localhost/theinternational2023";
	public String usuario="root";
	public String clave="";
	public Connection conn;
	public Connection obtenerConexion() {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(cadena,usuario,clave);
			System.out.println("Conexión establecida");
		}catch(ClassNotFoundException e1) {
			System.out.println("Error en el driver "+e1);
		}catch(SQLException e2) {
			System.out.println("Error en la conexión "+e2);
		}
		return conn;
	}

}