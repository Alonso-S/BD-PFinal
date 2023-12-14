package dao;

import modelo.Equipo;
import conexion.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipoDao {
    ConexionBD cn=new ConexionBD();


    public List<Equipo> obtenerTodosEquipos() {
        List<Equipo> equipos = new ArrayList<>();

        String query = "SELECT * FROM equipos";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Equipo equipo = new Equipo();
                equipo.setCodigo(resultSet.getInt("Codigo"));
                equipo.setNombreEquipo(resultSet.getString("NomEqui"));
                equipo.setRegion(resultSet.getString("Region"));
                equipo.setLocacion(resultSet.getString("Locacion"));

                equipos.add(equipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipos;
    }
    
    


    public boolean insertarEquipo(Equipo equipo) {
        String query = "INSERT INTO equipos (NomEqui, Region, Locacion) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setString(1, equipo.getNombreEquipo());
            preparedStatement.setString(2, equipo.getRegion());
            preparedStatement.setString(3, equipo.getLocacion());

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarEquipo(Equipo equipo) {
        String query = "UPDATE equipos SET NomEqui=?, Region=?, Locacion=? WHERE Codigo=?";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setString(1, equipo.getNombreEquipo());
            preparedStatement.setString(2, equipo.getRegion());
            preparedStatement.setString(3, equipo.getLocacion());
            preparedStatement.setInt(4, equipo.getCodigo());

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarEquipo(int codigo) {
        String query = "DELETE FROM equipos WHERE Codigo=?";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, codigo);

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Equipo buscarEquipoPorCodigo(int codigo) {
        String query = "SELECT * FROM equipos WHERE Codigo=?";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, codigo);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapearEquipoDesdeResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Equipo mapearEquipoDesdeResultSet(ResultSet resultSet) throws SQLException {
        Equipo equipo = new Equipo();
        equipo.setCodigo(resultSet.getInt("Codigo"));
        equipo.setNombreEquipo(resultSet.getString("NomEqui"));
        equipo.setRegion(resultSet.getString("Region"));
        equipo.setLocacion(resultSet.getString("Locacion"));

        return equipo;
    }
    
    
    
    
    
    
    
    

}
