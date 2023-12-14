package dao;

import java.sql.*;
import conexion.ConexionBD;
import modelo.Usuario;

public class UsuarioDao {
    
	ConexionBD cn= new ConexionBD();
	
    public boolean registrarUsuario(Usuario usuario) {
        String query = "INSERT INTO usuarios (nombres, apellido_materno, apellido_paterno, dni, email, contrasena, rango) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = cn.obtenerConexion().prepareStatement(query)) {
            ps.setString(1, usuario.getNombres());
            ps.setString(2, usuario.getApellidoMaterno());
            ps.setString(3, usuario.getApellidoPaterno());
            ps.setString(4, usuario.getDni());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getContrasena());
            ps.setString(7, usuario.getRango());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean verificarCredenciales(String email, String contrasena) {
        String query = "SELECT * FROM usuarios WHERE email = ? AND contrasena = ?";
        
        try (PreparedStatement ps = cn.obtenerConexion().prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, contrasena);

            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    
    
    public Usuario obtenerUsuarioPorEmail(String email) {
        String query = "SELECT * FROM usuarios WHERE email = ?";
        Usuario usuario = null;

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                usuario = new Usuario(
                        resultSet.getString("nombres"),
                        resultSet.getString("apellido_materno"),
                        resultSet.getString("apellido_paterno"),
                        resultSet.getString("dni"),
                        resultSet.getString("email"),
                        resultSet.getString("contrasena"),
                        resultSet.getString("rango")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
    
}
