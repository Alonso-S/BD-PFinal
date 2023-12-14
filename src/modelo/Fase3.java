package modelo;

public class Fase3 {
    private int idFase3;
    private String equipo1;
    private String equipo2;
    private int resEquipo1;
    private int resEquipo2;
    private int etapa;
    private String bracket;


    public int getIdFase3() {
		return idFase3;
	}


	public void setIdFase3(int idFase3) {
		this.idFase3 = idFase3;
	}


	public String getEquipo1() {
		return equipo1;
	}


	public void setEquipo1(String equipo1) {
		this.equipo1 = equipo1;
	}


	public String getEquipo2() {
		return equipo2;
	}


	public void setEquipo2(String equipo2) {
		this.equipo2 = equipo2;
	}


	public int getResEquipo1() {
		return resEquipo1;
	}


	public void setResEquipo1(int resEquipo1) {
		this.resEquipo1 = resEquipo1;
	}


	public int getResEquipo2() {
		return resEquipo2;
	}


	public void setResEquipo2(int resEquipo2) {
		this.resEquipo2 = resEquipo2;
	}


	public int getEtapa() {
		return etapa;
	}


	public void setEtapa(int etapa) {
		this.etapa = etapa;
	}


	public String getBracket() {
		return bracket;
	}


	public void setBracket(String bracket) {
		this.bracket = bracket;
	}

	public Fase3() {
		
	}
	public Fase3(int idFase3, String equipo1, String equipo2, int resEquipo1, int resEquipo2, int etapa, String bracket) {
        this.idFase3 = idFase3;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.resEquipo1 = resEquipo1;
        this.resEquipo2 = resEquipo2;
        this.etapa = etapa;
        this.bracket = bracket;
    }
	
    public String toString() {
        return "Fase3{" +
                "idFase3=" + idFase3 +
                ", equipo1='" + equipo1 + '\'' +
                ", equipo2='" + equipo2 + '\'' +
                ", resEquipo1=" + resEquipo1 +
                ", resEquipo2=" + resEquipo2 +
                ", etapa=" + etapa +
                ", bracket='" + bracket + '\'' +
                '}';
    }
}
