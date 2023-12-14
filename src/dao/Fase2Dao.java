package dao;

import modelo.Fase2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionBD;

public class Fase2Dao {
	ConexionBD cn=new ConexionBD();


    
    public List<Fase2> obtenerTodosResultadosFase2() {
        List<Fase2> resultadosFase2 = new ArrayList<>();

        String query = "SELECT * FROM fase2";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Fase2 resultadoFase2 = mapearResultadoFase2DesdeResultSet(resultSet);
                resultadosFase2.add(resultadoFase2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultadosFase2;
    }

   
    public boolean insertarResultadoFase2(Fase2 resultadoFase2) {
        String query = "INSERT INTO fase2 (equipo1, equipo2, res_equi1, res_equi2) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setString(1, resultadoFase2.getEquipo1());
            preparedStatement.setString(2, resultadoFase2.getEquipo2());
            preparedStatement.setInt(3, resultadoFase2.getResultadoEquipo1());
            preparedStatement.setInt(4, resultadoFase2.getResultadoEquipo2());

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

  
    public boolean actualizarResultadoFase2(Fase2 resultadoFase2) {
        String query = "UPDATE fase2 SET equipo1=?, equipo2=?, res_equi1=?, res_equi2=? WHERE codigo=?";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setString(1, resultadoFase2.getEquipo1());
            preparedStatement.setString(2, resultadoFase2.getEquipo2());
            preparedStatement.setInt(3, resultadoFase2.getResultadoEquipo1());
            preparedStatement.setInt(4, resultadoFase2.getResultadoEquipo2());
            preparedStatement.setInt(5, resultadoFase2.getCodigo());

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

   
    public boolean eliminarResultadoFase2(int codigo) {
        String query = "DELETE FROM fase2 WHERE codigo=?";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, codigo);

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Fase2 buscarResultadoFase2PorCodigo(int codigo) {
        String query = "SELECT * FROM fase2 WHERE codigo=?";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, codigo);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapearResultadoFase2DesdeResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Fase2 mapearResultadoFase2DesdeResultSet(ResultSet resultSet) throws SQLException {
        Fase2 resultadoFase2 = new Fase2();
        resultadoFase2.setCodigo(resultSet.getInt("codigo"));
        resultadoFase2.setEquipo1(resultSet.getString("equipo1"));
        resultadoFase2.setEquipo2(resultSet.getString("equipo2"));
        resultadoFase2.setResultadoEquipo1(resultSet.getInt("res_equi1"));
        resultadoFase2.setResultadoEquipo2(resultSet.getInt("res_equi2"));

        return resultadoFase2;
    }
    
}
