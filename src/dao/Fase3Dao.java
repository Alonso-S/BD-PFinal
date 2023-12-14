package dao;

import conexion.ConexionBD;
import modelo.Fase3;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Fase3Dao {
    
    ConexionBD cn=new ConexionBD();


    public List<Fase3> obtenerTodos() {
        List<Fase3> listaFase3 = new ArrayList<>();
        String query = "SELECT * FROM fase3";
        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Fase3 fase3 = new Fase3(
                        resultSet.getInt("idFase3"),
                        resultSet.getString("equipo1"),
                        resultSet.getString("equipo2"),
                        resultSet.getInt("res_equi1"),
                        resultSet.getInt("res_equi2"),
                        resultSet.getInt("etapa"),
                        resultSet.getString("bracket")
                );
                listaFase3.add(fase3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaFase3;
    }

    public boolean insertarFase3(Fase3 fase3) {
        String query = "INSERT INTO fase3 (equipo1, equipo2, res_equi1, res_equi2, etapa, bracket) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setString(1, fase3.getEquipo1());
            preparedStatement.setString(2, fase3.getEquipo2());
            preparedStatement.setInt(3, fase3.getResEquipo1());
            preparedStatement.setInt(4, fase3.getResEquipo2());
            preparedStatement.setInt(5, fase3.getEtapa());
            preparedStatement.setString(6, fase3.getBracket());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean actualizarFase3(Fase3 fase3) {
        String query = "UPDATE fase3 SET equipo1=?, equipo2=?, res_equi1=?, res_equi2=?, etapa=?, bracket=? WHERE idFase3=?";
        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setString(1, fase3.getEquipo1());
            preparedStatement.setString(2, fase3.getEquipo2());
            preparedStatement.setInt(3, fase3.getResEquipo1());
            preparedStatement.setInt(4, fase3.getResEquipo2());
            preparedStatement.setInt(5, fase3.getEtapa());
            preparedStatement.setString(6, fase3.getBracket());
            preparedStatement.setInt(7, fase3.getIdFase3());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean eliminarFase3PorId(int idFase3) {
        String query = "DELETE FROM fase3 WHERE idFase3=?";
        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, idFase3);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Fase3 buscarFase3PorId(int idFase3) {
        String query = "SELECT * FROM fase3 WHERE idFase3=?";
        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, idFase3);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Fase3(
                            resultSet.getInt("idFase3"),
                            resultSet.getString("equipo1"),
                            resultSet.getString("equipo2"),
                            resultSet.getInt("res_equi1"),
                            resultSet.getInt("res_equi2"),
                            resultSet.getInt("etapa"),
                            resultSet.getString("bracket")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
